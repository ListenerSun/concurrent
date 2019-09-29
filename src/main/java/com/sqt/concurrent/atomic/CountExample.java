package com.sqt.concurrent.atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-09-29 1:49
 */
@Slf4j
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
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }

    private static void add() {
        count++;
    }
}
