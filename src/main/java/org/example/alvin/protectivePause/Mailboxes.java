package org.example.alvin.protectivePause;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Mailboxes {
    // 使用线程安全的HashTable
    private static Map<Integer, GuardedObject> boxes = new ConcurrentHashMap<>();

    private static int id = 1;

    private static synchronized int generateId(){
        // 使用synchronized保证线程安全性
        return id++;
    }

    public static GuardedObject createGuardedObject(){
        GuardedObject guardedObject = new GuardedObject(generateId());
        boxes.put(guardedObject.getId(), guardedObject);
        return guardedObject;
    }

    public static GuardedObject getGuardedObject(int id){
        // 居民取到信之后，不再需要对应信箱
        return boxes.remove(id);
    }

    public static Set<Integer> getAllIds(){
        return boxes.keySet();
    }
}
