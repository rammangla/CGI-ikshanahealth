package com.stackroute.userservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.userservice.model.User;

/*
 * User repository class to interact with database
 */
@Repository
public interface UserRepository extends MongoRepository<User,String>{

}
