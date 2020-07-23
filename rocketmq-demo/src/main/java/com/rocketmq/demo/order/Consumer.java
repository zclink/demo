package com.rocketmq.demo.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

public class Consumer {
    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("order_Consumer");
        consumer.setNamesrvAddr("10.211.55.4:9876");
        try {
            //设置Consumer从哪开始消费
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            consumer.subscribe("OrderTopic1", "*");
            // 实现了MessageListenerOrderly表示一个队列只会被一个线程取到, 第二个线程无法访问这个队列,MessageListenerOrderly默认单线程
//            consumer.setConsumeThreadMin(3);
//            consumer.setConsumeThreadMax(6);
            consumer.setMessageModel(MessageModel.BROADCASTING);
            consumer.registerMessageListener(new MessageListenerOrderly() {
                @Override
                public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context) {
                    try {
                        System.out.println("orderInfo: " + new String(msgs.get(0).getBody(), "utf-8"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return ConsumeOrderlyStatus.SUCCESS;
                }
            });
            consumer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Consumer1 Started.");
    }

}
