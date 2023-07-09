package com.stackroute.schedularservice.repository;

import com.stackroute.schedularservice.domain.Scheduler;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends CrudRepository<Scheduler,String> {

}
