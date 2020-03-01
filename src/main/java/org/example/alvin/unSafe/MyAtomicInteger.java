package org.example.alvin.unSafe;

import sun.misc.Unsafe;

public class MyAtomicInteger implements Account {
    private volatile int value;
    private static final long VALUE_OFFSET;
    private static final Unsafe UNSAFE;

    public MyAtomicInteger(int value) {
        this.value = value;
    }

    /**
     * JVM加载类的时候就会加载执行，而且只执行一次
     */
    static {
        UNSAFE = UnsafeAccessor.getUnsafe();
        try {
            VALUE_OFFSET = UNSAFE.objectFieldOffset(MyAtomicInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public int getValue() {
        return this.value;
    }

    public void decrement(int amount){
        while (true){
            int prev = this.value;
            int next = prev - amount;
            if (UNSAFE.compareAndSwapInt(this, VALUE_OFFSET, prev, next)) {
                break;
            }
        }
    }

    @Override
    public Integer getBalance() {
        return this.getValue();
    }

    @Override
    public void withdraw(Integer amount) {
        this.decrement(amount);
    }
}
