package com.sqt.concurrent.countexample;

import com.sqt.concurrent.annoations.ThreadUnsafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/** 模拟并发编程代码
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-09-29 1:49
 */
@Slf4j
@ThreadUnsafe
public class CountExample {
    /**
     * 请求总数
     */
    public static int clientTotal = 5000;
    /**
     * 并发的总数
     */
    public static int threadTotal = 200;
    /**
     * 计数
     */
    public static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    //获取许可
                    semaphore.acquire();
                    add();
                    //归还许可
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        //await()方法,计数为0时执行当前线程后面的方法
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }

    private static void add() {
        count++;
    }
}
