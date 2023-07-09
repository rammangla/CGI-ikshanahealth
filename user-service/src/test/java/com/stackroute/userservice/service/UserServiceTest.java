package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.Appointment;
import com.stackroute.userservice.model.DocAddress;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private User user1, user2;
    private List<User> userList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

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

        optional = Optional.of(user1);
    }

    @AfterEach
    public void tearDown() {
        user1 = null;
    }

    @Test
    public void givenUserToSaveThenShouldReturnSavedUser() throws UserAlreadyExistsException {
        when(userRepository.save(any())).thenReturn(user1);
        assertEquals(user1, userService.saveUser(user1));
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void givenUserToSaveThenShouldNotReturnSavedUser() {
        when(userRepository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.saveUser(user1);
        });
        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void givenGetAllUsersThenShouldReturnListOfAllUsers() {
        userRepository.save(user1);
        when(userRepository.findAll()).thenReturn(userList);
        List<User> userList1 = userService.getAllUsers();
        assertEquals(userList, userList1);
        verify(userRepository, times(1)).save(user1);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void givenUserIdThenShouldReturnRespectiveUser() throws UserNotFoundException {
        when(userRepository.findById(anyString())).thenReturn(Optional.of(user1));
        User retrieveduser = userService.getUserById(user1.getEmailId());
        assertEquals("suryanshraj99@gmail.com",retrieveduser.getEmailId());
        verify(userRepository, times(2)).findById(anyString());

    }

    @Test
    void givenUserIdToDeleteThenShouldReturnDeletedUser() throws UserNotFoundException {
        when(userRepository.findById(user1.getEmailId())).thenReturn(optional);
        User deleteduser = userService.deleteUser("suryanshraj99@gmail.com");
        assertEquals("suryanshraj99@gmail.com", deleteduser.getEmailId());
        verify(userRepository, times(1)).findById(user1.getEmailId());
        verify(userRepository, times(1)).deleteById(user1.getEmailId());
    }

    @Test
    void givenUserIdToDeleteThenShouldNotReturnDeletedUser() throws UserNotFoundException {
        when(userRepository.findById(user1.getEmailId())).thenReturn(optional);
        User deletedUser = userService.deleteUser("suryanshraj99@gmail.com");
        verify(userRepository, times(1)).findById(user1.getEmailId());
    }

    @Test
    public void givenUserToUpdateThenShouldReturnUpdatedUser() throws UserNotFoundException {
        when(userRepository.findById(user1.getEmailId())).thenReturn(optional);
        when(userRepository.save(user1)).thenReturn(user1);
        user1.setName("harshit");
        User user2 = userService.updateUser(user1);
        assertEquals(user2.getName(), "harshit");
        verify(userRepository, times(1)).save(user1);
        verify(userRepository, times(1)).findById(user2.getEmailId());
    }

    @Test
    public void givenUserToUpdateThenShouldNotReturnUpdatedUser() throws UserNotFoundException {
        when(userRepository.findById(user1.getEmailId())).thenReturn(optional);
        User user2 = userService.updateUser(user1);
        verify(userRepository, times(1)).findById(user1.getEmailId());
    }
}
