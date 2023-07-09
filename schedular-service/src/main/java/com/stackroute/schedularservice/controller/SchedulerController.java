package com.stackroute.schedularservice.controller;

import com.stackroute.schedularservice.domain.BookAppointment;
import com.stackroute.schedularservice.domain.Scheduler;
import com.stackroute.schedularservice.exceptions.DoctorNotFoundException;
import com.stackroute.schedularservice.service.SchedulerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value="/api/v1")
@CrossOrigin(value="*")
/*
@RestController
@Slf4j
@RequestMapping(value="/api/v1")
public class SchedulerController {

    private SchedulerService schedulerService;
    @Autowired
    public SchedulerController(SchedulerService schedulerService)
    {
        this.schedulerService = schedulerService;
    }

    @GetMapping("/getslot")
    public ResponseEntity<?> GetAllSlots( )
    {
        return new ResponseEntity<List<Scheduler>>(schedulerService.findAll(), HttpStatus.OK);
    }

} */

public class SchedulerController{

    private SchedulerService schedulerService;


    @Autowired
    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }
/*
    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receviedMessage(BookAppointment bookAppointment) throws DoctorNotFoundException {
        System.out.println(bookAppointment);
        LocalDate localdate=LocalDate.now();

        LocalDate localDate=bookAppointment.getAppointmentDate();
        String SlotTime = bookAppointment.getSlot();
        String emailId = bookAppointment.getDoctor().getEmailId();
        String Key="";
        String Value="Booked";
        if(localDate.isEqual(LocalDate.now()))
        {
            Key="today_"+SlotTime;
        }
        else if(localDate.isEqual(localdate.plusDays(1)))
        {
            Key="tomorrow_"+SlotTime;
        }
        else if(localDate.isEqual(localdate.plusDays(2)))
        {
            Key="Overmorrow_"+SlotTime;
        }
        schedulerService.putSlots(emailId,Key,Value);

    }

*/

   /* Fetch all slots from database */
    @GetMapping(value="/slots")
    public ResponseEntity<List<Scheduler>> getAll(){
        return new ResponseEntity<>(schedulerService.getAll(), HttpStatus.OK);
    }



  /* Fetch slots for given emailId */

    @GetMapping(value="/slots/{emailId}")
    public ResponseEntity<Scheduler> getSlots(@PathVariable String emailId){
        return new ResponseEntity<>(schedulerService.getSlots(emailId), HttpStatus.OK);
    }



  /* Post Slots in database */
    @PostMapping(value="/slot")
    public ResponseEntity<Scheduler> postSlots(@RequestBody Scheduler scheduler){
        return new ResponseEntity<>(schedulerService.save(scheduler),HttpStatus.CREATED);
    }



   /* Update Slots for particular EmailId */
    @PutMapping(value="/slots/{emailId}/{key}/{value}")
    public ResponseEntity<?> putSlots(@PathVariable String emailId,@PathVariable String key,@PathVariable String value){
        try {
            return new ResponseEntity<Scheduler>(schedulerService.putSlots(emailId, key, value), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<String>("Doctor Not Found", HttpStatus.NOT_FOUND);
        }
    }


   /* Deleting Slots for particular email Id */
    @DeleteMapping(value="/DeleteSlots/{emailId}")
    public ResponseEntity<?>DeleteSlot(@PathVariable String emailId)
    {
        try{
            return new ResponseEntity<Scheduler>(schedulerService.deleteSlots(emailId),HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<String>("Doctor Not Found",HttpStatus.OK);
        }
    }


}


