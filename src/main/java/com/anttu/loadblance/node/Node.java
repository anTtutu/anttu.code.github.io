package com.anttu.loadblance.node;

import lombok.Data;

/**
 * 描述
 *
 * @ClassName：Node
 * @Description：节点信息
 * @author：Anttu
 * @Date：18/8/2022 11:15
 */
@Data
public class Node {
    /**
     * 服务名
     */
    private final String serverName;
    /**
     * ip地址
     */
    private final String ipAddress;
    /**
     * 初始权重（保持不变）
     */
    private final int weight;
    /**
     * 当前权重
     */
    private int currentWeight;

    public Node(String serverName, String ipAddress, int weight) {
        this.serverName = serverName;
        this.ipAddress = ipAddress;
        this.weight = weight;
        this.currentWeight = weight;
    }
}
