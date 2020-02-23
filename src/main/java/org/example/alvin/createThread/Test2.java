package org.example.alvin.createThread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test2")
public class Test2 {
    public static void main(String[] args) {
        Runnable runnable = () -> log.debug("running");

        Thread thread = new Thread(runnable, "thread2");
        thread.start();
        log.debug("running");
    }
}
