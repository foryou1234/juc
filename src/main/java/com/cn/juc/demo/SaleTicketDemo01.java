package com.cn.juc.demo;



//获取cpu的核数
//cpu 密集型，io密集型
//        System.out.println(Runtime.getRuntime().availableProcessors());
//        Thread.State;
/*
* 线程就是一个单独的资源类，没有任何附属的操作
*
* */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        //并发：多个线程操作同一个资源，把资源类丢入线程
        Ticket ticket = new Ticket();
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
class Ticket{
    //属性、方法
    private int number = 50;
    //卖票的方式
    public synchronized void sale(){
        if(number > 0){
            System.out.println(Thread.currentThread().getName()+"卖出了："+number--+"票,剩余："+number);

        }
    }
}
