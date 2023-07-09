package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.UserNotFoundException;
import com.stackroute.recommendationservice.model.doctorgraph.Doctor;
import com.stackroute.recommendationservice.model.usergraph.User;
import com.stackroute.recommendationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getUserById(String emailId) throws UserNotFoundException {
        if (userRepository.findById(emailId).isPresent()) {
            return userRepository.findById(emailId).get();
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String emailId) throws UserNotFoundException{
        if (userRepository.findById(emailId).isPresent()) {
            userRepository.deleteById(emailId);
        }
        else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public void addRelationship(String userEmail, String doctorEmail) {
        userRepository.createBookedRelationship(userEmail, doctorEmail);
    }

    @Override
    public List<?> getRecommendations(String emailId) {
        return userRepository.getRecommendations(emailId);
    }

    @Override
    public List<?> getRecommendationsLocation(String emailId) {
        return userRepository.getRecommendations(emailId);
    }
}

