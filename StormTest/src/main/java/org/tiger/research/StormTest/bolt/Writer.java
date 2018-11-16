/**
 *
 * Project Name:	StormTest
 * File Name:	Writer.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月3日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.bolt;

import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.tiger.research.StormTest.model.One;

import com.alibaba.fastjson.JSON;

/**
 * @author WangHuiyuan
 *
 */
public class Writer extends BaseBasicBolt {


	private static final long serialVersionUID = -4131344359995248833L;


	@Override
	public void execute(Tuple input, BasicOutputCollector collector) {
		One one = (One)input.getValue(0);		
		String json = JSON.toJSONString(one);
		System.out.println(json);
		
	}


	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

}
