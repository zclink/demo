package com.rocketmq.demo.transcation;

import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newFixedThreadPool;

public class TransactionProducer {

    public static void main(String[] args) throws Exception {
        TransactionMQProducer transactionProducer=new
                TransactionMQProducer("tx_producer_group");
        transactionProducer.setNamesrvAddr("10.211.55.4:9876");
        ExecutorService executorService = newFixedThreadPool(10);
        //自定义线程池，用于异步执行事务操作
        transactionProducer.setExecutorService(executorService);
        transactionProducer.setTransactionListener(new TransactionListenerLocal());
        transactionProducer.start();
        for(int i=0;i<20;i++) {
            String orderId= UUID.randomUUID().toString();
            String body="{'operation':'doOrder','orderId':'"+orderId+"'}";
            Message message = new Message("pay_tx_topic", "TagA",orderId,body.getBytes(RemotingHelper.DEFAULT_CHARSET));
            transactionProducer.sendMessageInTransaction(message,orderId+"&"+i);
            Thread.sleep(1000);
        }
    }

}
