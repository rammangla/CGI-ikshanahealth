package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.DoctorNotFoundException;
import com.stackroute.recommendationservice.model.doctorgraph.Doctor;
import com.stackroute.recommendationservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl (DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    public Doctor getDoctorById(String emailId) throws DoctorNotFoundException {
        if(doctorRepository.findById(emailId).isPresent()) {
            return doctorRepository.findById(emailId).get();
        }
        else {
            throw new DoctorNotFoundException();
        }
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctor(String emailId) throws DoctorNotFoundException {
        if (doctorRepository.findById(emailId).isPresent()) {
            doctorRepository.deleteById(emailId);
        }
        else {
            throw new DoctorNotFoundException();
        }
    }

    @Override
    public List<Doctor> getRecommendations(String emailId) {
        return (List<Doctor>) doctorRepository.getRecommendations(emailId);
    }
}