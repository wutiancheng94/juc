package com.wtc.juc.review;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/16
 * @Description: com.wtc.juc.review
 * @version: 1.0
 */
public class Demo01 {

    public static void main(String[] args) {
        Resource resource = new Resource();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> resource.add(finalI), "A").start();
            new Thread(() -> resource.print(), "B").start();
        }
    }
}

class Resource {
    private volatile boolean flag = false;

    private List list = new ArrayList();

    private Object object = new Object();

    public void add(Object elem) {
        while (flag) {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        list.add(elem);
        if (list.size() == 5) {
            flag = true;
        }
        object.notify();
    }

    public void print() {
        while (!flag) {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + "开始行动");
        flag = false;
        object.notify();
    }
}
