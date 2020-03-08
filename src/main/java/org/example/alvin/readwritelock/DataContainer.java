package org.example.alvin.readwritelock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class DataContainer {
    private Object data;

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

    public Object read() {
        log.debug("获取读锁...");
        readLock.lock();
        try {
            log.debug("读取数据");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return data;
        } finally {
            log.debug("释放读锁...");
            readLock.unlock();
        }
    }

    public void write(Object input) {
        log.debug("获取写锁...");
        writeLock.lock();
        try {
            log.debug("写入数据");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data = input;
        } finally {
            log.debug("释放写锁...");
            writeLock.unlock();
        }
    }
}
