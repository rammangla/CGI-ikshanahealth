package com.stackroute.schedularservice.service;

import com.stackroute.schedularservice.domain.Scheduler;
import com.stackroute.schedularservice.exceptions.DoctorNotFoundException;

import java.util.List;
/*
import com.stackroute.schedularservice.Model.Scheduler;

import java.util.List;

public interface SchedulerService {

   void Save(Scheduler scheduler);

   List<Scheduler> findAll();
}
*/
public interface SchedulerService {

    Scheduler getSlots(String emailId);
    Scheduler save(Scheduler scheduler);
    Scheduler putSlots(String emailId,String key,String value) throws DoctorNotFoundException;
    List<Scheduler> getAll();
    Scheduler deleteSlots(String emailId) throws DoctorNotFoundException;
}

