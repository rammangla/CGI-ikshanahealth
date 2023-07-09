package com.stackroute.schedularservice.domain;

import com.stackroute.schedularservice.service.QuartzJobService;
import com.stackroute.schedularservice.service.SchedulerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.core.ApplicationContext;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
/*package com.stackroute.schedularservice.domain;

import com.stackroute.schedularservice.Model.Scheduler;
import lombok.SneakyThrows;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.stackroute.schedularservice.Serivce.SchedulerService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class Quartzjob implements Job {

       @Autowired
       SchedulerService schedulerService;


     @Autowired
     Scheduler scheduler;

    @SneakyThrows
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap =context.getMergedJobDataMap();
        Map<String,String> map1 = scheduler.getSlots();

         Date date=context.getScheduledFireTime();

         Date ScheduleTime=new SimpleDateFormat("HH:mm:ss").parse(date.toString());


         if((ScheduleTime.compareTo(new Date(0,0,0,12,0,0)))<=0)
         {
             map1.put("Morning",ScheduleTime.toString());
         }

        else if(((ScheduleTime.compareTo(new Date(0,0,0,12,0,0)))>=0) &&
                 (ScheduleTime.compareTo(new Date(0,0,0,15,0,0))<=0))
        {
            map1.put("Afternoon",ScheduleTime.toString());
        }

        if((ScheduleTime.compareTo(new Date(0,0,0,16,0,0)))>0)
        {
            map1.put("Evening",ScheduleTime.toString());
        }
        scheduler.setSlots(map1);
        schedulerService.Save(scheduler);
         map1.clear();
    }
} */




/* Job Class */
@Slf4j
@Component
@DisallowConcurrentExecution
public class QuartzJob extends QuartzJobBean {

  /*  private SchedulerServiceImpl schedulerService;

    @Autowired
    public QuartzJob(SchedulerServiceImpl schedulerService) {
        this.schedulerService = schedulerService;
    }
    */
    @Autowired
    QuartzJobService quartzJobService;

    @Override
    public void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        try {
            ApplicationContext applicationContext = (ApplicationContext)
                    jobExecutionContext.getScheduler().getContext().get("applicationContext");
            log.info("Job ** {} ** starting @ {}", jobExecutionContext.getJobDetail().getKey().getName(), jobExecutionContext.getFireTime());
                    //.getJobDetail().getKey().getName(), context.getFireTime());
            quartzJobService.JobMethod();
            log.info("Job ** {} ** completed.  Next job scheduled @ {}", jobExecutionContext.getJobDetail().getKey().getName(), jobExecutionContext.getNextFireTime());

        /*
            ApplicationContext applicationContext = (ApplicationContext)
                    jobExecutionContext.getScheduler().getContext().get("applicationContext");


            List<Scheduler> schedulerList;
            schedulerList = schedulerService.getAll();

            Map<String, Integer> map1;
            for (Scheduler scheduler : schedulerList) {
                map1 = scheduler.getSlots();
                map1.put("todayMorning", map1.get("tomorrowMorning"));
                map1.put("todayAfternoon", map1.get("tomorrowAfternoon"));
                map1.put("todayEvening", map1.get("tomorrowEvening"));
                map1.put("tomorrowMorning", map1.get("overmorrowMorning"));
                map1.put("tomorrowAfternoon", map1.get("overmorrowAfternoon"));
                map1.put("tomorrowEvening", map1.get("overmorrowEvening"));
                map1.put("OvermorrowMorning", 0);
                map1.put("OvermorrowAfternoon", 0);
                map1.put("OvermorrowEvening", 0);
                scheduler.setSlots(map1);
                schedulerService.save(scheduler);
                map1.clear();

            }
            */
        }
        catch(Exception e) {

        }
    }
}

