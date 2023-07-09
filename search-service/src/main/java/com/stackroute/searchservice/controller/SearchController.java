package com.stackroute.searchservice.controller;

import com.stackroute.searchservice.exception.SpecializationNotFoundException;
import com.stackroute.searchservice.exception.TownNotFoundException;
import com.stackroute.searchservice.exchange.SearchResponse;
import com.stackroute.searchservice.model.Caretaker;
import com.stackroute.searchservice.model.Doctor;
import com.stackroute.searchservice.service.SearchService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1")
public class SearchController {
    @Autowired
    SearchService searchService;

    @RabbitListener(queues = "${spring.rabbitmq.queue.caretaker}")
    public void receivedMessage(Caretaker caretaker) {
       System.out.println(caretaker);
        searchService.saveCaretaker(caretaker);
    }
    @RabbitListener(queues = "${spring.rabbitmq.queue.doctor}")
    public void receivedMessage(Doctor doctor) {
        searchService.saveDoctor(doctor);
    }


    //endpoint for search based on location
    @GetMapping("/search/{addressTown}")
    public ResponseEntity<?> getByLocation(@PathVariable String addressTown){
        try {
            return new ResponseEntity<SearchResponse>(searchService.searchByLocation(addressTown), HttpStatus.OK);
        } catch (TownNotFoundException e) {
            return new ResponseEntity<>("Town not found",HttpStatus.NOT_FOUND);
        }
    }
    //endpoint for nested search based on location AND specialization
    @GetMapping("/search/{addressTown}/{specialization}")
    public ResponseEntity<?> getByTownAndSpecialization(@PathVariable String addressTown,@PathVariable String specialization){
        try {
            return new ResponseEntity<SearchResponse>(searchService.searchByTownAndSpecialization(addressTown,specialization),HttpStatus.OK);
        } catch (SpecializationNotFoundException e) {
            return new ResponseEntity<>("Specialization not found",HttpStatus.NOT_FOUND);
        }
    }
}
