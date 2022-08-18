package com.anttu.loadblance;

import com.anttu.loadblance.node.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 记录一下nginx加权分配算法。
 *
 * nginx可以指定轮询几率，weight和访问比率成正比，用于后端服务器性能不均的情况。
 * 例如：
 *
 * upstream backend {
 *   server a weight=6;
 *   server b weight=3;
 *   server c weight=1;
 * }
 * 按照配置，每有10次请求，其中6个会转发到a服务器，3个转发到b服务器，1个转发到c服务器。
 *
 * 每个服务器都有三个权重变量，先解释下它们的含义。
 *
 * (1) weight
 *
 * 配置文件中指定的该服务器的权重，这个值是固定不变的。
 *
 * (2) effective_weight
 *
 * 服务器的有效权重，初始值为weight。
 *
 * 在释放服务器时，如果发现和某服务器的通信过程中发生了错误，就减小它的effective_weight。
 * 此后有新的请求过来时，在选取该服务器的过程中，再逐步增加effective_weight，最终又恢复到weight。
 * 之所以增加这个字段，是为了当服务器发生错误时，降低其权重。
 *
 * (3) current_weight
 *
 * 服务器目前的权重，初始为0，之后会动态调整。
 *
 * 那么如何动态调整呢？
 *
 * nginx每次选取服务器时：
 *
 * 先遍历集群中所有服务器，将每个服务器的current_weight增加它的effective_weight，
 * 再累加所有服务器的effective_weight，保存为total。
 * 判断当前服务器的current_weight是否最大，是则选中该服务器，然后把它的current_weight减去total。不是则不会被选中，current_weight也就不用减了。
 * 弄清了三个weight字段的含义后，加权轮询算法可描述为：
 *
 * 对于每个请求，遍历集群中的所有可用服务器，对于每个服务器执行：current_weight += effecitve_weight。
 * 累加所有effective_weight，保存为total。
 * 选出current_weight最大的服务器，作为本次选定的服务器。
 * 对于本次选定的服务器，执行：current_weight -= total。
 * 下面以表格形式记录其过程：
 *
 * 请求次数	开始current_weight	增加effective_weight	累加total	选中服务器	选中后current_weight
 * 1	    [0, 0, 0]	        [6, 3, 1]	              10	     a	        [-4, 3, 1]
 * 2	    [-4, 3, 1]	        [2, 6, 2]	              10	     b	        [2, -4, 2]
 * 3	    [2, -4, 2]	        [8, -1, 3]	              10	     a	        [-2, -1, 3]
 * 4	    [-2, -1, 3]	        [4, 2, 4]	              10	     a	        [-6, 2, 4]
 * 5	    [-6, 2, 4]	        [0, 5, 5]	              10	     b	        [0, -5, 5]
 * 6	    [0, -5, 5]	        [6, -2, 6]	              10	     a	        [-4, -2, 6]
 * 7	    [-4, -2, 6]	        [2, 1, 7]	              10	     c	        [2, 1, -3]
 * 8	    [2, 1, -3]	        [8, 4, -2]	              10	     a	        [-2, 4, -2]
 * 9	    [-2, 4, -2]	        [4, 7, -1]	              10	     b	        [4, -3, -1]
 * 10	    [4, -3, -1]	        [10, 0, 0]	              10	     a	        [0, 0, 0]
 * 可以看到，选中服务器依次为[a, b, a, a, b, a, c, a, b, a]。
 *
 * a,b,c分别被选中了6,3,1次，正好是符合其权重值的；
 * 服务器a虽然权重大，但没有被连续选取，不会对a服务器连续请求；
 * 经过10次请求后，a,b,c的当前权重current_weight又全部归0，如此便可循环往复。
 *
 * ps: 这里我们发现total永远都是10，因为这里假定服务器都没有发生故障或返回错误，其effective_weight不变。实际中如果服务器发生了错误，nginx当然也会进行降权处理，total也会变啦。这里我们学习一下正常算法，出错的情况就先不展开了。
 *
 * @ClassName：SmoothWeightRound
 * @Description：平滑权重轮询算法示例
 * @author：Anttu
 * @Date：18/8/2022 11:09
 */
public class SmoothWeightRound {
    /**
     * 保存权重
     */
    private volatile List<Node> nodeList = new ArrayList<>();
    /**
     * 锁，保障线程安全
     */
    private ReentrantLock lock = new ReentrantLock();

    public SmoothWeightRound(Node... nodes) {
        for (Node node : nodes) {
            nodeList.add(node);
        }
    }

    public Node select() {
        try {
            lock.lock();
            return this.selectInner();
        } finally {
            lock.unlock();
        }
    }

    private Node selectInner() {
        int totalWeight = 0;
        Node maxNode = null;
        int maxWeight = 0;
        for (int i = 0; i < nodeList.size(); i++) {
            Node n = nodeList.get(i);
            totalWeight += n.getWeight();
            // 每个节点的当前权重要加上原始的权重
            n.setCurrentWeight(n.getCurrentWeight() + n.getWeight());
            // 保存当前权重最大的节点
            if (maxNode == null || maxWeight < n.getCurrentWeight()) {
                maxNode = n;
                maxWeight = n.getCurrentWeight();
            }
        }
        // 被选中的节点权重减掉总权重
        maxNode.setCurrentWeight(maxNode.getCurrentWeight() - totalWeight);
        // nodeList.forEach(node -> System.out.print(node.getCurrentWeight()));
        return maxNode;
    }
}
