package com.stackroute.userauthservice.controller;

import com.stackroute.userauthservice.model.User;
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
public class DoctorController implements RabbitListenerConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    MyUserDetailService myUserDetailService;
    @Autowired
    public DoctorController(MyUserDetailService myUserDetailService){
        this.myUserDetailService=myUserDetailService;
    }

    public User userDb = new User();

    @RabbitListener(queues = "${spring.rabbitmq.queue.doctor}")
    public void receivedMessage(Doctor doctor) {
        logger.info("Received Doctor Details: " + doctor);
        doctorToUserDb(doctor);
        myUserDetailService.saveUser(this.userDb);
    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {

    }
    public void doctorToUserDb(Doctor doctor) {
        this.userDb.setUsername(doctor.getEmailId());
        this.userDb.setPassword(doctor.getPassword());
        this.userDb.setRole(doctor.getRole());

    }

}
