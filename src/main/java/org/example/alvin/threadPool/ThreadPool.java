package org.example.alvin.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPool {
    // 任务队列
    private BlockingQueue<Runnable> taskQueue;

    // 线程集合
    private final HashSet<Worker> workers = new HashSet<>();

    // 核心线程数
    private int coreSize;

    // 任务超时时间
    private long timeout;

    // 超时时间单位
    private TimeUnit timeUnit;

    private RejectPolicy<Runnable> rejectPolicy;

    public ThreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy<Runnable> rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.rejectPolicy = rejectPolicy;
        taskQueue = new BlockingQueue<>(queueCapacity);
    }

    // 执行任务
    public void execute(Runnable task) {
        synchronized (workers){
            /**
             * 当任务数没有超过 coreSize 时，直接交给 worker 对象执行
             * 当任务数超过 coreSizes 时，加入阻塞队列
             */
            if (workers.size() < coreSize) {
                Worker worker = new Worker(task);
                log.debug("新增 worker {}, {}", worker, task);
                workers.add(worker);
                worker.start();
            } else {
                taskQueue.tryPut(rejectPolicy, task);
            }
        }
    }

    class Worker extends Thread {
        private Runnable task;

        public Worker(Runnable task){
            this.task = task;
        }

        @Override
        public void run() {
            /**
             * 执行任务
             * 1) 当 task 不为空，执行任务
             * 2) 当 task 执行完毕，再接着从任务队列获取任务并执行
             */
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    log.debug("正在执行... {}", task);
                    task.run();
                } catch (Exception e) {

                } finally {
                    task = null;
                }
            }

            synchronized (workers) {
                log.debug("worker {} 被移除", this);
                workers.remove(this);
            }
        }
    }
}
