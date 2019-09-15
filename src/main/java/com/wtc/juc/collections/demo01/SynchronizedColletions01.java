package com.wtc.juc.collections.demo01;

import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;

/**
 * 〈同步类容器 线程安全问题〉
 *
 * @author 吴天成
 * @create 2019/9/15
 * @since 1.0.0
 */
public class SynchronizedColletions01 {

    public static void main(String[] args) {
        Vector<Object> vector = new Vector<>();
        vector.add("1");
        vector.add("2");
        vector.add("3");

//        Collection collection = m1(vector);
        Collection collection = m2(vector);
//        Collection collection = m3(vector);
        collection.forEach(System.out::println);
    }

    /**
     *  successful
     *  整个过程是单线程的
     */
    private static Collection m3(Vector<Object> vector) {
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
    private static Collection m2(Vector<Object> vector) {
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
    private static Collection m1(Vector<Object> vector) {
        for (Object v : vector) {
            if ("3".equals(v)) {
                vector.remove(v);
            }
        }

        return vector;
    }

}