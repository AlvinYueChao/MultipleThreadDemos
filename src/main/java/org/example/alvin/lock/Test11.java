package org.example.alvin.lock;

import lombok.extern.slf4j.Slf4j;
import org.openjdk.jol.info.ClassLayout;

@Slf4j
public class Test11 {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj){
                log.info(ClassLayout.parseInstance(obj).toPrintable());
            }
        }, "t1");
        t1.start();

        t1.join();
        log.info(ClassLayout.parseInstance(obj).toPrintable());

        log.info("obj hashCode: {}", obj.hashCode());
        log.info(ClassLayout.parseInstance(obj).toPrintable());

        // hashCode 十进制：1191747167
        // hashCode 二进制：1000111 00001000 10011110 01011111
    }
}
