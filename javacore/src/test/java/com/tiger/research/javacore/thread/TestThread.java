/**
 *
 * Project Name:	javacore
 * File Name:	TestThread.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年12月5日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.thread;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.tiger.research.javacore.Thread.MyThread;
import com.tiger.research.javacore.Thread.NamedThreadFactory;
import com.tiger.research.javacore.http.HttpClientUtility;


/**
 * @author WangHuiyuan
 *
 */
public class TestThread {

	private final static Logger logger = LoggerFactory.getLogger(TestThread.class);

	@Before
	public void setUp() throws Exception {
	}


	
	@Test
	public void testThread() throws InterruptedException{
		logger.info("start");
		Executors.newScheduledThreadPool(1).scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {
				try {
					logger.info("hello");
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		}, 10, 5, TimeUnit.SECONDS);
		
		while(true){
			Thread.sleep(10);
		}
	}
	
	
	@Test
	public void testThreadAllcationMem() throws InterruptedException {
		for(int i=1; i<=200; i++) {
			(new MyThread()).start();
		}
		Thread.sleep(3600000);
	}
	
	@Test
	public void testThreadState() {
		ThreadPoolExecutor es = new ThreadPoolExecutor(40, 400, 1000, TimeUnit.MILLISECONDS,
	               new SynchronousQueue<Runnable>(),
	               new NamedThreadFactory("my-test", true));
	       for (int i = 0; i < 10; ++i) {

	           es.submit(new Thread() {

	               @Override
	               public void run() {
	                   System.out.println(System.currentTimeMillis());
	                   System.out.println(this.toString());
	                   try {
	                	   Map<String, String> request = new HashMap<>();
		               		request.put("request", super.getName());
		               		String json = JSON.toJSONString(request);
		               		String result = HttpClientUtility.getInstance().httpPostWithJSON("http://127.0.0.1:8080/rest/whyServcie/hello", json);
		               		System.out.println(result);
//	                       Thread.sleep(100000);
	                   } catch (Exception e) {
	                       
	                       e.printStackTrace();
	                   }
	               }
	           });
	       }
	       
	       try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	@Test
	public void testHttp() throws Exception {
		Map<String, String> request = new HashMap<>();
		request.put("hi", "whx");
		String json = JSON.toJSONString(request);
		String result = HttpClientUtility.getInstance().httpPostWithJSON("http://127.0.0.1:8080/rest/whyServcie/hello", json);
		System.out.println(result);
	}

}
