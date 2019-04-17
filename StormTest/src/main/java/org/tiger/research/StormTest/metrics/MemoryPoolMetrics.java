/**
 *
 * Project Name:	StormTest
 * File Name:	MemoryPoolMetrics.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年4月11日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.metrics;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.storm.metric.api.IMetric;

import clojure.lang.AFn;
import clojure.lang.IFn;

/**
 * @author WangHuiyuan
 *
 */
public class MemoryPoolMetrics implements IMetric {
		
	final List<MemoryPoolMXBean> lstMemPoolMxBeans = ManagementFactory.getMemoryPoolMXBeans();
    public MemoryPoolMetrics() {
    	_getUsage = new AFn() {
            public Object invoke() {
            	        	
                return lstMemPoolMxBeans;
            }
        };
    }
    
    public MemoryPoolMetrics(String topologyName,final List<MemoryPoolMXBean> lstMemPoolMxBeans) {
    	_getUsage = new AFn() {
            public Object invoke() {
            	        	
                return lstMemPoolMxBeans;
            }
        };
    }
    
    IFn _getUsage;

	@Override
	public Object getValueAndReset() {
		List<MemoryPoolMXBean> lstMemPool = (List<MemoryPoolMXBean>)_getUsage.invoke();
		Map<String, Map<String, Long>> mapPool = new HashMap<>();
		for(MemoryPoolMXBean bean : lstMemPool) {
			
			MemoryUsage memUsage = bean.getUsage();
			Map<String, Long> one = new HashMap<>();
			one.put("maxBytes", memUsage.getMax());
			one.put("committedBytes", memUsage.getCommitted());
			one.put("initBytes", memUsage.getInit());
			one.put("usedBytes", memUsage.getUsed());
			one.put("virtualFreeBytes", memUsage.getMax() - memUsage.getUsed());
			one.put("unusedBytes", memUsage.getCommitted() - memUsage.getUsed());
			mapPool.put(bean.getName(), one);
		}
        return mapPool;
	}


}
