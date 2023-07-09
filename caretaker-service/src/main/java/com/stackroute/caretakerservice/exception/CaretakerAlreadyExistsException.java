package com.stackroute.caretakerservice.exception;

//Caretaker defined exception for conditions in which caretaker already exists
public class CaretakerAlreadyExistsException extends Exception {
    private String message;

    public CaretakerAlreadyExistsException() {

    }
     public CaretakerAlreadyExistsException(String message) {
        this.message = message;
     }
}
