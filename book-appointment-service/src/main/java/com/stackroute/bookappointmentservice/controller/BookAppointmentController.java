package com.stackroute.bookappointmentservice.controller;

import com.stackroute.bookappointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.bookappointmentservice.exception.AppointmentNotFoundException;
import com.stackroute.bookappointmentservice.model.BookAppointment;
import com.stackroute.bookappointmentservice.service.BookAppointmentService;
import com.stackroute.bookappointmentservice.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class BookAppointmentController {
    BookAppointmentService appointmentService;

    @Autowired
    public BookAppointmentController(BookAppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Autowired
    RabbitMQSender rabbitMQSender;
    /*
     * API for adding new appointments
     */
    @PostMapping(value = "/appointments")
    public ResponseEntity<?> addNewAppointment(@RequestBody BookAppointment bookAppointment){
        try {
            appointmentService.saveAppointment(bookAppointment);

        }
        catch (AppointmentAlreadyExistsException e) {
            return new ResponseEntity<>("User already Exist",HttpStatus.CONFLICT);
        }
        catch (Exception e) {
            return new ResponseEntity<>("There is some problem with the server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        rabbitMQSender.send(bookAppointment);
        return new ResponseEntity<>("Appointment Saved and message sent to the RabbitMQ Successfully",HttpStatus.CREATED);

    }

    /*
     * API for updating a appointment information
     */
    @PutMapping(value = "/appointments")
    public ResponseEntity<?> updateAppointment(@RequestBody BookAppointment bookAppointment) {
        try {
            return new ResponseEntity<BookAppointment>(appointmentService.updateAppointment(bookAppointment),HttpStatus.OK);

        }
        catch(AppointmentNotFoundException e) {
            return new ResponseEntity<>("User Not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>("There is some problem with the server",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /*
     * API for deleting a particular appointment
     */
    @DeleteMapping(value = "/appointments/{id}")
    public ResponseEntity<?> DeleteAppointment(@PathVariable String id) {
        try {
            return new ResponseEntity<BookAppointment>(appointmentService.deleteAppointment(id),HttpStatus.OK);
        }
        catch(AppointmentNotFoundException e) {
            return new ResponseEntity<>("User Not found",HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<>("There is some problem with the server",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
