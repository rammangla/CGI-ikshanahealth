package com.stackroute.userservice.controller;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.RabbitMQSender;
import com.stackroute.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    RabbitMQSender rabbitMQSender;

    /*
     * API for fetching all users
     */
    @GetMapping(value = "/users")
    public ResponseEntity<?> getAllGuestUsers() {
        try {
            return new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>("There is some problem with the server",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * API for adding new users
     */
    @PostMapping(value = "/users")
    public ResponseEntity<?> addNewGuestUser(@RequestBody User user){
        try {
            userService.saveUser(user);

        }
        catch (UserAlreadyExistsException e) {
            return new ResponseEntity<>("User already Exist",HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>("There is some problem with the server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        rabbitMQSender.send(user);
        return new ResponseEntity<>("User Saved and message sent to the RabbitMQ from User Successfully",HttpStatus.CREATED);

    }

    /*
     * API for fetching a particular user based on email
     */
    @GetMapping(value = "/users/{emailId}")
    public ResponseEntity<?> getByIdUser(@PathVariable String emailId) {
        try {
            return new ResponseEntity<User>(userService.getUserById(emailId),HttpStatus.OK);
        }
        catch(UserNotFoundException e) {
            return new ResponseEntity<>("User Not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>("There is some problem with the server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * API for updating a user information
     */
    @PutMapping(value = "/users")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        try {
            return new ResponseEntity<User>(userService.updateUser(user),HttpStatus.OK);

        }
        catch(UserNotFoundException e) {
            return new ResponseEntity<>("User Not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>("There is some problem with the server",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * API for deleting a particular user
     */
    @DeleteMapping(value = "/users/{emailId}")
    public ResponseEntity<?> DeleteUser(@PathVariable String emailId) {
        try {
            return new ResponseEntity<User>(userService.deleteUser(emailId),HttpStatus.OK);
        }
        catch(UserNotFoundException e) {
            return new ResponseEntity<>("User Not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>("There is some problem with the server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
