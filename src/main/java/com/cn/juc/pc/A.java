package com.cn.juc.pc;


/*
* 线程通信      等待、通知
* 生产者、消费者问题
* 线程A、B交替执行，操作同一个变量
* */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for(int i=0; i<10; i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for(int i=0; i<10; i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for(int i=0; i<10; i++){
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for(int i=0; i<10; i++){
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}
class Data{
    private int number=0;
    //+1
    public synchronized void increment() throws InterruptedException {
        while(number!=0){
            //等待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"=>number"+number);
        //通知其他线程
        this.notifyAll();
    }

    //-1
    public synchronized void decrement() throws InterruptedException {
        while(number==0){
            //等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"=>number"+number);
        //通知其他线程
        this.notifyAll();
    }
}
