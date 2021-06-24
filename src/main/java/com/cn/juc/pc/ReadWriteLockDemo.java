package com.cn.juc.pc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 *独占锁（写锁） 一次只能被一个线程占有
 *共享锁（读锁） 多个线程可以同时占有
 *ReadWriteLock
 *读-读	可以共存！
 *读-写	不能共存！
 *写-写	不能共存！
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCacheLock = new MyCacheLock();
        // 写入
        for(int i=0; i<=5; i++){
            final int temp = i;
            new Thread(()->{
                myCacheLock.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }
        // 读取
        for (int i = 1; i <= 5 ; i++) { final int temp = i;
            new Thread(()->{ myCacheLock.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

/*
* 声明变量是 volatile 的，JVM 保证了每次读变量都从内存中读，跳过 CPU cache 这一步。
* */
class MyCacheLock{
    /*
    *可见性，是指线程之间的可见性，一个线程修改的状态对另一个线程是可见的。
    *也就是一个线程修改的结果。另一个线程马上就能看到。比如：用volatile修饰的变量，就会具有可见性。
    * volatile修饰的变量不允许线程内部缓存和重排序，即直接修改内存。所以对其他线程是可见的。
    * 但是这里需要注意一个问题，volatile只能让被他修饰内容具有可见性，但不能保证它具有原子性。
    * 比如 volatile int a = 0；之后有一个操作 a++；这个变量a具有可见性，但是a++ 依然是一个非原子操作，
    * 也就是这个操作同样存在线程安全问题。
    * 在 Java 中 volatile、synchronized 和 final实现可见性
     */
    /*
    * 原子是世界上的最小单位，具有不可分割性。比如 a=0；（a非long和double类型） 这个操作是不可分割的，
    * 那么我们说这个操作时原子操作。再比如：a++； 这个操作实际是a = a + 1；是可分割的，
    * 所以他不是一个原子操作。非原子操作都会存在线程安全问题，需要我们使用同步技术（sychronized）
    * 来让它变成一个原子操作。
    * 在 Java 中 synchronized 和在 lock、unlock 中操作保证原子性。
    * */
    /*
    * 只要在某个线程中无法检测到重排序情况（即使在其他线程中可以明显地看到该线程中的重排序），
    * 那么就无法确保线程中的操作将按照程序中指定的顺序来执行。
    * */
    /*
    *volatile变量不会被缓存在寄存器或者对其他处理器不可见的地方，因此在读取volatile类型的变量时总会
    * 返回最新写入的值。在访问volatile变量时不会执行加锁操作，因此也就不会使执行线程阻塞，
    * 因此volatile变量是一种比sychronized关键字更轻量级的同步机制
    * 声明变量是 volatile 的，JVM 保证了每次读变量都从内存中读，跳过 CPU cache 这一步。
    * */
    private volatile Map<String,Object> map = new HashMap<>();
    // 读写锁： 更加细粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock lock = new ReentrantLock();

    // 存，写入的时候，只希望同时只有一个线程写
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName()+"写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }
    public void get(String key){
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}