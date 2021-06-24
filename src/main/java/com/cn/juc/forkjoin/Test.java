package com.cn.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 *求和计算的任务！
 *3000	6000（ForkJoin）	9000（Stream并行流）
 * 如何使用 forkjoin
        *1、forkjoinPool 通过它来执行
        * 2、计算任务 forkjoinPool.execute(ForkJoinTask task)
        * 3. 计算类要继承 ForkJoinTask
        */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        test1();    //sum=-5340232216128654848时间2915
//        test2();    //sum=-5340232216128654848时间1662
        test3();    //sum=-5340232216128654848时间952
    }


    public static long end =  10_0000_00000L;
    //普通
    public static  void test1(){
        long sum=0L;
        long start = System.currentTimeMillis();
        for(long i=1; i<=end; i++){
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"时间"+(end-start));
    }

    //forkJoin
    public static  void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        //1、ForkJoinPool通过他来执行
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(1L,end);
        //* 2、计算任务 forkjoinpool.execute(ForkJoinTask<?> task)
//        ForkJoinTask<Long> submit = forkJoinPool.submit(task);  //* @return the task
//        Long sum = submit.get();
        Long sum = forkJoinPool.invoke(task); //或者使用来代替submit * @return the task's result

        long end = System.currentTimeMillis();
        System.out.println("sum="+sum+"时间"+(end-start));
    }
    public static  void test3(){
        long start = System.currentTimeMillis();
        //Stream并行流 range范围() rangeClose范围(]
        System.out.println("sum="+LongStream.rangeClosed(0L, end)  //范围
                .parallel() //并行运算
                .reduce(0, Long::sum)); //计算
        long end = System.currentTimeMillis();
        System.out.println("时间"+(end-start));
    }
}
