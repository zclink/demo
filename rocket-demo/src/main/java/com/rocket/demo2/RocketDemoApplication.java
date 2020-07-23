package com.rocket.demo2;

import com.rocket.demo2.model.OrderPaidEvent;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.support.MessageBuilder;

import java.math.BigDecimal;

@SpringBootApplication
public class RocketDemoApplication implements CommandLineRunner {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public static void main(String[] args) {
        SpringApplication.run(RocketDemoApplication.class, args);
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

        //事务消息发送
        rocketMQTemplate.sendMessageInTransaction("transaction_topic", MessageBuilder.withPayload("Hello transaction").build(), "transactionMessage");

        //rocketMQTemplate.destroy(); // notes:  once rocketMQTemplate be destroyed, you can not send any message again with this rocketMQTemplate
    }







}
