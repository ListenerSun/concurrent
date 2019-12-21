package com.sqt.concurrent.aqs;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:
 * @author: ListenerSun(男, 未婚) 微信:810548252
 * @Date: Created in 2019-12-09 23:35
 */
public class ObjectPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ObjectPool<Teacher,String> pool = new ObjectPool<>(10,new Teacher());
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0; i < 100; i++){
            exec.execute(() ->{
                String result = null;
                try {
                    result = pool.excute(t -> t.say());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(result);
            });
        }


    }
}
