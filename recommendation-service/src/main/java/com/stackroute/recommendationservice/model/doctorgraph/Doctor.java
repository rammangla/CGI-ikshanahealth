package com.stackroute.recommendationservice.model.doctorgraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/*
 * Doctor model class to be used in graph database
 */
@Node(labels = "Doctor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {
    @Id
    private String emailId;
    private String name;
    private String gender;
    private String educationalQualification;
    @Relationship(type = "LOCATED_IN")
    private Address address;
    @Relationship(type = "SPECIALIZED_IN")
    private Specialization specialization;
}
