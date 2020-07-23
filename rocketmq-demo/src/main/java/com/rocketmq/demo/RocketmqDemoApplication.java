package com.rocketmq.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;

@SpringBootApplication
public class RocketmqDemoApplication implements CommandLineRunner {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    public static void main(String[] args) {
        SpringApplication.run(RocketmqDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //发送同步消息
        rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
        //发送字符串同步消息
        rocketMQTemplate.send("test-topic-1", MessageBuilder.withPayload("Hello, World! I'm from spring message").build());
        //发送异步消息
        rocketMQTemplate.asyncSend("test-topic-2", new OrderPaidEvent("T_001", new BigDecimal("88.00")), new SendCallback() {
            @Override
            public void onSuccess(SendResult var1) {
                System.out.printf("async onSucess SendResult=%s %n", var1);
            }

            @Override
            public void onException(Throwable var1) {
                System.out.printf("async onException Throwable=%s %n", var1);
            }

        });
        //发送有序消息  使用hash算法实现自定义消息规则
        rocketMQTemplate.syncSendOrderly("orderly_topic",MessageBuilder.withPayload("Hello, World").build(),"hashkey");

        //rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate
    }

    @Data
    @AllArgsConstructor
    public class OrderPaidEvent implements Serializable {
        private String orderId;

        private BigDecimal paidMoney;
    }


    //=====    消费者

    @Service
    @RocketMQMessageListener(topic = "test-topic-1", consumerGroup = "my-consumer_test-topic-1")
    public class MyConsumer1 implements RocketMQListener<String> {
        @Override
        public void onMessage(String message) {
            System.out.println("received message: " + message);
        }
    }

    @Service
    @RocketMQMessageListener(topic = "test-topic-2", consumerGroup = "my-consumer_test-topic-2")
    public class MyConsumer2 implements RocketMQListener<OrderPaidEvent>{
        @Override
        public void onMessage(OrderPaidEvent orderPaidEvent) {
            System.out.println("received orderPaidEvent: " + orderPaidEvent);
        }
    }
}
