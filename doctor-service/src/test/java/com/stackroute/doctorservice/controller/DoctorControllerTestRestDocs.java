//package com.stackroute.doctorservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.doctorservice.DoctorServiceApplication;
//import com.stackroute.doctorservice.model.Address;
//import com.stackroute.doctorservice.model.Appointments;
//import com.stackroute.doctorservice.model.Doctor;
//import com.stackroute.doctorservice.model.PatientDetails;
//import com.stackroute.doctorservice.service.DoctorService;
//import com.stackroute.doctorservice.service.RabbitMQSender;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.RestDocumentationContextProvider;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
//
//@ExtendWith({RestDocumentationExtension.class, MockitoExtension.class})
//@ContextConfiguration(classes= DoctorServiceApplication.class)
//@WebMvcTest(DoctorController.class)
//@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
//public class DoctorControllerTestRestDocs {
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    private MockMvc mockMvc;
//
//    @MockBean
//    DoctorService doctorService;
//
//    @MockBean
//    RabbitMQSender rabbitMQSender;
//    @InjectMocks
//    private DoctorController doctorController;
//
//    private Doctor doctor1 = null;
//    private List<Doctor> doctorsList = null;
//
//    @BeforeEach
//    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(documentationConfiguration(restDocumentation)).build();
//        LocalDate localDate = LocalDate.now();
//        doctorsList = new ArrayList<>();
//        doctor1 = new Doctor();
//        Address address = new Address();
//        Appointments appointments = new Appointments();
//        PatientDetails patientDetails = new PatientDetails();
//        List<Appointments> appointmentsList= new ArrayList<>();
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
//    }
//    @AfterEach
//    public void tearDown() {
//        doctor1 = null;
//    }
//
//    @Test
//    public void givenDoctorToSaveThenShouldReturnSavedDoctor() throws Exception {
//        when(doctorService.saveDoctor(any())).thenReturn(doctor1);
//        mockMvc.perform(post("/api/v1/doctors")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(new Doctor())))
//                .andExpect(status().isCreated())
//                .andDo(document("{methodName}",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint())));
//    }
//
//    @Test
//    public void givenGetAllDoctorsThenShouldReturnListOfAllDoctors() throws Exception {
//        when(doctorService.getAllDoctors()).thenReturn(doctorsList);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(doctor1)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(document("{methodName}",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()))
//                );
//
//    }
//
//    @Test
//    void givenDoctorIdThenShouldReturnRespectiveDoctor() throws Exception {
//        when(doctorService.getDoctorbyId(doctor1.getEmailId())).thenReturn(doctor1);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/doctors/neeraj456@gmail.com"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(document("{methodName}",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint())));
//    }
//
//    @Test
//    public void givenDoctorIdToDeleteThenShouldNotReturnDeletedDoctor() throws Exception {
//        when(doctorService.deleteDoctor(doctor1.getEmailId())).thenReturn(doctor1);
//        mockMvc.perform(delete("/api/v1/doctors/neeraj456@gmail.com"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(document("{methodName}",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint())));
//    }
//
//    @Test
//    public void givenDoctorToUpdateThenShouldReturnUpdatedDoctor() throws Exception {
//        when(doctorService.updateDoctor(any())).thenReturn(doctor1);
//        doctor1.setName("Changed");
//        mockMvc.perform(put("/api/v1/doctors")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(new Doctor())))
//                .andExpect(status().isOk())
//                .andDo(document("{methodName}",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint())));
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return  new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}
