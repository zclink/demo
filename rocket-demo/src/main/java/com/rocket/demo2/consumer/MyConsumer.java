package com.rocket.demo2.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@RocketMQMessageListener(
        topic = "test-topic-1:tag|",
        consumerGroup = "my-consumer_test-topic-1",
        enableMsgTrace = true,
        customizedTraceTopic = "my-trace-topic"
)
public class MyConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {

    }
}
