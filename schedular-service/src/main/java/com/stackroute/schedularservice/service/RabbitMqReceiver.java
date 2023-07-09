package com.stackroute.schedularservice.service;

import com.stackroute.schedularservice.domain.BookAppointment;
import com.stackroute.schedularservice.exceptions.DoctorNotFoundException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class RabbitMqReceiver implements RabbitListenerConfigurer {
    private SchedulerServiceImpl schedulerService;

    @Autowired
    public RabbitMqReceiver(SchedulerServiceImpl schedulerService) {
        this.schedulerService = schedulerService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(BookAppointment bookAppointment) throws DoctorNotFoundException {
        System.out.println(bookAppointment);
        LocalDate localdate=LocalDate.now();
        System.out.println("Received msg = " + bookAppointment);
         LocalDate localDate = bookAppointment.getAppointmentDate();
        System.out.println("Received date = " + localDate);
        System.out.println("Localdate now = " + localdate);
         String SlotTime = bookAppointment.getSlot();
         String emailId = bookAppointment.getDoctor().getEmailId();
         String key="";
         String Value="Booked";
         if(localDate.isEqual(LocalDate.now()))
         {
             key="today_"+SlotTime;
             schedulerService.putSlots(emailId,key,Value);
             System.out.println("Received msg = " + key);
         }
         else if(localDate.isEqual(localdate.plusDays(1)))
         {
             key="tomorrow_"+SlotTime;
             schedulerService.putSlots(emailId,key,Value);
         }
         else if(localDate.isEqual(localdate.plusDays(2))) {
             key = "overmorrow_" + SlotTime;
             schedulerService.putSlots(emailId,key,Value);
         }

    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
