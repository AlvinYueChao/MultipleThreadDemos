package org.example.alvin.threadMethodTests;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class Exam1 {
    public static void main(String[] args) throws InterruptedException {
        // 方法甲
        long startTime1 = new Date().getTime();
        Thread t1 = new Thread(() -> {
            try {
                washKettle("甲");
                pourWater("甲");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");
        t1.start();

        Thread t9 = new Thread(() -> {
            try {
                t1.join();
                heatUpWater("甲");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t9");

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
                washTeaSet("甲");
                catchUpTea("甲");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2");
        t2.start();

        Thread t3 = new Thread(() -> {
            try {
                t9.join();
                t2.join();
                makeTea("甲");

                long endTime1 = new Date().getTime();
                log.debug("方法甲总耗时: {}", endTime1 - startTime1);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t3");
        t3.start();

        // 方法乙
        long startTime2 = new Date().getTime();
        Thread t4 = new Thread(() -> {
            try {
                washKettle("乙");
                washTeaSet("乙");
                catchUpTea("乙");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t4");
        t4.start();

        Thread t5 = new Thread(() -> {
            try {
                t4.join();
                pourWater("乙");
                heatUpWater("乙");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t5");
        t5.start();

        Thread t6 = new Thread(() -> {
            try {
                t5.join();
                makeTea("乙");

                long endTime2 = new Date().getTime();
                log.debug("方法乙总耗时: {}", endTime2 - startTime2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t6");
        t6.start();

        // 方法丙
        long startTime3 = new Date().getTime();
        Thread t7 = new Thread(() -> {
            try {
                washKettle("丙");
                pourWater("丙");
                heatUpWater("丙");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t7");
        t7.start();

        Thread t8 = new Thread(() -> {
            try {
                t7.join();
                catchUpTea("丙");
                washTeaSet("丙");
                makeTea("丙");

                long endTime3 = new Date().getTime();
                log.debug("方法丙总耗时: {}", endTime3 - startTime3);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t8");
        t8.start();

        /*
        [DEBUG] [t1] [2020-02-02 17:25:04] org.example.alvin.threadMethodTests.exam1.washKettle(126) --- 甲 洗水壶...
        [DEBUG] [t4] [2020-02-02 17:25:04] org.example.alvin.threadMethodTests.exam1.washKettle(126) --- 乙 洗水壶...
        [DEBUG] [t7] [2020-02-02 17:25:04] org.example.alvin.threadMethodTests.exam1.washKettle(126) --- 丙 洗水壶...
        [DEBUG] [t1] [2020-02-02 17:25:05] org.example.alvin.threadMethodTests.exam1.pourWater(131) --- 甲 往水壶中灌凉水...
        [DEBUG] [t7] [2020-02-02 17:25:05] org.example.alvin.threadMethodTests.exam1.pourWater(131) --- 丙 往水壶中灌凉水...
        [DEBUG] [t4] [2020-02-02 17:25:05] org.example.alvin.threadMethodTests.exam1.washTeaSet(141) --- 乙 洗茶壶，茶杯...
        [DEBUG] [t7] [2020-02-02 17:25:06] org.example.alvin.threadMethodTests.exam1.heatUpWater(136) --- 丙 烧水...
        [DEBUG] [t2] [2020-02-02 17:25:06] org.example.alvin.threadMethodTests.exam1.washTeaSet(141) --- 甲 洗茶壶，茶杯...
        [DEBUG] [t4] [2020-02-02 17:25:07] org.example.alvin.threadMethodTests.exam1.catchUpTea(151) --- 乙 拿茶叶...
        [DEBUG] [t2] [2020-02-02 17:25:08] org.example.alvin.threadMethodTests.exam1.catchUpTea(151) --- 甲 拿茶叶...
        [DEBUG] [t5] [2020-02-02 17:25:08] org.example.alvin.threadMethodTests.exam1.pourWater(131) --- 乙 往水壶中灌凉水...
        [DEBUG] [t5] [2020-02-02 17:25:09] org.example.alvin.threadMethodTests.exam1.heatUpWater(136) --- 乙 烧水...
        [DEBUG] [t8] [2020-02-02 17:25:09] org.example.alvin.threadMethodTests.exam1.catchUpTea(151) --- 丙 拿茶叶...
        [DEBUG] [t3] [2020-02-02 17:25:09] org.example.alvin.threadMethodTests.exam1.makeTea(146) --- 甲 泡茶...
        [DEBUG] [t8] [2020-02-02 17:25:10] org.example.alvin.threadMethodTests.exam1.washTeaSet(141) --- 丙 洗茶壶，茶杯...
        [DEBUG] [t3] [2020-02-02 17:25:10] org.example.alvin.threadMethodTests.exam1.lambda$main$3(49) --- 方法甲总耗时: 6051
        [DEBUG] [t8] [2020-02-02 17:25:12] org.example.alvin.threadMethodTests.exam1.makeTea(146) --- 丙 泡茶...
        [DEBUG] [t6] [2020-02-02 17:25:12] org.example.alvin.threadMethodTests.exam1.makeTea(146) --- 乙 泡茶...
        [DEBUG] [t6] [2020-02-02 17:25:13] org.example.alvin.threadMethodTests.exam1.lambda$main$6(87) --- 方法乙总耗时: 9006
        [DEBUG] [t8] [2020-02-02 17:25:13] org.example.alvin.threadMethodTests.exam1.lambda$main$8(116) --- 方法丙总耗时: 9004
        */
    }

    private static void washKettle(String name) throws InterruptedException {
        log.debug("{} 洗水壶...", name);
        Thread.sleep(1000);
    }

    private static void pourWater(String name) throws InterruptedException {
        log.debug("{} 往水壶中灌凉水...", name);
        Thread.sleep(500);
    }

    private static void heatUpWater(String name) throws InterruptedException {
        log.debug("{} 烧水...", name);
        Thread.sleep(3000);
    }

    private static void washTeaSet(String name) throws InterruptedException {
        log.debug("{} 洗茶壶，茶杯...", name);
        Thread.sleep(2000);
    }

    private static void makeTea(String name) throws InterruptedException {
        log.debug("{} 泡茶...", name);
        Thread.sleep(1500);
    }

    private static void catchUpTea(String name) throws InterruptedException {
        log.debug("{} 拿茶叶...", name);
        Thread.sleep(1000);
    }
}
