//package com.stackroute.caretakerservice.service;
//
//import com.stackroute.caretakerservice.model.Caretaker;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
//import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
//import org.springframework.stereotype.Component;
//
//@Component
//public class RabbitMQReceiver  implements RabbitListenerConfigurer {
//    private static final Logger logger = LoggerFactory.getLogger(RabbitMQReceiver.class);
//
//    @RabbitListener(queues = "${caretaker.rabbitmq.queue}")
//    public void receivedMessage(Caretaker caretaker) {
//
//        logger.info("Caretaker Details Received are.. " + caretaker);
//    }
//
//    @Override
//    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
//
//    }
//}
