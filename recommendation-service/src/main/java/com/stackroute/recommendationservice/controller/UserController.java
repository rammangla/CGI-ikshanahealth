package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.exception.UserNotFoundException;
import com.stackroute.recommendationservice.model.BookAppointment;
import com.stackroute.recommendationservice.model.doctor.Doctor;
import com.stackroute.recommendationservice.model.user.User;
import com.stackroute.recommendationservice.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserServiceImpl userService;

    public com.stackroute.recommendationservice.model.usergraph.User userGraph = new com.stackroute.recommendationservice.model.usergraph.User();
    public com.stackroute.recommendationservice.model.usergraph.Address userGraphAddress = new com.stackroute.recommendationservice.model.usergraph.Address();

    @Autowired
    public UserController (UserServiceImpl userService) {
        this.userService = userService;
    }

    /*
     * RabbitMQ Listener listening to 'user.queue'
     */
    @RabbitListener(queues = "${spring.rabbitmq.queue.user}")
    public void receivedMessage(User user) {
        logger.info("Received User Details: " + user);
        userToUserGraph(user);
        userService.saveUser(this.userGraph);
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue.appointment}")
    public void receivedMessageBookAppointment(BookAppointment bookAppointment) {
        logger.info("Received Book Appointment Details: " + bookAppointment);
        String userEmail = bookAppointment.getUser().getEmailId();
        String doctorEmail = bookAppointment.getDoctor().getEmailId();
        userService.addRelationship(userEmail, doctorEmail);
    }



    /*
     * API for fetching all the Users
     */
    @GetMapping("/users")
    public ResponseEntity<List<com.stackroute.recommendationservice.model.usergraph.User>> getAllUsers() {
        try {
            List<com.stackroute.recommendationservice.model.usergraph.User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * API for fetching a User using 'emailId'
     */
    @GetMapping("/users/{emailId}")
    public ResponseEntity<?> getUserById(@PathVariable String emailId) {
        try {
            return new ResponseEntity<>(userService.getUserById(emailId), HttpStatus.OK);
        } catch (UserNotFoundException userNotFoundException) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * API for posting a User and creating its node
     */
    @PostMapping("/users")
    public ResponseEntity<com.stackroute.recommendationservice.model.usergraph.User> saveUser(@RequestBody User user) {
        try {
            userToUserGraph(user);
            return new ResponseEntity<>(userService.saveUser(userGraph), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*
     * Deleting a node from the graph database
     */
    @DeleteMapping("/users/{emailId}")
    public ResponseEntity<?> deleteUser(@PathVariable String emailId) {
        try {
            userService.deleteUser(emailId);
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        } catch (UserNotFoundException userNotFoundException) {
            return new ResponseEntity<>("User not found.", HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Converting User received from RabbitMQ to more suitable from to be used in graph database
     */
    public void userToUserGraph(User user) {
        this.userGraph.setEmailId(user.getEmailId());
        this.userGraph.setName(user.getName());
        this.userGraph.setGender(user.getGender());
        this.userGraphAddress.setTown(user.getAddress().getTown());
        this.userGraphAddress.setState(user.getAddress().getState());
        this.userGraph.setAddress(this.userGraphAddress);
    }

}
