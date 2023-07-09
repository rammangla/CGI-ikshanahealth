package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.Appointment;
import com.stackroute.userservice.model.DocAddress;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceIntegrationTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;
    private User user1, user2;
    private List<User> userList;

    @BeforeEach
    public void setUp() {
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
        user1.setEmailId("suryanshraj99@gmail.com");
        user1.setGender("male");
        user1.setPassword("ksbjdckbcd");

        user2 = new User();
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
        appointments.add(appointment);
        user2.setName("Harshit");
        user2.setRole("user");
        user2.setPhoneNumber(1234567890);
        user2.setIdCard("Aadhar");
        address.setStreet("Bank Colony");
        address.setPlotNo("C-32");
        address.setTown("Sigra");
        address.setDistrict("Varanasi");
        address.setState("Uttar Pradesh");
        address.setPincode(221005);
        user2.setAddress(address);
        user2.setAppointments(appointments);
        user2.setDob(localDate);
        user2.setEmailId("harshit@gmail.com");
        user2.setGender("male");
        user2.setPassword("ksbjdckbcd");

        userList.add(user1);
        userList.add(user2);
    }

    @AfterEach
    public void tearDown() {
        user1 = user2 = null;
        userList = null;
    }

    @Test
    public void givenUserToSaveThenShouldReturnSavedUser() throws UserAlreadyExistsException {
        User savedUser = userService.saveUser(user1);
        assertNotNull(savedUser);
        assertEquals(user1.getEmailId(), savedUser.getEmailId());
    }

    @Test
    public void givenGetAllUsersThenShouldReturnListOfAllUsers() {
        List<User> userList = (List<User>) userService.getAllUsers();
        assertNotNull(userList);
    }
}
