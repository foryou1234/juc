package com.cn.juc.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

// 懒汉式单例
// 道高一尺，魔高一丈！
public class LazyMan {
    private volatile static LazyMan lazyMan;

    private static boolean qingjiang = false;

    private LazyMan(){
        System.out.println(Thread.currentThread().getName()+"ok");
        synchronized (LazyMan.class){
            if(lazyMan!=null || qingjiang==true){
                throw new RuntimeException("不要试图使用反射破坏异常");
            }else{
                qingjiang=true;
            }
        }
    }

    // 双重检测锁模式的 懒汉式单例	DCL懒汉式
    public static LazyMan getInstance(){
        if(lazyMan==null){
            synchronized(LazyMan.class){
                if(lazyMan==null){
                    lazyMan=new LazyMan();  // 不是一个原子性操作
                    /**
                     *1. 分配内存空间
                     *2、执行构造方法，初始化对象
                     *3、把这个对象指向这个空间
                     *  123
                     * * 132 A
                     * *     B // 此时lazyMan还没有完成构造
                     **/
                }
            }
        }
        return lazyMan;
    }

    //多线程并发
//    public static void main(String[] args) {
//        for(int i=0; i<1000; i++){
//            new Thread(()->{
//                LazyMan.getInstance();
//            }).start();
//        }
//    }


    //反射！可以破坏单例
    public static void main(String[] args) throws Exception {

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
//        LazyMan lazyMan1 = LazyMan.getInstance();
        LazyMan lazyMan1 = declaredConstructor.newInstance();

        Field qingjiang = LazyMan.class.getDeclaredField("qingjiang");
        qingjiang.setAccessible(true);
        qingjiang.set(lazyMan1,false);
        LazyMan lazyMan2 = declaredConstructor.newInstance();

        System.out.println(lazyMan1);
        System.out.println(lazyMan2);

//        System.out.println(LazyMan.getInstance());
    }
}
