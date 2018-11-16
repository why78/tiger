/**
 *
 * Project Name:	StormTest
 * File Name:	Producer.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月1日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.spout;

import java.util.Map;
import java.util.Random;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.tiger.research.StormTest.model.One;

/**
 * @author WangHuiyuan
 *
 */
public class Producer extends BaseRichSpout {

	private static final long serialVersionUID = 5610513957690310953L;

	private SpoutOutputCollector collector;

	@Override
	public void nextTuple() {
		Random random = new Random();
		One one = new One();
		one.setName("why");
		one.setAge(random.nextInt(90));
		collector.emit(new Values(one, one.getAge()));		

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		One two = new One();
		two.setName("whx");
		two.setAge(random.nextInt(90));
		collector.emit(new Values(two, two.getAge()));	
	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		this.collector = arg2;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("one", "age"));
	}

}
