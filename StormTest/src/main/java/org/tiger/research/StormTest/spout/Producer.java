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

/**
 * @author WangHuiyuan
 *
 */
public class Producer extends BaseRichSpout {

	private static final long serialVersionUID = 5610513957690310953L;

	private SpoutOutputCollector collector;

	@Override
	public void nextTuple() {
		String[] str1 = new String[] { "aaa", "bbb", "ccc", "ddd" };
		String[] str2 = new String[] { "ee", "ff", "gg", "kk" };
		Random random = new Random();
		String word1 = str1[random.nextInt(str1.length)];
		String word2 = str2[random.nextInt(str2.length)];
		collector.emit(new Values(word1, word2));
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void open(Map arg0, TopologyContext arg1, SpoutOutputCollector arg2) {
		this.collector = arg2;
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		arg0.declare(new Fields("ppee", "ttt"));
	}

}
