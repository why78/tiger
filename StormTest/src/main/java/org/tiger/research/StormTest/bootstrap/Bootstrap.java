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

import org.apache.logging.log4j.util.Strings;
import org.apache.storm.generated.AlreadyAliveException;
import org.apache.storm.generated.AuthorizationException;
import org.apache.storm.generated.InvalidTopologyException;
import org.tiger.research.StormTest.topology.TestTopology;
import org.tiger.research.StormTest.utils.AppConfigurations;

import hbec.platform.storm.tools.config.ConfigurationManager;

/**
 * @author WangHuiyuan
 *
 */
public class Bootstrap {


	public static void main(String[] args) throws Exception {
		ConfigurationManager.loadConfig("setup.cfg");
		AppConfigurations.loadConfig();
		
		TestTopology topology = new TestTopology();
		String topologyName = null;
		if(Strings.isBlank(args[0])){
			throw new RuntimeException("No topology name!!!");
		} else {
			topologyName = args[0];
		}
		topology.createTopology(topologyName);
	}

}
