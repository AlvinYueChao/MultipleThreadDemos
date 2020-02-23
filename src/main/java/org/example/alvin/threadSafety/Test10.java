package org.example.alvin.threadSafety;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test10 {
    private int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Test10 instance = new Test10();
        Thread t1 = new Thread(instance::method1, "t1");
        Thread t2 = new Thread(instance::method2, "t2");

        t1.start();
        t2.start();
    }

    private void method1(){
        for (int i = 0; i < 100; i++){
            synchronized (Test10.class){
                count ++;
                log.info("count value: {}", this.count);
            }
        }
    }

    private void method2() {
        for (int i = 0; i < 100; i++){
            synchronized (this){
                count --;
                log.info("count value: {}", this.count);
            }
        }
    }
}
