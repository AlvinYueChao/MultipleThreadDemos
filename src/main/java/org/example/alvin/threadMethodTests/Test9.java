package org.example.alvin.threadMethodTests;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test9 {
    public static void main(String[] args) {
        // Thread.State.NEW
        Thread t1 = new Thread("t1"){
            @Override
            public void run() {

            }
        };

        // Thread.State.RUNNABLE
        Thread t2 = new Thread("t2"){
            @Override
            public void run() {
                while (true){

                }
            }
        };
        t2.start();

        // Thread.State.TERMINATED
        Thread t3 = new Thread("t3"){
            @Override
            public void run() {

            }
        };
        t3.start();

        // Thread.State.WAITING
        Thread t4 = new Thread("t4"){
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t4.start();

        // Thread.State.TIMED_WAITING
        Thread t5 = new Thread("t5"){
            @Override
            public void run() {
                synchronized (Test9.class){
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t5.start();

        Thread t6 = new Thread("t6"){
            @Override
            public void run() {
                synchronized (Test9.class){
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t6.start();

        log.debug("t1 state: {}", t1.getState());
        log.debug("t2 state: {}", t2.getState());
        log.debug("t3 state: {}", t3.getState());
        log.debug("t4 state: {}", t4.getState());
        log.debug("t5 state: {}", t5.getState());
        log.debug("t6 state: {}", t6.getState());

        /*
        [DEBUG] [main] [2020-02-02 15:55:23] org.example.alvin.threadMethodTests.Test9.main(78) --- t1 state: NEW
        [DEBUG] [main] [2020-02-02 15:55:23] org.example.alvin.threadMethodTests.Test9.main(79) --- t2 state: RUNNABLE
        [DEBUG] [main] [2020-02-02 15:55:23] org.example.alvin.threadMethodTests.Test9.main(80) --- t3 state: TERMINATED
        [DEBUG] [main] [2020-02-02 15:55:23] org.example.alvin.threadMethodTests.Test9.main(81) --- t4 state: WAITING
        [DEBUG] [main] [2020-02-02 15:55:23] org.example.alvin.threadMethodTests.Test9.main(82) --- t5 state: TIMED_WAITING
        [DEBUG] [main] [2020-02-02 15:55:23] org.example.alvin.threadMethodTests.Test9.main(83) --- t6 state: BLOCKED
        */
    }
}
