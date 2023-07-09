package com.stackroute.caretakerservice.controller;

import com.stackroute.caretakerservice.exception.CaretakerAlreadyExistsException;
import com.stackroute.caretakerservice.exception.CaretakerNotFoundException;
import com.stackroute.caretakerservice.model.Caretaker;
import com.stackroute.caretakerservice.service.CaretakerService;
import com.stackroute.caretakerservice.service.RabbitMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/v1")
public class CaretakerController {
    CaretakerService caretakerService;
    RabbitMQSender rabbitMQSender;
    @Autowired
    public CaretakerController(CaretakerService caretakerService, RabbitMQSender rabbitMQSender) {
        this.caretakerService = caretakerService;
        this.rabbitMQSender = rabbitMQSender;
    }

    //API for getting all caretakers
    @GetMapping(value = "/caretakers")
    public ResponseEntity<?> getAllNewCaretakers() {
        return new ResponseEntity<List<Caretaker>>(caretakerService.getAllCaretakers(), HttpStatus.OK);

    }

    // API for adding new caretaker
    @PostMapping(value = "/caretakers")
    public ResponseEntity<?> addNewCaretaker(@RequestBody Caretaker caretaker) {
        try {
           caretakerService.saveCaretaker(caretaker);
           System.out.println(caretaker.toString());
            rabbitMQSender.send(caretaker);
        }
        catch (CaretakerAlreadyExistsException e) {
            return new ResponseEntity<>("Caretaker Already Exists",HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>("Caretaker saved successfully",HttpStatus.OK);
    }

    //API for getting a particular caretaker based on emailId
    @GetMapping(value = "/caretakers/{emailId}")
    public ResponseEntity<?> getByIdCaretaker(@PathVariable String emailId) {
        try {
            return new ResponseEntity<Caretaker>(caretakerService.getCaretakerbyId(emailId),HttpStatus.OK);
        }
        catch (CaretakerNotFoundException e) {
            return new ResponseEntity<>("caretaker not found", HttpStatus.NOT_FOUND);
        }
    }

    //API for updating caretaker information
    @PutMapping(value = "/caretakers")
    public ResponseEntity<?> updateCaretaker(@RequestBody Caretaker caretaker) {
        try {
            return new ResponseEntity<Caretaker>(caretakerService.updateCaretaker(caretaker),HttpStatus.OK);
        }
        catch (CaretakerNotFoundException e) {
            return new ResponseEntity<>("Caretaker Not Found",HttpStatus.NOT_FOUND);
        }
    }

    //API for deleting a particular caretaker
    @DeleteMapping(value = "/caretakers/{emailId}")
    public ResponseEntity<?> deleteCaretaker(@PathVariable String emailId) {
        try {
            return new ResponseEntity<Caretaker>(caretakerService.deleteCaretaker(emailId),HttpStatus.OK);
        }
        catch (CaretakerNotFoundException e) {
            return new ResponseEntity<>("Caretaker Not Found",HttpStatus.NOT_FOUND);
        }
    }
}
