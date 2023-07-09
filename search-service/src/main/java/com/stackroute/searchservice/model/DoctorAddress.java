package com.stackroute.searchservice.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

//Doctor Address Model
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorAddress implements Serializable {
        private String clinicName;
        private String plotNo;
        private String street;
        private String town;
        private String district;
        private String state;
        private long pincode;
}
