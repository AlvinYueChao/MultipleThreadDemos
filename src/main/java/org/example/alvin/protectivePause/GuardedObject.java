package org.example.alvin.protectivePause;

public class GuardedObject {
    private int id;
    private Object response;
    private final Object lock = new Object();

    public GuardedObject(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public Object getResponse(long timeout){
        synchronized (this){
            long start = System.currentTimeMillis();
            long passedTime = 0;
            while (this.response == null){
                long waitTime = timeout - passedTime;
                if (waitTime <= 0){
                    break;
                }

                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                passedTime = System.currentTimeMillis() - start;
            }
        }
        return this.response;
    }

    public void complete(Object response){
        synchronized (this){
            this.response = response;
            this.notifyAll();
        }
    }
}
