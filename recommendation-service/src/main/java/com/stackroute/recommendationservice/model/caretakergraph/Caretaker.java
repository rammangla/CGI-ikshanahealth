package com.stackroute.recommendationservice.model.caretakergraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
/*
 * Caretaker Model class to be used in graph database
 */
@Node(labels = "Caretaker")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Caretaker {
    @Id
    private String emailId;
    private String name;
    private String gender;
    private String totalExperience;
    private String fess;
    @Relationship(type = "LOCATED_IN")
    private Address address;
}
