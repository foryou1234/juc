package com.cn.juc.CAS;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

//什么是 CAS
/*
* CAS ： 比较当前工作内存中的值和主内存中的值，如果这个值是期望的，
* 那么则执行操作！如果不是就一直循环！
缺点：
1、 循环会耗时
2、一次性只能保证一个共享变量的原子性
3、ABA问题
*/
//Unsafe 类
public class CASDemo {
    // CAS	compareAndSet : 比较并交换！
    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(2020);
        //期望、更新
        //public final boolean compareAndSet(int except, int update)
        // 如果我期望的值达到了，那么就更新，否则，就不更新, CAS 是CPU的并发原语！
        // ============== 捣乱的线程 ==================
        System.out.println(integer.compareAndSet(2020,2021));
        System.out.println(integer.get());


        System.out.println(integer.compareAndSet(2021, 2020));
        System.out.println(integer.get());

//        integer.getAndIncrement();  //原子操作

        // ============== 期望的线程 ==================
        System.out.println(integer.compareAndSet(2020, 2021));
        System.out.println(integer.get());

//        AtomicInteger integer = new AtomicInteger(2020);

    }
}
