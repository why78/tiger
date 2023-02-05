package com.tiger.research.javacore.guava.eventBus;

import org.junit.Before;
import org.junit.Test;
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
public class EventBusTest {

    private final static Logger logger = LoggerFactory.getLogger(EventBusTest.class);

    EventListener1 listener1;
    EventListener2 listener2;

    @Before
    public void init() {
        listener1 = new EventListener1();
        listener2 = new EventListener2();

    }

    private void registerSyncEventBus() {
        EventBusUtil.registerToSyncEventBus(listener1);
        EventBusUtil.registerToSyncEventBus(listener2);
    }

    private void registerAsyncEventBus() {
        EventBusUtil.registerToAsyncEventBus(listener1);
        EventBusUtil.registerToAsyncEventBus(listener2);
    }

    @Test
    public void testPostToSyncEventBus() {
        this.registerSyncEventBus();
        CustomEvent customEvent = new CustomEvent(23);
        EventBusUtil.postToSyncEventBus(customEvent);
//        System.out.println(String.format("%s,主线程执行完毕：%s", Instant.now().toString(), Thread.currentThread().getName()));
        logger.info("Main thread has done! Thread[{}]", Thread.currentThread().getName());
    }

    @Test
    public void testPostToAsyncEventBus() throws InterruptedException {
        this.registerAsyncEventBus();
        CustomEvent customEvent = new CustomEvent(23);
        EventBusUtil.postToAsyncEventBus(customEvent);
//        System.out.println(String.format("%s,主线程执行完毕：%s", Instant.now().toString(), Thread.currentThread().getName()));
        logger.info("Main thread has done! Thread[{}]", Thread.currentThread().getName());
        Thread.sleep(10*1000);
    }
}