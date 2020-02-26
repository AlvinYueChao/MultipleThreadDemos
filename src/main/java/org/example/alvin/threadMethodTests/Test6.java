package org.example.alvin.threadMethodTests;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test6 {

    public static void main(String[] args) throws InterruptedException {
        TwoPhaseTermination tpt = new TwoPhaseTermination();
        tpt.start();

        Thread.sleep(3500);
        tpt.stop();
    }

    public static class TwoPhaseTermination {
        /**
         * volatile适用于仅有一个线程写，多个线程读的场景
         * volatile关键字只能保证线程之间的可见性，不能保证原子性
         */
        private volatile boolean shouldStop;
        private boolean starting;
        private Thread monitor;

        public void start() {
            synchronized (this) {
                if (starting) {
                    return;
                }
                starting = true;
            }
            monitor = new Thread(() -> {
                while (true) {
                    if (shouldStop) {
                        log.debug("清理后续...");
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                        log.debug("执行监控步骤...");
                    } catch (InterruptedException e) {
                        shouldStop = true;
                    }
                }
            }, "monitor");
            monitor.start();
        }

        public void stop() {
            log.debug("停止监控");
            shouldStop = true;
            monitor.interrupt();
        }
    }
}
