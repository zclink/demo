package com.rocket.demo2.consumer;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

// Define transaction listener with the annotation @RocketMQTransactionListener
@RocketMQTransactionListener
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        // ... local transaction process, return bollback, commit or unknown
        System.out.println("事务消息本地事务提交：" + msg);
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {
        // ... check transaction status and return bollback, commit or unknown
        System.out.println("事务消息本地事务确认：" + msg);
        return RocketMQLocalTransactionState.COMMIT;
    }
}
