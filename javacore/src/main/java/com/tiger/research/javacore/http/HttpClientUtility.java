/**
 *
 * Project Name:	hbec-platform-logCenter-log4c
 * File Name:	HttpClientUtility.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2017年5月10日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.http;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author WangHuiyuan
 *
 */
public class HttpClientUtility {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpClientUtility.class);
	
	private static final String APPLICATION_JSON       = "application/json";

	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";

	private static final int _defaultMaxPerRoute = 1;
	private static final int _defaultMaxTotal = 200;
	private static final int _defaultTimeout = 3000000; // unit: ms
	
	private Object lock = new Object();
	
	private static HttpClientUtility instance = null;
	
	private HttpClientUtility(){
		this.init();
	}
	
	public static synchronized HttpClientUtility getInstance(){
		
		if(instance == null)
			instance = new HttpClientUtility();
		return instance;
	}
	
	private CloseableHttpClient httpClient;
	
	private int getDefaultMaxPerRoute(){
		return _defaultMaxPerRoute;
	}
	
	private int getDefaultMaxTotal(){
		return  _defaultMaxTotal;
	}
	
	private int getDefaultTimeout(){
		return _defaultTimeout;
	}
	
	
	private void init() {
        if (httpClient == null) {
            synchronized (lock) {
                if (httpClient == null) {
                    try {
                    	logger.info("Http client is initializing...");
                        // 创建httpclient连接池
                        PoolingHttpClientConnectionManager httpClientConnectionManager = new PoolingHttpClientConnectionManager();
                        httpClientConnectionManager.setMaxTotal(this.getDefaultMaxTotal()); // 设置连接池线程最大数量
                        logger.info("======Http client's MaxTotal:[{}]", this.getDefaultMaxTotal());
                        httpClientConnectionManager.setDefaultMaxPerRoute(this.getDefaultMaxPerRoute()); // 设置单个路由最大的连接线程数量
                        logger.info("======Http client's MaxPerRoute:[{}]", this.getDefaultMaxPerRoute());
                        // 创建http request的配置信息
                        RequestConfig requestConfig = RequestConfig.custom()
                        		.setConnectionRequestTimeout(this.getDefaultTimeout())
                        		.setSocketTimeout(this.getDefaultTimeout()).build();
                        // 设置重定向策略
                        LaxRedirectStrategy redirectStrategy = new LaxRedirectStrategy();
                        // 初始化httpclient客户端
                        httpClient = HttpClients.custom()
                        		.setConnectionManager(httpClientConnectionManager)
                        		.setDefaultRequestConfig(requestConfig)
                        		.setRedirectStrategy(redirectStrategy).build();

                    }
                    catch (Exception e) {
                    	logger.error("", e);
                    }

                }
            }
        }

    }
	

	public String httpPostWithJSON(String url, String json) throws Exception {
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
        StringEntity se = new StringEntity(json,"UTF-8");
//        se.setContentType(CONTENT_TYPE_TEXT_JSON);
//        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
        httppost.setEntity(se);
        logger.debug("executing request " + httppost.getURI());
        CloseableHttpResponse response = null;
        try {
        	response = this.httpClient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String entityString = EntityUtils.toString(entity, "UTF-8");

                return entityString;
            }
        } finally {
        	if(response != null)
        		response.close();
        }
        return null;
    }
}
