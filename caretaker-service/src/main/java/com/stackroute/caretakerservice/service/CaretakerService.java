package com.stackroute.caretakerservice.service;

import com.stackroute.caretakerservice.exception.CaretakerAlreadyExistsException;
import com.stackroute.caretakerservice.exception.CaretakerNotFoundException;
import com.stackroute.caretakerservice.model.Caretaker;

import java.util.List;

public interface CaretakerService {

    Caretaker saveCaretaker (Caretaker caretaker) throws CaretakerAlreadyExistsException; //abstract method to save a caretaker

    List<Caretaker> getAllCaretakers(); //abstract method to get all caretaker


    Caretaker getCaretakerbyId(String emailId) throws CaretakerNotFoundException; //abstract method to get caretaker by emailId

    Caretaker deleteCaretaker(String emailId) throws CaretakerNotFoundException; //abstract method to delete caretaker by emailId

    Caretaker updateCaretaker(Caretaker caretaker) throws CaretakerNotFoundException; //abstract method to update caretaker by emailId
}
