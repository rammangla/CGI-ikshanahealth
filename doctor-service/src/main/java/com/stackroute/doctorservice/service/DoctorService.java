package com.stackroute.doctorservice.service;

import com.stackroute.doctorservice.exception.DoctorAlreadyExistsException;
import com.stackroute.doctorservice.exception.DoctorNotFoundException;
import com.stackroute.doctorservice.model.Doctor;

import java.util.List;

public interface DoctorService {
    /**
     * AbstractMethod to save a doctor
     */
    Doctor saveDoctor(Doctor doctor) throws DoctorAlreadyExistsException;

    /**
     * AbstractMethod to get all doctors
     */
    List<Doctor> getAllDoctors();

    /**
     * AbstractMethod to get doctor by emailid
     */
    Doctor getDoctorbyId(String emailId) throws DoctorNotFoundException;
    /**
     * AbstractMethod to delete doctor by emailid
     */
    Doctor deleteDoctor(String emailId) throws DoctorNotFoundException;
    /**
     * AbstractMethod to update a doctor
     */
    Doctor updateDoctor(Doctor doctor) throws DoctorNotFoundException;

}
