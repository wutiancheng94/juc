package com.wtc.juc.collections.demo01.queue.delayqueue01;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/16
 * @Description: com.wtc.juc.collections.demo01.queue
 * @version: 1.0
 */
public class DelayQueueDemo {

    public static void main(String[] args) {
        WangBa wangBa = new WangBa();
        wangBa.yingye("1", "张三", 1);
        wangBa.yingye("2", "李四", 2);
        wangBa.yingye("3", "王五", 3);
        new Thread(wangBa).start();
    }
}




