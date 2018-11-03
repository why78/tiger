/*
 * Copyright 2004-2014 touker.com All right reserved. This software is the
 * confidential and proprietary information of touker.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with touker.com.
 */
package org.tiger.research.StormTest.schema;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.storm.spout.Scheme;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tiger.research.StormTest.model.One;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;

/**
 * 类WarnStrategySchema.java的实现描述：TODO 类实现描述
 * @author Danmy  2016年2月19日 上午9:12:21
 */
public  class OneScheme implements Scheme {

	private static final long serialVersionUID = 395922296697898172L;

	private static final Logger logger = LoggerFactory.getLogger(OneScheme.class);

    private static final Gson gson = new Gson();

    private int slotCount = 0;

    public OneScheme(int slotCount) {
        this.slotCount = slotCount;
    }

    @Override
    public List<Object> deserialize(ByteBuffer byteBuffer) {
        try {
            String str = new String(byteBuffer.array(), Charset.forName("utf-8"));
            logger.info("Receive json <= {}", str);

            One sme = gson.fromJson(str, new TypeToken<One>(){}.getType());

            logger.info("Received strategy <= {}", sme);

            return new Values(sme,sme.getAge() % slotCount);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Fields getOutputFields() {
        return new Fields("one","partition_id");
    }
}
