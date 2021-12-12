package com.study.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
@Slf4j
public class AbortPolicy {

    //AbortPolicy - 丢弃任务，并抛出拒绝执行 RejectedExecutionException 异常信息。
    // 线程池默认的拒绝策略。必须处理好抛出的异常，否则会打断当前的执行流程，影响后续的任务执行。
    public static void main(String[] args) throws Exception{
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        long keepAliveTime = 5;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10);
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardOldestPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.SECONDS, workQueue, handler);
        for(int i=0; i<100; i++) {
            try {
                executor.execute(new Thread(() -> log.info(Thread.currentThread().getName() + " is running")));
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }
        executor.shutdown();
    }
}
