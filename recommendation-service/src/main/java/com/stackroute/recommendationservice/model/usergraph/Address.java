package com.stackroute.recommendationservice.model.usergraph;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/*
 * Address class for User's address
 */
@Node(labels = "UserAddress")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    private String town;
    private String state;
}
