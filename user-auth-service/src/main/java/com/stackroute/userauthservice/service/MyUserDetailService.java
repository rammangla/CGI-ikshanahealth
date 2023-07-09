package com.stackroute.userauthservice.service;


import com.stackroute.userauthservice.exception.UserNotFoundException;
import com.stackroute.userauthservice.model.MyUserDetails;
import com.stackroute.userauthservice.model.User;
import com.stackroute.userauthservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailService  implements UserDetailsService {
    @Autowired
    UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User>  user= this.userRepository.findByUsername(username);

        return user.map(MyUserDetails::new).orElseThrow(()->new UsernameNotFoundException("user not found"));

    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}
