package com.mq.consumer.listener;

import com.mq.consumer.mapper.UserMapper;
import com.mq.model.entity.User;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * 消费
 */
@Component
public class MyReceiver {

    @Autowired
    private UserMapper userMapper;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${spring.rabbitmq.listener.test.queue.name}",
                    durable = "${spring.rabbitmq.listener.test.queue.durable}"),
            exchange =@Exchange(value="${spring.rabbitmq.listener.test.exchange.name}",
                    durable = "${spring.rabbitmq.listener.test.exchange.durable}",
                    type = "${spring.rabbitmq.listener.test.exchange.type}",
                    ignoreDeclarationExceptions = "${spring.rabbitmq.listener.test.exchange.ignoreDeclarationExceptions}"),
            key="${spring.rabbitmq.listener.test.key}"
    ))
    /**
     * 同一个会话， consumerTag 是固定的 可以做此会话的名字， deliveryTag 每次接收消息+1，可以做此消息处理通道的名字。
     * 因此 deliveryTag 可以用来回传告诉 rabbitmq 这个消息处理成功 清除此消息（basicAck方法）。
     * @param message
     * @param channel
     */
    @RabbitHandler
    public void receiver(User user, Channel channel, @Headers Map<String,Object> headers) throws IOException {
        System.out.println("接受到的消息是 :"+user);
        Long deliverTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        userMapper.saveUser(user);
        channel.basicAck(deliverTag,false);
    }
}