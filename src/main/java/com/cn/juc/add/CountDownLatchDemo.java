package com.cn.juc.add;

import java.util.concurrent.CountDownLatch;


/*
*
每次有线程调用 countDown() 数量-1，假设计数器变为0，countDownLatch.await()  就会被唤醒，继续执行！
* */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);// 总数是6，必须要执行任务的时候，再使用！
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();// 数量-1
            },String.valueOf(i)).start();
        }
        countDownLatch.await();// 等待计数器归零，然后再向下执行
        System.out.println("close door");
    }
}
