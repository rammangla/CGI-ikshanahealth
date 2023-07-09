package com.stackroute.bookappointmentservice.repository;

import com.stackroute.bookappointmentservice.model.BookAppointment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookAppointmentRepository extends MongoRepository<BookAppointment,String> {

}
