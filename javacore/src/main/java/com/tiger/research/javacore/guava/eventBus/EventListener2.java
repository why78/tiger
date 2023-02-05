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
public class EventListener2 {

    private final static Logger logger = LoggerFactory.getLogger(EventListener2.class);

    @Subscribe
    public void consume(CustomEvent event){
//        System.out.println(Instant.now() +",监听者2,收到事件："+event.getAge()+"，线程号为："+Thread.currentThread().getName());
        logger.info("Listener2 received event[{}], thread[{}]", event.getAge(), Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Listener2 finished event[{}], thread[{}]", event.getAge(), Thread.currentThread().getName());
//        System.out.println(String.format("%s,监听者2,收到事件：%d，线程号为：%s", Instant.now(), event.getAge(), Thread.currentThread().getName()));

    }
}
