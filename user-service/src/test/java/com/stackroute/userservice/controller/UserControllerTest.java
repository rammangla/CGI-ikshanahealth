package com.stackroute.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.Appointment;
import com.stackroute.userservice.model.DocAddress;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.RabbitMQSender;
import com.stackroute.userservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    private MockMvc mockMvc;
    @Mock
    UserService userService;
    @Mock
    RabbitMQSender rabbitMQSender;
    @InjectMocks
    private UserController userController;
    private User user1;
    private List<User> userList;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        LocalDate localDate = LocalDate.now();
        userList = new ArrayList<>();
        user1 = new User();
        Address address = new Address();
        Appointment appointment = new Appointment();
        DocAddress docAddress = new DocAddress();
        List<Appointment> appointments= new ArrayList<>();
        docAddress.setClinicName("AGC");
        docAddress.setStreet("Bank Colony");
        docAddress.setPlotNo("C-32");
        docAddress.setTown("Sigra");
        docAddress.setDistrict("Varanasi");
        docAddress.setState("Uttar Pradesh");
        appointment.setAddress(docAddress);
        appointment.setDate(localDate);
        appointment.setSlot("9:00-9:15");
        appointment.setDoctorName("John");
        appointment.setClinicName("AGC");
        appointments.add(appointment);
        user1.setName("Harry");
        user1.setRole("user");
        user1.setPhoneNumber(1234567890);
        user1.setIdCard("Aadhar");
        address.setStreet("Bank Colony");
        address.setPlotNo("C-32");
        address.setTown("Sigra");
        address.setDistrict("Varanasi");
        address.setState("Uttar Pradesh");
        address.setPincode(221005);
        user1.setAddress(address);
        user1.setAppointments(appointments);
        user1.setDob(localDate);
        user1.setEmailId("suryanshraj99");
        user1.setGender("male");
        user1.setPassword("ksbjdckbcd");
        userList.add(user1);
    }

    @AfterEach
    public void tearDown() {
        user1 = null;
    }

    @Test
    public void givenUserToSaveThenShouldReturnSavedUser() throws Exception {
        mockMvc.perform(post("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(new User())))
                .andExpect(status().isCreated())
                .andDo(print());
        verify(userService).saveUser(any());
    }

    @Test
    public void givenGetAllUsersThenShouldReturnListOfAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(userList);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(user1)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
        verify(userService).getAllUsers();
        verify(userService, times(1)).getAllUsers();

    }

    @Test
    void givenUserIdThenShouldReturnRespectiveUser() throws Exception {
        when(userService.getUserById(user1.getEmailId())).thenReturn(user1);
        mockMvc.perform(get("/api/v1/users/suryanshraj99"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
    }

    @Test
    public void givenUserIdToDeleteThenShouldNotReturnDeletedUser() throws Exception {
        when(userService.deleteUser(user1.getEmailId())).thenReturn(user1);
        mockMvc.perform(delete("/api/v1/users/suryanshraj99"))
                .andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
    }

    @Test
    public void givenUserToUpdateThenShouldReturnUpdatedUser() throws Exception {
        when(userService.updateUser(any())).thenReturn(user1);
        user1.setName("Changed");
        mockMvc.perform(put("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new User()))).andExpect(status().isOk()).andDo(print());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
