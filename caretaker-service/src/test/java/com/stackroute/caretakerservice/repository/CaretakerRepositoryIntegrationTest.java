package com.stackroute.caretakerservice.repository;

import com.stackroute.caretakerservice.model.Appointments;
import com.stackroute.caretakerservice.model.Caretaker;
import com.stackroute.caretakerservice.model.CaretakerAddress;
//import com.stackroute.caretakerservice.model.CaretakerDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class CaretakerRepositoryIntegrationTest {

    @Autowired
    private CaretakerRepository caretakerRepository;
    private Caretaker caretaker;

    @BeforeEach
    public void setUp() {

        LocalDate localDate = LocalDate.now();
        caretaker = new Caretaker();
        CaretakerAddress caretakerAddress = new CaretakerAddress();
        Appointments appointments = new Appointments();
//        CaretakerDetails caretakerDetails = new CaretakerDetails();
        caretaker.setName("Diksha");
        caretaker.setEmailId("bhadwaldiksha8@gmail.com");
        caretaker.setPassword("Diksha@99");
        caretaker.setDob(localDate);
        caretaker.setGender("Female");
        caretakerAddress.setPlotNo("10");
        caretakerAddress.setStreet("Nadholi");
        caretakerAddress.setTown("Jawali");
        caretakerAddress.setDistrict("Kangra");
        caretakerAddress.setState("HP");
        caretaker.setIdCard("Aadhar Card");
        caretaker.setTotalExperience("2 Years");
        appointments.setDate(localDate);
        caretaker.setPhoneNumber(862792312);
        caretaker.setServices(Arrays.asList("Babysitter","NursingCare"));
        caretaker.setRole("Caretaker");
        appointments.setAppointmentId(05);
//        appointments.setCaretakerDetails(caretakerDetails);
//        caretakerDetails.setHireName("Diksha");
//        caretakerDetails.setPhoneNumber(862792312);
//        caretakerDetails.setCaretakerAddress(caretakerAddress);
//        caretakerAddress.setPlotNo("10");
//        caretakerAddress.setStreet("xyz");
//        caretakerAddress.setTown("Nahan");
//        caretakerAddress.setDistrict("Nahan");
//        caretakerAddress.setState("HP");
//        caretakerAddress.setPincode(173001);
//        caretakerDetails.setGender("Female");
//        caretakerDetails.setDob(localDate);

    }

    @AfterEach
    public void tearDown() {
        caretakerRepository.deleteAll();
        caretaker = null;
    }
    @Test
    public void givenGetAllCaretakersThenShouldReturnListOfAllCaretakers() {

        caretakerRepository.save(caretaker);

        List<Caretaker> caretakerList = (List<Caretaker>) caretakerRepository.findAll();
        assertEquals("bhadwaldiksha8@gmail.com", caretakerList.get(0).getEmailId());
    }

    @Test
    public void givenEmailIdThenShouldReturnRespectiveCaretaker() {
        caretakerRepository.save(caretaker);
        Optional<Caretaker> optional = caretakerRepository.findById(caretaker.getEmailId());
        assertEquals(caretaker.getEmailId(), optional.get().getEmailId());
        assertEquals(caretaker.getName(), optional.get().getName());
    }

    @Test
    public void givenCaretakerIdToDeleteThenShouldReturnDeletedCaretaker() {
        caretakerRepository.save(caretaker);
        caretakerRepository.deleteById(caretaker.getEmailId());
        Optional optional = caretakerRepository.findById(caretaker.getEmailId());
        assertEquals(Optional.empty(), optional);
    }
}
