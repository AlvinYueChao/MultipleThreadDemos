package org.example.alvin.protectivePause;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class People extends Thread {
    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.createGuardedObject();
        log.debug("收信 id: {}", guardedObject.getId());
        Object mail = guardedObject.getResponse(5000);
        log.debug("收到信 id: {}, 内容: {}", guardedObject.getId(), mail);
    }
}
