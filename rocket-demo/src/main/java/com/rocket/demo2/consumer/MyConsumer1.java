package com.rocket.demo2.consumer;

//=====    消费者

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
public class MyConsumer1 implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        System.out.println("received message: " + message);
    }
}
