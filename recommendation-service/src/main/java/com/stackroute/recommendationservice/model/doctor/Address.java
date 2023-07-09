package com.stackroute.recommendationservice.model.doctor;

import lombok.Data;

@Data
public class Address {
    private String clinicName;
    private String plotNo;
    private String street;
    private String town;
    private String district;
    private String state;
    private long pinCode;
}
