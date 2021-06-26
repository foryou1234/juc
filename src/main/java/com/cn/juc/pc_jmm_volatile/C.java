package com.cn.juc.pc_jmm_volatile;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class C {
    public static void main(String[] args) {
        Data3 data3 = new Data3();
        new Thread(()->{
            for(int i=0; i<10; i++){
                data3.PrintA();
            }
        }).start();
        new Thread(()->{
            for(int i=0; i<10; i++){
                data3.PrintB();
            }
        }).start();
        new Thread(()->{
            for(int i=0; i<10; i++){
                data3.PrintC();
            }
        }).start();
    }
}
class Data3{
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int number=1;

    public void PrintA(){
        lock.lock();
        try {
            while(number!=1){
                condition1.await();
            };
            System.out.println(Thread.currentThread().getName()+"AAA");
            number=2;
            //唤醒指定的线程
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void PrintB(){
        lock.lock();
        try {
            while(number!=2){
                condition2.await();
            };
            System.out.println(Thread.currentThread().getName()+"BBB");
            number=3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void PrintC(){
        lock.lock();
        try {
            while(number!=3){
                condition3.await();
            };
            System.out.println(Thread.currentThread().getName()+"CCC");
            number=1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
