package com.stackroute.searchservice.service;

import com.stackroute.searchservice.exception.SpecializationNotFoundException;
import com.stackroute.searchservice.exception.TownNotFoundException;
import com.stackroute.searchservice.exchange.SearchResponse;
import com.stackroute.searchservice.model.Caretaker;
import com.stackroute.searchservice.model.Doctor;

public interface SearchService {

    void saveDoctor(Doctor doctor);
    void saveCaretaker(Caretaker caretaker);
    SearchResponse searchByLocation(String addresstown) throws TownNotFoundException;
    SearchResponse searchByTownAndSpecialization(String addresstown,String specialization) throws SpecializationNotFoundException;

}
