package com.stackroute.userservice.service;

import com.stackroute.userservice.model.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${user.rabbitmq.exchange}")
    private String exchange;

    @Value("${user.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${user.rabbitmq.exchangea}")
    private String exchangea;

    @Value("${user.rabbitmq.routingkeya}")
    private String routingkeya;

    @Value("${user.rabbitmq.exchangeb}")
    private String exchangeb;

    @Value("${user.rabbitmq.routingkeyb}")
    private String routingkeyb;

    public void send(User user) {
        rabbitTemplate.convertAndSend(exchange, routingkey, user);
        System.out.println("Send msg to queue = " + user);
        rabbitTemplate.convertAndSend(exchangea, routingkeya, user);
        System.out.println("Send msg to queuea = " + user);
        rabbitTemplate.convertAndSend(exchangeb, routingkeyb, user);
        System.out.println("Send msg to queueb = " + user);

    }
}
