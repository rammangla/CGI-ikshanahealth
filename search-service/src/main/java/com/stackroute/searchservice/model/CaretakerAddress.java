package com.stackroute.searchservice.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

//Caretaker Address model
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CaretakerAddress implements Serializable {
    private String plotNo;
    private String street;
    private String town;
    private String district;
    private String state;
    private long pincode;
}
