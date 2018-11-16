/**
 *
 * Project Name:	StormTest
 * File Name:	IIBuilder.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月5日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.spout;


/**
 * @author WangHuiyuan
 *
 */
public interface IBuilder<T> {

	public T build();
	
	public IBuilder<T> setMqHost(String mqHost);
	
	public IBuilder<T> setMqPort(int mqPort);

	public IBuilder<T> setMqUsername(String mqUsername);

	public IBuilder<T> setMqPassword(String mqPassword);

	public IBuilder<T> setMqHeartBeat(int mqHeartBeat);
	
	public IBuilder<T> setSpoutName(String spoutName);

	public IBuilder<T> setExchangeName(String exchangeName);

	public IBuilder<T> setQueueName(String queueName);

	public IBuilder<T> setRoutingKey(String routingKey);

	public IBuilder<T> setMessageTtl(int messageTtl);

	public IBuilder<T> setTransType(String transType);

	public IBuilder<T> setDurable(boolean durable);

	public IBuilder<T> setAutoDelete(boolean autoDelete);

	public IBuilder<T> setPrefetchCount(int prefetchCount);

	public IBuilder<T> setAutoAck(boolean autoAck);
	
	public IBuilder<T> setSlot(int slot);
}
