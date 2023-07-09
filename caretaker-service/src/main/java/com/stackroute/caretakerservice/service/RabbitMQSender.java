package com.stackroute.caretakerservice.service;

import com.stackroute.caretakerservice.model.Caretaker;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${caretaker.rabbitmq.exchange}")
    private String exchange;

    @Value("${caretaker.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${caretaker.rabbitmq.exchangea}")
    private String exchangea;

    @Value("${caretaker.rabbitmq.routingkeya}")
    private String routingkeya;

    @Value("${caretaker.rabbitmq.exchangeb}")
    private String exchangeb;

    @Value("${caretaker.rabbitmq.routingkeyb}")
    private String routingkeyb;

    public void send(Caretaker caretaker) {
        rabbitTemplate.convertAndSend(exchange, routingkey, caretaker);
        System.out.println("Send msg = " + caretaker);
        rabbitTemplate.convertAndSend(exchangea, routingkeya, caretaker);
        System.out.println("Send msg = " + caretaker);
        rabbitTemplate.convertAndSend(exchangeb, routingkeyb, caretaker);
        System.out.println("Send msg = " + caretaker);
    }
}
