package org.example.alvin.forkJoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;

@Slf4j
public class Test18 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool(4);
        log.debug("result {}", pool.invoke(new MyTask(15)));
    }
}
