//package com.stackroute.doctorservice.service;
//
//
//import com.stackroute.doctorservice.exception.DoctorAlreadyExistsException;
//import com.stackroute.doctorservice.model.Address;
//import com.stackroute.doctorservice.model.Appointments;
//import com.stackroute.doctorservice.model.Doctor;
//import com.stackroute.doctorservice.model.PatientDetails;
//import com.stackroute.doctorservice.repository.DoctorRepository;
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
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DoctorServiceIntegrationTest {
//
//    @Autowired
//    private DoctorRepository doctorRepository;
//
//    @Autowired
//    private DoctorServiceImpl doctorService;
//    private Doctor doctor1, doctor2;
//    private List<Doctor> doctorsList;
//
//    @BeforeEach
//    public void setUp() {
//        LocalDate localDate = LocalDate.now();
//        doctorsList = new ArrayList<>();
//        doctor1 = new Doctor();
//        Address address = new Address();
//        Appointments appointments = new Appointments();
//        PatientDetails patientDetails = new PatientDetails();
//        List<Appointments> appointmentsList = new ArrayList<>();
//        doctor1.setName("Dr. Neeraj");
//        doctor1.setEmailId("neeraj456@gmail.com");
//        doctor1.setPassword("neeraj854");
//        doctor1.setPhoneNumber(989454525);
//        doctor1.setDob(localDate);
//        doctor1.setGender("Male");
//        doctor1.setIdCard("AadharCard");
//        doctor1.setEducationalQualification("MBBS, MD, DM");
//        doctor1.setAddress(address);
//        address.setClinicName("GangaSheel");
//        address.setPlotNo("50");
//        address.setStreet("Rajendra Nagar");
//        address.setTown("Delhi");
//        address.setDistrict("Preet Vihar");
//        address.setState("Delhi");
//        address.setPinCode(243639);
//        doctor1.setLicenseNumber("HGIBNDS5252");
//        doctor1.setSpecialization("Cardiology");
//        doctor1.setTotalExperience("20");
//        doctor1.setFees("500");
//        doctor1.setAppointments(appointmentsList);
//        appointments.setDate(localDate);
//        appointments.setSlot("10:15-10:30am");
//        appointments.setPatientDetails(patientDetails);
//        patientDetails.setPatientName("Shyam");
//        patientDetails.setPhoneNumber(989840215);
//        patientDetails.setGender("Male");
//        patientDetails.setDob(localDate);
//        appointmentsList.add(appointments);
//        doctorsList.add(doctor1);
//
//        doctor2 = new Doctor();
//        doctor2.setName("Dr. Amit");
//        doctor2.setEmailId("amit1980@gmail.com");
//        doctor2.setPassword("amit854");
//        doctor2.setPhoneNumber(979454525);
//        doctor2.setDob(localDate);
//        doctor2.setGender("Male");
//        doctor2.setIdCard("AadharCard");
//        doctor2.setEducationalQualification("MBBS, MD, DM");
//        doctor2.setAddress(address);
//        address.setClinicName("Max");
//        address.setPlotNo("50");
//        address.setStreet("Rajendra Nagar");
//        address.setTown("Delhi");
//        address.setDistrict("Preet Vihar");
//        address.setState("Delhi");
//        address.setPinCode(243639);
//        doctor2.setLicenseNumber("HGIHNDS5252");
//        doctor2.setSpecialization("Neurology");
//        doctor2.setTotalExperience("20");
//        doctor2.setFees("550");
//        doctor2.setAppointments(appointmentsList);
//        appointments.setDate(localDate);
//        appointments.setSlot("10:15-10:30am");
//        appointments.setPatientDetails(patientDetails);
//        patientDetails.setPatientName("Shreya");
//        patientDetails.setPhoneNumber(989845415);
//        patientDetails.setGender("Female");
//        patientDetails.setDob(localDate);
//        appointmentsList.add(appointments);
//        doctorsList.add(doctor2);
//
//    }
//
//    @AfterEach
//    public void tearDown() {
//        doctor1 = doctor2 = null;
//        doctorsList = null;
//    }
//
//    @Test
//    public void givenDoctorToSaveThenShouldReturnSavedDoctor() throws DoctorAlreadyExistsException {
//        Doctor savedDoctor = doctorService.saveDoctor(doctor1);
//        assertNotNull(savedDoctor);
//        assertEquals(doctor1.getEmailId(), savedDoctor.getEmailId());
//    }
//
//    @Test
//    public void givenGetAllDoctorsThenShouldReturnListOfAllDoctors() {
//        List<Doctor> doctorList = (List<Doctor>) doctorService.getAllDoctors();
//        assertNotNull(doctorList);
//    }
//
//
//}
