package com.cn.juc.lock;

import java.util.concurrent.TimeUnit;

public class Demo1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(()->{
            phone.send();
        }).start();

        new Thread(()->{
            phone.send();
        }).start();
    }
}
class Phone{
    public synchronized void send(){
        System.out.println(Thread.currentThread().getName()+" send");
        call();
    }
    public synchronized void call(){
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" call");
    }
}