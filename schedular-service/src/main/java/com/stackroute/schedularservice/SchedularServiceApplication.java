package com.stackroute.schedularservice;

import org.quartz.SchedulerException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import({SchedulerConfig.class})
@SpringBootApplication
public class SchedularServiceApplication {

	public static void main(String[] args) throws SchedulerException {
		SpringApplication.run(SchedularServiceApplication.class, args);

	}

}
