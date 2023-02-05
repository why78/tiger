package com.tiger.research.javacore.guava.eventBus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

/**
 * Project Name:	javacore
 * <p>
 * Author:      Wang Huiyuan
 * Create Date: 2023/2/5
 * Version:		1.0
 * Remark：
 */
public class EventBusUtil {

    private final static Logger logger = LoggerFactory.getLogger(EventBusUtil.class);

    private static EventBus eventBus;
    private static AsyncEventBus asyncEventBus;
    private static Executor executor = new Executor() {
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    };
    //双重锁单例模式
    private static AsyncEventBus getAsyncEventBus(){
        if(asyncEventBus==null){
            synchronized (AsyncEventBus.class){
                if(asyncEventBus==null){
                    asyncEventBus = new AsyncEventBus(executor);
                }
            }
        }
        return asyncEventBus;
    }
    //双重锁单例模式
    private static EventBus getEventBus(){
        if(eventBus==null){
            synchronized (EventBus.class){
                if(eventBus==null){
                    eventBus = new EventBus();
                }
            }
        }
        return eventBus;
    }
    public static void postToSyncEventBus(Object event){
        getEventBus().post(event);
    }
    //异步方式发送事件
    public static void postToAsyncEventBus(Object event){
        getAsyncEventBus().post(event);
    }
    public static void registerToSyncEventBus(Object object){
        getEventBus().register(object);
        logger.info("Registered listener[{}] into eventBus.", object.getClass().getSimpleName());
    }

    public static void registerToAsyncEventBus(Object object){
        getAsyncEventBus().register(object);
        logger.info("Registered listener[{}] into asyncEventBus.", object.getClass().getSimpleName());
    }
}
