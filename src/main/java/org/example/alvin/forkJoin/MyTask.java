package org.example.alvin.forkJoin;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

@Slf4j
public class MyTask extends RecursiveTask<Integer> {
    private int number;

    public MyTask(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "{" + number + "}" ;
    }

    @Override
    protected Integer compute() {
        // 终止条件
        if (number == 1) {
            log.debug("join() {}", number);
            return number;
        }
        MyTask nextTask = new MyTask(number - 1);

        // 让另一个线程去执行下个任务
        nextTask.fork();
        log.debug("fork() {} + {}", number, nextTask);

        return number + nextTask.join();
    }
}
