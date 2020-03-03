package org.example.alvin.threadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
public class Test16 {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Future<Integer> result1 = pool.submit(() -> {
            log.debug("task 1 running...");
            Thread.sleep(1000);
            log.debug("task 1 finish...");
            return 1;
        });

        Future<Integer> result2 = pool.submit(() -> {
            log.debug("task 2 running...");
            Thread.sleep(1000);
            log.debug("task 2 finish...");
            return 2;
        });

        Future<Integer> result3 = pool.submit(() -> {
            log.debug("task 3 running...");
            Thread.sleep(1000);
            log.debug("task 3 finish...");
            return 3;
        });

        log.debug("shutdown");
        // pool.shutdown();
        // pool.awaitTermination(1, TimeUnit.SECONDS);
        List<Runnable> runnables = pool.shutdownNow();
        log.debug("other...{}", runnables);

        /*Future<Integer> result4 = pool.submit(() -> {
            log.debug("task 4 running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("task 4 finish...");
            return 4;
        });*/
    }

    private static void testInvokeAnyWithTimeout(ExecutorService pool) throws InterruptedException, ExecutionException, TimeoutException {
        // 返回一个最先得到的结果，其他任务取消
        String result = pool.invokeAny(Arrays.asList(
                () -> {
                    log.debug("begin 1");
                    Thread.sleep(1000);
                    log.debug("end 1");
                    return "1";
                },
                () -> {
                    log.debug("begin 2");
                    Thread.sleep(500);
                    log.debug("end 2");
                    return "2";
                },
                () -> {
                    log.debug("begin 3");
                    Thread.sleep(2000);
                    log.debug("end 3");
                    return "3";
                }
        ), 3000, TimeUnit.MILLISECONDS);

        log.debug("{}", result);
    }

    private static void testSubmit(ExecutorService pool) throws InterruptedException, ExecutionException {
        Future<String> task = pool.submit(() -> {
            log.debug("begin");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "ok";
        });

        log.debug("{}", task.get());
    }

    private static void testInvokeAllWithTimeout(ExecutorService pool) throws InterruptedException {
        List<Future<String>> futures = pool.invokeAll(Arrays.asList(
                () -> {
                    log.debug("begin");
                    Thread.sleep(1000);
                    log.debug("end");
                    return "1";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(500);
                    log.debug("end");
                    return "2";
                },
                () -> {
                    log.debug("begin");
                    Thread.sleep(2000);
                    log.debug("end");
                    return "3";
                }
        ), 2, TimeUnit.SECONDS);

        futures.forEach(future -> {
            try {
                log.debug("{}", future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
