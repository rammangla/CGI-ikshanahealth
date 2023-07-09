package com.stackroute.recommendationservice.repository;


import com.stackroute.recommendationservice.model.caretakergraph.Caretaker;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaretakerRepository extends Neo4jRepository<Caretaker, String> {
}