package com.wtc.juc.collections.demo01.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/15
 * @Description: com.wtc.juc.collections.demo01.map
 * @version: 1.0
 */
public class MapDemo {

    public static void main(String[] args) {
//        method01();

        System.out.println("同步类容器：hashtable");
//        method02();

//        method03();

        System.out.println("并发类容器：ConcurrentHashMap");
        method04();
    }

    private static void method04() {
        Map<String, Object> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(String.valueOf(finalI), String.valueOf(finalI));
                System.out.println(map );
            }, String.valueOf(i)).start();
        }
    }

    /**
     * ConcurrentModificationException
     **/
    private static void method03() {
        Map<String, Object> hashtable = new Hashtable<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> hashtable.put(String.valueOf(finalI), String.valueOf(finalI)), String.valueOf(i)).start();
        }

        System.out.println("使用增强for循环或iterator迭代时, 对容器进行增删操作会抛异常");
        for (Map.Entry<String, Object> entry : hashtable.entrySet()) {
            if ("2".equals(entry.getKey())) {
//                hashtable.remove(entry.getKey());
                hashtable.put("11", "22");
            }
        }
    }

    private static void method02() {
        Map<Object, Object> hashtable = new Hashtable<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                hashtable.put(finalI, String.valueOf(finalI));
                System.out.println(hashtable);
            }, String.valueOf(i)).start();
        }
    }

    /**
     * ConcurrentModificationException
     **/
    private static void method01() {
        Map<Integer, Object> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                map.put(finalI, String.valueOf(finalI));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
