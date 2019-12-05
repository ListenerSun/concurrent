package com.sqt.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: 信号量 例子
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-05 22:37
 */
@Slf4j
public class SemaphoreExample {

    private static final int threadCount = 100;

    public static void main(String[] args) throws InterruptedException {
        // 只允许 10个线程  同时获得请求 执行任务
        final Semaphore semaphore = new Semaphore(10);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute( () ->{
                try {
                    //尝试获得一个许可
//            semaphore.tryAcquire()；
                    // 获取一个许可，获取到了许可往下执行任务
                    semaphore.acquire();
                    test(threadNum);
                    // 释放一个许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        //关闭线程池
        exec.shutdown();
    }

    private static void test(int threadNum) {
        log.info("threadNum:{}",threadNum);
    }
}
