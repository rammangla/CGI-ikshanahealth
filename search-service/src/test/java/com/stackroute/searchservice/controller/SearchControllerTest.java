//package com.stackroute.searchservice.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.stackroute.searchservice.exchange.SearchResponse;
//import com.stackroute.searchservice.model.Doctor;
//import com.stackroute.searchservice.model.DoctorAddress;
//import com.stackroute.searchservice.service.SearchService;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Date;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//public class SearchControllerTest {
//    private MockMvc mockMvc;
//    @Mock
//    SearchService searchService;
//    @InjectMocks
//    private SearchController searchController;
//    private Doctor doctor;
//    private DoctorAddress doctorAddress;
//    private SearchResponse searchResponse;
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
//        doctor=new Doctor();
//        doctorAddress=new DoctorAddress();
//        Date date=new Date();
//
////        doctor.setName("dev");
////        doctor.setEmailId("dev@gmail.com");
////        doctor.setPassword("kjbjsdkcb38y");
////        //doctor.setPhonenumber(1234567890L);
////        //doctor.setDob(Localdate);
////        doctor.setGender("male");
////        doctor.setIdCard("adoajaj");
////        doctor.setEducationalQualification("abcd");
////        doctor.setAddress(doctorAddress);
////        doctorAddress.setClinicName("abcd clinic");
////        doctorAddress.setPlotNo("B-31");
////        doctorAddress.setStreet("abc");
////        doctorAddress.setTown("def");
////        doctorAddress.setDistrict("Varanasi");
////        doctorAddress.setState("UP");
////        doctorAddress.setPincode(123456);
////        doctor.setLicenseno("ascasdcad");
////        doctor.setSpecialization("dental");
////        doctor.setTotalexperience("9years");
////        doctor.setFees("2113");
//    }
//    @AfterEach
//    public void tearDown() {
//        doctor = null;
//    }
//
//
//    @Test
//    void givenTownThenShouldReturnRespectiveDoctorlist() throws Exception {
//        when(searchService.searchByLocation(doctorAddress.getTown())).thenReturn(searchResponse);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/def")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(doctor)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(print());
//        verify(searchService).searchByLocation("def");
//        verify(searchService, times(1)).searchByLocation("def");
//
//    }
//    @Test
//    void givenSpecializationAndTownThenShouldReturnRespectiveDoctorlist() throws Exception {
//        when(searchService.searchByTownAndSpecialization(doctorAddress.getTown(),doctor.getSpecialization())).thenReturn(searchResponse);
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/search/def/dental")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(doctor)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(print());
//        verify(searchService).searchByTownAndSpecialization("def","dental");
//        verify(searchService, times(1)).searchByTownAndSpecialization("def","dental");
//
//    }
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//}