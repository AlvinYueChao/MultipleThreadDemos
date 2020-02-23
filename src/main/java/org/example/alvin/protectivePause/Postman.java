package org.example.alvin.protectivePause;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Postman extends Thread {
    private int mailId;
    private String mailContext;

    public Postman(int mailId, String mailContext) {
        this.mailId = mailId;
        this.mailContext = mailContext;
    }

    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.getGuardedObject(mailId);
        log.debug("送信 id: {}, 内容: {}", mailId, mailContext);
        guardedObject.complete(mailContext);
    }
}
