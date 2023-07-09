package com.stackroute.userauthservice.repository;


import com.stackroute.userauthservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo  extends JpaRepository<User,String> {
 Optional<User> findByUsername(String username);
}

