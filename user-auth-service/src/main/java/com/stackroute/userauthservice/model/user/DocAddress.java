package com.stackroute.userauthservice.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocAddress {
    private String clinicName;
    private String plotNo;
    private String street;
    private String town;
    private String district;
    private String state;
    private long pincode;
}
