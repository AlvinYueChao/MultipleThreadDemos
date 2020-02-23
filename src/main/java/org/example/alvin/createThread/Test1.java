package org.example.alvin.createThread;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test1")
public class Test1 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> log.debug("running"));

        thread.setName("Thread1");
        thread.start();
        log.debug("running");
    }
}
