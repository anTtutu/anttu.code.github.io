package com.anttu.demo;

import com.anttu.loadblance.SmoothWeightRound;
import com.anttu.loadblance.node.Node;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述
 *
 * @ClassName：TestSmoothWeightRound
 * @Description：测试平滑权重轮询算法
 * @author：Anttu
 * @Date：18/8/2022 11:21
 */
public class TestSmoothWeightRound {

    @Test
    public void testWeightRound () {
        /**
         * 假设有三个服务器权重配置如下：
         * server A  weight = 5;
         * server B  weight = 3;
         * server C  weight = 1;
         */
        Node serverA = new Node("serverA", "192.168.1.100",5);
        Node serverB = new Node("serverB", "192.168.1.101",3);
        Node serverC = new Node("serverC", "192.168.1.102",1);

        SmoothWeightRound weightRound = new SmoothWeightRound(serverA, serverB, serverC);

        Map<String, Integer> resMap = new HashMap<>();
        for (int i = 0; i < 89; i++) {
            Node node = weightRound.select();
            if (resMap.containsKey(node.getIpAddress())) {
                resMap.put(node.getIpAddress(), resMap.get(node.getIpAddress()) + 1);
            } else {
                resMap.put(node.getIpAddress(), 1);
            }
//            System.out.println(String.format("ServerName:[%s] - IPAddress:[%s]", node.getServerName(), node.getIpAddress()));
        }

        resMap.forEach((k, v) -> {
            System.out.println(String.format("IPAddress: [%s] - Sum: [%d] ==> Percentage: (%d%%)", k, v, v));
        });
    }
}
