package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.CaretakerNotFoundException;
import com.stackroute.recommendationservice.model.caretakergraph.Caretaker;
import com.stackroute.recommendationservice.repository.CaretakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaretakerServiceImpl implements CaretakerService{
    private CaretakerRepository caretakerRepository;

    @Autowired
    public CaretakerServiceImpl (CaretakerRepository caretakerRepository) {
        this.caretakerRepository = caretakerRepository;
    }

    @Override
    public List<Caretaker> getAllCaretakers() {
        return (List<Caretaker>) caretakerRepository.findAll();
    }

    @Override
    public Caretaker getCaretakerById(String emailId) throws CaretakerNotFoundException {
        if (caretakerRepository.findById(emailId).isPresent()) {
            return caretakerRepository.findById(emailId).get();
        }
        else {
            throw new CaretakerNotFoundException();
        }
    }

    @Override
    public Caretaker saveCaretaker(Caretaker caretaker) {
        return caretakerRepository.save(caretaker);
    }

    @Override
    public void deleteCaretaker(String emailId) throws CaretakerNotFoundException {
        if (caretakerRepository.findById(emailId).isPresent()) {
            caretakerRepository.deleteById(emailId);
        }
        else {
            throw new CaretakerNotFoundException();
        }
    }
}