package com.rocket.demo2.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "transaction_topic",
        consumerGroup = "my-consumer_test-topic-3",
        enableMsgTrace = true,
        customizedTraceTopic = "my-trace-topic"
)
public class TranscationConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("事务消息消费" + message);
    }
}
