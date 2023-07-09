package com.stackroute.userservice.service;

import java.util.List;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;

/*
 * User service interface for specific purpose functions
 */
public interface UserService {
    User saveUser(User user) throws UserAlreadyExistsException;
    List<User> getAllUsers();
    User getUserById(String email) throws UserNotFoundException;
    User deleteUser(String email) throws UserNotFoundException;
    User updateUser(User user) throws UserNotFoundException;
}
