package com.cn.juc.pool;


import java.util.concurrent.*;
/*
源码分析
public static ExecutorService newSingleThreadExecutor()
        { return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1,0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>()));
        }
public static ExecutorService newFixedThreadPool(int nThreads)
        { return new ThreadPoolExecutor(5, 5,0L, TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>());
        }
public static ExecutorService newCachedThreadPool()
        { return new ThreadPoolExecutor(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,
        new SynchronousQueue<Runnable>());
        }
// 本质ThreadPoolExecutor（）

public ThreadPoolExecutor(int corePoolSize, // 核心线程池大小
int maximumPoolSize, // 最大核心线程池大小
long keepAliveTime, // 超时了没有人调用就会释放
TimeUnit unit, // 超时单位
BlockingQueue<Runnable> workQueue, // 阻塞队列
ThreadFactory threadFactory, // 线程工厂：创建线程的，一般
不用动
RejectedExecutionHandler handle // 拒绝策略) {
*/

// Executors 工具类、3大方法
public class Demo01 {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 单个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);   //创建一个固定的线程池的大小
//        ExecutorService threadPool = Executors.newCachedThreadPool(); //可伸缩的，遇强则强，遇弱则弱

        // 最大线程到底该如何定义
        // 1、CPU 密集型，几核，就是几，可以保持CPu的效率最高！
        // 2、IO	密集型	> 判断你程序中十分耗IO的线程，
        // 程序	15个大型任务	io十分占用资源！

        // 自 定 义 线 程 池 ！ 工 作 ThreadPoolExecutor
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                Runtime.getRuntime().availableProcessors(),  //最大线程数如何定义
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());//队列满了，尝试去和最早的竞争，也不会抛出异常！
        /**
         * 拒绝策略
         *new ThreadPoolExecutor.AbortPolicy() // 银行满了，还有人进来，不处理这个人的，抛出异常
         *new ThreadPoolExecutor.CallerRunsPolicy() // 哪来的去哪里！
         *new ThreadPoolExecutor.DiscardPolicy() //队列满了，丢掉任务，不会抛出异常！
         *new ThreadPoolExecutor.DiscardOldestPolicy() //队列满了，尝试去和最早的竞争，也不会抛出异常！
         */

        try {
            // 最大承载：Deque + max
            // 超过 RejectedExecutionException
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+" ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
