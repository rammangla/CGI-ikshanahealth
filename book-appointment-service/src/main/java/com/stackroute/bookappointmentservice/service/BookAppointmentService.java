package com.stackroute.bookappointmentservice.service;

import com.stackroute.bookappointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.bookappointmentservice.exception.AppointmentNotFoundException;
import com.stackroute.bookappointmentservice.model.BookAppointment;

import java.util.List;

public interface BookAppointmentService {
    BookAppointment saveAppointment(BookAppointment bookAppointment) throws AppointmentAlreadyExistsException;
    BookAppointment deleteAppointment(String id) throws AppointmentNotFoundException;
    BookAppointment updateAppointment(BookAppointment bookAppointment) throws AppointmentNotFoundException;
}