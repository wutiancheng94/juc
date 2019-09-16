package com.wtc.juc.collections.demo01.queue.delayqueue01;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/16
 * @Description: com.wtc.juc.collections.demo01.queue.delayqueue01
 * @version: 1.0
 */
@Data
@NoArgsConstructor
class WangMin implements Delayed {

    private String id;

     private String name;

    private long endTime;

    private TimeUnit timeUnit;

    public WangMin(String id, String name, long endTime) {
        this.id = id;
        this.name = name;
        this.endTime = endTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return endTime - System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed delayed) {
        WangMin wangMin = (WangMin) delayed;
        return this.getDelay(timeUnit) - wangMin.getDelay(timeUnit) > 0 ? 1 : 0;
    }
}
