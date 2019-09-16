package com.wtc.juc.collections.demo01.queue;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/15
 * @Description: com.wtc.juc.collections.demo01.queue
 * @version: 1.0
 */
public class MyBlockingQueue {

    private volatile Object[] objects;

    private AtomicInteger size = new AtomicInteger();

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public MyBlockingQueue() {
        this.objects = new Object[]{};
    }

    public MyBlockingQueue(int length) {
        if (length < 0) {
            throw new IllegalArgumentException();
        }
        this.objects = new Object[length];
    }

    public void put(Object object) {
        lock.lock();
        try {
            while (size.get() == objects.length) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            objects[size.getAndIncrement()] = object;
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    public Object take() {
        lock.lock();
        try {
            while (size.get() == 0) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Object object = objects[size.decrementAndGet()];
            condition.signal();
            return object;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyBlockingQueue queue = new MyBlockingQueue(3);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                queue.put(finalI);
                System.out.println("生产者：" + Thread.currentThread().getName() + "\tput:" + finalI);
            } , String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("消费者：" + Thread.currentThread().getName() + "\ttake:" + queue.take());
            } , String.valueOf(i)).start();
        }
    }
}
