package com.cn.juc.single;


// 饿汉式单例
public class Hungry {
    private final static Hungry hungry = new Hungry();
    // 可能会浪费空间
    private byte[] data1 = new byte[1024*1024];
    private byte[] data2 = new byte[1024*1024];
    private byte[] data3 = new byte[1024*1024];
    private byte[] data4 = new byte[1024*1024];

    private Hungry(){
    }

    public static Hungry getInstance(){
        return hungry;
    }
}
