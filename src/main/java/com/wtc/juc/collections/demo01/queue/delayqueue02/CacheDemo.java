package com.wtc.juc.collections.demo01.queue.delayqueue02;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/17
 * @Description: com.wtc.juc.collections.demo01.queue.delayqueue02
 * @version: 1.0
 */
public class CacheDemo {

    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();
        myCache.put("1",1, 100);
        myCache.put("2",2, 20000);
        Thread.sleep(3000);
        myCache.getAll();

    }
}
