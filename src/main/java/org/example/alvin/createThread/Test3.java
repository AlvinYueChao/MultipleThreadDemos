package org.example.alvin.createThread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j(topic = "c.Test3")
public class Test3 {
    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            log.debug("running...");
            Thread.sleep(1000);
            return 100;
        });

        Thread thread = new Thread(task, "thread1");
        thread.start();
        try {
            task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        log.debug("running");
    }
}
