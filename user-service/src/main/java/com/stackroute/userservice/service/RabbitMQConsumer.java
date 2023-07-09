package com.stackroute.userservice.service;

import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.Address;
import com.stackroute.userservice.model.Appointment;
import com.stackroute.userservice.model.DocAddress;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.model.bookappointment.BookAppointment;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RabbitMQConsumer implements RabbitListenerConfigurer {

    private UserServiceImpl userService;

    @Autowired
    public RabbitMQConsumer(UserServiceImpl userService) {
        this.userService = userService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(BookAppointment bookAppointment) throws UserNotFoundException {
        System.out.println(bookAppointment);
        User user= userService.getUserById(bookAppointment.getUser().getEmailId());
        Address address = new Address();
        DocAddress docAddress = new DocAddress();
        Appointment appointment = new Appointment();
        List<Appointment> appointmentList = new ArrayList<>();

        /*address.setPlotNo(bookAppointment.getUser().getAddress().getPlotNo());
        address.setStreet(bookAppointment.getUser().getAddress().getStreet());
        address.setTown(bookAppointment.getUser().getAddress().getTown());
        address.setDistrict(bookAppointment.getUser().getAddress().getDistrict());
        address.setState(bookAppointment.getUser().getAddress().getState());
        address.setPincode(bookAppointment.getUser().getAddress().getPincode());*/

        docAddress.setClinicName(bookAppointment.getDoctor().getAddress().getClinicName());
        docAddress.setPlotNo(bookAppointment.getDoctor().getAddress().getPlotNo());
        docAddress.setStreet(bookAppointment.getDoctor().getAddress().getStreet());
        docAddress.setTown(bookAppointment.getDoctor().getAddress().getTown());
        docAddress.setDistrict(bookAppointment.getDoctor().getAddress().getDistrict());
        docAddress.setState(bookAppointment.getDoctor().getAddress().getState());
        docAddress.setPincode(bookAppointment.getDoctor().getAddress().getPincode());

        appointment.setClinicName(bookAppointment.getDoctor().getAddress().getClinicName());
        appointment.setAppointmentId(bookAppointment.getAppointmentId());
        appointment.setDoctorName(bookAppointment.getDoctor().getName());
        appointment.setSlot(bookAppointment.getSlot());
        appointment.setDate(bookAppointment.getAppointmentDate());
        appointment.setAddress(docAddress);

       /* user.setName(bookAppointment.getUser().getName());
        user.setEmailId(bookAppointment.getUser().getEmailId());
        user.setGender(bookAppointment.getUser().getGender());
        user.setDob(bookAppointment.getUser().getDob());
        user.setAddress(address);
        user.setIdCard(bookAppointment.getUser().getIdCard());
        user.setPhoneNumber(bookAppointment.getUser().getPhone());
        user.setDob(bookAppointment.getUser().getDob());
        user.setPassword(bookAppointment.getUser().getPassword());
        user.setRole(bookAppointment.getUser().getRole());*/
       // appointmentList.add(appointment);
        user.addAppointments(appointment);
        userService.updateUser(user);


    }


    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
