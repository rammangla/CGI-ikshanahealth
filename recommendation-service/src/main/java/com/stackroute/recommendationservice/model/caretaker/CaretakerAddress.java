package com.stackroute.recommendationservice.model.caretaker;

import lombok.Data;

@Data
public class CaretakerAddress {
    private String plotNo;
    private String street;
    private String town;
    private String district;
    private String state;
    private long pincode;
}
