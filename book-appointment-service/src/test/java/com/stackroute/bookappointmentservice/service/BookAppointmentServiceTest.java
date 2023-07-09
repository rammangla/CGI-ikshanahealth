package com.stackroute.bookappointmentservice.service;

import com.stackroute.bookappointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.bookappointmentservice.exception.AppointmentNotFoundException;
import com.stackroute.bookappointmentservice.model.BookAppointment;
import com.stackroute.bookappointmentservice.repository.BookAppointmentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookAppointmentServiceTest {
    @Mock
    private BookAppointmentRepository bookAppointmentRepository;

    @InjectMocks
    private BookAppointmentServiceImpl bookAppointmentService;

    private BookAppointment bookAppointment;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        LocalDate localDate = LocalDate.now();
        bookAppointment = new BookAppointment();
        bookAppointment.setId("1");
        optional = Optional.of(bookAppointment);
    }

    @AfterEach
    public void tearDown() {
        bookAppointmentRepository.deleteAll();
        bookAppointment = null;
    }

    @Test
    public void givenAppointmentToSaveThenShouldReturnSavedAppointment() throws AppointmentAlreadyExistsException {
        when(bookAppointmentRepository.save(any())).thenReturn(bookAppointment);
        assertEquals(bookAppointment, bookAppointmentService.saveAppointment(bookAppointment));
        verify(bookAppointmentRepository, times(1)).save(any());
    }

    @Test
    public void givenAppointmentToSaveThenShouldNotReturnSavedAppointment() {
        when(bookAppointmentRepository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            bookAppointmentService.saveAppointment(bookAppointment);
        });
        verify(bookAppointmentRepository, times(1)).save(any());
    }

    @Test
    void givenAppointmentIdToDeleteThenShouldReturnDeletedAppointment() throws AppointmentNotFoundException {
        when(bookAppointmentRepository.findById(bookAppointment.getId())).thenReturn(optional);
        BookAppointment deletedAppointment = bookAppointmentService.deleteAppointment("1");
        assertEquals("1", deletedAppointment.getId());
        verify(bookAppointmentRepository, times(1)).findById(bookAppointment.getId());
        verify(bookAppointmentRepository, times(1)).deleteById(bookAppointment.getId());
    }

    @Test
    void givenAppointmentIdToDeleteThenShouldNotReturnDeletedAppointment() throws AppointmentNotFoundException {
        when(bookAppointmentRepository.findById(bookAppointment.getId())).thenReturn(optional);
        BookAppointment deletedAppointment = bookAppointmentService.deleteAppointment("1");
        verify(bookAppointmentRepository, times(1)).findById(bookAppointment.getId());
    }

    @Test
    public void givenAppointmentToUpdateThenShouldReturnUpdatedAppointment() throws AppointmentNotFoundException {
        when(bookAppointmentRepository.findById(bookAppointment.getId())).thenReturn(optional);
        lenient().when(bookAppointmentRepository.save(bookAppointment)).thenReturn(bookAppointment);
        bookAppointment.setSlot("2:00-3:00");
        BookAppointment bookAppointment1 = bookAppointmentService.updateAppointment(bookAppointment);
        assertEquals(bookAppointment1.getSlot(), "2:00-3:00");
        verify(bookAppointmentRepository, times(1)).save(bookAppointment);
        verify(bookAppointmentRepository, times(1)).findById(bookAppointment1.getId());
    }

    @Test
    public void givenAppointmentToUpdateThenShouldNotReturnUpdatedAppointment() throws AppointmentNotFoundException {
        when(bookAppointmentRepository.findById(bookAppointment.getId())).thenReturn(optional);
        BookAppointment bookAppointment1 = bookAppointmentService.updateAppointment(bookAppointment);
        verify(bookAppointmentRepository, times(1)).findById(bookAppointment.getId());
    }
}
