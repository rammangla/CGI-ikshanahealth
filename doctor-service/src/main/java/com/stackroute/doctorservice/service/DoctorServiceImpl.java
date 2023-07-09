package com.stackroute.doctorservice.service;

import com.stackroute.doctorservice.exception.DoctorAlreadyExistsException;
import com.stackroute.doctorservice.exception.DoctorNotFoundException;
import com.stackroute.doctorservice.model.Doctor;
import com.stackroute.doctorservice.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private DoctorRepository doctorRepository;

    /**
     * Constructor based Dependency injection to inject DoctorRepository here
     */
    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        super();
        this.doctorRepository = doctorRepository;
    }
    /**
     * Implementation of saveDoctor method
     */
    @Override
    public Doctor saveDoctor(Doctor doctor) throws DoctorAlreadyExistsException {
        // TODO Auto-generated method stub
        if(doctorRepository.findById(doctor.getEmailId()).isPresent()) {
            throw new DoctorAlreadyExistsException();
        }
        else {
            return doctorRepository.save(doctor);
        }
    }
    /**
     * Implementation of getAllDoctors method
     */
    @Override
    public List<Doctor> getAllDoctors() {
        return (List<Doctor>) doctorRepository.findAll();
    }
    /**
     * Implementation of getDoctorById method
     */
    @Override
    public Doctor getDoctorbyId(String emailId) throws DoctorNotFoundException {
        // TODO Auto-generated method stub
        if(doctorRepository.findById(emailId).isPresent()) {

            return doctorRepository.findById(emailId).get();
        }
        else {
            throw new DoctorNotFoundException();
        }
    }
    /**
     * Implementation of deleteDoctorById method
     */
    @Override
    public Doctor deleteDoctor(String emailId) throws DoctorNotFoundException {
        // TODO Auto-generated method stub
        if(doctorRepository.findById(emailId).isPresent()) {
            doctorRepository.deleteById(emailId);
            return doctorRepository.findById(emailId).get();
        }
        else {
            throw new DoctorNotFoundException();
        }
    }

    /**
     * Implementation of updateDoctor method
     */
    @Override
    public Doctor updateDoctor(Doctor doctor) throws DoctorNotFoundException {
        // TODO Auto-generated method stub
        if(doctorRepository.findById(doctor.getEmailId()).isPresent()) {
            Doctor getDoctor = new Doctor();
            getDoctor.setName(doctor.getName());
            getDoctor.setEmailId(doctor.getEmailId());
            getDoctor.setPassword(doctor.getPassword());
            getDoctor.setPhoneNumber(doctor.getPhoneNumber());
            getDoctor.setDob(doctor.getDob());
            getDoctor.setGender(doctor.getGender());
            getDoctor.setIdCard(doctor.getIdCard());
            getDoctor.setEducationalQualification(doctor.getEducationalQualification());
            getDoctor.setAddress(doctor.getAddress());
            getDoctor.setLicenseNumber(doctor.getLicenseNumber());
            getDoctor.setSpecialization(doctor.getSpecialization());
            getDoctor.setTotalExperience(doctor.getTotalExperience());
            getDoctor.setFees(doctor.getFees());
            getDoctor.setAppointments(doctor.getAppointments());
            getDoctor.setFacilities(doctor.getFacilities());
            getDoctor.setRole(doctor.getRole());
            Doctor updatedDoctor = doctorRepository.save(getDoctor);
            return updatedDoctor;
        }
        else {
            throw new DoctorNotFoundException();
        }
    }
}
