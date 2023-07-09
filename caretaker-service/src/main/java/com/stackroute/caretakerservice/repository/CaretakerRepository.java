package com.stackroute.caretakerservice.repository;

import com.stackroute.caretakerservice.model.Caretaker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//Caretaker repository class to interact with database
@Repository
public interface CaretakerRepository extends MongoRepository<Caretaker, String> {
}
