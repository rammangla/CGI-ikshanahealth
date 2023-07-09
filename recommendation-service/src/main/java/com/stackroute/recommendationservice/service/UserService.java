package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.UserNotFoundException;
import com.stackroute.recommendationservice.model.doctorgraph.Doctor;
import com.stackroute.recommendationservice.model.usergraph.User;


import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(String emailId) throws UserNotFoundException;

    User saveUser(User user);

    void deleteUser(String emailId) throws UserNotFoundException;

    void addRelationship(String userEmail, String doctorEmail);

    List<?> getRecommendations(String emailId);

    List<?> getRecommendationsLocation(String emailId);

}