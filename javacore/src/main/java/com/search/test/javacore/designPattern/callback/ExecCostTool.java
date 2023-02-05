/**
 *
 * Project Name:	javacore
 * File Name:	ExecCostTool.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2022年7月31日
 * Version:		1.0
 * Remark：
 */
package com.search.test.javacore.designPattern.callback;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;

/**
 * @author WangHuiyuan
 *
 */
public class ExecCostTool {
	
	private final static Logger logger = LoggerFactory.getLogger(ExecCostTool.class);

	public void call(Callback callback) {
		Stopwatch stopwatch = Stopwatch.createStarted();
		try {
			callback.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info("Cost: {} ms!", stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));
	}
}
