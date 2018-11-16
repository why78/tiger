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

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.tiger.research.StormTest.bolt.Choice;
import org.tiger.research.StormTest.bolt.Writer;
import org.tiger.research.StormTest.spout.IBuilder;
import org.tiger.research.StormTest.spout.Producer;
import org.tiger.research.StormTest.spout.RabbitMQOneSpout;
import org.tiger.research.StormTest.utils.AppConfigurations;

/**
 * @author WangHuiyuan
 *
 */
public class TestTopology {

	public static void main(String[] args) throws Exception {
		TestTopology topology = new TestTopology();
		topology.createTopology();
	}
	
	public void createTopology() {
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
		
		Config con=new Config();
		con.setDebug(false);
		con.setNumWorkers(5);
		
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("HelloStorm", con, builder.createTopology());
	}
}
