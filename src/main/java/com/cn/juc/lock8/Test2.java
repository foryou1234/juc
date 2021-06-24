package com.cn.juc.lock8;

import java.util.concurrent.TimeUnit;

/*
* 锁的对象不同
* */
public class Test2 {
    public static void main(String[] args){
        Phone2 phone = new Phone2();
        Phone2 phone2 = new Phone2();

        //锁的存在
        new Thread(()->{
            phone.sendSms();
        },"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{
            phone2.call();
        },"B").start();
    }
}
class Phone2{
    public synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void call(){
        System.out.println("打电话");
    }

    //这里没有锁! 不是同步方法
    public void hello(){
        System.out.println("hello");
    }
}
