package com.stackroute.bookappointmentservice.service;

import com.stackroute.bookappointmentservice.model.BookAppointment;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class RabbitMQSender implements Serializable {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Value("${appointment.rabbitmq.exchange}")
    private String exchange;

    @Value("${appointment.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${appointment.rabbitmq.exchangea}")
    private String exchangea;

    @Value("${appointment.rabbitmq.routingkeya}")
    private String routingkeya;

    @Value("${appointment.rabbitmq.exchangeb}")
    private String exchangeb;

    @Value("${appointment.rabbitmq.routingkeyb}")
    private String routingkeyb;

    @Value("${appointment.rabbitmq.exchangec}")
    private String exchangec;

    @Value("${appointment.rabbitmq.routingkeyc}")
    private String routingkeyc;

    public void send(BookAppointment bookAppointment) {
        rabbitTemplate.convertAndSend(exchange, routingkey, bookAppointment);
        System.out.println("Send msg = " + bookAppointment);
        rabbitTemplate.convertAndSend(exchangea, routingkeya, bookAppointment);
        System.out.println("Send msg = " + bookAppointment);
        rabbitTemplate.convertAndSend(exchangeb, routingkeyb, bookAppointment);
        System.out.println("Send msg = " + bookAppointment);
        rabbitTemplate.convertAndSend(exchangec, routingkeyc, bookAppointment);
        System.out.println("Send msg = " + bookAppointment);
    }
}
