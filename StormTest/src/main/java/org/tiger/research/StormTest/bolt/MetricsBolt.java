/**
 *
 * Project Name:	StormTest
 * File Name:	MetricsBolt.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年4月12日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.bolt;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.List;
import java.util.Map;

import org.apache.storm.Config;
import org.apache.storm.task.IBolt;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.IBasicBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;
import org.tiger.research.StormTest.metrics.MemoryPoolMetrics;

import clojure.lang.RT;

/**
 * @author WangHuiyuan
 *
 */
public class MetricsBolt implements IBasicBolt {


	private static final long serialVersionUID = -7181551399599053056L;
	
	private static boolean _prepareWasCalled = false;
	
	@Override	
    public void prepare(final Map stormConf, TopologyContext context) {
        if(_prepareWasCalled && !"local".equals(stormConf.get(Config.STORM_CLUSTER_MODE))) {
            throw new RuntimeException("A single worker should have 1 SystemBolt instance.");
        }
        _prepareWasCalled = true;

        int bucketSize = RT.intCast(stormConf.get(Config.TOPOLOGY_BUILTIN_METRICS_BUCKET_SIZE_SECS));
        
        final List<MemoryPoolMXBean> lstMemPoolMxBeans = ManagementFactory.getMemoryPoolMXBeans();
        
        context.registerMetric("mempool", new MemoryPoolMetrics("why-test-storm", lstMemPoolMxBeans), bucketSize);

        
    }
	
	@Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        throw new RuntimeException("Non-system tuples should never be sent to __system bolt.");
    }

    @Override
    public void cleanup() {
    }

	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IComponent#declareOutputFields(org.apache.storm.topology.OutputFieldsDeclarer)
	 */
	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.apache.storm.topology.IComponent#getComponentConfiguration()
	 */
	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
