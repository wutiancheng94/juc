package com.wtc.juc.collections.demo01.list;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟写时复制容器
 * @Auther: 吴天成
 * @Date: 2019/9/15
 * @Description: com.wtc.juc.collections.demo01
 * @version: 1.0
 */
public class MyCopyOnWriteArrayList {

    private volatile Object[] objects;

    private Lock lock = new ReentrantLock();

    public MyCopyOnWriteArrayList() {
        this.objects = new Object[]{};
    }

    public Object get(int index) {
        if (index < 0 || index > objects.length) {
            throw new IllegalArgumentException();
        }

        return objects[index];
    }

    public void add(Object object) {
       lock.lock();
       try {
           int length = objects.length;
           Object[] newObjects = Arrays.copyOf(this.objects, length + 1);
           newObjects[length] = object;
           objects = newObjects;
       } finally {
           lock.unlock();
       }
    }

    public static void main(String[] args) {
        MyCopyOnWriteArrayList demo = new MyCopyOnWriteArrayList();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                demo.add(finalI);
                System.out.println(demo);
            }, String.valueOf(i)).start();
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(objects);
    }
}
