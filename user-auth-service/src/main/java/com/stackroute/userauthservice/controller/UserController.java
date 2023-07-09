package com.stackroute.userauthservice.controller;


import com.stackroute.userauthservice.model.User;
import com.stackroute.userauthservice.service.MyUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserController implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private MyUserDetailService myUserDetailService;
    @Autowired
    public UserController(MyUserDetailService myUserDetailService){
        this.myUserDetailService=myUserDetailService;
    }

    public User userDb=new User();

    @RabbitListener(queues = "${spring.rabbitmq.queue.user}")
    public void receivedMessage(com.stackroute.userauthservice.model.user.User user) {
        logger.info("Received User Details: " + user);
        userToUserDb(user);
        myUserDetailService.saveUser(this.userDb);
    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {

    }
    public void userToUserDb(com.stackroute.userauthservice.model.user.User user) {
       this.userDb.setUsername(user.getEmailId());
      this.userDb.setPassword(user.getPassword());
      this.userDb.setRole(user.getRole());

    }


}
