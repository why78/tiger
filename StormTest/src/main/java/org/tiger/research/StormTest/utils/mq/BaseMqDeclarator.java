/**
 *
 * Project Name:	StormTest
 * File Name:	BaseMqDeclarator.java
 *
 * Author:      Wang Huiyuan
 * Create Date: 2018年11月3日
 * Version:		1.0
 * Remark：
 */
package org.tiger.research.StormTest.utils.mq;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;

import hbec.platform.storm.tools.rabbitMQ.Declarator;

/**
 * @author WangHuiyuan
 *
 */
public class BaseMqDeclarator implements Declarator {

	private static final long   serialVersionUID = 1L;

    private static final Logger logger           = LoggerFactory.getLogger(BaseMqDeclarator.class);

    private final String        queueName;

    private final String        exchangeName;

    private final String        routingKey;

    private final String        transType;

    private final boolean        durable;

    private final boolean autoDelete;
    
    private final Map<String,Object> args;

    public BaseMqDeclarator(String exchangeName, String queueName, String routingKey, String transType, boolean durable, boolean autoDelete, Map<String, Object> args) {
        this.queueName = queueName;
        this.exchangeName = exchangeName;
        this.routingKey = routingKey;
        this.transType = transType;
        this.durable = durable;
        this.autoDelete = autoDelete;
        this.args = args;
    }

    /*
     * (non-Javadoc)
     * @see hbec.platform.storm.tools.hbec_platform_storm_tools.rabbitMQ.Declarator#execute(com.rabbitmq.client.Channel)
     */
    @Override
    public void execute(Channel channel) {
        try {
            /*
             * Exchange.DeclareOk exchangeOk = channel.exchangeDeclarePassive(exchangeName); Queue.DeclareOk queueOk =
             * channel.queueDeclarePassive(queueName); if(exchangeOk == null) if(queueOk == null)
             */
            channel.exchangeDeclare(exchangeName, transType, durable);
            channel.queueDeclare(queueName, durable, false, autoDelete, this.args);
            channel.queueBind(queueName, exchangeName, routingKey);

            // channel.queueDeclare(queueName, true, false, false, null);
            // channel.queueBind(queueName, exchangeName, routingKey);
        } catch (IOException e) {
            logger.error("Declaring quotation MQ consumer occurred errors: ", e);
        }

    }
}
