package com.stackroute.searchservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

//Doctor Entity model for storing in elastic search
@Document(indexName = "doctor")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoctorEntity {

//    private String Id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    @Id
    private String emailId;
    @Field(type = FieldType.Text)
    private String password;
    @Field(type = FieldType.Long)
    private Long phonenumber;
    @Field(type = FieldType.Date)
    private Date dob;
    @Field(type = FieldType.Text)
    private String gender;
    @Field(type = FieldType.Text)
    private String idCard;
    @Field(type = FieldType.Text)
    private String educationalQualification;
    @Field(type = FieldType.Text)
    private String addressClinicName;
    @Field(type = FieldType.Text)
    private String addressPlotNo;
    @Field(type = FieldType.Text)
    private String addressStreet;
    @Field(type = FieldType.Text)
    private String addressTown;
    @Field(type = FieldType.Text)
    private String addressDistrict;
    @Field(type = FieldType.Text)
    private String addressState;
    @Field(type = FieldType.Long)
    private long addressPincode;
    @Field(type = FieldType.Text)
    private String licenseno;
    @Field(type = FieldType.Text)
    private String specialization;
    @Field(type = FieldType.Text)
    private String totalexperience;
    @Field(type = FieldType.Text)
    private String fees;
}
