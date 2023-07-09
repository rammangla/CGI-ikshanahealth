package com.stackroute.recommendationservice.service;

import com.stackroute.recommendationservice.exception.CaretakerNotFoundException;
import com.stackroute.recommendationservice.model.caretakergraph.Caretaker;

import java.util.List;

public interface CaretakerService {
    List<Caretaker> getAllCaretakers();

    Caretaker getCaretakerById(String emailId) throws CaretakerNotFoundException;

    Caretaker saveCaretaker(Caretaker caretaker);

    void deleteCaretaker(String emailId) throws CaretakerNotFoundException;
}