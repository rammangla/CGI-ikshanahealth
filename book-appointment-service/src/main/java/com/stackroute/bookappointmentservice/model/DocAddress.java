package com.stackroute.bookappointmentservice.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class DocAddress implements Serializable {
    private String clinicName;
    private String plotNo;
    private String street;
    private String town;
    private String district;
    private String state;
    private long pincode;
}
