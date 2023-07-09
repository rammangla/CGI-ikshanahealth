package com.stackroute.doctorservice.service;

import com.stackroute.doctorservice.model.Doctor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${doctor.rabbitmq.exchange}")
    private String exchange;

    @Value("${doctor.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${doctor.rabbitmq.exchangea}")
    private String exchangea;

    @Value("${doctor.rabbitmq.routingkeya}")
    private String routingkeya;

    @Value("${doctor.rabbitmq.exchange2}")
    private String exchange2;

    @Value("${doctor.rabbitmq.routingkey2}")
    private String routingkey2;

    public void send(Doctor doctor) {
        rabbitTemplate.convertAndSend(exchange, routingkey, doctor);
        System.out.println("Send msg = " + doctor);
        rabbitTemplate.convertAndSend(exchangea, routingkeya, doctor);
        System.out.println("Send msg = " + doctor);
        rabbitTemplate.convertAndSend(exchange2, routingkey2, doctor);
        System.out.println("Send msg = " + doctor);
    }
}
