package com.tiger.research.javacore.guava.eventBus;

import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/2/5
 * Version:		1.0
 * Remark：
 */
public class EventListener1 {

    private final static Logger logger = LoggerFactory.getLogger(EventListener1.class);

    @Subscribe
    public void test1(CustomEvent event){
//        System.out.println(Instant.now() +"监听者1-->订阅者1,收到事件："+event.getAge()+"，线程号为："+Thread.currentThread().getName());
//        System.out.println(String.format("%s 监听者1-->订阅者1,收到事件：%d，线程号为：%s",Instant.now().toString(), event.getAge(), Thread.currentThread().getName()));
        logger.info("Listener1->subscriber1 received event[{}], thread[{}]", event.getAge(), Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Listener1->subscriber1 finished event[{}], thread[{}]", event.getAge(), Thread.currentThread().getName());
    }
    @Subscribe
    public void test2(CustomEvent event){
//        System.out.println(Instant.now() +"监听者1-->订阅者2,收到事件："+event.getAge()+"，线程号为："+Thread.currentThread().getName());
//        System.out.println(String.format("%s 监听者1-->订阅者2,收到事件：%d，线程号为：%s",Instant.now().toString(), event.getAge(), Thread.currentThread().getName()));
        logger.info("Listener1->subscriber2 received and finished event[{}], thread[{}]", event.getAge(), Thread.currentThread().getName());
    }
}
