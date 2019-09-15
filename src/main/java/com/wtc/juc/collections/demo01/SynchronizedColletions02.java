package com.wtc.juc.collections.demo01;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/15
 * @Description: com.wtc.juc.collections.demo01
 * @version: 1.0
 */
public class SynchronizedColletions02 {

    public static void main(String[] args) {
        List<Object> list = Collections.synchronizedList(new Vector<>());
        list.add("1");
        list.add("2");
        list.add("3");

        list.m1
    }
}
