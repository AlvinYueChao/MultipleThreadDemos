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
        private Thread minitor;

        public void start(){
            minitor = new Thread("monitor thread"){
                @Override
                public void run() {
                    while (true){
                        Thread current = Thread.currentThread();
                        if (current.isInterrupted()){
                            log.debug("被打断了，清理后续...");
                            break;
                        }
                        try {
                            Thread.sleep(1000);
                            log.debug("执行监控步骤...");
                        } catch (InterruptedException e) {
                            current.interrupt();
                            e.printStackTrace();
                        }
                    }
                }
            };
            minitor.start();
        }

        public void stop(){
            minitor.interrupt();
        }
    }
}
