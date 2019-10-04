package com.sqt.concurrent.atomic;

import com.sqt.concurrent.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/** AtomicReference demo
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-10-05 1:49
 */
@Slf4j
@ThreadSafe
public class AtomicCountRefrenceExample {
    private static AtomicReference<Integer> atomicReference = new AtomicReference<>(0);

    public static void main(String[] args) throws InterruptedException {
        //compareAndSet()方法  将AtomicReference中的值与第一个参数比较,相等将值设置成第二个参数
        atomicReference.compareAndSet(0,2); //yes
        atomicReference.compareAndSet(0,1); //no
        atomicReference.compareAndSet(1,3); //no
        atomicReference.compareAndSet(2,3); //no
        atomicReference.compareAndSet(3,5); //yes
        atomicReference.compareAndSet(4,6); //no
        log.info("count:{}",atomicReference.get());
    }


}
