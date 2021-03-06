/**
 *
 * Project Name:	StormTest
 * File Name:	AppConfigurations.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月3日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.utils;

import java.util.HashMap;
import java.util.Map;

import hbec.platform.storm.tools.config.ConfigUtils;
import hbec.platform.storm.tools.config.ConfigurationManager;
import hbec.platform.storm.tools.rabbitMQ.config.ConsumerConfig;

/**
 * @author WangHuiyuan
 *
 */
public class AppConfigurations {

	// MQ properties
    public static String mqHost = "10.0.30.65";
    public static int mqPort = 5672;
    public static String market_mqHost = "10.0.30.65";
    public static int market_mqPort = 5672;
    public static String mqUsername = "guest";
    public static String mqPassword = "guest";
    public static int mqInterval = 10000;
    public static int oneMpPrefetchCount = 1;
    /**
     * Unit: second
     */
    public static int mqHeartBeat = 10;
	
    public static String oneMqQueueName = "testStorm_one_queue";
    public static String oneMqExchangeName = "testStorm_one_exchange";
    public static String oneMqRoutingKey = "one";
    public static String oneMqTransType = "direct";
    public static boolean oneMqAutoAck = false;
    
    // Redis properties
    public static String redisPoolHost = "10.0.30.66";
    public static int redisPoolPort = 6379;
    public static int redisPoolMaxTotal = 50;
    public static int redisPoolMaxIdle = 5;
    public static boolean redisPoolTestOnReturn = true;
    public static boolean redisPoolTestOnBorrow = true;
    
	public static int countTask = 3;
	public static int countBolt = 3;
	public static int countWorker = 3;
	public static String metricsServerHost = "127.0.0.1";
	public static int metricsServerPort = 15015;
	public static String getMetricsServer() {
		return "http://" + metricsServerHost + ":" + String.valueOf(metricsServerPort);
	}
	
//	public static boolean isCustomDebug = false;
    
	public static void loadConfig() {
        mqHost = ConfigurationManager.getProperty("mq.host", mqHost);
        market_mqHost=ConfigurationManager.getProperty("market_mq.host", market_mqHost);
        mqPort = ConfigurationManager.getIntProperty("mq.port", mqPort);
        market_mqHost = ConfigurationManager.getProperty("market_mq.host", mqHost);
        market_mqPort = ConfigurationManager.getIntProperty("market_mq.port", mqPort);
        mqUsername = ConfigurationManager.getProperty("mq.username", mqUsername);
        mqPassword = ConfigurationManager.getProperty("mq.password", mqPassword);
        mqInterval = ConfigurationManager.getIntProperty("mq.interval", mqInterval);
        mqHeartBeat = ConfigurationManager.getIntProperty("mq.heartbeat", mqHeartBeat);
        
        oneMpPrefetchCount = ConfigurationManager.getIntProperty("mp.onePrefetchCount", oneMpPrefetchCount);
        oneMqQueueName = ConfigurationManager.getProperty("mq.oneQueueName", oneMqQueueName);
        oneMqExchangeName = ConfigurationManager.getProperty("mq.oneExchangeName", oneMqExchangeName);
        oneMqRoutingKey = ConfigurationManager.getProperty("mq.oneRoutingKey", oneMqRoutingKey);
        oneMqTransType = ConfigurationManager.getProperty("mq.oneTransType", oneMqTransType);
        oneMqAutoAck = ConfigurationManager.getBooleanProperty("mq.one_autoack", oneMqAutoAck);        

        countBolt = ConfigurationManager.getIntProperty("storm.bolt.count", countBolt);
        countWorker = ConfigurationManager.getIntProperty("storm.worker.count", countWorker);
        countTask = ConfigurationManager.getIntProperty("storm.task.count", countTask);

        redisPoolHost = ConfigurationManager.getProperty("redis.pool.host", redisPoolHost);
        redisPoolPort = ConfigurationManager.getIntProperty("redis.pool.port", redisPoolPort);
        redisPoolMaxTotal = ConfigurationManager.getIntProperty("redis.pool.maxTotal", redisPoolMaxTotal);
        redisPoolMaxIdle = ConfigurationManager.getIntProperty("redis.pool.maxIdle", redisPoolMaxIdle);
        redisPoolTestOnReturn = ConfigurationManager.getBooleanProperty("redis.pool.testOnReturn",
                redisPoolTestOnReturn);
        redisPoolTestOnBorrow = ConfigurationManager.getBooleanProperty("redis.pool.testOnBorrow",
                redisPoolTestOnBorrow);

        metricsServerHost = ConfigurationManager.getProperty("metrics.server.host", mqHost);
        metricsServerPort = ConfigurationManager.getIntProperty("metrics.server.port", metricsServerPort);
        
//        isCustomDebug = ConfigurationManager.getBooleanProperty(ConfigUtils.key_isCustomDebug,
//        		isCustomDebug);
    }
	
	public static Map<String, Object> getAllConfigAsMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.putAll(ConfigurationManager.getAllConfig());
        return map;
    }
}
