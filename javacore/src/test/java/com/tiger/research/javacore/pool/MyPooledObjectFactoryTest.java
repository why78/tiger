/**
 *
 * Project Name:	javacore
 * File Name:	MyPooledObjectFactoryTest.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年10月28日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.pool2.impl.AbandonedConfig;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;


/**
 * @author WangHuiyuan
 *
 */
public class MyPooledObjectFactoryTest {

	/***********************************************
	* @Title: setUp
	* @Description: TODO
	* @throws java.lang.Exception 
	*/

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        poolConfig.setMinIdle(2);
        poolConfig.setMaxIdle(30);
        poolConfig.setMaxTotal(50);
        poolConfig.setMaxWaitMillis(30000);
        // destroy broken connections immediately
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setSoftMinEvictableIdleTimeMillis(60000);
        poolConfig.setTimeBetweenEvictionRunsMillis(1100);
        poolConfig.setJmxNamePrefix("objPool");
        poolConfig.setNumTestsPerEvictionRun(30);
		
		MyPooledObjectFactory objFactory = new MyPooledObjectFactory();
		GenericObjectPool<MyObject> objPool = new GenericObjectPool<>(objFactory, poolConfig);
		
		AbandonedConfig abandonedConfig = new AbandonedConfig();
		abandonedConfig.setRemoveAbandonedOnMaintenance(true); //在Maintenance的时候检查是否有泄漏
		abandonedConfig.setRemoveAbandonedOnBorrow(true); //borrow 的时候检查泄漏
		abandonedConfig.setRemoveAbandonedTimeout(10); //如果一个对象borrow之后10秒还没有返还给pool，认为是泄漏的对象
		
		objPool.setAbandonedConfig(abandonedConfig);
		
		for(int i=0; i<100; i++){
			this.validatePool(objPool);
			
			Thread.sleep(6000);
		}
		
		
	}

	private void validatePool(GenericObjectPool<MyObject> objPool) throws Exception {
		System.out.println("------Started");
		int repeatedCount = 0;
		Map<Integer, MyObject> map = new TreeMap<Integer, MyObject>();
		int n = 0;
//		System.out.printf("getNumIdle %d", objPool.getNumIdle());
//		System.out.println();
		for(int i = 0; i<35; i++){
			MyObject one;
			try{
				one = objPool.borrowObject();
//				System.out.printf("The destroyed count by BorrowValidationCount is %d, Created count is %d", objPool.getDestroyedByBorrowValidationCount(), objPool.getCreatedCount());
//				System.out.println();
				n++;
			}catch(Exception ex) {
				ex.printStackTrace();
				break;
			}
			if(map.containsKey(one.getId())){
				repeatedCount++;
				System.out.printf("******The id[%d] exists", one.getId());
				System.out.println();
			} else {
				map.put(one.getId(), one);
			}
		}
		System.out.printf("======The repeated count is %d", repeatedCount);
		System.out.println();
		System.out.println(map.size());
//		System.out.printf("getBorrowedCount %d", objPool.getBorrowedCount());
//		System.out.println();
		for(Integer id : map.keySet()){
//			System.out.println(JSON.toJSONString(map.get(id)));
			objPool.returnObject(map.get(id));
		}
//		System.out.printf("getReturnedCount %d", objPool.getReturnedCount());
//		System.out.println();
	}
}
