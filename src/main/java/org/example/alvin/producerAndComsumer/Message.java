package org.example.alvin.producerAndComsumer;

/**
 * 线程安全的Message类，只能多线程获取现有id，不能更改。且不能被继承
 */
public final class Message {
    private int id;
    private Object value;

    public Message(int id, Object value) {
        this.id = id;
        this.value = value;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return "Message{id = " + id + ", value = " + value + "}";
    }
}
