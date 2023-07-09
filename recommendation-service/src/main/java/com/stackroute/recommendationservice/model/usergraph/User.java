package com.stackroute.recommendationservice.model.usergraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

/*
 * User model class to be used in graph database
 */
@Node(labels = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String emailId;
    private String name;
    private String gender;
    @Relationship(type = "LIVES_IN")
    private Address address;
}
