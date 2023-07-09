package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.Appointment;
import com.stackroute.userservice.model.DocAddress;
import com.stackroute.userservice.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserRepositoryIntegrationTest {
    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    public void setUp() {
        LocalDate localDate = LocalDate.now();
        user = new User();
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
        user.setName("Harry");
        user.setRole("user");
        user.setPhoneNumber(1234567890);
        user.setIdCard("Aadhar");
        address.setStreet("Bank Colony");
        address.setPlotNo("C-32");
        address.setTown("Sigra");
        address.setDistrict("Varanasi");
        address.setState("Uttar Pradesh");
        address.setPincode(221005);
        user.setAddress(address);
        user.setAppointments(appointments);
        user.setDob(localDate);
        user.setEmailId("suryanshraj99@gmail.com");
        user.setGender("male");
        user.setPassword("ksbjdckbcd");
    }

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
        user = null;
    }

    @Test
    public void givenUserToSaveThenShouldReturnSavedUser() {
        userRepository.save(user);
        User fetchedUser = userRepository.findById(user.getEmailId()).get();
        assertEquals("suryanshraj99@gmail.com", fetchedUser.getEmailId());
    }


    @Test
    public void givenGetAllUsersThenShouldReturnListOfAllUsers() {

        userRepository.save(user);

        List<User> userList = (List<User>) userRepository.findAll();
        assertEquals("suryanshraj99@gmail.com", userList.get(0).getEmailId());
    }

    @Test
    public void givenEmailIdThenShouldReturnRespectiveUser() {
        userRepository.save(user);
        Optional<User> optional = userRepository.findById(user.getEmailId());
        assertEquals(user.getEmailId(), optional.get().getEmailId());
        assertEquals(user.getName(), optional.get().getName());
    }

    @Test
    public void givenUserEmailIdToDeleteThenShouldReturnDeletedUser() {
        userRepository.save(user);
        userRepository.deleteById(user.getEmailId());
        Optional optional = userRepository.findById(user.getEmailId());
        assertEquals(Optional.empty(), optional);
    }
}
