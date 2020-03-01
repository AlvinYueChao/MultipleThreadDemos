package org.example.alvin.unSafe;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test14 {
    public static void main(String[] args) throws InterruptedException {
        MyAtomicInteger account = new MyAtomicInteger(10000);
        Thread[] demoThreads = new Thread[1000];

        for (int i = 0; i < demoThreads.length; i++) {
            demoThreads[i] = new Thread(() -> {
                account.withdraw(10);
            });
        }

        long begin = System.currentTimeMillis();
        for (Thread demoThread : demoThreads) {
            demoThread.start();
            demoThread.join();
        }
        long end = System.currentTimeMillis();

        log.debug("账户余额: {}, cost: {} ms", account.getBalance(), end - begin);
    }
}
