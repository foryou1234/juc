package com.cn.juc.pc_jmm_volatile;

import java.util.concurrent.atomic.AtomicInteger;

// volatile 不保证原子性
public class JmmDemo2 {
    // volatile 不保证原子性
    private volatile static AtomicInteger num = new AtomicInteger(0);
    public static void add(){
        num.getAndIncrement();
    }
    public static void main(String[] args) {
        //理论上num结果应该为 2 万
        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000 ; j++) { add();
                }
            }).start();
        }

        while (Thread.activeCount()>2){ // main	gc
            Thread.yield();//yield()从未导致线程转到等待/睡眠/阻塞状态。在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。
        }
        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
