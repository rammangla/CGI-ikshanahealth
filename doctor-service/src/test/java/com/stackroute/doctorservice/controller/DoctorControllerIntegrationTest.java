//package com.stackroute.doctorservice.controller;
//
//import com.stackroute.doctorservice.model.Address;
//import com.stackroute.doctorservice.model.Appointments;
//import com.stackroute.doctorservice.model.Doctor;
//import com.stackroute.doctorservice.model.PatientDetails;
//import com.stackroute.doctorservice.service.DoctorService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DoctorControllerIntegrationTest {
//    @Autowired
//    private DoctorService doctorService;
//
//    private Doctor doctor1;
//    private List<Doctor> doctorsList;
//
//
//    @BeforeEach
//    public void setUp() {
//        LocalDate localDate = LocalDate.now();
//        doctorsList = new ArrayList<>();
//        doctor1 = new Doctor();
//        Address address = new Address();
//        Appointments appointments = new Appointments();
//        PatientDetails patientDetails = new PatientDetails();
//        List<Appointments> appointmentsList= new ArrayList<>();
//        patientDetails.setPatientName("Shyam");
//        patientDetails.setPhoneNumber(989840215);
//        patientDetails.setGender("Male");
//        patientDetails.setDob(localDate);
//        appointments.setDate(localDate);
//        appointments.setSlot("10:15-10:30am");
//        appointments.setPatientDetails(patientDetails);
//        appointments.setAppointmentId(01);
//        appointmentsList.add(appointments);
//        doctor1.setLicenseNumber("HGIBNDS5252");
//        doctor1.setSpecialization("Cardiology");
//        doctor1.setTotalExperience("20");
//        doctor1.setFees("500");
//        doctor1.setAppointments(appointmentsList);
//        address.setClinicName("GangaSheel");
//        address.setPlotNo("50");
//        address.setStreet("Rajendra Nagar");
//        address.setTown("Delhi");
//        address.setDistrict("Preet Vihar");
//        address.setState("Delhi");
//        address.setPinCode(243639);
//        doctor1.setName("Dr. Neeraj");
//        doctor1.setEmailId("preet@gmail.com");
//        doctor1.setPassword("neeraj854");
//        doctor1.setPhoneNumber(989454525);
//        doctor1.setDob(localDate);
//        doctor1.setGender("Male");
//        doctor1.setIdCard("AadharCard");
//        doctor1.setEducationalQualification("MBBS, MD, DM");
//        doctor1.setAddress(address);
////        doctor1.setFacilities(Arrays.asList("Ambulance","Emergency 24*7"));
//        doctor1.setRole("Doctor");
//
//        doctorsList.add(doctor1);
//    }
//
//    @AfterEach
//    public void tearDown() {
//        doctor1 = null;
//    }
//
//    @Test
//    void givenDoctorToSaveThenShouldReturnTheSavedDoctor() throws Exception {
//        Doctor savedDoctor = doctorService.saveDoctor(doctor1);
//        assertNotNull(savedDoctor);
//        assertEquals(doctor1.getEmailId(), savedDoctor.getEmailId());
//    }
//
//    @Test
//    public void givenGetAllDoctorsThenDoctorListShouldNotBeNull() throws Exception {
//        List<Doctor> doctorList = doctorService.getAllDoctors();
//        assertNotNull(doctorList);
//    }
//
//    @Test
//    public void givenDoctorToUpdateThenShouldReturnUpdatedDoctor() throws Exception {
//        doctor1.setName("Neelesh");
//        doctorService.updateDoctor(doctor1);
//        assertEquals("Neelesh", doctor1.getName());
//    }
//
//}
