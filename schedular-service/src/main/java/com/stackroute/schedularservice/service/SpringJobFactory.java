package com.stackroute.schedularservice.service;


import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

// Injecting Object to our Job
public final class SpringJobFactory extends SpringBeanJobFactory implements ApplicationContextAware {


    private transient AutowireCapableBeanFactory beanFactory;
    @Override
    public void setApplicationContext(final ApplicationContext context) {
        beanFactory = context.getAutowireCapableBeanFactory();
    }

    @Override
    protected Object createJobInstance(final TriggerFiredBundle bundle) throws Exception {
        final Object job = super.createJobInstance(bundle);
        beanFactory.autowireBean(job);
        return job;

    }
}














/*
import com.stackroute.schedularservice.domain.Quartzjob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpringJobFactory {

  public void ScheduleJob() throws SchedulerException {

    /* Created JobDetail instance using JobBuilder */
/*
    JobDetail jobDetail = JobBuilder.newJob(Quartzjob.class).
            withIdentity("SimpleJob").build();


    /* Created trigger instance using TriggerBuilder */
/*
    Trigger trigger = TriggerBuilder.newTrigger().withIdentity("crontrigger", "crontriggergroup1")
            .withSchedule(CronScheduleBuilder.cronSchedule("* 0/15 9-11,12-15,16-20 * * MON-WED *")).build();


    /* passes the JobDetail instance and Trigger instance in Scheduler */
/*
    SchedulerFactory schFactory = new StdSchedulerFactory();
    Scheduler sch = schFactory.getScheduler();
    sch.start();
    sch.scheduleJob(jobDetail, trigger);
  }

} */

