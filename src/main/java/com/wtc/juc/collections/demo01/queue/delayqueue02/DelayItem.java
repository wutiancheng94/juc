package com.wtc.juc.collections.demo01.queue.delayqueue02;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/17
 * @Description: com.wtc.juc.collections.demo01.queue.delayqueue02
 * @version: 1.0
 */
@Data
@AllArgsConstructor
public class DelayItem implements Delayed {
    private long now;  //创建时间
    private  long delay; //延迟时间
    private  long expire;  //到期时间

    private String key;

    public DelayItem(long expire, String key) {
        this.expire = expire + System.currentTimeMillis();
        this.key = key;
    }

    public String get() {
        return key;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS)>0?1:-1;
    }
}
