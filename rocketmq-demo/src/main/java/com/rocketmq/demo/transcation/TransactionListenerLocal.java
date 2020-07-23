package com.rocketmq.demo.transcation;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionListenerLocal  implements TransactionListener {
    private static final Map<String,Boolean> results=new ConcurrentHashMap<>();
    //执行本地事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object
            arg) {
        System.out.println(":执行本地事务："+arg.toString());
        String orderId=arg.toString();
        boolean rs=saveOrder(orderId);//模拟数据入库操作
        return rs?LocalTransactionState.COMMIT_MESSAGE:LocalTransactionState.UNKNOW;
        // 这个返回状态表示告诉broker这个事务消息是否被确认，允许给到consumer进行消费
        // LocalTransactionState.ROLLBACK_MESSAGE 回滚
        //LocalTransactionState.UNKNOW 未知
    }
    //提供事务执行状态的回查方法，提供给broker回调
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        String orderId=msg.getKeys();
        System.out.println("执行事务执行状态的回查，orderId:"+orderId);
        boolean rs=Boolean.TRUE.equals(results.get(orderId));
        System.out.println("回调："+rs);
        return rs?LocalTransactionState.COMMIT_MESSAGE:LocalTransactionState.ROLLBACK_MESSAGE;
    }

    private boolean saveOrder(String orderId){
        //如果订单取模等于0，表示成功,否则表示失败
        boolean success=Math.abs(Objects.hash(orderId))%2==0;
        results.put(orderId,success);
        return success;
    }

}
