package com.wtc.juc.collections.demo01.queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: 吴天成
 * @Date: 2019/9/15
 * @Description: com.wtc.juc.collections.demo01.queue
 * @version: 1.0
 */
public class BlockingQueueDemo {

    public static void main(String[] args) {

//        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue(3);
        BlockingQueue<Object> blockingQueue = new LinkedBlockingQueue<>();

//        addAndRemove(blockingQueue);

//        offerAndPoll(blockingQueue);

//        putAndTake(blockingQueue);

        offerTimeAndPollTime(blockingQueue);
    }

    private static void offerTimeAndPollTime(BlockingQueue<Object> blockingQueue) {
        try {
            System.out.println(blockingQueue.offer(1, 1, TimeUnit.SECONDS));
            blockingQueue.offer(2, 1, TimeUnit.SECONDS);
            blockingQueue.offer(3, 1, TimeUnit.SECONDS);
            System.out.println(blockingQueue.offer(4, 1, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void putAndTake(BlockingQueue<Object> blockingQueue) {
        try {
            blockingQueue.put(1);
            blockingQueue.put(2);
            blockingQueue.put(3);
//            blockingQueue.put(4);

            blockingQueue.poll();
            blockingQueue.poll();
            System.out.println(blockingQueue.poll());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void offerAndPoll(BlockingQueue<Object> blockingQueue) {

        System.out.println(blockingQueue.offer(1));
        blockingQueue.offer(2);
        blockingQueue.offer(3);
        System.out.println(blockingQueue.offer(4));

        System.out.println("队首元素：" + blockingQueue.peek());

        System.out.println(blockingQueue.poll());
        blockingQueue.poll();
        blockingQueue.poll();
        System.out.println(blockingQueue.poll());
    }

    private static void addAndRemove(BlockingQueue blockingQueue) {
        System.out.println(blockingQueue.add(1));
        blockingQueue.add(2);
        System.out.println("队首元素：" +  blockingQueue.element());
        blockingQueue.add(3);
//        抛异常
//        blockingQueue.add(4);

        System.out.println(blockingQueue.remove(1));
        List list = new ArrayList();
        int removeNum = blockingQueue.drainTo(list);
        System.out.println("移除队列中的" + removeNum + "个元素：" + list);
    }
}
