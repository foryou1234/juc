package com.cn.juc.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
/*
*
3、ABA问题
*
* CAS ： ABA 问题（狸猫换太子）
* 解决ABA 问题，引入原子引用！ 对应的思想：乐观锁！
* */
//Unsafe 类

public class ABADemo {
    //AtomicStampedReference 注意，如果泛型是一个包装类，注意对象的引用问题

    // 正常在业务操作，这里面比较的都是一个个对象
    static AtomicStampedReference<Integer> integer = new AtomicStampedReference<>(1,1);

    public static void main(String[] args) {
        new Thread(()->{
            int stamp = integer.getStamp(); //获取版本号
            System.out.println("a1->"+stamp);

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e){
                e.printStackTrace();
            }

            integer.compareAndSet(1,2,integer.getStamp(),
                    integer.getStamp()+1);
            System.out.println("a2->"+integer.getStamp());

            System.out.println("a "+integer.compareAndSet(2, 1, integer.getStamp(),
                    integer.getStamp() + 1));
            System.out.println("a3->"+integer.getStamp());
        },"a").start();

        // 乐观锁的原理相同！
        new Thread(()->{
            int stamp = integer.getStamp(); //获取版本号
            System.out.println("b1->"+stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("b "+integer.compareAndSet(1, 6,
                    stamp, stamp + 1));
            System.out.println("b2->"+integer.getStamp());
        }).start();
    }
}
