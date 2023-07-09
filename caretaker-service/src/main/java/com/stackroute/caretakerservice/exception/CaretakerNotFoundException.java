package com.stackroute.caretakerservice.exception;

//Caretaker defined exception for conditions in which user is not found
public class CaretakerNotFoundException extends Exception {

    private String message;

    public CaretakerNotFoundException() {

    }
    public CaretakerNotFoundException(String message) {
        this.message = message;
    }
}
