package com.stackroute.doctorservice.repository;


import com.stackroute.doctorservice.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

/**
 * Doctor repository to interact with database
 */
@Repository
public interface DoctorRepository  extends MongoRepository<Doctor,String> {

}
