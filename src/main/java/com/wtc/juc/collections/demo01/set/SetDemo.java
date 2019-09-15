package com.wtc.juc.collections.demo01.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/15
 * @Description: com.wtc.juc.collections.demo01
 * @version: 1.0
 */
public class SetDemo {
    
    public static void main(String[] args) {
       method01();
    }

    private static void method01() {
        // HashSet 底层是 value恒定为一个Object对象的hashmap
        Set set = new HashSet();
    }
}
