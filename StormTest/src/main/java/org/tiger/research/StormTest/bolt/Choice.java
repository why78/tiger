/**
 *
 * Project Name:	StormTest
 * File Name:	Choice.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月3日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.bolt;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tiger.research.StormTest.model.One;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import hbec.platform.storm.tools.redis.RedisHelperV1;
import hbec.platform.storm.tools.redis.RedisHelperV3;

/**
 * @author WangHuiyuan
 *
 */
public class Choice extends BaseBasicBolt {

	private static final long serialVersionUID = 7328667034075473879L;

	private static final Logger LOGGER = LoggerFactory.getLogger(Choice.class);

	public static final String boltName = Choice.class.getSimpleName();

	private LinkedHashMap<String, One> oneCache = new LinkedHashMap<>();
	
	private String cacheId;
	
	private static final Gson gson =  new Gson();

	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		super.prepare(stormConf, context);

		RedisHelperV1.init(stormConf);
		
		cacheId = String.valueOf(stormConf.get("one.cache.keyPrefix")) + context.getThisTaskIndex();

		loadRedisDataToCache();
	}
	
	private void loadRedisDataToCache() {
        Collection<String> csl = RedisHelperV1.hgetallByKey(cacheId).values();
        Type token = new TypeToken<One>() { }.getType();
        One one;
        for (String obj : csl) {
            one = gson.fromJson(obj, token);
            oneCache.put(one.getName(), one);
        }
        LOGGER.info("==========cacheId is ========" + cacheId + " , init Cache size is =========");
        LOGGER.info("oneCache size is : " + oneCache.size());
        LOGGER.info("=========================================================================");
	}

	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		One one = (One) input.getValue(0);
		if(oneCache.isEmpty()) {
			oneCache.put(one.getName(), one);
			RedisHelperV1.hset(cacheId, one.getName(), JSON.toJSONString(one));
		} else {
			for(String name : oneCache.keySet()) {
	
				if (one.getName().equals(name)) {
					List<Object> tuple = new ArrayList<>();
					tuple.add(one);
					collector.emit(tuple);
				}else {
					oneCache.put(one.getName(), one);
					RedisHelperV1.hset(cacheId, one.getName(), JSON.toJSONString(one));
				}
			}
		}
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("One"));

	}

}
