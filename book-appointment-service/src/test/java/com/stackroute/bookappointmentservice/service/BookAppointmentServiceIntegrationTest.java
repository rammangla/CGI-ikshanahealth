package com.stackroute.bookappointmentservice.service;

import com.stackroute.bookappointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.bookappointmentservice.model.BookAppointment;
import com.stackroute.bookappointmentservice.repository.BookAppointmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookAppointmentServiceIntegrationTest {

    @Autowired
    private BookAppointmentRepository bookAppointmentRepository;

    @Autowired
    private BookAppointmentServiceImpl bookAppointmentService;

    BookAppointment bookAppointment = new BookAppointment();

    @BeforeEach
    public void setUp() {
        LocalDate localDate = LocalDate.now();
        bookAppointment = new BookAppointment();
        bookAppointment.setId("1");
    }

    @AfterEach
    public void tearDown() {
        bookAppointmentRepository.deleteAll();
        bookAppointment = null;
    }

    @Test
    public void givenUserToSaveThenShouldReturnSavedAppointment() throws AppointmentAlreadyExistsException {
        BookAppointment savedAppointment = bookAppointmentService.saveAppointment(bookAppointment);
        assertNotNull(savedAppointment);
        assertEquals(bookAppointment.getId(), savedAppointment.getId());
    }
}
