/**
 *
 * Project Name:	javacore
 * File Name:	TestCommons1.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年12月5日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.common;


import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author WangHuiyuan
 *
 */
public class TestBigMemOldGen {
	
	private final static Logger logger = LoggerFactory.getLogger(TestBigMemOldGen.class);

	@Before
	public void setUp() throws Exception {
	}

	Map<Integer, String> map;
	
	boolean stop = false;
	
	@Test
	public void test() {
		map = new HashMap<>();
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					if(stop)
						break;
					try {
	//					logger.info("======The size of map is {}", map.size());
						logger.info("size[{}]--{}", map.size(), JSON.toJSONString(map));
					
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
//		Map<Integer, String> tmpmap = new HashMap<>();
		for(int i=0; i<5; i++){
			map.put(i, String.valueOf(i));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		map = tmpmap;

		logger.info("{}", JSON.toJSONString(map));
		
//		for(int i=0; i<5; i++){
//			logger.info("{}--{}", i, map.get(i));
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
//		tmpmap = new HashMap<>();
		map.clear();
		for(int i=0; i<5; i++){
			map.put(i, String.valueOf(i+10));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		map = tmpmap;
		
		logger.info("{}", JSON.toJSONString(map));
//		for(int i=0; i<5; i++){
//			logger.info("{}--{}", i, map.get(i));
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
		this.stop = true;
		
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test1() {
		map = new HashMap<>();
		
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					if(stop)
						break;
					try {
						logger.info("size[{}]--{}", map.size(), JSON.toJSONString(map));
					
						Thread.sleep(10);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		Map<Integer, String> tmpmap = new HashMap<>();
		for(int i=0; i<5; i++){
			tmpmap.put(i, String.valueOf(i));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		map = tmpmap;

		logger.info("{}", JSON.toJSONString(map));
				
		tmpmap = new HashMap<>();
//		map.clear();
		for(int i=0; i<5; i++){
			tmpmap.put(i, String.valueOf(i+10));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		map = tmpmap;
		
		logger.info("{}", JSON.toJSONString(map));
		
		tmpmap = null;

//		this.stop = true;
		
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
