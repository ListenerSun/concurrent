package com.sqt.concurrent.sync;

import com.sun.corba.se.impl.orbutil.concurrent.Sync;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: Synchronized demo
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-10-05 1:33
 */
@Slf4j
public class SynchronizedExample1 {

    /**
     * 修饰一个代码块
     */
    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - :{}", i);
            }
        }
    }

    /**
     * 修饰一个静态方法
     */
    public synchronized static void test2() {
        for (int i = 0; i < 10; i++) {
            log.info("test2 - :{}", i);
        }
    }

    /**
     * 修饰一个类
     */
    public void test3() {
        synchronized (SynchronizedExample1.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 - :{}", i);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 synchronizedExample1 = new SynchronizedExample1();
        SynchronizedExample1 synchronizedExample2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        /**
         * 两个类执行test1()方法时,互不影响，可以交替执行
         */
        executorService.execute(() -> synchronizedExample1.test1());
        executorService.execute(() -> synchronizedExample2.test1());


        /**
         * 修饰静态方法 跟 修饰一个类 的作用是一样的
         */
        // 两个类执行静态test2()方法时,只有一个类可以执行该方法,执行完后另一个类才可以执行test2()方法
//        executorService.execute(() -> SynchronizedExample1.test2());
//        executorService.execute(() -> SynchronizedExample1.test2());
        //两个类执行静态test3()方法时,只有一个类可以执行该方法,执行完后另一个类才可以执行test3()方法
//        executorService.execute(() -> synchronizedExample1.test3());
//        executorService.execute(() -> synchronizedExample1.test3());
        executorService.shutdown();
    }
}
