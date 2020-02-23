package org.example.alvin.producerAndComsumer;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * Java 线程间通信消息队列
 */
@Slf4j
public class MessageQueue {
    // 消息的队列集合，使用双向链表
    private final LinkedList<Message> list = new LinkedList<>();
    // 队列容量
    private int capacity;

    public MessageQueue(int capacity) {
        this.capacity = capacity;
    }

    // 获取消息
    public Message take(){
        // 检查队列是否为空
        synchronized (list){
            while (list.isEmpty()){
                try {
                    log.debug("队列为空，消费者线程等待...");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 从队列头部获取消息并返回
            Message message = list.removeFirst();
            log.debug("已消费消息 {}", message);
            // 唤醒等待的生产者线程
            list.notifyAll();
            return message;
        }
    }

    // 存入消息
    public void put(Message message){
        synchronized (list){
            // 检查队列是否已满
            while (list.size() == capacity){
                try {
                    log.debug("队列已满，生产者线程等待...");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 将消息加入消息队列尾部
            list.addLast(message);
            log.debug("已生产消息 {}", message);
            // 唤醒等待消费者线程
            list.notifyAll();
        }
    }
}
