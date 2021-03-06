/**
 *
 * Project Name:	StormTest
 * File Name:	TestTopology.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月3日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.topology;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.apache.storm.topology.TopologyBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tiger.research.StormTest.bolt.Choice;
import org.tiger.research.StormTest.bolt.Writer;
import org.tiger.research.StormTest.spout.RabbitMQOneSpout;
import org.tiger.research.StormTest.utils.AppConfigurations;

import hbec.platform.storm.tools.metrics.HbecHttpForwardingMetricsConsumer;
import hbec.platform.storm.tools.metrics.MemoryPoolMetrics;


/**
 * @author WangHuiyuan
 *
 */
public class TestTopology {
	
	private final static Logger logger = LoggerFactory.getLogger(TestTopology.class);

	public static void main(String[] args) throws Exception {
		TestTopology topology = new TestTopology();

		String topoloyName = "HelloStorm";
		topology.createTopology(topoloyName);
	}
	
	public void createTopology(String topologyName) throws AlreadyAliveException, InvalidTopologyException, AuthorizationException {
		TopologyBuilder builder=new TopologyBuilder();
		
		RabbitMQOneSpout oneMqSpout =  RabbitMQOneSpout.newBuilder()
			.setMqHost(AppConfigurations.mqHost)
			.setMqPort(AppConfigurations.mqPort)
			.setMqUsername(AppConfigurations.mqUsername)
			.setMqPassword(AppConfigurations.mqPassword)
			.setMqHeartBeat(AppConfigurations.mqHeartBeat)
			.setExchangeName(AppConfigurations.oneMqExchangeName)
			.setQueueName(AppConfigurations.oneMqQueueName)
			.setDurable(true)
			.setAutoDelete(false)
			.setAutoAck(AppConfigurations.oneMqAutoAck)
			.setPrefetchCount(AppConfigurations.oneMpPrefetchCount)
			.setRoutingKey(AppConfigurations.oneMqRoutingKey)
			.setTransType(AppConfigurations.oneMqTransType)
			.setSlot(AppConfigurations.countTask).build();
		
//		builder.setSpout("1", new Producer(),1);
		builder.setSpout("1", oneMqSpout.getSpout(), 1).addConfigurations(oneMqSpout.getMqConsumerConfig().asMap());
		builder.setBolt("2", new Choice(), AppConfigurations.countBolt)
			.addConfigurations(AppConfigurations.getAllConfigAsMap())
			.setNumTasks(AppConfigurations.countTask)
			.shuffleGrouping("1");
		builder.setBolt("3", new Writer(), 1).allGrouping("2");
//		builder.setBolt("metricsBolt", new MetricsBolt(), 1);
		
		Config conf = new Config();
		conf.put("toplogyName", topologyName);
		conf.setDebug(false);
		conf.setNumWorkers(5);
//		conf.registerMetricsConsumer(org.apache.storm.metric.LoggingMetricsConsumer.class, 1);
//		conf.registerMetricsConsumer(org.apache.storm.metric.HttpForwardingMetricsConsumer.class, "http://localhost:15050", 1);
//		conf.registerMetricsConsumer(HbecHttpForwardingMetricsConsumer.class, "http://localhost:15050", 1);
		conf.registerMetricsConsumer(HbecHttpForwardingMetricsConsumer.class, AppConfigurations.getMetricsServer(), 1);
		logger.info("Metrics server is [{}]", AppConfigurations.getMetricsServer());
		
		Map<String,String> metrics = new HashMap<String,String>();
		metrics.put("mempool", MemoryPoolMetrics.class.getName());
		conf.put(Config.TOPOLOGY_WORKER_METRICS, metrics);
		conf.put(Config.TOPOLOGY_WORKER_CHILDOPTS, "-Xms512m -Xmx512m -XX:MaxDirectMemorySize=128m -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=2%ID% -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false  -Dcom.sun.management.jmxremote.ssl=false");
		conf.put(Config.TOPOLOGY_WORKER_MAX_HEAP_SIZE_MB, 512);
		conf.put(Config.WORKER_HEAP_MEMORY_MB, 512);
//		conf.put(Config.TOPOLOGY_COMPONENT_RESOURCES_ONHEAP_MEMORY_MB, 4096);
//		conf.put(Config.TOPOLOGY_COMPONENT_RESOURCES_OFFHEAP_MEMORY_MB, 1024);
		
//		conf.put(ConfigUtils.key_isCustomDebug, AppConfigurations.isCustomDebug);
		
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology(topologyName, conf, builder.createTopology());
		
//		if (topologyName != null) {
//            conf.setNumWorkers(AppConfigurations.countWorker);
//            StormSubmitter.submitTopologyWithProgressBar(topologyName, conf, builder.createTopology());
//
//        } else {
//            conf.setMaxTaskParallelism(50);
//
//            LocalCluster cluster = new LocalCluster();
//            cluster.submitTopology("localDebug", conf, builder.createTopology());
//        }
	}
}
