package org.example.alvin.threadMethodTests;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

@Slf4j
public class Test7 {
    public static void main(String[] args) {
        try {
            test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void test() throws InterruptedException {
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {
                log.debug("park...");
                log.debug("isInterrupted value: {}", Thread.currentThread().isInterrupted());
                LockSupport.park();
                log.debug("unpark...");
                log.debug("isInterrupted value: {}", Thread.interrupted());
                log.debug("isInterrupted value: {}", Thread.currentThread().isInterrupted());
//                LockSupport.park();
            }
        };

        t1.start();

        Thread.sleep(2000);
        t1.interrupt();
    }
}
