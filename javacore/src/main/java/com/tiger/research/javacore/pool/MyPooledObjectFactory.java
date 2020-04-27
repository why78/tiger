/**
 *
 * Project Name:	javacore
 * File Name:	MyPooledObjectFactory.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2019年10月28日
 * Version:		1.0
 * Remark：
 */
package com.tiger.research.javacore.pool;

import java.util.Date;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author WangHuiyuan
 *
 */
public class MyPooledObjectFactory  extends BasePooledObjectFactory<MyObject> {

	private final static Logger logger = LoggerFactory.getLogger(MyPooledObjectFactory.class);

	@Override
	public MyObject create() throws Exception {
		MyObject obj = new MyObject();
		logger.info("The object[{}] is created!", obj.getId());
//		System.out.printf("The object[%d] is created!", obj.getId());
//        System.out.println();
		return obj;
	}
	
    @Override
    public boolean validateObject(PooledObject<MyObject> pooledObject) {
    	MyObject obj = pooledObject.getObject();
        boolean valid = !obj.isBroken();
        logger.info("Validate myObject --> {}, return {}", obj.getId(), valid);
//        System.out.printf("Validate myObject --> %d, return %b", obj.getId(), valid);
//        System.out.println();
        return valid;
    }


	@Override
	public PooledObject<MyObject> wrap(MyObject obj) {
		return new DefaultPooledObject<>(obj);
	}
	
    @Override
    public void destroyObject(PooledObject<MyObject> p)
        throws Exception  {
    	MyObject obj = p.getObject();
//    	logger.info("The object[{}] is closed!", obj.getId());
//    	logger.info("destroid");
    	
    	Date date = new Date();    	
    	System.out.printf("%s The object[%d] is closed!", date.toString(), obj.getId());
        System.out.println();
    }

}
