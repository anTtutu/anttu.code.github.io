package com.anttu.demo;

import com.anttu.loadblance.HashVirtualNodeCircle;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.*;

/**
 * 描述
 *
 * @ClassName：TestHashCricle
 * @Description：测试Hash环算法
 * @author：Anttu
 * @Date：18/8/2022 14:10
 */
public class TestHashCircle {

    @Test
    public void testHashCircle () {
        HashVirtualNodeCircle hashVirtualNodeCircle = new HashVirtualNodeCircle();
        hashVirtualNodeCircle.refreshVirtualHashCircle(() -> {
            LinkedList<String> nodes = new LinkedList<>();
            nodes.add("192.168.11.23:8090");
            nodes.add("192.168.11.23:8093");
            nodes.add("192.168.11.23:8094");
            return nodes;
        });

        // 生成随机数进行测试
        Map<String, Integer> resMap = new HashMap<>();

        List<String> plcList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            String plcHost = "192.168.0." + i + 1;
            for (int j = 0; j < 10; j++) {
                plcList.add(plcHost + ":" + j + 100);
            }
        }

        for (int i = 0; i < plcList.size(); i++) {
            String plcWidget = plcList.get(i);
            String group = hashVirtualNodeCircle.getServer(plcWidget);
            if (resMap.containsKey(group)) {
                resMap.put(group, resMap.get(group) + 1);
            } else {
                resMap.put(group, 1);
            }
        }

        resMap.forEach((k, v) -> {
            System.out.println(String.format("group: [%s] - sum: [%d] ==> Percentage: (%s%%)", k, v, new DecimalFormat("0.00#").format(v / 100.0d)));
        });

        System.out.println("=========================================");
    }
}
