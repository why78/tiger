/**
 *
 * Project Name:	javacore
 * File Name:	Main.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年2月1日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.jvm.memLeak;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangHuiyuan
 *
 */
public class Main {

	public static void main(String[] args) {
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		String codePrefix = "sh";
		int n = 1000;
//		Map<String, Map<String, Stock>> map = new HashMap<>();
		for(int j=0; j<n; j++) {
			Map<String, Stock> mapStocks = new HashMap<>();
			for(int i=0; i<n; i++) {
				Stock stock = new Stock();
				String code = codePrefix + i + j;
				stock.setCode(code);
				stock.setPrice(new Double(20 + i));
				mapStocks.put(code, stock);
			}
			Date date = new Date();
			String timestamp = String.valueOf(date.getTime());
			Cache.map.put(timestamp + j, mapStocks);
		}
//		for(Entry<String, Stock> entry : mapStocks.entrySet()) {
//			System.out.println(entry.getValue().getPrice());
//		}
		
		System.out.println("The cache's size: " + Cache.map.size());
		System.out.println("Loading is done!");
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
//		Cache.map.clear();
		int z = 0;
		List<String> lstKeys = new ArrayList<>();
		for(String key: Cache.map.keySet()) {
			lstKeys.add(key);
			z++;
			if(z>500)
				break;
		}
		for(String key : lstKeys) {
			Cache.map.remove(key);			
		}
		lstKeys.clear();
		lstKeys = null;
		System.out.println("The cache is cleared!");
		System.out.println("After clear, the cache's size: " + Cache.map.size());
		
		try {
			Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for(int j=0; j<200; j++) {
			Map<String, Stock> mapStocks = new HashMap<>();
			for(int i=0; i<n; i++) {
				Stock stock = new Stock();
				String code = codePrefix + i + j;
				stock.setCode(code);
				stock.setPrice(new Double(20 + i));
				mapStocks.put(code, stock);
			}
			Date date = new Date();
			String timestamp = String.valueOf(date.getTime());
			Cache.map.put(timestamp + j, mapStocks);
		}
		System.out.println("Loading again!");
		try {
			Thread.sleep(3600 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
