package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.DoctorNotFoundException;
import com.stackroute.recommendationservice.model.doctorgraph.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAllDoctors();

    Doctor getDoctorById(String emailId) throws DoctorNotFoundException;

    Doctor saveDoctor(Doctor doctor);

    void deleteDoctor(String emailId) throws DoctorNotFoundException;

    List<Doctor> getRecommendations(String emailId);

}