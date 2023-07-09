package com.stackroute.schedularservice.config;

import com.stackroute.schedularservice.domain.QuartzJob;
import com.stackroute.schedularservice.service.SpringJobFactory;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
public class SchedulerConfig {


    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext) {
        SpringJobFactory jobFactory = new SpringJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory,
                                                     Trigger simpleJobTrigger) throws IOException {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setJobFactory(jobFactory); //setting jobFactory for Scheduler
        factory.setQuartzProperties(quartzProperties());  //Setting Quartz Property
        factory.setTriggers(simpleJobTrigger); // Setting Trigger
        return factory;
    }




    @Bean
    //CronTrigger instance created
    public CronTriggerFactoryBean simpleJobTrigger(
            @Qualifier("simpleJobDetail") JobDetail jobDetail)
    {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail); //jobDetail associated with trigger
        factoryBean.setStartDelay(120L); //Trigger will start after 120L
        factoryBean.setCronExpression("0 0 0 * * ?"); //Set cron expression for trigger
        return factoryBean;
    }

    @Bean
    //
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(
                "/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

    @Bean
    // Created JobDetail instance
    public JobDetailFactoryBean simpleJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(QuartzJob.class);  //Specifying Job implementing class
        factoryBean.setDurability(true); //Set job to store in job store even no trigger points to it
        return factoryBean;
    }
}
