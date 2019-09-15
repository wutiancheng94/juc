package com.wtc.juc.collections.demo01.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/15
 * @Description: com.wtc.juc.collections.demo01
 * @version: 1.0
 */
public class SetDemo {

    public static void main(String[] args) {
        method01();

        System.out.println("Collections工具类");
        method02();

        System.out.println("CopyOnWrite");
        method03();
    }

    private static void method03() {
        System.out.println("底层是CopyOnWriteArrayList");
        CopyOnWriteArraySet<Object> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                set.add(finalI);
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    private static void method02() {
        Set<Object> set = Collections.synchronizedSet(new HashSet<>());
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                set.add(finalI);
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * 抛出 ConcurrentModificationException
     **/
    private static void method01() {
        // HashSet 底层是初始值为16,负载因子为0.75的,value恒定为一个Object对象的hashmap
        Set set = new HashSet();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                set.add(finalI);
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
