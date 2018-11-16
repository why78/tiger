/**
 *
 * Project Name:	StormTest
 * File Name:	RabbitMQSpout.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月3日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.spout;

import java.util.HashMap;
import java.util.Map;

import org.apache.storm.topology.IRichSpout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tiger.research.StormTest.schema.OneScheme;
import org.tiger.research.StormTest.utils.AppConfigurations;
import org.tiger.research.StormTest.utils.mq.BaseMqDeclarator;

import com.rabbitmq.client.ConnectionFactory;

import hbec.platform.storm.tools.rabbitMQ.Declarator;
import hbec.platform.storm.tools.rabbitMQ.RabbitMQSpout;
import hbec.platform.storm.tools.rabbitMQ.config.ConnectionConfig;
import hbec.platform.storm.tools.rabbitMQ.config.ConsumerConfig;
import hbec.platform.storm.tools.rabbitMQ.config.ConsumerConfigBuilder;

/**
 * @author WangHuiyuan
 *
 */
public class RabbitMQOneSpout {

	private final static Logger logger = LoggerFactory.getLogger(RabbitMQOneSpout.class);
	
    public static final String spoutName = RabbitMQOneSpout.class.getSimpleName();

    private ConnectionConfig mqConnConfig;
    private Declarator       mqDecl;
    private ConsumerConfig   mqConsumerConfig;
    
    // rabbit mq 配置信息
  	private String mqExchangeName;
  	private String mqQueueName;
  	private String mqRoutingKey;
  	private int mqMessageTtl;
  	private String mqTransType;
  	private boolean mqDurable;
  	private boolean mqAutoDelete;
  	private boolean mqAutoAck;
  	private int mqPrefetchCount;

	private String mqHost;
  	private int mqPort;
  	private String mqUsername;
  	private String mqPassword;
  	private int mqHeartBeat;
  	
  	private int oneBoltTask;

	private IRichSpout spout;

	private void init() {
//    	mqConnConfig = new ConnectionConfig(AppConfigurations.mqHost, AppConfigurations.mqPort,
//                AppConfigurations.mqUsername, AppConfigurations.mqPassword,
//                ConnectionFactory.DEFAULT_VHOST, AppConfigurations.mqHeartBeat);
//        Map<String, Object> args = new HashMap<String, Object>();
//        mqDecl = new BaseMqDeclarator(AppConfigurations.oneMqExchangeName, AppConfigurations.oneMqQueueName,
//                AppConfigurations.oneMqRoutingKey, AppConfigurations.oneMqTransType, true, false, args);
//        mqConsumerConfig = new ConsumerConfigBuilder().connection(mqConnConfig).
//                queue(AppConfigurations.oneMqQueueName).autoAck(AppConfigurations.oneMqAutoAck).
//                prefetch(AppConfigurations.mpPrefetchCount).requeueOnFail().build();
//        spout = new RabbitMQSpout(new OneScheme(AppConfigurations.countTask), mqDecl);
        
        mqConnConfig = new ConnectionConfig(this.mqHost, this.mqPort,
        		this.mqUsername, this.mqPassword,
                ConnectionFactory.DEFAULT_VHOST, this.mqHeartBeat);
        Map<String, Object> args = new HashMap<String, Object>();
        mqDecl = new BaseMqDeclarator(this.mqExchangeName, this.mqQueueName,
        		this.mqRoutingKey, this.mqTransType, this.mqDurable, this.mqAutoDelete, args);
        mqConsumerConfig = new ConsumerConfigBuilder().connection(mqConnConfig).
                queue(this.mqQueueName).autoAck(this.mqAutoAck).
                prefetch(this.mqPrefetchCount).requeueOnFail().build();
        spout = new RabbitMQSpout(new OneScheme(this.oneBoltTask), mqDecl);

    }
    
    public RabbitMQOneSpout() {
    	
    }
    
    public IRichSpout getSpout() {
		return spout;
	}
    
    public ConsumerConfig getMqConsumerConfig() {
		return mqConsumerConfig;
	}
    
    public static RabbitMqOneSpoutBuilder newBuilder() {
    	return new RabbitMqOneSpoutBuilder();
    }


  	public ConnectionConfig getMqConnConfig() {
		return mqConnConfig;
	}

	public void setMqConnConfig(ConnectionConfig mqConnConfig) {
		this.mqConnConfig = mqConnConfig;
	}

	public Declarator getMqDecl() {
		return mqDecl;
	}

	public void setMqDecl(Declarator mqDecl) {
		this.mqDecl = mqDecl;
	}

	public String getMqExchangeName() {
		return mqExchangeName;
	}

	public void setMqExchangeName(String mqExchangeName) {
		this.mqExchangeName = mqExchangeName;
	}

	public String getMqQueueName() {
		return mqQueueName;
	}

	public void setMqQueueName(String mqQueueName) {
		this.mqQueueName = mqQueueName;
	}

	public String getMqRoutingKey() {
		return mqRoutingKey;
	}

	public void setMqRoutingKey(String mqRoutingKey) {
		this.mqRoutingKey = mqRoutingKey;
	}

	public int getMqMessageTtl() {
		return mqMessageTtl;
	}

	public void setMqMessageTtl(int mqMessageTtl) {
		this.mqMessageTtl = mqMessageTtl;
	}

	public String getMqTransType() {
		return mqTransType;
	}

	public void setMqTransType(String mqTransType) {
		this.mqTransType = mqTransType;
	}

	public boolean isMqDurable() {
		return mqDurable;
	}

	public void setMqDurable(boolean mqDurable) {
		this.mqDurable = mqDurable;
	}

	public boolean isMqAutoDelete() {
		return mqAutoDelete;
	}

	public void setMqAutoDelete(boolean mqAutoDelete) {
		this.mqAutoDelete = mqAutoDelete;
	}

	public boolean isMqAutoAck() {
		return mqAutoAck;
	}

	public void setMqAutoAck(boolean mqAutoAck) {
		this.mqAutoAck = mqAutoAck;
	}

	public int getMqPrefetchCount() {
		return mqPrefetchCount;
	}

	public void setMqPrefetchCount(int mqPrefetchCount) {
		this.mqPrefetchCount = mqPrefetchCount;
	}

	public String getMqHost() {
		return mqHost;
	}

	public void setMqHost(String mqHost) {
		this.mqHost = mqHost;
	}

	public int getMqPort() {
		return mqPort;
	}

	public void setMqPort(int mqPort) {
		this.mqPort = mqPort;
	}

	public String getMqUsername() {
		return mqUsername;
	}

	public void setMqUsername(String mqUsername) {
		this.mqUsername = mqUsername;
	}

	public String getMqPassword() {
		return mqPassword;
	}

	public void setMqPassword(String mqPassword) {
		this.mqPassword = mqPassword;
	}

	public int getMqHeartBeat() {
		return mqHeartBeat;
	}

	public void setMqHeartBeat(int mqHeartBeat) {
		this.mqHeartBeat = mqHeartBeat;
	}

	public void setMqConsumerConfig(ConsumerConfig mqConsumerConfig) {
		this.mqConsumerConfig = mqConsumerConfig;
	}

	public int getOneBoltTask() {
		return oneBoltTask;
	}

	public void setOneBoltTask(int oneBoltTask) {
		this.oneBoltTask = oneBoltTask;
	}
    
    public final static class RabbitMqOneSpoutBuilder extends BaseBuilder<RabbitMQOneSpout> {


		private RabbitMqOneSpoutBuilder() {
		}

		

		@Override
		public RabbitMQOneSpout build() {
			RabbitMQOneSpout spout = new RabbitMQOneSpout();
			spout.setMqHost(this.mqHost);
			spout.setMqPort(this.mqPort);
			spout.setMqUsername(this.mqUsername);
			spout.setMqPassword(this.mqPassword);
			spout.setMqHeartBeat(this.mqHeartBeat);
			
			spout.setMqExchangeName(this.exchangeName);
			spout.setMqQueueName(this.queueName);
			spout.setMqRoutingKey(this.routingKey);
			spout.setMqAutoAck(this.autoAck);
			spout.setMqAutoDelete(this.autoDelete);
			spout.setMqDurable(this.durable);
			spout.setMqMessageTtl(this.messageTtl);
			spout.setMqTransType(this.transType);
			spout.setMqPrefetchCount(this.prefetchCount);
			
			spout.setOneBoltTask(this.slot);
			spout.init();
			return spout;
		}
    }
	
}
