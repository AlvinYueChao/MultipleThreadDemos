package org.example.alvin.readwritelock;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test19 {
    public static void main(String[] args) {
        DataContainer dataContainer = new DataContainer();

        new Thread(() -> {
            dataContainer.write("1");
        }, "t1").start();

        new Thread(() -> {
            dataContainer.write("2");
        }, "t2").start();
    }
}
