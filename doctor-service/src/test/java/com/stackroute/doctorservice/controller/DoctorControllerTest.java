package com.stackroute.doctorservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.doctorservice.model.Address;
import com.stackroute.doctorservice.model.Appointments;
import com.stackroute.doctorservice.model.Doctor;
import com.stackroute.doctorservice.model.PatientDetails;
import com.stackroute.doctorservice.service.DoctorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class DoctorControllerTest {

    private MockMvc mockMvc;

    @Mock
    DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;
    private Doctor doctor1;
    private List<Doctor> doctorsList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc= MockMvcBuilders.standaloneSetup(doctorController).build();
        LocalDate localDate = LocalDate.now();
        doctorsList = new ArrayList<>();
        doctor1 = new Doctor();
        Address address = new Address();
        Appointments appointments = new Appointments();
        PatientDetails patientDetails = new PatientDetails();
        List<Appointments> appointmentsList = new ArrayList<>();
        doctor1.setName("Dr. Neeraj");
        doctor1.setEmailId("neeraj456@gmail.com");
        doctor1.setPassword("neeraj854");
        doctor1.setPhoneNumber(989454525);
        doctor1.setDob(localDate);
        doctor1.setGender("Male");
        doctor1.setIdCard("AadharCard");
        doctor1.setEducationalQualification("MBBS, MD, DM");
        doctor1.setAddress(address);
        address.setClinicName("GangaSheel");
        address.setPlotNo("50");
        address.setStreet("Rajendra Nagar");
        address.setTown("Delhi");
        address.setDistrict("Preet Vihar");
        address.setState("Delhi");
        address.setPinCode(243639);
        doctor1.setLicenseNumber("HGIBNDS5252");
        doctor1.setSpecialization("Cardiology");
        doctor1.setTotalExperience("20");
        doctor1.setFees("500");
        appointments.setDate(localDate);
        appointments.setSlot("10:15-10:30am");
        appointments.setPatientDetails(patientDetails);
        doctor1.setAppointments(appointmentsList);
        patientDetails.setPatientName("Shyam");
        patientDetails.setPhoneNumber(989840215);
        patientDetails.setGender("Male");
        patientDetails.setDob(localDate);
        appointmentsList.add(appointments);
        doctor1.setFacilities(Arrays.asList("Ambulance","Emergency 24*7"));
        doctor1.setRole("Doctor");
        appointments.setAppointmentId(02);
        doctorsList.add(doctor1);
    }
    @AfterEach
    public void tearDown() {
        doctor1 = null;
    }

//    @Test
//    public void givenDoctortoSaveThenShouldReturnSavedDoctor() throws Exception {
//        when(doctorService.saveDoctor(any())).thenReturn(doctor1);
//        mockMvc.perform(post("/api/v1/doctors")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(new Doctor())))
//                .andExpect(status().isCreated())
//                .andDo(print());
//        verify(doctorService).saveDoctor(any());
//    }

    @Test
    public void givenGetAllDoctorsThenShouldReturnListOfAllDoctors() throws Exception {
        when(doctorService.getAllDoctors()).thenReturn(doctorsList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/doctors")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(doctor1)))
                .andDo(print());
        verify(doctorService).getAllDoctors();
        verify(doctorService, times(1)).getAllDoctors();
    }

//    @Test
//    void givenDoctorIdThenShouldReturnRespectiveDoctor() throws Exception {
//        when(doctorService.getDoctorbyId(doctor1.getEmailId())).thenReturn(doctor1);
//        mockMvc.perform(get("/api/v1/doctors/neeraj456@gmail.com"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//    }

//    @Test
//    public void givenDoctorIdToDeleteThenShouldNotReturnDeletedDoctor() throws Exception {
//        when(doctorService.deleteDoctor(doctor1.getEmailId())).thenReturn(doctor1);
//        mockMvc.perform(delete("/api/v1/doctors/neeraj456@gmail.com"))
//                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
//    }

    @Test
    public void givenDoctorToUpdateThenShouldReturnUpdatedDoctor() throws Exception {
        when(doctorService.updateDoctor(any())).thenReturn(doctor1);
        //doctor1.setName("Changed");
        mockMvc.perform(put("/api/v1/doctors")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(new Doctor())))
                .andExpect(status().isOk()).andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
