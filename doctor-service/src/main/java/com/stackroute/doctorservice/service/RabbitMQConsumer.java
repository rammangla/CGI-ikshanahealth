package com.stackroute.doctorservice.service;


import com.stackroute.doctorservice.exception.DoctorNotFoundException;
import com.stackroute.doctorservice.model.Address;
import com.stackroute.doctorservice.model.Appointments;
import com.stackroute.doctorservice.model.bookappointment.BookAppointment;
import com.stackroute.doctorservice.model.Doctor;
import com.stackroute.doctorservice.model.PatientDetails;
import com.stackroute.doctorservice.model.bookappointment.UserAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class RabbitMQConsumer implements RabbitListenerConfigurer {
    private DoctorServiceImpl doctorService;

    @Autowired
    public RabbitMQConsumer(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void receivedMessage(BookAppointment bookAppointment) throws DoctorNotFoundException {
        System.out.println(bookAppointment);
        Doctor doctor= doctorService.getDoctorbyId(bookAppointment.getDoctor().getEmailId());
        Address address = new Address();
        PatientDetails patientDetails = new PatientDetails();
        UserAddress userAddress = new UserAddress();
        Appointments appointments = new Appointments();
        List<Appointments> appointmentsList = new ArrayList<>();
        
        /*address.setClinicName(bookAppointment.getDoctor().getAddress().getClinicName());
        address.setPlotNo(bookAppointment.getDoctor().getAddress().getPlotNo());
        address.setStreet(bookAppointment.getDoctor().getAddress().getStreet());
        address.setTown(bookAppointment.getDoctor().getAddress().getTown());
        address.setDistrict(bookAppointment.getDoctor().getAddress().getDistrict());
        address.setState(bookAppointment.getDoctor().getAddress().getState());
        address.setPinCode(bookAppointment.getDoctor().getAddress().getPincode());*/

        userAddress.setPlotNo(bookAppointment.getUser().getAddress().getPlotNo());
        userAddress.setStreet(bookAppointment.getUser().getAddress().getStreet());
        userAddress.setTown(bookAppointment.getUser().getAddress().getTown());
        userAddress.setDistrict(bookAppointment.getUser().getAddress().getDistrict());
        userAddress.setState(bookAppointment.getUser().getAddress().getState());
        userAddress.setPincode(bookAppointment.getUser().getAddress().getPincode());

        patientDetails.setDob(bookAppointment.getUser().getDob());
        patientDetails.setPatientName(bookAppointment.getUser().getName());
        patientDetails.setGender(bookAppointment.getUser().getGender());
        patientDetails.setPhoneNumber(bookAppointment.getUser().getPhone());

        appointments.setAppointmentId(bookAppointment.getAppointmentId());
        appointments.setSlot(bookAppointment.getSlot());
        appointments.setDate(bookAppointment.getAppointmentDate());
        appointments.setPatientDetails(patientDetails);

        /*doctor.setName(bookAppointment.getDoctor().getName());
        doctor.setEmailId(bookAppointment.getDoctor().getEmailId());
        doctor.setPassword(bookAppointment.getDoctor().getPassword());
        doctor.setPhoneNumber(bookAppointment.getDoctor().getPhone());
        doctor.setGender(bookAppointment.getDoctor().getGender());
        doctor.setEducationalQualification(bookAppointment.getDoctor().getQualification());
        doctor.setAddress(address);
        doctor.setPassword(bookAppointment.getDoctor().getPassword());
        doctor.setRole(bookAppointment.getDoctor().getRole());*/
        // appointmentList.add(appointment);
        doctor.addAppointments(appointments);
        doctorService.updateDoctor(doctor);

    }



    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {

    }
}
