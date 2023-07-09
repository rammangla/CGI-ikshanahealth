package com.stackroute.caretakerservice.service;

import com.stackroute.caretakerservice.exception.CaretakerAlreadyExistsException;
import com.stackroute.caretakerservice.exception.CaretakerNotFoundException;
import com.stackroute.caretakerservice.model.Appointments;
import com.stackroute.caretakerservice.model.Caretaker;
import com.stackroute.caretakerservice.repository.CaretakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CaretakerServiceImpl implements CaretakerService{
    private CaretakerRepository caretakerRepository;

    @Autowired
    public CaretakerServiceImpl(CaretakerRepository caretakerRepository) {
        super();
        this.caretakerRepository = caretakerRepository;
    }

    //Caretaker service function to save a particular caretaker
    @Override
    public Caretaker saveCaretaker(Caretaker caretaker) throws CaretakerAlreadyExistsException {
        if(caretakerRepository.findById(caretaker.getEmailId()).isPresent()){
            throw new CaretakerAlreadyExistsException();
        }
        else {
            return caretakerRepository.save(caretaker);
        }
    }

    //Caretaker service function to show all caretakers
    @Override
    public List<Caretaker> getAllCaretakers() {
        return (List<Caretaker>) caretakerRepository.findAll();

    }

    //Caretaker service function to show a particular caretaker
    @Override
    public Caretaker getCaretakerbyId(String emailId) throws CaretakerNotFoundException {
        if(caretakerRepository.findById(emailId).isPresent()) {
            return caretakerRepository.findById(emailId).get();
        }
        else {
            throw new CaretakerNotFoundException();
        }

    }

    //Caretaker service function to delete a particular caretaker
    @Override
    public Caretaker deleteCaretaker(String emailId) throws CaretakerNotFoundException {
        if(caretakerRepository.findById(emailId).isPresent()) {
            caretakerRepository.deleteById(emailId);
            return caretakerRepository.findById(emailId).get();
        }
        else {
            throw new CaretakerNotFoundException();
        }
    }

    //Caretaker service function to update a particular caretaker
    @Override
    public Caretaker updateCaretaker(Caretaker caretaker) throws CaretakerNotFoundException {
        if(caretakerRepository.findById(caretaker.getEmailId()).isPresent()) {
//            Caretaker newCaretaker = caretakerRepository.findById(emailId).get();
            Caretaker getCaretaker = new Caretaker();
            getCaretaker.setName(caretaker.getName());
            getCaretaker.setEmailId(caretaker.getEmailId());
            getCaretaker.setPassword(caretaker.getPassword());
            getCaretaker.setDob(caretaker.getDob());
            getCaretaker.setGender(caretaker.getGender());
            getCaretaker.setAddress(caretaker.getAddress());
            getCaretaker.setIdCard(caretaker.getIdCard());
            getCaretaker.setTotalExperience(caretaker.getTotalExperience());
            getCaretaker.setFees(caretaker.getFees());
            getCaretaker.setPhoneNumber(caretaker.getPhoneNumber());
            getCaretaker.setAppointments(caretaker.getAppointments());
            getCaretaker.setBookedAppointments(caretaker.getBookedAppointments());
            getCaretaker.setSpecialization(caretaker.getSpecialization());
            getCaretaker.setServices(caretaker.getServices());
            getCaretaker.setRole(caretaker.getRole());
            Caretaker updateCaretaker = caretakerRepository.save(getCaretaker);
            return updateCaretaker;

        }
        else {
            throw new CaretakerNotFoundException();
        }
    }
}
