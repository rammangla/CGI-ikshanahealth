package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.exception.CaretakerNotFoundException;
import com.stackroute.recommendationservice.model.caretaker.Caretaker;
import com.stackroute.recommendationservice.service.CaretakerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin("*")
public class CaretakerController implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(CaretakerController.class);

    private com.stackroute.recommendationservice.model.caretakergraph.Caretaker caretakerGraph = new com.stackroute.recommendationservice.model.caretakergraph.Caretaker();
    private com.stackroute.recommendationservice.model.caretakergraph.Address caretakerAddressGraph = new com.stackroute.recommendationservice.model.caretakergraph.Address();

    CaretakerServiceImpl caretakerService;

    @Autowired
    public  CaretakerController (CaretakerServiceImpl caretakerService) {
        this.caretakerService = caretakerService;
    }

    /*
     * RabbitMQ Listener listening to 'caretaker.queue'
     */
    @RabbitListener(queues = "${spring.rabbitmq.queue.caretaker}")
    public void receivedMessage(Caretaker caretaker) {
        logger.info("Received Caretaker Details: " + caretaker);
        caretakerToCaretakerGraph(caretaker);
        caretakerService.saveCaretaker(this.caretakerGraph);
    }

    /*
     * API for fetching all the Caretakers
     */
    @GetMapping("/caretakers")
    public ResponseEntity<List<?>> getAllCaretakers() {
        try {
            List<com.stackroute.recommendationservice.model.caretakergraph.Caretaker> caretakers = caretakerService.getAllCaretakers();
            if(caretakers.isEmpty()) {
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(caretakers, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*
     * API for fetching a Caretaker using 'emailId'
     */
    @GetMapping("/caretakers/{emailId}")
    public ResponseEntity<?> getCaretakerById(@PathVariable String emailId) {
        try {
            return new ResponseEntity<>(caretakerService.getCaretakerById(emailId), HttpStatus.OK);
        } catch (CaretakerNotFoundException caretakerNotFoundException) {
            return new ResponseEntity<>("Caretaker not found.", HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * API for posting a Caretaker and creating its node
     */
    @PostMapping("/caretakers")
    public ResponseEntity<?> saveCaretaker (@RequestBody Caretaker caretaker) {
        try {
            caretakerToCaretakerGraph(caretaker);
            return new ResponseEntity<>(caretakerService.saveCaretaker(this.caretakerGraph), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * API for deleting a node from the graph database
     */
    @DeleteMapping("/caretakers/{emailId}")
    public ResponseEntity<String> deleteCaretaker (@PathVariable String emailId) {
        try {
            caretakerService.deleteCaretaker(emailId);
            return new ResponseEntity<>("Caretaker deleted successfully", HttpStatus.OK);
        } catch (CaretakerNotFoundException caretakerNotFoundException) {
            return new ResponseEntity<>("Caretaker not found.", HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Converting Caretaker received from RabbitMQ to more suitable from to be used in graph database
     */
    private void caretakerToCaretakerGraph(Caretaker caretaker) {
        this.caretakerGraph.setEmailId(caretaker.getEmailId());
        this.caretakerGraph.setName(caretaker.getName());
        this.caretakerGraph.setGender(caretaker.getGender());
        this.caretakerGraph.setTotalExperience(caretaker.getTotalExperience());
        this.caretakerAddressGraph.setTown(caretaker.getAddress().getTown());
        this.caretakerAddressGraph.setState(caretaker.getAddress().getState());
        this.caretakerGraph.setAddress(this.caretakerAddressGraph);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}

