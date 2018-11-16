/**
 *
 * Project Name:	StormTest
 * File Name:	BaseIBuilder.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月5日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.spout;

import org.tiger.research.StormTest.spout.RabbitMQOneSpout.RabbitMqOneSpoutBuilder;

/**
 * @author WangHuiyuan
 *
 */
public abstract class BaseBuilder<T> implements IBuilder<T> {

	protected String spoutName;
	protected String exchangeName;
	protected String queueName;
	protected String routingKey;
	protected int messageTtl;
	protected String transType;
	protected boolean durable;
	protected boolean autoDelete;
	protected boolean autoAck;
	protected int prefetchCount;
	
	protected String mqHost;
	protected int mqPort;
	protected String mqUsername;
	protected String mqPassword;
	protected int mqHeartBeat;
	
	protected int slot;

	public IBuilder<T> setMqHost(String mqHost) {
		this.mqHost = mqHost;
		return this;
	}
	public IBuilder<T> setMqPort(int mqPort) {
		this.mqPort = mqPort;
		return this;
	}

	public IBuilder<T> setMqUsername(String mqUsername) {
		this.mqUsername = mqUsername;
		return this;
	}

	public IBuilder<T> setMqPassword(String mqPassword) {
		this.mqPassword = mqPassword;
		return this;
	}

	public IBuilder<T> setMqHeartBeat(int mqHeartBeat) {
		this.mqHeartBeat = mqHeartBeat;
		return this;
	}

	public IBuilder<T> setSpoutName(String spoutName) {
		this.spoutName = spoutName;
		return this;
	}

	public IBuilder<T> setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
		return this;
	}

	public IBuilder<T> setQueueName(String queueName) {
		this.queueName = queueName;
		return this;
	}

	public IBuilder<T> setRoutingKey(String routingKey) {
		this.routingKey = routingKey;
		return this;
	}

	public IBuilder<T> setMessageTtl(int messageTtl) {
		this.messageTtl = messageTtl;
		return this;
	}

	public IBuilder<T> setTransType(String transType) {
		this.transType = transType;
		return this;
	}

	public IBuilder<T> setDurable(boolean durable) {
		this.durable = durable;
		return this;
	}

	public IBuilder<T> setAutoDelete(boolean autoDelete) {
		this.autoDelete = autoDelete;
		return this;
	}

	public IBuilder<T> setPrefetchCount(int prefetchCount) {
		this.prefetchCount = prefetchCount;
		return this;
	}

	public IBuilder<T> setAutoAck(boolean autoAck) {
		this.autoAck = autoAck;
		return this;
	}
	
	public IBuilder<T> setSlot(int slot) {
		this.slot = slot;
		return this;
	}
}
