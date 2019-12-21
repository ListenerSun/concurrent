package com.sqt.concurrent.aqs;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @Description: 对象池，只允许 size个数量使用对象，可以重复使用对象，并且可以达到限流
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-09 23:23
 */
public class ObjectPool<T,R> {

    /**
     *对象集合
     */
    final List<T> pool;
    /**
     * 信号量 控制 同时使用对象 的线程个数
     */
    final Semaphore sem;

    public ObjectPool(int size,T t) {
        pool = new Vector<>();
        for(int i = 0; i < size; i++){
            pool.add(t);
        }
        sem = new Semaphore(size);
    }

    R excute(Function<T,R> function) throws InterruptedException {
        T t = null;
        sem.acquire();
        try{
            t = pool.remove(0);
            return  function.apply(t);
        }finally {
            pool.add(t);
            sem.release();
        }
    }
}
