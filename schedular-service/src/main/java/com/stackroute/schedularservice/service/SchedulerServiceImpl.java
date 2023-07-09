package com.stackroute.schedularservice.service;

import com.stackroute.schedularservice.domain.Scheduler;
import com.stackroute.schedularservice.exceptions.DoctorNotFoundException;
import com.stackroute.schedularservice.repository.ScheduleRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@NoArgsConstructor
public class SchedulerServiceImpl implements SchedulerService {

    private ScheduleRepository scheduleRepository;



    @Autowired
    public SchedulerServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }




    @Override
    public Scheduler getSlots(String emailId) {



        Optional optional = scheduleRepository.findById(emailId);
        if (optional.isPresent()) {

            return (Scheduler) optional.get();
        } else {
            Scheduler scheduler = new Scheduler();
            scheduler.setEmailId(emailId);
            Map<String, String> map = new LinkedHashMap<String, String>();
            /*
            map.put("todayMorningSubSlot1","9:00 AM-9:30 AM");
            map.put("todayMorningSubSlot2","9:30 AM-10:00 AM");
            map.put("todayMorningSubSlot3","10:00 AM-10:30 AM");
            map.put("todayMorningSubSlot4","10:30 AM-11:00 AM");

            map.put("todayAfternoonSubSlot1", "12 PM-12:30 PM");
            map.put("todayAfternoonSubSlot2", "12:30 PM-1:00 PM");
            map.put("todayAfternoonSubSlot3",  "1:00 PM-1:30 PM");
            map.put("todayAfternoonSubSlot4",  "1:30 PM-2:00 PM");

            map.put("todayEveningSubSlot1", "3:00 PM - 3:30 PM");
            map.put("todayEveningSubSlot2", "3:30 PM - 4:00 PM");
            map.put("todayEveningSubSlot3", "4:00 PM - 4:30 PM");
            map.put("todayEveningSubSlot4", "4:30 PM - 5:00 PM");

            map.put("tomorrowMorningSubSlot1", "9:00 AM-9:30 AM" );
            map.put("tomorrowMorningSubSlot2", "9:30 AM-10:00 AM" );
            map.put("tomorrowMorningSubSlot3", "10:00 AM-10:30 AM" );
            map.put("tomorrowMorningSubSlot4", "10:30 AM-11:00 AM" );

            map.put("tomorrowAfternoonSubSlot1", "12 PM-12:30 PM");
            map.put("tomorrowAfternoonSubSlot2", "12:30 PM-1:00 PM");
            map.put("tomorrowAfternoonSubSlot3", "1:00 PM-1:30 PM");
            map.put("tomorrowAfternoonSubSlot4",  "1:30 PM-2:00 PM");

            map.put("tomorrowEveningSubSlot1", "3:00 PM - 3:30 PM" );
            map.put("tomorrowEveningSubSlot2", "3:30 PM - 4:00 PM");
            map.put("tomorrowEveningSubSlot3", "4:00 PM - 4:30 PM");
            map.put("tomorrowEveningSubSlot4", "4:30 PM - 5:00 PM");

            map.put("OvermorrowMorningSubSlot1", "9:00 AM-9:30 AM");
            map.put("OvermorrowMorningSubSlot2", "9:30 AM-10:00 AM");
            map.put("OvermorrowMorningSubSlot3", "10:00 AM-10:30 AM");
            map.put("OvermorrowMorningSubSlot4", "10:30 AM-11:00 AM");

            map.put("OvermorrowAfternoonSubSlot1", "12 PM-12:30 PM");
            map.put("OvermorrowAfternoonSubSlot2", "12:30 PM-1:00 PM");
            map.put("OvermorrowAfternoonSubSlot3", "1:00 PM-1:30 PM");
            map.put("OvermorrowAfternoonSubSlot4", "1:30 PM-2:00 PM");

            map.put("OvermorrowEveningSubSlot1", "3:00 PM - 3:30 PM");
            map.put("OvermorrowEveningSubSlot2", "3:30 PM - 4:00 PM");
            map.put("OvermorrowEveningSubSlot3", "4:00 PM - 4:30 PM");
            map.put("OvermorrowEveningSubSlot4", "4:30 PM - 5:00 PM");

             */

            map.put("today_9:00-9:30", "Available");
            map.put("today_9:30-10:00", "Available");
            map.put("today_10:00-10:30", "Available");
            map.put("today_10:30-11:00", "Available");

            map.put("today_12:00-12:30", "Available");
            map.put("today_12:30-13:00", "Available");
            map.put("today_13:00-13:30", "Available");
            map.put("today_13:30-14:00", "Available");

            map.put("today_15:00-15:30", "Available");
            map.put("today_15:30-16:00", "Available");
            map.put("today_16:00-16:30", "Available");
            map.put("today_16:30-17:00", "Available");

            map.put("tomorrow_9:00-9:30", "Available");
            map.put("tomorrow_9:30-10:00", "Available");
            map.put("tomorrow_10:00-10:30", "Available");
            map.put("tomorrow_10:30-11:00", "Available");

            map.put("tomorrow_12:00-12:30", "Available");
            map.put("tomorrow_12:00-13:00", "Available");
            map.put("tomorrow_13:00-13:30", "Available");
            map.put("tomorrow_13:00-14:00", "Available");

            map.put("tomorrow_15:00-15:30", "Available");
            map.put("tomorrow_15:30-16:00", "Available");
            map.put("tomorrow_16:00-16:30", "Available");
            map.put("tomorrow_16:00-17:00", "Available");


            map.put("overmorrow_9:00-9:30", "Available");
            map.put("overmorrow_9:30-10:00", "Available");
            map.put("overmorrow_10:00-10:30", "Available");
            map.put("overmorrow_10:30-11:00", "Available");

            map.put("overmorrow_12:00-12:30", "Available");
            map.put("overmorrow_12:30-13:00", "Available");
            map.put("overmorrow_13:00-13:30", "Available");
            map.put("overmorrow_14:30-14:00", "Available");

            map.put("overmorrow_15:00-15:30", "Available");
            map.put("overmorrow_15:30-16:00", "Available");
            map.put("overmorrow_16:00-16:30", "Available");
            map.put("overmorrow_16:30-17:00", "Available");


            scheduler.setSlots(map);
            scheduleRepository.save(scheduler);
            return scheduler;
        }

    }



    @Override
    public Scheduler save(Scheduler scheduler) {
        return scheduleRepository.save(scheduler);

    }




    @Override
    public Scheduler putSlots(String emailId, String key, String value) throws  DoctorNotFoundException{
        System.out.println("Received key in putSlot - "+key);
        Optional optional = scheduleRepository.findById(emailId);
        Scheduler scheduler = new Scheduler();
        if (optional.isPresent()) {
            scheduler = (Scheduler) optional.get();
            Map<String, String> map = scheduler.getSlots();
            map.put(key, value);
            scheduler.setSlots(map);
            scheduleRepository.save(scheduler);
        }
        else
        {
            throw new DoctorNotFoundException("Doctor does not exists");
        }
        return scheduler;
    }



    @Override
    public List<Scheduler> getAll() {
        List<Scheduler> schedulerList = (List<Scheduler>) scheduleRepository.findAll();
        return schedulerList;
    }



    @Override
    public Scheduler deleteSlots(String emailId) throws DoctorNotFoundException{
         Optional optional=scheduleRepository.findById(emailId);
         Scheduler scheduler=new Scheduler();
         if(optional.isPresent())
         {
              scheduler=(Scheduler) optional.get();
              scheduleRepository.deleteById(emailId);
              return scheduler;
         }
         else
         {
               throw new DoctorNotFoundException("Doctor Not Found");
         }
    }

}

