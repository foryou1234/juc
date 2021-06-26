package com.cn.juc.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02 {
    public static void main(String[] args) {
        //并发：多个线程操作同一个资源，把资源类丢入线程
        Ticket2 ticket = new Ticket2();
        new Thread(()->{
            for(int i=0; i<60; i++){
                ticket.sale();
            }
        },"A").start();
        new Thread(()->{
            for(int i=0; i<60; i++){
                ticket.sale();
            }
        },"B").start();
        new Thread(()->{
            for(int i=0; i<60; i++){
                ticket.sale();
            }
        },"C").start();
    }
}

//资源类 OOP
//lock三部曲
//1、new ReentrantLock()
//2、lock.lock()
//3、lock.unlock()
class Ticket2{
    //属性、方法
    private int number = 50;

    Lock lock = new ReentrantLock();    //默认非公平锁，随机，可以插队，所以公平 ------

    //卖票的方式
    public void sale(){
        lock.lock();
        try {
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+"卖出了："+number--+"票,剩余："+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
