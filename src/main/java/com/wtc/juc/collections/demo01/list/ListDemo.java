package com.wtc.juc.collections.demo01.list;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/15
 * @Description: com.wtc.juc.collections.demo01
 * @version: 1.0
 */
public class ListDemo {

    public static void main(String[] args) {
        System.out.println("线程不安全的List");
//        method01();

        System.out.println("同步类容器 线程安全的List");
//        method02();

        System.out.println("Collections工具类将线程不安全的容器转变为同步类容器");
//        method03();

        System.out.println("在迭代、跳转、条件运算等场景下，同步类容器也会存在线程安全问题");
//        method04();

        System.out.println("CopyOnWriteArrayList 写时复制容器来保证线程安全");
        method05();
    }

    private static void method05() {
        List list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                list.add(String.valueOf(finalI));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        System.out.println("迭代：" + m1(list));
    }

    private static void method04() {
        List<Object> vector = new ArrayList();
        vector.add("1");
        vector.add("2");
        vector.add("3");
        Collection collection = m1(vector);
//        Collection collection = m2(vector);
//        Collection collection = m3(vector);
        collection.forEach(System.out::println);

    }

    private static void method03() {
        List<Object> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                list.add(String.valueOf(finalI));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

//      System.out.println("迭代：" + m1(list));
    }

    private static void method02() {
        List vector = new Vector();

        // 多线程场景下出现 ConcurrentModificationException 异常
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                vector.add(String.valueOf(finalI));
                System.out.println(vector);
            }, String.valueOf(i)).start();
        }
    }

    private static void method01() {
        // 默认初始容量为10，扩容因子为0.5 的Object类型数组
        List list = new ArrayList();

        // 多线程场景下出现 ConcurrentModificationException 异常
        for (int i = 0; i < 20; i++) {
            int finalI = i;
            new Thread(() -> {
                list.add(String.valueOf(finalI));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }

    /**
     *  successful
     *  整个过程是单线程的
     */
    private static Collection m3(List<Object> vector) {
        for (int i = 0; i < vector.size(); i++) {
            if ("3".equals(vector.get(i))) {
                vector.remove(i);
                break;
            }
        }

        return vector;
    }


    /**
     * 使用iterator对集合进行删除操作
     * ConcurrentModificationException
     * 有一个线程专门去操作iterator
     */
    private static Collection m2(List<Object> vector) {
        Iterator<Object> iterator = vector.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            if ("3".equals(obj)) {
                vector.remove(obj);
            }
        }

        return vector;
    }

    /**
     * foreach 中对集合进行删除操作
     * ConcurrentModificationException
     */
    private static Collection m1(List<Object> vector) {
        for (Object v : vector) {
            if ("3".equals(v)) {
                vector.remove(v);
            }
        }

        return vector;
    }
}
