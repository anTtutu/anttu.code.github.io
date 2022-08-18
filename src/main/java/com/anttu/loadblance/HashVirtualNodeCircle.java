package com.anttu.loadblance;

import com.anttu.loadblance.node.HashCircleInstanceNodeBuild;
import com.anttu.loadblance.util.HashUtil;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 描述
 *
 * @ClassName：HashVirtualNodeCircle
 * @Description：Hash环算法-补齐虚拟节点让分布更均衡
 * @author：Anttu
 * @Date：18/8/2022 13:51
 */
public class HashVirtualNodeCircle {
    /** 真实集群列表 */
    private static List<String> instanceNodes;

    /** 虚拟节点映射关系 */
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();

    /** 虚拟节点数 */
    private static final int VIRTUAL_NODE_NUM = 1000;

    /** 刷新 服务实例 */
    public void refreshVirtualHashCircle(HashCircleInstanceNodeBuild build) {
        // 当集群变动时，刷新hash环，其余的集群在hash环上的位置不会发生变动
        virtualNodes.clear();
        // 获取 最新节点
        instanceNodes = build.instanceNodes();
        // 将虚拟节点映射到Hash环上
        for (String realInstance : instanceNodes) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                String virtualNodeName = getVirtualNodeName(realInstance, i);
                int hash = HashUtil.getFNV1Hash(virtualNodeName);
                System.out.println("[" + virtualNodeName + "] launched @ " + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
    }

    private static String getVirtualNodeName(String realName, int num) {
        return realName + "&&VN" + num;
    }

    private static String getRealNodeName(String virtualName) {
        return virtualName.split("&&")[0];
    }

    public static String getServer(String widgetKey) {
        int hash = HashUtil.getFNV1Hash(widgetKey);
        // 只取出所有大于该hash值的部分而不必遍历整个Tree
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNodeName;
        if (subMap.isEmpty()) {
            // hash值在最尾部，应该映射到第一个group上
            virtualNodeName = virtualNodes.get(virtualNodes.firstKey());
        } else {
            virtualNodeName = subMap.get(subMap.firstKey());
        }
        return getRealNodeName(virtualNodeName);
    }
}
