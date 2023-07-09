package com.stackroute.searchservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;

//Caretaker Entity model for storing in Elastic search
@Document(indexName = "caretaker")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CaretakerEntity {
    //private String Id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    @Id
    private String emailId;
    @Field(type = FieldType.Text)
    private String password;
    @Field(type = FieldType.Date)
    private Date dob;
    @Field(type = FieldType.Text)
    private String gender;
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
    private String addressPincode;
    @Field(type = FieldType.Text)
    private String idCard;
    @Field(type = FieldType.Text)
    private String totalExperience;
    @Field(type = FieldType.Text)
    private String fees;
    @Field(type = FieldType.Text)
    private String specialization;
    @Field(type = FieldType.Text)
    private String role;
}
