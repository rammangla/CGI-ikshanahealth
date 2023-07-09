package com.stackroute.doctorservice.controller;

import com.stackroute.doctorservice.exception.DoctorAlreadyExistsException;
import com.stackroute.doctorservice.exception.DoctorNotFoundException;
import com.stackroute.doctorservice.model.Doctor;
import com.stackroute.doctorservice.service.DoctorService;
import com.stackroute.doctorservice.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class DoctorController {
    DoctorService doctorService;
    RabbitMQSender rabbitMQSender;

    @Autowired
    public DoctorController(DoctorService doctorService, RabbitMQSender rabbitMQSender){
        this.doctorService = doctorService;
        this.rabbitMQSender= rabbitMQSender;
    }


    /*
     * API to fetch all Doctors
     */

    @GetMapping(value = "/doctors")
    public ResponseEntity<?> getAllNewDoctors() {
        return new ResponseEntity<List<Doctor>>(doctorService.getAllDoctors(), HttpStatus.OK);

    }
    /*
     * API to add new Doctors
     */
    @PostMapping(value = "/addDoctor")
    public ResponseEntity<?> addNewDoctor(@RequestBody Doctor doctor){
        try {
            doctorService.saveDoctor(doctor);
            rabbitMQSender.send(doctor);
        }
        catch (DoctorAlreadyExistsException e) {
            return new ResponseEntity<>("Doctor already Exist",HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Doctor saved Successfully",HttpStatus.OK);
    }
    /*
     * API to fetch Doctors by emailId
     */
    @GetMapping(value = "/doctors/{emailId}")
    public ResponseEntity<?> getByIdDoctor(@PathVariable String emailId) {
        try {
            return new ResponseEntity<Doctor>(doctorService.getDoctorbyId(emailId),HttpStatus.OK);
        }
        catch(DoctorNotFoundException e) {
            return new ResponseEntity<>("Doctor Not found",HttpStatus.NOT_FOUND);
        }
    }
    /*
     * API to update a Doctor information
     */
    @PutMapping(value = "/doctors")
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor) {
        try {
            return new ResponseEntity<Doctor>(doctorService.updateDoctor(doctor),HttpStatus.OK);
        }
        catch(DoctorNotFoundException e) {
            return new ResponseEntity<>("Doctor Not found",HttpStatus.NOT_FOUND);
        }
    }
    /*
     * API to delete Doctor by emailId
     */
    @DeleteMapping(value = "/doctors/{emailId}")
    public ResponseEntity<?> DeleteDoctor(@PathVariable String emailId) {
        try {
            return new ResponseEntity<Doctor>(doctorService.deleteDoctor(emailId),HttpStatus.OK);
        }
        catch(DoctorNotFoundException e) {
            return new ResponseEntity<>("Doctor Not found",HttpStatus.NOT_FOUND);
        }
    }

}
