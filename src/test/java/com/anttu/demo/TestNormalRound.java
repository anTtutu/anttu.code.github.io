package com.anttu.demo;

import com.anttu.loadblance.NormalRound;
import com.anttu.loadblance.node.Node;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述
 *
 * @ClassName：TestNormalRound
 * @Description：普通轮询算法测试
 * @author：Anttu
 * @Date：18/8/2022 15:29
 */
public class TestNormalRound {

    @Test
    public void testNormalRound() {
        /**
         * 假设有三个服务器权重配置如下：
         * server A  weight = 0;
         * server B  weight = 0;
         * server C  weight = 0;
         * server D  weight = 0;
         * server E  weight = 0;
         * server F  weight = 0;
         * server G  weight = 0;
         * server H  weight = 0;
         * server I  weight = 0;
         * server J  weight = 0;
         */
        List<Node> nodeList = new ArrayList<>();
        char da = 'A';
        for (int i = 0; i < 26; i++) {
            //char 直接参与运算
            Node server = new Node("server" + (da++), "192.168.1." + String.valueOf(100 + i),0);
            nodeList.add(server);
        }

        NormalRound normalRound = new NormalRound(nodeList);

        Map<String, Integer> resMap = new HashMap<>();
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = normalRound.select();

            if (resMap.containsKey(node.getIpAddress())) {
                resMap.put(node.getIpAddress(), resMap.get(node.getIpAddress()) + 1);
            } else {
                resMap.put(node.getIpAddress(), 1);
            }
        }

        resMap.forEach((k, v) -> {
            System.out.println(String.format("IPAddress: [%s] - Sum: [%d] ==> Percentage: (%d%%)", k, v, v));
        });

    }
}
