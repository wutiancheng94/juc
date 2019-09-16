package com.wtc.juc.collections.demo01.queue.delayqueue02;

import lombok.Data;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/16
 * @Description: com.wtc.juc.collections.demo01.queue.delayqueue02
 * @version: 1.0
 */
@Data
public class MyCache implements Delayed {

    private Map map = new ConcurrentHashMap();
    private DelayQueue delayQueue = new DelayQueue();

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }

    public void init() {
        new Thread(() -> {
            while (true) {
                try {
                    DelayItem item = (DelayItem) delayQueue.take();
                    map.remove(item.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public MyCache() {
        init();
    }

    public void put(String key, Object value, long time) {
        DelayItem delayItem = new DelayItem(time, key);
        if (delayQueue.contains(key)) {
            delayQueue.remove(key);
            map.remove(key);
        }
        map.put(key, value);
        delayQueue.offer(delayItem);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public void getAll() {
        Iterator iterator = delayQueue.iterator();
        while (iterator.hasNext()) {
            DelayItem delayItem = (DelayItem) iterator.next();
            String key = delayItem.getKey();
            System.out.println(key + " ---" +map.get(key));
        }
    }
}
