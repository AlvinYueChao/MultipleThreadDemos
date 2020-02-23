package org.example.alvin.threadMethodTests;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test4")
public class Test4 {
    public static void main(String[] args) {
        Thread t1 = new Thread("thread1"){
            @Override
            public void run() {
                log.debug("running...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        log.debug("t1 state: {}", t1.getState());

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.debug("t1 state: {}", t1.getState());
    }
}
