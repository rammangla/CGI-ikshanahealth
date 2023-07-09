/*
package com.stackroute.caretakerservice.service;

import com.stackroute.caretakerservice.exception.CaretakerAlreadyExistsException;
import com.stackroute.caretakerservice.model.Appointments;
import com.stackroute.caretakerservice.model.Caretaker;
import com.stackroute.caretakerservice.model.CaretakerAddress;
import com.stackroute.caretakerservice.model.CaretakerDetails;
import com.stackroute.caretakerservice.repository.CaretakerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CaretakerServiceIntegrationTest {

    @Autowired
    private CaretakerRepository caretakerRepository;

    @Autowired
    private CaretakerServiceImpl caretakerService;
    private Caretaker caretaker1, caretaker2;
    private List<Caretaker> caretakerList;

    @BeforeEach
    public void setUp() {

        LocalDate localDate = LocalDate.now();
        caretakerList = new ArrayList<>();
        caretaker1 = new Caretaker();
        CaretakerAddress caretakerAddress = new CaretakerAddress();
        Appointments appointments = new Appointments();
        CaretakerDetails caretakerDetails = new CaretakerDetails();
        caretaker1.setName("Diksha");
        caretaker1.setEmailId("bhadwaldiksha8@gmail.com");
        caretaker1.setPassword("Diksha@99");
        caretaker1.setDob(localDate);
        caretaker1.setGender("Female");
        caretakerAddress.setPlotNo("10");
        caretakerAddress.setStreet("Nadholi");
        caretakerAddress.setTown("Jawali");
        caretakerAddress.setDistrict("Kangra");
        caretakerAddress.setState("HP");
        caretaker1.setIdCard("Aadhar Card");
        caretaker1.setTotalExperience("2 Years");
        appointments.setDate(localDate);
        appointments.setCaretakerDetails(caretakerDetails);
        caretakerDetails.setHireName("Diksha");
        caretakerDetails.setPhoneNumber(862792312);
        caretakerDetails.setCaretakerAddress(caretakerAddress);
        caretakerAddress.setPlotNo("10");
        caretakerAddress.setStreet("xyz");
        caretakerAddress.setTown("Nahan");
        caretakerAddress.setDistrict("Nahan");
        caretakerAddress.setState("HP");
        caretakerAddress.setPincode(173001);
        caretakerDetails.setGender("Female");
        caretakerDetails.setDob(localDate);

        caretaker2 = new Caretaker();
        caretaker1.setName("Aakanksha");
        caretaker1.setEmailId("bhadwalaakanksha@gmail.com");
        caretaker1.setPassword("Aakanksha@96");
        caretaker1.setDob(localDate);
        caretaker1.setGender("Female");
        caretakerAddress.setPlotNo("17");
        caretakerAddress.setStreet("Johar");
        caretakerAddress.setTown("Jawali");
        caretakerAddress.setDistrict("Kangra");
        caretakerAddress.setState("HP");
        caretaker1.setIdCard("Aadhar Card");
        caretaker1.setTotalExperience("2 Years");
        appointments.setDate(localDate);
        appointments.setCaretakerDetails(caretakerDetails);
        caretakerDetails.setHireName("Aakanksha");
        caretakerDetails.setPhoneNumber(862794442);
        caretakerDetails.setCaretakerAddress(caretakerAddress);
        caretakerAddress.setPlotNo("20");
        caretakerAddress.setStreet("pqr");
        caretakerAddress.setTown("Dharamshala");
        caretakerAddress.setDistrict("Kangra");
        caretakerAddress.setState("HP");
        caretakerAddress.setPincode(176225);
        caretakerDetails.setGender("Female");
        caretakerDetails.setDob(localDate);

        caretakerList.add(caretaker1);
        caretakerList.add(caretaker2);

    }

    @AfterEach
    public void tearDown() {
        caretaker1 = caretaker2 = null;
        caretakerList = null;
    }

    @Test
    public void givenCaretakerToSaveThenShouldReturnSavedCaretaker() throws CaretakerAlreadyExistsException {
        Caretaker savedCaretaker = caretakerService.saveCaretaker(caretaker1);
        assertNotNull(savedCaretaker);
        assertEquals(caretaker1.getEmailId(), savedCaretaker.getEmailId());
    }

    @Test
    public void givenGetAllCaretakersThenShouldReturnListOfAllCaretakers() {
        List<Caretaker> caretakerList = (List<Caretaker>) caretakerService.getAllCaretakers();
        assertNotNull(caretakerList);
    }
}
*/
