package com.zorginstellingen.service;

import com.zorginstellingen.enums.AppointmentStatus;
import com.zorginstellingen.enums.Gender;
import com.zorginstellingen.enums.UserStatus;
import com.zorginstellingen.exception.EmptyException;
import com.zorginstellingen.exception.NotFoundException;
import com.zorginstellingen.model.Appointment;
import com.zorginstellingen.model.Patient;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.model.User;
import com.zorginstellingen.repositories.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {AppointmentService.class})
@ExtendWith(SpringExtension.class)
class AppointmentServiceTest {
    @MockBean
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;

    @MockBean
    private PatientService patientService;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllAppointments() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(appointmentRepository.findAll()).thenReturn(appointmentList);
        List<Appointment> actualAllAppointments = appointmentService.getAllAppointments();
        assertSame(appointmentList, actualAllAppointments);
        assertTrue(actualAllAppointments.isEmpty());
        verify(appointmentRepository).findAll();
    }


    @Test
    void testGetAllAppointmentsByDoctorId() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(appointmentRepository.findByDoctorId((Long) any())).thenReturn(appointmentList);
        List<Appointment> actualAllAppointmentsByDoctorId = appointmentService.getAllAppointmentsByDoctorId(123L);
        assertSame(appointmentList, actualAllAppointmentsByDoctorId);
        assertTrue(actualAllAppointmentsByDoctorId.isEmpty());
        verify(appointmentRepository).findByDoctorId((Long) any());
    }

    @Test
    void testGetAllAppointmentsByPatientId() {
        ArrayList<Appointment> appointmentList = new ArrayList<>();
        when(appointmentRepository.findByPatientId((Long) any())).thenReturn(appointmentList);
        List<Appointment> actualAllAppointmentsByPatientId = appointmentService.getAllAppointmentsByPatientId(123L);
        assertSame(appointmentList, actualAllAppointmentsByPatientId);
        assertTrue(actualAllAppointmentsByPatientId.isEmpty());
        verify(appointmentRepository).findByPatientId((Long) any());
    }


    @Test
    void testApplyForAppointment() throws EmptyException, NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");

        User user = new User();
        user.setActive(true);
        user.setContactNumber("42");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setGender(Gender.man);
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setQualification("Qualification");
        user.setRole(role);
        user.setRoleId(123L);
        user.setToken("ABC123");
        user.setUserDescription("User Description");
        user.setUsername("janedoe");

        Patient patient = new Patient();
        patient.setAccountNumber("42");
        patient.setBloodGroup("Blood Group");
        patient.setContactNumber("42");
        patient.setDoctorName("Doctor Name");
        patient.setEmail("jane.doe@example.org");
        patient.setGender(Gender.man);
        patient.setId(123L);
        patient.setPassword("iloveyou");
        patient.setPatientAddress("42 Main St");
        patient.setPatientName("Patient Name");
        patient.setUser(user);
        patient.setUserId(123L);
        patient.setUserStatus(UserStatus.ACTIVE);
        patient.setUsername("janedoe");

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");

        User user1 = new User();
        user1.setActive(true);
        user1.setContactNumber("42");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setEmail("jane.doe@example.org");
        user1.setGender(Gender.man);
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setQualification("Qualification");
        user1.setRole(role1);
        user1.setRoleId(123L);
        user1.setToken("ABC123");
        user1.setUserDescription("User Description");
        user1.setUsername("janedoe");

        Appointment appointment = new Appointment();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        appointment.setAppointmentDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        appointment.setAppointmentDescription("Appointment Description");
        appointment.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointment.setDoctorId(123L);
        appointment.setDoctorName("Doctor Name");
        appointment.setId(123L);
        appointment.setPatient(patient);
        appointment.setPatientId(123L);
        appointment.setPatientName("Patient Name");
        appointment.setUser(user1);
        when(appointmentRepository.save((Appointment) any())).thenReturn(appointment);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");

        User user2 = new User();
        user2.setActive(true);
        user2.setContactNumber("42");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setEmail("jane.doe@example.org");
        user2.setGender(Gender.man);
        user2.setId(123L);
        user2.setPassword("iloveyou");
        user2.setQualification("Qualification");
        user2.setRole(role2);
        user2.setRoleId(123L);
        user2.setToken("ABC123");
        user2.setUserDescription("User Description");
        user2.setUsername("janedoe");

        Patient patient1 = new Patient();
        patient1.setAccountNumber("42");
        patient1.setBloodGroup("Blood Group");
        patient1.setContactNumber("42");
        patient1.setDoctorName("Doctor Name");
        patient1.setEmail("jane.doe@example.org");
        patient1.setGender(Gender.man);
        patient1.setId(123L);
        patient1.setPassword("iloveyou");
        patient1.setPatientAddress("42 Main St");
        patient1.setPatientName("Patient Name");
        patient1.setUser(user2);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");
        when(patientService.findPatientByPatientId((Long) any())).thenReturn(patient1);

        Role role3 = new Role();
        role3.setId(123L);
        role3.setRoleName("Role Name");

        User user3 = new User();
        user3.setActive(true);
        user3.setContactNumber("42");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setEmail("jane.doe@example.org");
        user3.setGender(Gender.man);
        user3.setId(123L);
        user3.setPassword("iloveyou");
        user3.setQualification("Qualification");
        user3.setRole(role3);
        user3.setRoleId(123L);
        user3.setToken("ABC123");
        user3.setUserDescription("User Description");
        user3.setUsername("janedoe");
        when(userService.findUserById((Long) any())).thenReturn(user3);

        Role role4 = new Role();
        role4.setId(123L);
        role4.setRoleName("Role Name");

        User user4 = new User();
        user4.setActive(true);
        user4.setContactNumber("42");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setCreatedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user4.setEmail("jane.doe@example.org");
        user4.setGender(Gender.man);
        user4.setId(123L);
        user4.setPassword("iloveyou");
        user4.setQualification("Qualification");
        user4.setRole(role4);
        user4.setRoleId(123L);
        user4.setToken("ABC123");
        user4.setUserDescription("User Description");
        user4.setUsername("janedoe");

        Patient patient2 = new Patient();
        patient2.setAccountNumber("42");
        patient2.setBloodGroup("Blood Group");
        patient2.setContactNumber("42");
        patient2.setDoctorName("Doctor Name");
        patient2.setEmail("jane.doe@example.org");
        patient2.setGender(Gender.man);
        patient2.setId(123L);
        patient2.setPassword("iloveyou");
        patient2.setPatientAddress("42 Main St");
        patient2.setPatientName("Patient Name");
        patient2.setUser(user4);
        patient2.setUserId(123L);
        patient2.setUserStatus(UserStatus.ACTIVE);
        patient2.setUsername("janedoe");

        Role role5 = new Role();
        role5.setId(123L);
        role5.setRoleName("Role Name");

        User user5 = new User();
        user5.setActive(true);
        user5.setContactNumber("42");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user5.setCreatedDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        user5.setEmail("jane.doe@example.org");
        user5.setGender(Gender.man);
        user5.setId(123L);
        user5.setPassword("iloveyou");
        user5.setQualification("Qualification");
        user5.setRole(role5);
        user5.setRoleId(123L);
        user5.setToken("ABC123");
        user5.setUserDescription("User Description");
        user5.setUsername("janedoe");

        Appointment appointment1 = new Appointment();
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        appointment1.setAppointmentDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        appointment1.setAppointmentDescription("Appointment Description");
        appointment1.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointment1.setDoctorId(123L);
        appointment1.setDoctorName("Doctor Name");
        appointment1.setId(123L);
        appointment1.setPatient(patient2);
        appointment1.setPatientId(123L);
        appointment1.setPatientName("Patient Name");
        appointment1.setUser(user5);
        assertSame(appointment, appointmentService.applyForAppointment(appointment1));
        verify(appointmentRepository).save((Appointment) any());
        verify(patientService).findPatientByPatientId((Long) any());
        verify(userService).findUserById((Long) any());
    }

    /**
     * Method under test: {@link AppointmentService#applyForAppointment(Appointment)}
     */
    @Test
    void testApplyForAppointment2() throws EmptyException, NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");

        User user = new User();
        user.setActive(true);
        user.setContactNumber("42");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setGender(Gender.man);
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setQualification("Qualification");
        user.setRole(role);
        user.setRoleId(123L);
        user.setToken("ABC123");
        user.setUserDescription("User Description");
        user.setUsername("janedoe");

        Patient patient = new Patient();
        patient.setAccountNumber("42");
        patient.setBloodGroup("Blood Group");
        patient.setContactNumber("42");
        patient.setDoctorName("Doctor Name");
        patient.setEmail("jane.doe@example.org");
        patient.setGender(Gender.man);
        patient.setId(123L);
        patient.setPassword("iloveyou");
        patient.setPatientAddress("42 Main St");
        patient.setPatientName("Patient Name");
        patient.setUser(user);
        patient.setUserId(123L);
        patient.setUserStatus(UserStatus.ACTIVE);
        patient.setUsername("janedoe");

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");

        User user1 = new User();
        user1.setActive(true);
        user1.setContactNumber("42");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setEmail("jane.doe@example.org");
        user1.setGender(Gender.man);
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setQualification("Qualification");
        user1.setRole(role1);
        user1.setRoleId(123L);
        user1.setToken("ABC123");
        user1.setUserDescription("User Description");
        user1.setUsername("janedoe");

        Appointment appointment = new Appointment();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        appointment.setAppointmentDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        appointment.setAppointmentDescription("Appointment Description");
        appointment.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointment.setDoctorId(123L);
        appointment.setDoctorName("Doctor Name");
        appointment.setId(123L);
        appointment.setPatient(patient);
        appointment.setPatientId(123L);
        appointment.setPatientName("Patient Name");
        appointment.setUser(user1);
        when(appointmentRepository.save((Appointment) any())).thenReturn(appointment);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");

        User user2 = new User();
        user2.setActive(true);
        user2.setContactNumber("42");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setEmail("jane.doe@example.org");
        user2.setGender(Gender.man);
        user2.setId(123L);
        user2.setPassword("iloveyou");
        user2.setQualification("Qualification");
        user2.setRole(role2);
        user2.setRoleId(123L);
        user2.setToken("ABC123");
        user2.setUserDescription("User Description");
        user2.setUsername("janedoe");

        Patient patient1 = new Patient();
        patient1.setAccountNumber("42");
        patient1.setBloodGroup("Blood Group");
        patient1.setContactNumber("42");
        patient1.setDoctorName("Doctor Name");
        patient1.setEmail("jane.doe@example.org");
        patient1.setGender(Gender.man);
        patient1.setId(123L);
        patient1.setPassword("iloveyou");
        patient1.setPatientAddress("42 Main St");
        patient1.setPatientName("Patient Name");
        patient1.setUser(user2);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");
        when(patientService.findPatientByPatientId((Long) any())).thenReturn(patient1);
        when(userService.findUserById((Long) any())).thenThrow(new NotFoundException("An error occurred"));

        Role role3 = new Role();
        role3.setId(123L);
        role3.setRoleName("Role Name");

        User user3 = new User();
        user3.setActive(true);
        user3.setContactNumber("42");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setEmail("jane.doe@example.org");
        user3.setGender(Gender.man);
        user3.setId(123L);
        user3.setPassword("iloveyou");
        user3.setQualification("Qualification");
        user3.setRole(role3);
        user3.setRoleId(123L);
        user3.setToken("ABC123");
        user3.setUserDescription("User Description");
        user3.setUsername("janedoe");

        Patient patient2 = new Patient();
        patient2.setAccountNumber("42");
        patient2.setBloodGroup("Blood Group");
        patient2.setContactNumber("42");
        patient2.setDoctorName("Doctor Name");
        patient2.setEmail("jane.doe@example.org");
        patient2.setGender(Gender.man);
        patient2.setId(123L);
        patient2.setPassword("iloveyou");
        patient2.setPatientAddress("42 Main St");
        patient2.setPatientName("Patient Name");
        patient2.setUser(user3);
        patient2.setUserId(123L);
        patient2.setUserStatus(UserStatus.ACTIVE);
        patient2.setUsername("janedoe");

        Role role4 = new Role();
        role4.setId(123L);
        role4.setRoleName("Role Name");

        User user4 = new User();
        user4.setActive(true);
        user4.setContactNumber("42");
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setCreatedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        user4.setEmail("jane.doe@example.org");
        user4.setGender(Gender.man);
        user4.setId(123L);
        user4.setPassword("iloveyou");
        user4.setQualification("Qualification");
        user4.setRole(role4);
        user4.setRoleId(123L);
        user4.setToken("ABC123");
        user4.setUserDescription("User Description");
        user4.setUsername("janedoe");

        Appointment appointment1 = new Appointment();
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        appointment1.setAppointmentDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        appointment1.setAppointmentDescription("Appointment Description");
        appointment1.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointment1.setDoctorId(123L);
        appointment1.setDoctorName("Doctor Name");
        appointment1.setId(123L);
        appointment1.setPatient(patient2);
        appointment1.setPatientId(123L);
        appointment1.setPatientName("Patient Name");
        appointment1.setUser(user4);
        assertThrows(NotFoundException.class, () -> appointmentService.applyForAppointment(appointment1));
        verify(userService).findUserById((Long) any());
    }

    @Test
    void testUpdateAppointment() throws EmptyException, NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");

        User user = new User();
        user.setActive(true);
        user.setContactNumber("42");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setGender(Gender.man);
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setQualification("Qualification");
        user.setRole(role);
        user.setRoleId(123L);
        user.setToken("ABC123");
        user.setUserDescription("User Description");
        user.setUsername("janedoe");

        Patient patient = new Patient();
        patient.setAccountNumber("42");
        patient.setBloodGroup("Blood Group");
        patient.setContactNumber("42");
        patient.setDoctorName("Doctor Name");
        patient.setEmail("jane.doe@example.org");
        patient.setGender(Gender.man);
        patient.setId(123L);
        patient.setPassword("iloveyou");
        patient.setPatientAddress("42 Main St");
        patient.setPatientName("Patient Name");
        patient.setUser(user);
        patient.setUserId(123L);
        patient.setUserStatus(UserStatus.ACTIVE);
        patient.setUsername("janedoe");

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");

        User user1 = new User();
        user1.setActive(true);
        user1.setContactNumber("42");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setEmail("jane.doe@example.org");
        user1.setGender(Gender.man);
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setQualification("Qualification");
        user1.setRole(role1);
        user1.setRoleId(123L);
        user1.setToken("ABC123");
        user1.setUserDescription("User Description");
        user1.setUsername("janedoe");

        Appointment appointment = new Appointment();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        appointment.setAppointmentDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        appointment.setAppointmentDescription("Appointment Description");
        appointment.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointment.setDoctorId(123L);
        appointment.setDoctorName("Doctor Name");
        appointment.setId(123L);
        appointment.setPatient(patient);
        appointment.setPatientId(123L);
        appointment.setPatientName("Patient Name");
        appointment.setUser(user1);
        Optional<Appointment> ofResult = Optional.of(appointment);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");

        User user2 = new User();
        user2.setActive(true);
        user2.setContactNumber("42");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setEmail("jane.doe@example.org");
        user2.setGender(Gender.man);
        user2.setId(123L);
        user2.setPassword("iloveyou");
        user2.setQualification("Qualification");
        user2.setRole(role2);
        user2.setRoleId(123L);
        user2.setToken("ABC123");
        user2.setUserDescription("User Description");
        user2.setUsername("janedoe");

        Patient patient1 = new Patient();
        patient1.setAccountNumber("42");
        patient1.setBloodGroup("Blood Group");
        patient1.setContactNumber("42");
        patient1.setDoctorName("Doctor Name");
        patient1.setEmail("jane.doe@example.org");
        patient1.setGender(Gender.man);
        patient1.setId(123L);
        patient1.setPassword("iloveyou");
        patient1.setPatientAddress("42 Main St");
        patient1.setPatientName("Patient Name");
        patient1.setUser(user2);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");

        Role role3 = new Role();
        role3.setId(123L);
        role3.setRoleName("Role Name");

        User user3 = new User();
        user3.setActive(true);
        user3.setContactNumber("42");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        user3.setEmail("jane.doe@example.org");
        user3.setGender(Gender.man);
        user3.setId(123L);
        user3.setPassword("iloveyou");
        user3.setQualification("Qualification");
        user3.setRole(role3);
        user3.setRoleId(123L);
        user3.setToken("ABC123");
        user3.setUserDescription("User Description");
        user3.setUsername("janedoe");

        Appointment appointment1 = new Appointment();
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        appointment1.setAppointmentDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        appointment1.setAppointmentDescription("Appointment Description");
        appointment1.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointment1.setDoctorId(123L);
        appointment1.setDoctorName("Doctor Name");
        appointment1.setId(123L);
        appointment1.setPatient(patient1);
        appointment1.setPatientId(123L);
        appointment1.setPatientName("Patient Name");
        appointment1.setUser(user3);
        when(appointmentRepository.save((Appointment) any())).thenReturn(appointment1);
        when(appointmentRepository.findById((Long) any())).thenReturn(ofResult);

        Role role4 = new Role();
        role4.setId(123L);
        role4.setRoleName("Role Name");

        User user4 = new User();
        user4.setActive(true);
        user4.setContactNumber("42");
        LocalDateTime atStartOfDayResult6 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user4.setCreatedDate(Date.from(atStartOfDayResult6.atZone(ZoneId.of("UTC")).toInstant()));
        user4.setEmail("jane.doe@example.org");
        user4.setGender(Gender.man);
        user4.setId(123L);
        user4.setPassword("iloveyou");
        user4.setQualification("Qualification");
        user4.setRole(role4);
        user4.setRoleId(123L);
        user4.setToken("ABC123");
        user4.setUserDescription("User Description");
        user4.setUsername("janedoe");

        Patient patient2 = new Patient();
        patient2.setAccountNumber("42");
        patient2.setBloodGroup("Blood Group");
        patient2.setContactNumber("42");
        patient2.setDoctorName("Doctor Name");
        patient2.setEmail("jane.doe@example.org");
        patient2.setGender(Gender.man);
        patient2.setId(123L);
        patient2.setPassword("iloveyou");
        patient2.setPatientAddress("42 Main St");
        patient2.setPatientName("Patient Name");
        patient2.setUser(user4);
        patient2.setUserId(123L);
        patient2.setUserStatus(UserStatus.ACTIVE);
        patient2.setUsername("janedoe");
        when(patientService.findPatientByPatientId((Long) any())).thenReturn(patient2);

        Role role5 = new Role();
        role5.setId(123L);
        role5.setRoleName("Role Name");

        User user5 = new User();
        user5.setActive(true);
        user5.setContactNumber("42");
        LocalDateTime atStartOfDayResult7 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user5.setCreatedDate(Date.from(atStartOfDayResult7.atZone(ZoneId.of("UTC")).toInstant()));
        user5.setEmail("jane.doe@example.org");
        user5.setGender(Gender.man);
        user5.setId(123L);
        user5.setPassword("iloveyou");
        user5.setQualification("Qualification");
        user5.setRole(role5);
        user5.setRoleId(123L);
        user5.setToken("ABC123");
        user5.setUserDescription("User Description");
        user5.setUsername("janedoe");
        when(userService.findUserById((Long) any())).thenReturn(user5);

        Role role6 = new Role();
        role6.setId(123L);
        role6.setRoleName("Role Name");

        User user6 = new User();
        user6.setActive(true);
        user6.setContactNumber("42");
        LocalDateTime atStartOfDayResult8 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user6.setCreatedDate(Date.from(atStartOfDayResult8.atZone(ZoneId.of("UTC")).toInstant()));
        user6.setEmail("jane.doe@example.org");
        user6.setGender(Gender.man);
        user6.setId(123L);
        user6.setPassword("iloveyou");
        user6.setQualification("Qualification");
        user6.setRole(role6);
        user6.setRoleId(123L);
        user6.setToken("ABC123");
        user6.setUserDescription("User Description");
        user6.setUsername("janedoe");

        Patient patient3 = new Patient();
        patient3.setAccountNumber("42");
        patient3.setBloodGroup("Blood Group");
        patient3.setContactNumber("42");
        patient3.setDoctorName("Doctor Name");
        patient3.setEmail("jane.doe@example.org");
        patient3.setGender(Gender.man);
        patient3.setId(123L);
        patient3.setPassword("iloveyou");
        patient3.setPatientAddress("42 Main St");
        patient3.setPatientName("Patient Name");
        patient3.setUser(user6);
        patient3.setUserId(123L);
        patient3.setUserStatus(UserStatus.ACTIVE);
        patient3.setUsername("janedoe");

        Role role7 = new Role();
        role7.setId(123L);
        role7.setRoleName("Role Name");

        User user7 = new User();
        user7.setActive(true);
        user7.setContactNumber("42");
        LocalDateTime atStartOfDayResult9 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user7.setCreatedDate(Date.from(atStartOfDayResult9.atZone(ZoneId.of("UTC")).toInstant()));
        user7.setEmail("jane.doe@example.org");
        user7.setGender(Gender.man);
        user7.setId(123L);
        user7.setPassword("iloveyou");
        user7.setQualification("Qualification");
        user7.setRole(role7);
        user7.setRoleId(123L);
        user7.setToken("ABC123");
        user7.setUserDescription("User Description");
        user7.setUsername("janedoe");

        Appointment appointment2 = new Appointment();
        LocalDateTime atStartOfDayResult10 = LocalDate.of(1970, 1, 1).atStartOfDay();
        appointment2.setAppointmentDate(Date.from(atStartOfDayResult10.atZone(ZoneId.of("UTC")).toInstant()));
        appointment2.setAppointmentDescription("Appointment Description");
        appointment2.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointment2.setDoctorId(123L);
        appointment2.setDoctorName("Doctor Name");
        appointment2.setId(123L);
        appointment2.setPatient(patient3);
        appointment2.setPatientId(123L);
        appointment2.setPatientName("Patient Name");
        appointment2.setUser(user7);
        assertSame(appointment1, appointmentService.updateAppointment(appointment2));
        verify(appointmentRepository).save((Appointment) any());
        verify(appointmentRepository).findById((Long) any());
        verify(patientService).findPatientByPatientId((Long) any());
        verify(userService).findUserById((Long) any());
    }

    /**
     * Method under test: {@link AppointmentService#deleteAppointment(Long)}
     */
    @Test
    void testDeleteAppointment() throws EmptyException, NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");

        User user = new User();
        user.setActive(true);
        user.setContactNumber("42");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setGender(Gender.man);
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setQualification("Qualification");
        user.setRole(role);
        user.setRoleId(123L);
        user.setToken("ABC123");
        user.setUserDescription("User Description");
        user.setUsername("janedoe");

        Patient patient = new Patient();
        patient.setAccountNumber("42");
        patient.setBloodGroup("Blood Group");
        patient.setContactNumber("42");
        patient.setDoctorName("Doctor Name");
        patient.setEmail("jane.doe@example.org");
        patient.setGender(Gender.man);
        patient.setId(123L);
        patient.setPassword("iloveyou");
        patient.setPatientAddress("42 Main St");
        patient.setPatientName("Patient Name");
        patient.setUser(user);
        patient.setUserId(123L);
        patient.setUserStatus(UserStatus.ACTIVE);
        patient.setUsername("janedoe");

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");

        User user1 = new User();
        user1.setActive(true);
        user1.setContactNumber("42");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setEmail("jane.doe@example.org");
        user1.setGender(Gender.man);
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setQualification("Qualification");
        user1.setRole(role1);
        user1.setRoleId(123L);
        user1.setToken("ABC123");
        user1.setUserDescription("User Description");
        user1.setUsername("janedoe");

        Appointment appointment = new Appointment();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        appointment.setAppointmentDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        appointment.setAppointmentDescription("Appointment Description");
        appointment.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointment.setDoctorId(123L);
        appointment.setDoctorName("Doctor Name");
        appointment.setId(123L);
        appointment.setPatient(patient);
        appointment.setPatientId(123L);
        appointment.setPatientName("Patient Name");
        appointment.setUser(user1);
        Optional<Appointment> ofResult = Optional.of(appointment);
        doNothing().when(appointmentRepository).delete((Appointment) any());
        when(appointmentRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(appointment, appointmentService.deleteAppointment(123L));
        verify(appointmentRepository).findById((Long) any());
        verify(appointmentRepository).delete((Appointment) any());
    }


    @Test
    void testFindAppointmentById() throws NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");

        User user = new User();
        user.setActive(true);
        user.setContactNumber("42");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setGender(Gender.man);
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setQualification("Qualification");
        user.setRole(role);
        user.setRoleId(123L);
        user.setToken("ABC123");
        user.setUserDescription("User Description");
        user.setUsername("janedoe");

        Patient patient = new Patient();
        patient.setAccountNumber("42");
        patient.setBloodGroup("Blood Group");
        patient.setContactNumber("42");
        patient.setDoctorName("Doctor Name");
        patient.setEmail("jane.doe@example.org");
        patient.setGender(Gender.man);
        patient.setId(123L);
        patient.setPassword("iloveyou");
        patient.setPatientAddress("42 Main St");
        patient.setPatientName("Patient Name");
        patient.setUser(user);
        patient.setUserId(123L);
        patient.setUserStatus(UserStatus.ACTIVE);
        patient.setUsername("janedoe");

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");

        User user1 = new User();
        user1.setActive(true);
        user1.setContactNumber("42");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setEmail("jane.doe@example.org");
        user1.setGender(Gender.man);
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setQualification("Qualification");
        user1.setRole(role1);
        user1.setRoleId(123L);
        user1.setToken("ABC123");
        user1.setUserDescription("User Description");
        user1.setUsername("janedoe");

        Appointment appointment = new Appointment();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        appointment.setAppointmentDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        appointment.setAppointmentDescription("Appointment Description");
        appointment.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointment.setDoctorId(123L);
        appointment.setDoctorName("Doctor Name");
        appointment.setId(123L);
        appointment.setPatient(patient);
        appointment.setPatientId(123L);
        appointment.setPatientName("Patient Name");
        appointment.setUser(user1);
        Optional<Appointment> ofResult = Optional.of(appointment);
        when(appointmentRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(appointment, appointmentService.findAppointmentById(123L));
        verify(appointmentRepository).findById((Long) any());
    }
}

