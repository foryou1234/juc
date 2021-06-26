package com.cn.juc.future;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
* 异步调用
*异步执行
* 成功回调
* 失败回调
* */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 没有返回值的 runAsync 异步回调
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(()->{
//            try {
//                TimeUnit.SECONDS.sleep(2);
//                System.out.println(Thread.currentThread().getName()+"CompletableFuture");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        System.out.println("111");
//        completableFuture.get();    //等待获取回调结果
//        System.out.println("222");

        // 有返回值的 supplyAsync 异步回调
        // ajax，成功和失败的回调
        // 返回的是错误信息；
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println(Thread.currentThread().getName()+"supplyAsync=>Integer");
//            int i = 10/0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t,u)->{  //成功回调
            System.out.println("t=>" + t); // 正常的返回结果
            System.out.println("u=>" + u); // 错误信息：java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e)->{ //失败回调
            System.out.println(e.getMessage());
            return 500; // 可以获取到错误的返回结果
        }).get());
    }
}
