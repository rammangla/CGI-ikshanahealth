package com.stackroute.doctorservice.repository;

import com.stackroute.doctorservice.model.Address;
import com.stackroute.doctorservice.model.Appointments;
import com.stackroute.doctorservice.model.Doctor;
import com.stackroute.doctorservice.model.PatientDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class DoctorRepositoryIntegrationTest {
    @Autowired
    private DoctorRepository doctorRepository;
    private Doctor doctor;

    @BeforeEach
    public void setUp() {
        LocalDate localDate = LocalDate.now();
        doctor = new Doctor();
        Address address = new Address();
        Appointments appointments = new Appointments();
        PatientDetails patientDetails = new PatientDetails();
        List<Appointments> appointmentsList= new ArrayList<>();
        doctor.setName("Dr. Neeraj");
        doctor.setEmailId("neeraj456@gmail.com");
        doctor.setPassword("neeraj854");
        doctor.setPhoneNumber(989454525);
        doctor.setDob(localDate);
        doctor.setGender("Male");
        doctor.setIdCard("AadharCard");
        doctor.setEducationalQualification("MBBS, MD, DM");
        doctor.setAddress(address);
        address.setClinicName("GangaSheel");
        address.setPlotNo("50");
        address.setStreet("Rajendra Nagar");
        address.setTown("Delhi");
        address.setDistrict("Preet Vihar");
        address.setState("Delhi");
        address.setPinCode(243639);
        doctor.setLicenseNumber("HGIBNDS5252");
        doctor.setSpecialization("Cardiology");
        doctor.setTotalExperience("20");
        doctor.setFees("500");
        doctor.setAppointments(appointmentsList);
        appointments.setDate(localDate);
        appointments.setSlot("10:15-10:30am");
        appointments.setPatientDetails(patientDetails);
        appointments.setAppointmentId(04);
        patientDetails.setPatientName("Shyam");
        patientDetails.setPhoneNumber(989840215);
        patientDetails.setGender("Male");
        patientDetails.setDob(localDate);
        appointmentsList.add(appointments);
        doctor.setRole("Doctor");
        doctor.setFacilities(Arrays.asList("Ambulance","Emergency"));
    }

    @AfterEach
    public void tearDown() {
        doctorRepository.deleteAll();
        doctor = null;
    }

    @Test
    public void givenDoctorToSaveThenShouldReturnSavedDoctor() {
        doctorRepository.save(doctor);
        Doctor fetchedDoctor = doctorRepository.findById(doctor.getEmailId()).get();
        assertEquals("neeraj456@gmail.com", fetchedDoctor.getEmailId());
    }


    @Test
    public void givenGetAllDoctorsThenShouldReturnListOfAllDoctors() {

        doctorRepository.save(doctor);

        List<Doctor> doctorList = (List<Doctor>) doctorRepository.findAll();
        assertEquals("neeraj456@gmail.com", doctorList.get(0).getEmailId());
    }

    @Test
    public void givenEmailIdThenShouldReturnRespectiveDoctor() {
        doctorRepository.save(doctor);
        Optional<Doctor> optional = doctorRepository.findById(doctor.getEmailId());
        assertEquals(doctor.getEmailId(), optional.get().getEmailId());
        assertEquals(doctor.getName(), optional.get().getName());
    }

    @Test
    public void givenDoctorEmailIdToDeleteThenShouldReturnDeletedDoctor() {
        doctorRepository.save(doctor);
        doctorRepository.deleteById(doctor.getEmailId());
        Optional optional = doctorRepository.findById(doctor.getEmailId());
        assertEquals(Optional.empty(), optional);
    }

}
