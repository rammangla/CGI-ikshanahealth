package com.stackroute.recommendationservice.model.doctorgraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/*
 * Address class for Doctor's Address
 */
@Node(labels = "DoctorAddress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    private String town;
    private String state;
}
