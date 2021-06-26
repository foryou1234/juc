package com.cn.juc.pc_jmm_volatile;

import java.util.concurrent.TimeUnit;

public class JmmDemo {
    // 不加 volatile 程序就会死循环！
    // 加 volatile 可以保证可见性
    private volatile static int num=0;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{    // 线程 1 对主内存的变化不知道的
            while(num==0){
//                System.out.println("num");
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);
        num=1;
        System.out.println(num);
    }
}
