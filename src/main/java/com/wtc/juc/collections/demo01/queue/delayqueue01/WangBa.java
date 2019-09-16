package com.wtc.juc.collections.demo01.queue.delayqueue01;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/16
 * @Description: com.wtc.juc.collections.demo01.queue.delayqueue01
 * @version: 1.0
 */
class WangBa implements Runnable {

    private boolean start = true;

    private BlockingQueue<WangMin> delayQueue = new DelayQueue();

    public void yingye(String id, String name, int money) {
        WangMin wangMin = new WangMin(id, name, System.currentTimeMillis() + money * 1000);
        System.out.println("网名：" + name + ", 身份证：" + id + ", 缴费：" + money + "元, 开始上网");
        delayQueue.offer(wangMin);
    }

    public void overMachine(WangMin wangMin) {
        System.out.println(System.currentTimeMillis() + " \t网名：" + wangMin.getName() + ", 身份证：" + wangMin.getId() + ", 下机");
    }

    @Override
    public void run() {
        while (start) {
            try {
                WangMin wangMin = delayQueue.take();
                overMachine(wangMin);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
