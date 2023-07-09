package com.stackroute.schedularservice.service;

import com.stackroute.schedularservice.domain.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QuartzJobService {
    private SchedulerServiceImpl schedulerService;

    @Autowired
    public QuartzJobService(SchedulerServiceImpl schedulerService) {
        this.schedulerService = schedulerService;
    }

    public void JobMethod() {
        List<Scheduler> schedulerList;
        schedulerList = schedulerService.getAll();
        Map<String, String> map1;
        for (Scheduler scheduler : schedulerList){
            map1 = scheduler.getSlots();

            /*

            map1.put("todayMorningSubSlot1", map1.get("tomorrowMorningSubSlot1"));
            map1.put("todayMorningSubSlot2",map1.get("tomorrowMorningSubSlot2"));
            map1.put("todayMorningSubSlot3",map1.get("tomorrowMorningSubSlot3"));
            map1.put("todayMorningSubSlot4",map1.get("tomorrowMorningSubSlot4"));

            map1.put("todayAfternoonSubSlot1", map1.get("tomorrowAfternoonSubSlot1"));
            map1.put("todayAfternoonSubSlot2", map1.get("tomorrowAfternoonSubSlot2"));
            map1.put("todayAfternoonSubSlot3", map1.get("tomorrowAfternoonSubSlot3"));
            map1.put("todayAfternoonSubSlot4", map1.get("tomorrowAfternoonSubSlot4"));

            map1.put("todayEveningSubSlot1", map1.get("tomorrowEveningSubSlot1"));
            map1.put("todayEveningSubSlot2", map1.get("tomorrowEveningSubSlot2"));
            map1.put("todayEveningSubSlot3", map1.get("tomorrowEveningSubSlot3"));
            map1.put("todayEveningSubSlot4", map1.get("tomorrowEveningSubSlot4"));

            map1.put("tomorrowMorningSubSlot1", map1.get("OvermorrowMorningSubSlot1"));
            map1.put("tomorrowMorningSubSlot2", map1.get("OvermorrowMorningSubSlot2"));
            map1.put("tomorrowMorningSubSlot3", map1.get("OvermorrowMorningSubSlot3"));
            map1.put("tomorrowMorningSubSlot4", map1.get("OvermorrowMorningSubSlot4"));

            map1.put("tomorrowAfternoonSubSlot1", map1.get("OvermorrowAfternoonSubSlot1"));
            map1.put("tomorrowAfternoonSubSlot2", map1.get("OvermorrowAfternoonSubSlot2"));
            map1.put("tomorrowAfternoonSubSlot3", map1.get("OvermorrowAfternoonSubSlot3"));
            map1.put("tomorrowAfternoonSubSlot4", map1.get("OvermorrowAfternoonSubSlot4"));

            map1.put("tomorrowEveningSubSlot1", map1.get("OvermorrowEveningSubSlot1"));
            map1.put("tomorrowEveningSubSlot2", map1.get("OvermorrowEveningSubSlot2"));
            map1.put("tomorrowEveningSubSlot3", map1.get("OvermorrowEveningSubSlot3"));
            map1.put("tomorrowEveningSubSlot4", map1.get("OvermorrowEveningSubSlot4"));

            map1.put("OvermorrowMorningSubSlot1", "9:00 AM-9:30 AM");
            map1.put("OvermorrowMorningSubSlot2", "9:30 AM-10:00 AM");
            map1.put("OvermorrowMorningSubSlot3", "10:00 AM-10:30 AM");
            map1.put("OvermorrowMorningSubSlot4", "10:30 AM-11:00 AM");

            map1.put("OvermorrowAfternoonSubSlot1", "12 PM-12:30 PM");
            map1.put("OvermorrowAfternoonSubSlot2", "12:30 PM-1:00 PM");
            map1.put("OvermorrowAfternoonSubSlot3", "1:00 PM-1:30 PM");
            map1.put("OvermorrowAfternoonSubSlot4", "1:30 PM-2:00 PM");

            map1.put("OvermorrowEveningSubSlot1", "3:00 PM - 3:30 PM");
            map1.put("OvermorrowEveningSubSlot2", "3:30 PM - 4:00 PM");
            map1.put("OvermorrowEveningSubSlot3", "4:00 PM - 4:30 PM");
            map1.put("OvermorrowEveningSubSlot4", "4:30 PM - 5:00 PM"); */

            map1.put("today_9:00-9:30", map1.get("tomorrow_9:00-9:30"));
            map1.put("today_9:30-10:00",map1.get("tomorrow_9:30-10:00"));
            map1.put("today_10:00-10:30",map1.get("tomorrow_10:00-10:30"));
            map1.put("today_10:30-11:00",map1.get("tomorrow_10:30-11:00"));

            map1.put("today_12:00-12:30", map1.get("tomorrow_12:00-12:30"));
            map1.put("today_12:30-13:00", map1.get("tomorrow_12:30-13:00"));
            map1.put("today_13:00-13:30", map1.get("tomorrow_13:00-13:30"));
            map1.put("today_13:30-14:00", map1.get("tomorrow_13:30-14:00"));

            map1.put("today_15:00-15:30", map1.get("tomorrow_15:00-15:30"));
            map1.put("today_15:30-16:00", map1.get("tomorrow_15:30-16:00"));
            map1.put("today_16:00-16:30", map1.get("tomorrow_16:00-16:30"));
            map1.put("today_16:30-17:00", map1.get("tomorrow_16:30-17:00"));

            map1.put("tomorrow_9:00-9:30", map1.get("overmorrow_9:00-9:30"));
            map1.put("tomorrow_9:30-10:00", map1.get("overmorrow_9:30-10:00"));
            map1.put("tomorrow_10:00-10:30", map1.get("overmorrow_10:00-10:30"));
            map1.put("tomorrow_10:30-11:00", map1.get("overmorrow_10:30-11:00"));

            map1.put("tomorrow_12:00-12:30", map1.get("overmorrow_12:00-12:30"));
            map1.put("tomorrow_12:30-13:00", map1.get("overmorrow_12:30-13:00"));
            map1.put("tomorrow_13:00-13:30", map1.get("overmorrow_13:00-13:30"));
            map1.put("tomorrow_13:30-14:00", map1.get("overmorrow_14:30-14:00"));

            map1.put("tomorrow_15:00-15:30", map1.get("overmorrow_15:00-15:30"));
            map1.put("tomorrow_15:30-16:00", map1.get("overmorrow_15:30-16:00"));
            map1.put("tomorrow_16:00-16:30", map1.get("overmorrow_16:00-16:30"));
            map1.put("tomorrow_16:30-17:00", map1.get("overmorrow_16:30-17:00"));

            map1.put("overmorrow_9:00-9:30", "Available");
            map1.put("overmorrow_9:30-10:00", "Available");
            map1.put("overmorrow_10:00-10:30", "Available");
            map1.put("overmorrow_10:30-11:00", "Available");

            map1.put("overmorrow_12:00-12:30", "Available");
            map1.put("overmorrow_12:30-13:00", "Available");
            map1.put("overmorrow_13:00-13:30", "Available");
            map1.put("overmorrow_14:30-14:00", "Available");

            map1.put("overmorrow_15:00-15:30", "Available");
            map1.put("overmorrow_15:30-16:00", "Available");
            map1.put("overmorrow_16:00-16:30", "Available");
            map1.put("overmorrow_16:30-17:00", "Available");

            schedulerService.save(scheduler);
            map1.clear();

        }
    }
}
