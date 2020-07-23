package com.rocketmq.demo.transcation;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class TransactionConsumer {

    public static void main(String[] args) throws MQClientException, IOException
    {
        DefaultMQPushConsumer defaultMQPushConsumer=new
                DefaultMQPushConsumer("tx_consumer_group");
        defaultMQPushConsumer.setNamesrvAddr("10.211.55.4:9876");
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        defaultMQPushConsumer.subscribe("pay_tx_topic","*");
        defaultMQPushConsumer.registerMessageListener((MessageListenerConcurrently)
                (msgs, context) -> {
                    msgs.stream().forEach(messageExt -> {
                        try {
                            String orderId=messageExt.getKeys();
                            String body=new String(messageExt.getBody(),
                                    RemotingHelper.DEFAULT_CHARSET);
                            System.out.println("收到消息:"+body+"，开始扣减库存："+orderId);
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    });
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                });
        defaultMQPushConsumer.start();
        System.in.read();
    }


}
