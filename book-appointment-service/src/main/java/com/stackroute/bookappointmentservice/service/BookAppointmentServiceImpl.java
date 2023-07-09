package com.stackroute.bookappointmentservice.service;

import com.stackroute.bookappointmentservice.exception.AppointmentAlreadyExistsException;
import com.stackroute.bookappointmentservice.exception.AppointmentNotFoundException;
import com.stackroute.bookappointmentservice.model.BookAppointment;
import com.stackroute.bookappointmentservice.repository.BookAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookAppointmentServiceImpl implements BookAppointmentService {

    private BookAppointmentRepository appointmentRepository;

    @Autowired
    public BookAppointmentServiceImpl(BookAppointmentRepository appointmentRepository) {
        super();
        this.appointmentRepository = appointmentRepository;
    }

    /*
     * Appointment service function to save a particular appointment
     */
    @Override
    public BookAppointment saveAppointment(BookAppointment bookAppointment) throws AppointmentAlreadyExistsException {
        // TODO Auto-generated method stub
        if(appointmentRepository.findById(bookAppointment.getId()).isPresent()) {
            throw new AppointmentAlreadyExistsException();
        }
        else {
            return appointmentRepository.save(bookAppointment);
        }
    }

    /*
     * Appointment service function to delete a particular appointment
     */
    @Override
    public BookAppointment deleteAppointment(String id) throws AppointmentNotFoundException {
        // TODO Auto-generated method stub
        Optional<BookAppointment> usrOpt = appointmentRepository.findById(id);
        if(usrOpt.isPresent()) {
            BookAppointment userRet = usrOpt.get();
            appointmentRepository.deleteById(id);
            return userRet;
        }
        else {
            throw new AppointmentNotFoundException();
        }
    }

    /*
     * Appointment service function to update a particular appointment
     */
    @Override
    public BookAppointment updateAppointment(BookAppointment bookAppointment) throws AppointmentNotFoundException {
        // TODO Auto-generated method stub
        if(appointmentRepository.findById(bookAppointment.getId()).isPresent()) {
            BookAppointment getBookAppointment = new BookAppointment();
            getBookAppointment.setAppointmentDate(bookAppointment.getAppointmentDate());
            getBookAppointment.setUser(bookAppointment.getUser());
            getBookAppointment.setDoctor(bookAppointment.getDoctor());
            getBookAppointment.setAppointmentId(bookAppointment.getAppointmentId());
            getBookAppointment.setSlot(bookAppointment.getSlot());
            BookAppointment updatedBookAppointment = appointmentRepository.save(getBookAppointment);
            return updatedBookAppointment;
        }
        else {
            throw new AppointmentNotFoundException();
        }
    }
}
