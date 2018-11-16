/**
 *
 * Project Name:	StormTest
 * File Name:	OneStreamGrouping.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月5日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.grouping;

import java.util.Collections;
import java.util.List;

import org.apache.storm.generated.GlobalStreamId;
import org.apache.storm.grouping.CustomStreamGrouping;
import org.apache.storm.task.WorkerTopologyContext;

/**
 * @author WangHuiyuan
 *
 */
public class OneStreamGrouping implements CustomStreamGrouping {

	private static final long serialVersionUID = 8983165344677092416L;
	
	private List<Integer> tasks;

	@Override
	public void prepare(WorkerTopologyContext context, GlobalStreamId stream, List<Integer> targetTasks) {
		this.tasks = targetTasks;
		
	}


	@Override
	public List<Integer> chooseTasks(int taskId, List<Object> values) {
		return Collections.singletonList(tasks.get((int) values.get(1)));
	}

}
