package com.cn.juc.bq;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class QueueTest {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }
    /*抛出异常
    * */
    public static void test1(){
        //队列大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
        //抛出队列已满
//        System.out.println(arrayBlockingQueue.add("d"));
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        //抛出没有元素
//        System.out.println(arrayBlockingQueue.remove());
    }
    /*不抛出异常
     * */
    public static void test2(){
        //队列大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(arrayBlockingQueue.offer("a"));
        System.out.println(arrayBlockingQueue.offer("b"));
        System.out.println(arrayBlockingQueue.offer("c"));
        //队列已满,返回false
        System.out.println(arrayBlockingQueue.offer("d"));

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        //返回空元素
        System.out.println(arrayBlockingQueue.poll());
    }
    /*队列阻塞
     * */
    public static void test3() throws InterruptedException {
        //队列大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
        //队列阻塞
        arrayBlockingQueue.put("d");

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        //队列阻塞
        System.out.println(arrayBlockingQueue.take());
    }
    /*超时等待
     * */
    public static void test4() throws InterruptedException {
        //队列大小
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        arrayBlockingQueue.offer("a");
        arrayBlockingQueue.offer("b");
        arrayBlockingQueue.offer("c");
        //队列超时退出
        arrayBlockingQueue.offer("d",2,TimeUnit.SECONDS);

        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        //返回空元素
        System.out.println(arrayBlockingQueue.poll(2,TimeUnit.SECONDS));
    }
}
