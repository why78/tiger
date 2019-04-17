/**
 *
 * Project Name:	StormTest
 * File Name:	HbecHttpForwardingMetricsConsumer.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年4月17日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.metrics;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import org.apache.storm.metric.api.IMetricsConsumer;
import org.apache.storm.serialization.KryoValuesSerializer;
import org.apache.storm.task.IErrorReporter;
import org.apache.storm.task.TopologyContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.esotericsoftware.kryo.io.Output;

/**
 * @author WangHuiyuan
 *
 */
public class HbecHttpForwardingMetricsConsumer implements IMetricsConsumer {
	
	private final static Logger logger = LoggerFactory.getLogger(HbecHttpForwardingMetricsConsumer.class);

	private transient URL _url; 
    private transient IErrorReporter _errorReporter;
    private transient KryoValuesSerializer _serializer;
    private transient String _topologyName;

    @Override
    public void prepare(Map stormConf, Object registrationArgument, TopologyContext context, IErrorReporter errorReporter) { 
        try {
        	_topologyName = (String) stormConf.get("toplogyName");
            _url = new URL((String)registrationArgument);
            _errorReporter = errorReporter;
            _serializer = new KryoValuesSerializer(stormConf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleDataPoints(TaskInfo taskInfo, Collection<DataPoint> dataPoints) {
        try {
            HttpURLConnection con = (HttpURLConnection)_url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            Output out = new Output(con.getOutputStream());
            _serializer.serializeInto(Arrays.asList(taskInfo, dataPoints, _topologyName), out);
            out.flush();
            out.close();
            //The connection is not sent unless a response is requested
            int response = con.getResponseCode();
        } catch (Exception e) {
        	logger.error("Sending metrics to server occurred errors: {}", e.getMessage());
//            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanup() { }
    
}
