package com.stackroute.schedularservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {


    @ExceptionHandler(value= DoctorNotFoundException.class)
    public ResponseEntity<String> returnDoctorNotFoundException(DoctorNotFoundException doctorNotFoundExcetion)
    {

        return new ResponseEntity<String>(doctorNotFoundExcetion.getMessage(), HttpStatus.CONFLICT);
    }
}

