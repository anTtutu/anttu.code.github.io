package com.anttu.loadblance;

import com.anttu.loadblance.node.Node;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述
 *
 * @ClassName：NormalRound
 * @Description：普通的轮询算法
 * @author：Anttu
 * @Date：18/8/2022 15:05
 */
public class NormalRound {
    /**
     * 服务器清单
     */
    private volatile List<Node> nodeList = new ArrayList<>();
    /**
     * 当前计数
     */
    private volatile int currentIndex;
    /**
     * 最大计数，等于服务器总数
     */
    private volatile int totalServer;

    /**
     * 锁，保障线程安全
     */
    private ReentrantLock lock = new ReentrantLock();

    public NormalRound(List<Node> nodeList) {
        this.nodeList = nodeList;
        this.totalServer = nodeList.size();
        this.currentIndex = totalServer - 1;
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
        currentIndex = (currentIndex + 1) % totalServer;
        return nodeList.get(currentIndex);
    }
}
