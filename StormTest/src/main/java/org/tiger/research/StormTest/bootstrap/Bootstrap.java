/**
 *
 * Project Name:	StormTest
 * File Name:	Bootstrap.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月3日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.bootstrap;

import org.tiger.research.StormTest.topology.TestTopology;
import org.tiger.research.StormTest.utils.AppConfigurations;

import hbec.platform.storm.tools.config.ConfigurationManager;

/**
 * @author WangHuiyuan
 *
 */
public class Bootstrap {


	public static void main(String[] args) {
		ConfigurationManager.loadConfig("setup.cfg");
		AppConfigurations.loadConfig();
		
		TestTopology topology = new TestTopology();
		topology.createTopology();
	}

}
