package org.example.alvin.protectivePause;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test12 {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 200; i++) {
            new People().start();
        }

        Thread.sleep(3000);

        for (Integer id : Mailboxes.getAllIds()){
            new Postman(id, "内容" + id).start();
        }
    }
}
