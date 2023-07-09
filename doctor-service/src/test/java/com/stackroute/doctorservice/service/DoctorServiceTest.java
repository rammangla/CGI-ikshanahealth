package com.stackroute.doctorservice.service;

import com.stackroute.doctorservice.exception.DoctorAlreadyExistsException;
import com.stackroute.doctorservice.exception.DoctorNotFoundException;
import com.stackroute.doctorservice.model.Address;
import com.stackroute.doctorservice.model.Appointments;
import com.stackroute.doctorservice.model.Doctor;
import com.stackroute.doctorservice.model.PatientDetails;
import com.stackroute.doctorservice.repository.DoctorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceTest {
    @Mock
    private DoctorRepository doctorRepository;

    @InjectMocks
    private DoctorServiceImpl doctorService;
    private Doctor doctor1, doctor2;
    private List<Doctor> doctorsList;
    private Optional optional;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        LocalDate localDate = LocalDate.now();
        doctorsList = new ArrayList<>();
        doctor1 = new Doctor();
        Address address = new Address();
        Appointments appointments = new Appointments();
        PatientDetails patientDetails = new PatientDetails();
        List<Appointments> appointmentsList = new ArrayList<>();
        doctor1.setName("Dr. Neeraj");
        doctor1.setEmailId("neeraj456@gmail.com");
        doctor1.setPassword("neeraj854");
        doctor1.setPhoneNumber(989454525);
        doctor1.setDob(localDate);
        doctor1.setGender("Male");
        doctor1.setIdCard("AadharCard");
        doctor1.setEducationalQualification("MBBS, MD, DM");
        doctor1.setAddress(address);
        address.setClinicName("GangaSheel");
        address.setPlotNo("50");
        address.setStreet("Rajendra Nagar");
        address.setTown("Delhi");
        address.setDistrict("Preet Vihar");
        address.setState("Delhi");
        address.setPinCode(243639);
        doctor1.setLicenseNumber("HGIBNDS5252");
        doctor1.setSpecialization("Cardiology");
        doctor1.setTotalExperience("20");
        doctor1.setFees("500");
        doctor1.setAppointments(appointmentsList);
        appointments.setDate(localDate);
        appointments.setSlot("10:15-10:30am");
        appointments.setPatientDetails(patientDetails);
        patientDetails.setPatientName("Shyam");
        patientDetails.setPhoneNumber(989840215);
        patientDetails.setGender("Male");
        patientDetails.setDob(localDate);
        appointmentsList.add(appointments);
        doctor1.setRole("Doctor");
        doctor1.setFacilities(Arrays.asList("Ambulance","Emergency"));
        appointments.setAppointmentId(04);
        doctorsList.add(doctor1);

        doctor2 = new Doctor();
        doctor2.setName("Dr. Amit");
        doctor2.setEmailId("amit1980@gmail.com");
        doctor2.setPassword("amit854");
        doctor2.setPhoneNumber(979454525);
        doctor2.setDob(localDate);
        doctor2.setGender("Male");
        doctor2.setIdCard("AadharCard");
        doctor2.setEducationalQualification("MBBS, MD, DM");
        doctor2.setAddress(address);
        address.setClinicName("Max");
        address.setPlotNo("50");
        address.setStreet("Rajendra Nagar");
        address.setTown("Delhi");
        address.setDistrict("Preet Vihar");
        address.setState("Delhi");
        address.setPinCode(243639);
        doctor2.setLicenseNumber("HGIHNDS5252");
        doctor2.setSpecialization("Neurology");
        doctor2.setTotalExperience("20");
        doctor2.setFees("550");
        doctor2.setAppointments(appointmentsList);
        appointments.setDate(localDate);
        appointments.setSlot("10:15-10:30am");
        appointments.setPatientDetails(patientDetails);
        patientDetails.setPatientName("Shreya");
        patientDetails.setPhoneNumber(989845415);
        patientDetails.setGender("Female");
        patientDetails.setDob(localDate);
        appointmentsList.add(appointments);
        doctor2.setRole("Doctor");
        doctor2.setFacilities(Arrays.asList("Ambulance","Emergency"));
        appointments.setAppointmentId(03);
        doctorsList.add(doctor2);

        optional = Optional.of(doctor1);
    }

    @AfterEach
    public void tearDown() {
        doctor1 = null;
    }

    @Test
    public void givenDoctorToSaveThenShouldReturnSavedDoctor() throws DoctorAlreadyExistsException {
        when(doctorRepository.save(any())).thenReturn(doctor1);
        assertEquals(doctor1, doctorService.saveDoctor(doctor1));
        verify(doctorRepository, times(1)).save(any());
    }

    @Test
    public void givenDoctorToSaveThenShouldNotReturnSavedDoctor() {
        when(doctorRepository.save(any())).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> {
            doctorService.saveDoctor(doctor1);
        });
        verify(doctorRepository, times(1)).save(any());
    }

    @Test
    public void givenGetAllDoctorsThenShouldReturnListOfAllDoctors() {
        doctorRepository.save(doctor1);
        when(doctorRepository.findAll()).thenReturn(doctorsList);
        List<Doctor> doctorList1 = doctorService.getAllDoctors();
        assertEquals(doctorsList, doctorList1);
        verify(doctorRepository, times(1)).save(doctor1);
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    public void givenDoctorIdThenShouldReturnRespectiveDoctor() throws DoctorNotFoundException {
        when(doctorRepository.findById(anyString())).thenReturn(Optional.of(doctor1));
        Doctor retrievedDoctor = doctorService.getDoctorbyId(doctor1.getEmailId());
        assertEquals("neeraj456@gmail.com",retrievedDoctor.getEmailId());
        verify(doctorRepository, times(2)).findById(anyString());

    }

    @Test
    public void givenDoctorIdToDeleteThenShouldReturnDeletedDoctor() throws DoctorNotFoundException {
        when(doctorRepository.findById(doctor1.getEmailId())).thenReturn(optional);
        Doctor deletedDoctor = doctorService.deleteDoctor("neeraj456@gmail.com");
        assertEquals("neeraj456@gmail.com", deletedDoctor.getEmailId());
        verify(doctorRepository, times(2)).findById(doctor1.getEmailId());
        verify(doctorRepository, times(1)).deleteById(doctor1.getEmailId());
    }

    @Test
    public void givenDoctorIdToDeleteThenShouldNotReturnDeletedDoctor() throws DoctorNotFoundException {
        when(doctorRepository.findById(doctor1.getEmailId())).thenReturn(optional);
        Doctor deletedDoctor = doctorService.deleteDoctor("neeraj456@gmail.com");
        verify(doctorRepository, times(2)).findById(doctor1.getEmailId());
    }

    @Test
    public void givenDoctorToUpdateThenShouldReturnUpdatedDoctor() throws DoctorNotFoundException {
        when(doctorRepository.findById(doctor1.getEmailId())).thenReturn(optional);
        when(doctorRepository.save(doctor1)).thenReturn(doctor1);
        doctor1.setName("Dr. Vishwas");
        Doctor Doctor2 = doctorService.updateDoctor(doctor1);
        assertEquals(Doctor2.getName(), "Dr. Vishwas");
        verify(doctorRepository, times(1)).save(doctor1);
        verify(doctorRepository, times(1)).findById(Doctor2.getEmailId());
    }

    @Test
    public void givenDoctorToUpdateThenShouldNotReturnUpdatedDoctor() throws DoctorNotFoundException {
        when(doctorRepository.findById(doctor1.getEmailId())).thenReturn(optional);
        Doctor Doctor2 = doctorService.updateDoctor(doctor1);
        verify(doctorRepository, times(1)).findById(doctor1.getEmailId());
    }
}

