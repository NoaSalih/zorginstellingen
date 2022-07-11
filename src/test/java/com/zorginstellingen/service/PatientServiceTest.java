package com.zorginstellingen.service;

import com.zorginstellingen.dto.UserDto;
import com.zorginstellingen.enums.Gender;
import com.zorginstellingen.enums.UserStatus;
import com.zorginstellingen.exception.*;
import com.zorginstellingen.model.Patient;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.model.User;
import com.zorginstellingen.repositories.PatientRepository;
import org.junit.jupiter.api.Disabled;
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

@ContextConfiguration(classes = {PatientService.class})
@ExtendWith(SpringExtension.class)
class PatientServiceTest {
    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private UserService userService;

    @Test
    void testGetAllPatientsByDoctorId() throws NotFoundException {
        ArrayList<Patient> patientList = new ArrayList<>();
        when(patientRepository.findPatientsByDoctorId((Long) any())).thenReturn(patientList);
        List<Patient> actualAllPatientsByDoctorId = patientService.getAllPatientsByDoctorId(123L);
        assertSame(patientList, actualAllPatientsByDoctorId);
        assertTrue(actualAllPatientsByDoctorId.isEmpty());
        verify(patientRepository).findPatientsByDoctorId((Long) any());
    }

    @Test
    void testGetAllPatientsByDoctorId2() throws NotFoundException {
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

        ArrayList<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        when(patientRepository.findPatientsByDoctorId((Long) any())).thenReturn(patientList);
        List<Patient> actualAllPatientsByDoctorId = patientService.getAllPatientsByDoctorId(123L);
        assertSame(patientList, actualAllPatientsByDoctorId);
        assertEquals(1, actualAllPatientsByDoctorId.size());
        verify(patientRepository).findPatientsByDoctorId((Long) any());
    }

    @Test
    void testGetAllPatientsByDoctorId3() throws NotFoundException {
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
        patient1.setUser(user1);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");

        ArrayList<Patient> patientList = new ArrayList<>();
        patientList.add(patient1);
        patientList.add(patient);
        when(patientRepository.findPatientsByDoctorId((Long) any())).thenReturn(patientList);
        List<Patient> actualAllPatientsByDoctorId = patientService.getAllPatientsByDoctorId(123L);
        assertSame(patientList, actualAllPatientsByDoctorId);
        assertEquals(2, actualAllPatientsByDoctorId.size());
        verify(patientRepository).findPatientsByDoctorId((Long) any());
    }

    @Test
    void testAddPatient() throws NotFoundException, PasswordException, ResourceAlreadyExists {
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
        when(patientRepository.save((Patient) any())).thenReturn(patient);

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

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");

        User user2 = new User();
        user2.setActive(true);
        user2.setContactNumber("42");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
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
        when(userService.findUserById((Long) any())).thenReturn(user1);
        when(userService.register((UserDto) any())).thenReturn(user2);

        Role role3 = new Role();
        role3.setId(123L);
        role3.setRoleName("Role Name");

        User user3 = new User();
        user3.setActive(true);
        user3.setContactNumber("42");
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user3.setCreatedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
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
        patient1.setUser(user3);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");
        assertSame(patient, patientService.addPatient(patient1));
        verify(patientRepository).save((Patient) any());
        verify(userService).findUserById((Long) any());
        verify(userService).register((UserDto) any());
        assertSame(user1, patient1.getUser());
    }

    /**
     * Method under test: {@link PatientService#addPatient(Patient)}
     */
    @Test
    void testAddPatient2() throws NotFoundException, PasswordException, ResourceAlreadyExists {
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
        when(patientRepository.save((Patient) any())).thenReturn(patient);
        when(userService.findUserById((Long) any())).thenThrow(new NotFoundException("An error occurred"));
        when(userService.register((UserDto) any())).thenThrow(new ResourceAlreadyExists("Not all who wander are lost"));

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
        patient1.setUser(user1);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");
        assertThrows(NotFoundException.class, () -> patientService.addPatient(patient1));
        verify(userService).findUserById((Long) any());
    }


    @Test
    @Disabled("TODO: Complete this test")
    void testLoginAsPatient() throws InvalidCredentialsException {

        when(patientRepository.findByUserPassword((String) any(), (String) any())).thenReturn(new ArrayList<>());

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
        patientService.loginAsPatient(patient);
    }


    @Test
    void testLoginAsPatient2() throws InvalidCredentialsException {
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

        ArrayList<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        when(patientRepository.findByUserPassword((String) any(), (String) any())).thenReturn(patientList);

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
        patient1.setUser(user1);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");
        assertSame(patient, patientService.loginAsPatient(patient1));
        verify(patientRepository).findByUserPassword((String) any(), (String) any());
    }

    /**
     * Method under test: {@link PatientService#removePatient(Patient)}
     */
    @Test
    void testRemovePatient() throws InvalidCredentialsException, NotFoundException {
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
        Optional<Patient> ofResult = Optional.of(patient);
        doNothing().when(patientRepository).delete((Patient) any());
        when(patientRepository.findById((Long) any())).thenReturn(ofResult);

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
        patient1.setUser(user1);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");
        assertSame(patient, patientService.removePatient(patient1));
        verify(patientRepository).findById((Long) any());
        verify(patientRepository).delete((Patient) any());
    }


    @Test
    void testRemovePatient2() throws InvalidCredentialsException, NotFoundException {
        doNothing().when(patientRepository).delete((Patient) any());
        when(patientRepository.findById((Long) any())).thenReturn(Optional.empty());

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
        assertThrows(NotFoundException.class, () -> patientService.removePatient(patient));
        verify(patientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PatientService#updatePatientInfo(Patient)}
     */
    @Test
    void testUpdatePatientInfo() throws InvalidCredentialsException, NotFoundException {
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
        Optional<Patient> ofResult = Optional.of(patient);

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
        patient1.setUser(user1);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");
        when(patientRepository.save((Patient) any())).thenReturn(patient1);
        when(patientRepository.findById((Long) any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");

        User user2 = new User();
        user2.setActive(true);
        user2.setContactNumber("42");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
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
        patient2.setUser(user2);
        patient2.setUserId(123L);
        patient2.setUserStatus(UserStatus.ACTIVE);
        patient2.setUsername("janedoe");
        assertSame(patient1, patientService.updatePatientInfo(patient2));
        verify(patientRepository).save((Patient) any());
        verify(patientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PatientService#findPatientByPatientId(Long)}
     */
    @Test
    void testFindPatientByPatientId() throws NotFoundException {
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
        Optional<Patient> ofResult = Optional.of(patient);
        when(patientRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(patient, patientService.findPatientByPatientId(123L));
        verify(patientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PatientService#findPatientByPatientId(Long)}
     */
    @Test
    void testFindPatientByPatientId2() throws NotFoundException {
        when(patientRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> patientService.findPatientByPatientId(123L));
        verify(patientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PatientService#getAllPatients()}
     */
    @Test
    void testGetAllPatients() {
        ArrayList<Patient> patientList = new ArrayList<>();
        when(patientRepository.findAll()).thenReturn(patientList);
        List<Patient> actualAllPatients = patientService.getAllPatients();
        assertSame(patientList, actualAllPatients);
        assertTrue(actualAllPatients.isEmpty());
        verify(patientRepository).findAll();
    }

    /**
     * Method under test: {@link PatientService#getAllPatients()}
     */
    @Test
    void testGetAllPatients2() {
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

        ArrayList<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        when(patientRepository.findAll()).thenReturn(patientList);
        List<Patient> actualAllPatients = patientService.getAllPatients();
        assertSame(patientList, actualAllPatients);
        assertEquals(1, actualAllPatients.size());
        verify(patientRepository).findAll();
    }

    /**
     * Method under test: {@link PatientService#getAllPatients()}
     */
    @Test
    void testGetAllPatients3() {
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
        patient1.setUser(user1);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");

        ArrayList<Patient> patientList = new ArrayList<>();
        patientList.add(patient1);
        patientList.add(patient);
        when(patientRepository.findAll()).thenReturn(patientList);
        List<Patient> actualAllPatients = patientService.getAllPatients();
        assertSame(patientList, actualAllPatients);
        assertEquals(2, actualAllPatients.size());
        verify(patientRepository).findAll();
    }

    /**
     * Method under test: {@link PatientService#deleteById(Long)}
     */
    @Test
    void testDeleteById() throws DuplicateException, EmptyException, NotFoundException {
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
        Optional<Patient> ofResult = Optional.of(patient);
        doNothing().when(patientRepository).delete((Patient) any());
        when(patientRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(patient, patientService.deleteById(123L));
        verify(patientRepository).findById((Long) any());
        verify(patientRepository).delete((Patient) any());
    }

    @Test
    void testDeleteById3() throws DuplicateException, EmptyException, NotFoundException {
        doNothing().when(patientRepository).delete((Patient) any());
        when(patientRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> patientService.deleteById(123L));
        verify(patientRepository).findById((Long) any());
    }

    @Test
    void testDeleteById4() throws DuplicateException, EmptyException, NotFoundException {
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
        Optional<Patient> ofResult = Optional.of(patient);
        doNothing().when(patientRepository).delete((Patient) any());
        when(patientRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(EmptyException.class, () -> patientService.deleteById(null));
    }

    /**
     * Method under test: {@link PatientService#activePatientAccount(Patient)}
     */
    @Test
    void testActivePatientAccount() throws NotFoundException {
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
        Optional<Patient> ofResult = Optional.of(patient);

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
        patient1.setUser(user1);
        patient1.setUserId(123L);
        patient1.setUserStatus(UserStatus.ACTIVE);
        patient1.setUsername("janedoe");
        when(patientRepository.save((Patient) any())).thenReturn(patient1);
        when(patientRepository.findById((Long) any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");

        User user2 = new User();
        user2.setActive(true);
        user2.setContactNumber("42");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
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
        patient2.setUser(user2);
        patient2.setUserId(123L);
        patient2.setUserStatus(UserStatus.ACTIVE);
        patient2.setUsername("janedoe");
        assertSame(patient1, patientService.activePatientAccount(patient2));
        verify(patientRepository).save((Patient) any());
        verify(patientRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link PatientService#findPatientByUsername(String)}
     */
    @Test
    void testFindPatientByUsername() {
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
        when(patientRepository.findByUsername((String) any())).thenReturn(patient);
        assertSame(patient, patientService.findPatientByUsername("janedoe"));
        verify(patientRepository).findByUsername((String) any());
    }
}

