package com.stackroute.caretakerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.caretakerservice.CaretakerServiceApplication;
import com.stackroute.caretakerservice.model.Appointments;
import com.stackroute.caretakerservice.model.Caretaker;
import com.stackroute.caretakerservice.model.CaretakerAddress;
//import com.stackroute.caretakerservice.model.CaretakerDetails;
import com.stackroute.caretakerservice.service.CaretakerService;
import com.stackroute.caretakerservice.service.RabbitMQSender;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;

@ExtendWith({RestDocumentationExtension.class, MockitoExtension.class})
@ContextConfiguration(classes= CaretakerServiceApplication.class)
@WebMvcTest(CaretakerController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class CaretakerControllerRestDocs {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    CaretakerService caretakerService;

    @MockBean
    RabbitMQSender rabbitMQSender;

    @InjectMocks
    private CaretakerController caretakerController;

    private Caretaker caretaker1 = null;
    private List<Caretaker> caretakerList = null;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(documentationConfiguration(restDocumentation)).build();
        LocalDate localDate = LocalDate.now();
        caretakerList = new ArrayList<>();
        caretaker1 = new Caretaker();
        CaretakerAddress caretakerAddress = new CaretakerAddress();
        Appointments appointments = new Appointments();
//        CaretakerDetails caretakerDetails = new CaretakerDetails();
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
        caretakerList.add(caretaker1);
    }

        @AfterEach
        public void tearDown() {
            caretaker1 = null;
        }

        @Test
        public void givenCaretakerToSaveThenShouldReturnSavedCaretaker() throws Exception {
            when(caretakerService.saveCaretaker(any())).thenReturn(caretaker1);
            mockMvc.perform(post("/api/v1/caretakers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(new Caretaker())))
                    .andExpect(status().isCreated())
                    .andDo(document("{methodName}",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())));
        }

        @Test
        public void givenGetAllCaretakersThenShouldReturnListOfAllCaretakers() throws Exception {
            when(caretakerService.getAllCaretakers()).thenReturn(caretakerList);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/caretakers")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(caretaker1)))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(document("{methodName}",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())));

        }

        @Test
        void givenCaretakerIdThenShouldReturnRespectiveCaretaker() throws Exception {
            when(caretakerService.getCaretakerbyId(caretaker1.getEmailId())).thenReturn(caretaker1);
            mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/caretakers/bhadwaldiksha8"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(document("{methodName}",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())));
        }

        @Test
        public void givenCaretakerIdToDeleteThenShouldNotReturnDeletedCaretaker() throws Exception {
            when(caretakerService.deleteCaretaker(caretaker1.getEmailId())).thenReturn(caretaker1);
            mockMvc.perform(delete("/api/v1/caretakers/bhadwaldiksha8"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andDo(document("{methodName}",
                            preprocessRequest(prettyPrint()),
                            preprocessResponse(prettyPrint())));
        }

        public static String asJsonString(final Object obj) {
            try {
                return new ObjectMapper().writeValueAsString(obj);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
