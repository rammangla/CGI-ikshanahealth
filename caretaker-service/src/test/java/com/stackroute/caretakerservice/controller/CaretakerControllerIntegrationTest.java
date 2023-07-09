//
//package com.stackroute.caretakerservice.controller;
//
//import com.stackroute.caretakerservice.model.Appointments;
//import com.stackroute.caretakerservice.model.Caretaker;
//import com.stackroute.caretakerservice.model.CaretakerAddress;
////import com.stackroute.caretakerservice.model.CaretakerDetails;
//import com.stackroute.caretakerservice.service.CaretakerService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//public class CaretakerControllerIntegrationTest {
//
//    @Autowired
//    private CaretakerService caretakerService;
//
//    private Caretaker caretaker1;
//    private List<Caretaker> caretakerList;
//
//    @BeforeEach
//    public void setUp() {
//        LocalDate localDate = LocalDate.now();
//        caretakerList= new ArrayList<>();
//        caretaker1 = new Caretaker();
//        CaretakerAddress caretakerAddress = new CaretakerAddress();
//        Appointments appointments= new Appointments();
////        CaretakerDetails caretakerDetails = new CaretakerDetails();
//        caretaker1.setName("Diksha");
//        caretaker1.setEmailId("bhadwaldiksha8@gmail.com");
//        caretaker1.setPassword("Diksha@99");
//        caretaker1.setDob(localDate);
//        caretaker1.setGender("Female");
//        caretakerAddress.setPlotNo("10");
//        caretakerAddress.setStreet("Nadholi");
//        caretakerAddress.setTown("Jawali");
//        caretakerAddress.setDistrict("Kangra");
//        caretakerAddress.setState("HP");
//        caretaker1.setIdCard("Aadhar Card");
//        caretaker1.setTotalExperience("2 Years");
//        appointments.setDate(localDate);
//        appointments.setAppointmentId(03);
////        appointments.setCaretakerDetails(caretakerDetails);
////        caretakerDetails.setHireName("Diksha");
////        caretakerDetails.setPhoneNumber(862792312);
////        caretakerDetails.setCaretakerAddress(caretakerAddress);
//        caretakerAddress.setPlotNo("10");
//        caretakerAddress.setStreet("xyz");
//        caretakerAddress.setTown("Nahan");
//        caretakerAddress.setDistrict("Nahan");
//        caretakerAddress.setState("HP");
//        caretakerAddress.setPincode(173001);
////        caretakerDetails.setGender("Female");
////        caretakerDetails.setDob(localDate);
//        caretaker1.setServices(Arrays.asList("Babysitter","NursingCare"));
//        caretaker1.setRole("Caretaker");
//        caretakerList.add(caretaker1);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        caretaker1 = null;
//    }
//
//    @Test
//    void givenCaretakerToSaveThenShouldReturnTheSavedCaretaker() throws Exception {
//        Caretaker savedCaretaker = caretakerService.saveCaretaker(caretaker1);
//        assertNotNull(savedCaretaker);
//        assertEquals(caretaker1.getEmailId(),savedCaretaker.getEmailId());
//    }
//
//    @Test
//    public void givenGetAllCaretakersThenCaretakerListShouldNotBeNull() throws Exception {
//        List<Caretaker> caretakerList= caretakerService.getAllCaretakers();
//        assertNotNull(caretakerList);
//    }
//
//   // @Test
//    //public void givenCaretakerToUpdateThenShowReturnUpdatedCaretaker() throws Exception {
//      //  caretaker1.setName(("Diksha"));
//        //caretakerService.updateCaretaker(caretaker1);
//        //assertEquals("Diksha",caretaker1.getName());
//    //}
//
//}
//
