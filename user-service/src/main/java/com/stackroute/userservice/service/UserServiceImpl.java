package com.stackroute.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    /*
     * User service function to save a particular user
     */
    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        // TODO Auto-generated method stub
        if(userRepository.findById(user.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        else {
            return userRepository.save(user);
        }
    }

    /*
     * User service function to fetch all users
     */
    @Override
    public List<User> getAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAll();
    }

    /*
     * User service function to fetch particular user
     */
    @Override
    public User getUserById(String email) throws UserNotFoundException {
        // TODO Auto-generated method stub
        if(userRepository.findById(email).isPresent()) {

            return userRepository.findById(email).get();
        }
        else {
            throw new UserNotFoundException();
        }
    }

    /*
     * User service function to delete a particular user
     */
    @Override
    public User deleteUser(String email) throws UserNotFoundException {
        // TODO Auto-generated method stub
        Optional<User> usrOpt = userRepository.findById(email);
        if(usrOpt.isPresent()) {
            User userRet = usrOpt.get();
            userRepository.deleteById(email);
            return userRet;
        }
        else {
            throw new UserNotFoundException();
        }
    }

    /*
     * User service function to update a particular user
     */
    @Override
    public User updateUser(User user) throws UserNotFoundException {
        // TODO Auto-generated method stub
        Optional<User> userOptional= userRepository.findById(user.getEmailId());
        if(userOptional.isPresent()) {
            User getUser = userOptional.get();
            getUser.setDob(user.getDob());
            getUser.setEmailId(user.getEmailId());
            getUser.setGender(user.getGender());
            getUser.setName(user.getName());
            getUser.setAppointments(user.getAppointments());
            getUser.setCaretakerAppointments(user.getCaretakerAppointments());
            getUser.setAddress(user.getAddress());
            getUser.setIdCard(user.getIdCard());
            getUser.setPhoneNumber(user.getPhoneNumber());
            getUser.setRole(user.getRole());
            getUser.setPassword(user.getPassword());
            User updatedUser = userRepository.save(getUser);
            return updatedUser;
        }
        else {
            throw new UserNotFoundException();
        }
    }


}
