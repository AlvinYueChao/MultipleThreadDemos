package org.example.alvin.threadPoolExecutor;

import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Test17 {
    public static void main(String[] args) {
        /**
         * 定时任务，每周四18:00:00
         * initialDelay 当前时间和周四晚6时的时间差
         * period 一周的时间间隔
         */
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        // 获取周四时间
        LocalDateTime targetTime = now.withHour(18).withMinute(0).withSecond(0).withNano(0).with(DayOfWeek.THURSDAY);
        if (now.isAfter(targetTime)) {
            targetTime = targetTime.plusWeeks(1);
        }
        long initialDelay = Duration.between(now, targetTime).toMillis();
        long period = 1000 * 60 * 60 * 24 * 7;
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
        pool.scheduleAtFixedRate(() -> {
            log.debug("running...");
        }, initialDelay, period, TimeUnit.MILLISECONDS);
    }
}
