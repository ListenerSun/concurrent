package com.sqt.concurrent.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 计数器例子
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-10-06 1:30
 */
@Slf4j
public class CountDownLanchExample {

    private static final int threadCount = 100;

    private static final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++){
            final int threadNum = i;
            exec.execute(()->{
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception:{}",e);
                }finally {
                    // 计数器内的数字 减一
                    countDownLatch.countDown();
                }
            });
        }
        // 计数器 数字 减到 0 的时候 后面的操作进行
        countDownLatch.await();
        exec.shutdown();
        log.info("finished");
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("threadNum:{}",threadNum);
    }
}
