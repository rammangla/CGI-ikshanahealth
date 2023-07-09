package com.stackroute.recommendationservice.controller;

import com.stackroute.recommendationservice.exception.DoctorNotFoundException;
import com.stackroute.recommendationservice.model.doctor.Doctor;
import com.stackroute.recommendationservice.service.DoctorServiceImpl;
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
public class DoctorController implements RabbitListenerConfigurer {
    private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    private com.stackroute.recommendationservice.model.doctorgraph.Doctor doctorGraph = new com.stackroute.recommendationservice.model.doctorgraph.Doctor();
    private com.stackroute.recommendationservice.model.doctorgraph.Address doctorAddressGraph = new com.stackroute.recommendationservice.model.doctorgraph.Address();
    private com.stackroute.recommendationservice.model.doctorgraph.Specialization doctorSpecializationGraph = new com.stackroute.recommendationservice.model.doctorgraph.Specialization();

    DoctorServiceImpl doctorService;

    @Autowired
    public DoctorController (DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    /*
     * RabbitMQ Listener listening to 'doctor.queue'
     */
    @RabbitListener(queues = "${spring.rabbitmq.queue.doctor}")
    public void receivedMessage(Doctor doctor) {
        logger.info("Received Doctor Details: " + doctor);
        doctorToDoctorGraph(doctor);
        doctorService.saveDoctor(this.doctorGraph);
    }

    /*
     * API for fetching recommendations for particular user
     */
    @GetMapping("/recommendations/{emailId}")
    public ResponseEntity<List<com.stackroute.recommendationservice.model.doctorgraph.Doctor>> getRecommendations(@PathVariable String emailId) {
        try {
            List<com.stackroute.recommendationservice.model.doctorgraph.Doctor> recommendations = (List<com.stackroute.recommendationservice.model.doctorgraph.Doctor>) doctorService.getRecommendations(emailId);
            if(recommendations.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(recommendations, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * API for fetching all the doctors
     */
    @GetMapping("/doctors")
    public ResponseEntity<List<com.stackroute.recommendationservice.model.doctorgraph.Doctor>> getAllDoctors() {
        try {
            List<com.stackroute.recommendationservice.model.doctorgraph.Doctor> doctors = doctorService.getAllDoctors();
            if(doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * API for fetching a Doctor using 'emailID'
     */
    @GetMapping("/doctors/{emailId}")
    public ResponseEntity<?> getDoctorById(@PathVariable String emailId) {
        try {
            return new ResponseEntity<>(doctorService.getDoctorById(emailId), HttpStatus.OK);
        } catch (DoctorNotFoundException doctorNotFoundException) {
            return new ResponseEntity<>("Doctor not found.", HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * API for posting a Doctor and creating its node
     */
    @PostMapping("/doctors")
    public ResponseEntity<?> saveDoctor(@RequestBody Doctor doctor) {
        try {
            doctorToDoctorGraph(doctor);
            return new ResponseEntity<>(doctorService.saveDoctor(this.doctorGraph), HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * API for deleting a node from graph database
     */
    @DeleteMapping("/doctors/{emailId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable String emailId) {
        try {
            doctorService.deleteDoctor(emailId);
            return new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK);
        } catch (DoctorNotFoundException doctorNotFoundException) {
            return new ResponseEntity<>("Doctor not found.", HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * Converting Doctor received from RabbitMQ to more suitable from to be used in graph database
     */
    public void doctorToDoctorGraph(Doctor doctor) {
        this.doctorGraph.setEmailId(doctor.getEmailId());
        this.doctorGraph.setName(doctor.getName());
        this.doctorGraph.setGender(doctor.getGender());
        this.doctorGraph.setEducationalQualification(doctor.getEducationalQualification());
        this.doctorAddressGraph.setTown(doctor.getAddress().getTown());
        this.doctorAddressGraph.setState(doctor.getAddress().getState());
        this.doctorSpecializationGraph.setSpeciality(doctor.getSpecialization());
        this.doctorSpecializationGraph.setTotalExperience(doctor.getTotalExperience());
        this.doctorGraph.setAddress(this.doctorAddressGraph);
        this.doctorGraph.setSpecialization(this.doctorSpecializationGraph);
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}