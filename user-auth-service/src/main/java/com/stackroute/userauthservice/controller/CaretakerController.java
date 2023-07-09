package com.stackroute.userauthservice.controller;

import com.stackroute.userauthservice.model.User;
import com.stackroute.userauthservice.model.caretaker.Caretaker;
import com.stackroute.userauthservice.model.doctor.Doctor;
import com.stackroute.userauthservice.service.MyUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CaretakerController  implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    MyUserDetailService myUserDetailService;
    @Autowired
    public CaretakerController(MyUserDetailService myUserDetailService){
        this.myUserDetailService=myUserDetailService;
    }

    public User userDb = new User();

    @RabbitListener(queues = "${spring.rabbitmq.queue.caretaker}")
    public void receivedMessage(Caretaker caretaker) {
        logger.info("Received Caretaker Details: " + caretaker);
        caretakerToUserDb(caretaker);
        myUserDetailService.saveUser(this.userDb);
    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {

    }

    public void caretakerToUserDb(Caretaker caretaker) {
        this.userDb.setUsername(caretaker.getEmailId());
        this.userDb.setPassword(caretaker.getPassword());
        this.userDb.setRole(caretaker.getRole());

    }
}
