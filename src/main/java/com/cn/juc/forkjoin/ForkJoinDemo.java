package com.cn.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/*
* 如何使用ForkJoin
* 1、ForkJoinPool通过他来执行
* 2、计算任务 forkjoinpool.execute(ForkJoinTask<?> task)
*
 * */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private long start;
    private long end;

    private long temp = 10000L;

    public ForkJoinDemo(long start,long end) {
        this.start=start;
        this.end=end;
    }


    //计算方法
    @Override
    protected Long compute() {
        if(end-start<temp){
            long sum=0;
            for (long i = start; i <= end ; i++) {
                sum+=i;
            }
            return sum;
        }else{  //forkjoin 递归
            long mid = (start+end)/2;
            ForkJoinDemo forkJoinDemo1 = new ForkJoinDemo(start, mid);
            forkJoinDemo1.fork(); //拆分任务，把任务压入线程队列
            ForkJoinDemo forkJoinDemo2 = new ForkJoinDemo(mid+1, end);
            forkJoinDemo2.fork(); //拆分任务，把任务压入线程队列
            return forkJoinDemo1.join()+forkJoinDemo2.join();
        }
    }
}
