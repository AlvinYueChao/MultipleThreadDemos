package org.example.alvin.producerAndComsumer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test13 {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(2);

        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                queue.put(new Message(id, "值" + id));
            }, "生产者" + i).start();
        }

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message message = queue.take();
            }
        }, "消费者").start();
    }
}
