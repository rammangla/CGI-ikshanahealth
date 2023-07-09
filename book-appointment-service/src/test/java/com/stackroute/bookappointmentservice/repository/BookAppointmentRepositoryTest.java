package com.stackroute.bookappointmentservice.repository;

import com.stackroute.bookappointmentservice.model.BookAppointment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class BookAppointmentRepositoryTest {
    @Autowired
    private BookAppointmentRepository bookAppointmentRepository;
    private BookAppointment bookAppointment;

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
    public void givenAppointmentToSaveThenShouldReturnSavedAppointment() {
        bookAppointmentRepository.save(bookAppointment);
        BookAppointment fetchedAppointment = bookAppointmentRepository.findById(bookAppointment.getId()).get();
        assertEquals("1", fetchedAppointment.getId());
    }

    @Test
    public void givenUserEmailIdToDeleteThenShouldReturnDeletedAppointment() {
        bookAppointmentRepository.save(bookAppointment);
        bookAppointmentRepository.deleteById(bookAppointment.getId());
        Optional optional = bookAppointmentRepository.findById(bookAppointment.getId());
        assertEquals(Optional.empty(), optional);
    }
}
