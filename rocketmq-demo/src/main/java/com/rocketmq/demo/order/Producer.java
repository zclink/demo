package com.rocketmq.demo.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

public class Producer {
    public static void main(String[] args) {
        try {
            DefaultMQProducer producer = new DefaultMQProducer("producerGroup1");
            producer.setNamesrvAddr("10.211.55.4:9876");
            producer.setRetryTimesWhenSendFailed(3);
            producer.start();
            String[] tags = new String[]{"创建订单", "支付", "发货", "收货", "五星好评"};
            for (int i = 5; i < 25; i++) {
                int orderId = i / 5;
                Message msg = new Message("OrderTopic1", tags[i % tags.length], "uniqueId:" + i,
                        ("order_" + orderId + " " + tags[i % tags.length]).getBytes(RemotingHelper.DEFAULT_CHARSET));


                /**
                 * 实现MessageQueueSelector 保证顺序发送
                 * 实现MessageQueueSelector的三种实现：
                 * SelectMessageQueueByHash
                 * SelectMessageQueueByMachineRoom
                 * SelectMessageQueueByRandom
                 */
                SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                        //此刻arg == orderId,可以保证是每个订单进入同一个队列
                        Integer id = (Integer) arg;
                        int index = id % mqs.size();
                        return mqs.get(index);
                    }
                }, orderId);
                System.out.printf("%s%n", sendResult);
            }
            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
