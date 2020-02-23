package org.example.alvin.lock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadLockTest {
    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        new Thread(() -> {
            synchronized (lockA){
                log.debug("lock A");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockB){
                    log.debug("lock B");
                    log.debug("操作");
                }
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lockB){
                log.debug("lock B");

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (lockA){
                    log.debug("lock A");
                    log.debug("操作");
                }
            }
        }, "t2").start();
    }
}
