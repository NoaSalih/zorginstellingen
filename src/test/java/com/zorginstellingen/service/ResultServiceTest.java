package com.zorginstellingen.service;

import com.zorginstellingen.enums.Gender;
import com.zorginstellingen.enums.UserStatus;
import com.zorginstellingen.exception.EmptyException;
import com.zorginstellingen.exception.NotFoundException;
import com.zorginstellingen.model.Patient;
import com.zorginstellingen.model.Result;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.model.User;
import com.zorginstellingen.repositories.ResultRepository;
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

@ContextConfiguration(classes = {ResultService.class})
@ExtendWith(SpringExtension.class)
class ResultServiceTest {
    @MockBean
    private PatientService patientService;

    @MockBean
    private ResultRepository resultRepository;

    @Autowired
    private ResultService resultService;

    @Test
    void testGetAllResults() {
        ArrayList<Result> resultList = new ArrayList<>();
        when(resultRepository.findAll()).thenReturn(resultList);
        List<Result> actualAllResults = resultService.getAllResults();
        assertSame(resultList, actualAllResults);
        assertTrue(actualAllResults.isEmpty());
        verify(resultRepository).findAll();
    }


    @Test
    void testAddResult() throws EmptyException, NotFoundException {
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
        when(patientService.findPatientByPatientId((Long) any())).thenReturn(patient);

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

        Result result = new Result();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result.setCreatedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        result.setId(123L);
        result.setPatient(patient1);
        result.setPatientId(123L);
        result.setResultDescription("Result Description");
        when(resultRepository.save((Result) any())).thenReturn(result);

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

        Result result1 = new Result();
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result1.setCreatedDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
        result1.setId(123L);
        result1.setPatient(patient2);
        result1.setPatientId(123L);
        result1.setResultDescription("Result Description");
        assertSame(result, resultService.addResult(result1));
        verify(patientService).findPatientByPatientId((Long) any());
        verify(resultRepository).save((Result) any());
        assertSame(patient, result1.getPatient());
    }


    @Test
    void testUpdateResult() throws EmptyException, NotFoundException {
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

        Result result = new Result();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result.setCreatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        result.setId(123L);
        result.setPatient(patient);
        result.setPatientId(123L);
        result.setResultDescription("Result Description");
        Optional<Result> ofResult = Optional.of(result);

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");

        User user1 = new User();
        user1.setActive(true);
        user1.setContactNumber("42");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
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

        Result result1 = new Result();
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result1.setCreatedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        result1.setId(123L);
        result1.setPatient(patient1);
        result1.setPatientId(123L);
        result1.setResultDescription("Result Description");
        when(resultRepository.save((Result) any())).thenReturn(result1);
        when(resultRepository.findById((Long) any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");

        User user2 = new User();
        user2.setActive(true);
        user2.setContactNumber("42");
        LocalDateTime atStartOfDayResult4 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedDate(Date.from(atStartOfDayResult4.atZone(ZoneId.of("UTC")).toInstant()));
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

        Result result2 = new Result();
        LocalDateTime atStartOfDayResult5 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result2.setCreatedDate(Date.from(atStartOfDayResult5.atZone(ZoneId.of("UTC")).toInstant()));
        result2.setId(123L);
        result2.setPatient(patient2);
        result2.setPatientId(123L);
        result2.setResultDescription("Result Description");
        assertSame(result1, resultService.updateResult(result2));
        verify(resultRepository).save((Result) any());
        verify(resultRepository).findById((Long) any());
    }

    @Test
    void testDeleteResult() throws EmptyException, NotFoundException {
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

        Result result = new Result();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result.setCreatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        result.setId(123L);
        result.setPatient(patient);
        result.setPatientId(123L);
        result.setResultDescription("Result Description");
        Optional<Result> ofResult = Optional.of(result);
        doNothing().when(resultRepository).delete((Result) any());
        when(resultRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(result, resultService.deleteResult(123L));
        verify(resultRepository).findById((Long) any());
        verify(resultRepository).delete((Result) any());
    }

    /**
     * Method under test: {@link ResultService#deleteResult(Long)}
     */
    @Test
    void testDeleteResult2() throws EmptyException, NotFoundException {
        doNothing().when(resultRepository).delete((Result) any());
        when(resultRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> resultService.deleteResult(123L));
        verify(resultRepository).findById((Long) any());
    }

    @Test
    void testFindResultById() throws NotFoundException {
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

        Result result = new Result();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        result.setCreatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        result.setId(123L);
        result.setPatient(patient);
        result.setPatientId(123L);
        result.setResultDescription("Result Description");
        Optional<Result> ofResult = Optional.of(result);
        when(resultRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(result, resultService.findResultById(123L));
        verify(resultRepository).findById((Long) any());
    }

    @Test
    void testFindResultById2() throws NotFoundException {
        when(resultRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> resultService.findResultById(123L));
        verify(resultRepository).findById((Long) any());
    }

    @Test
    void testGetAllResultsByDoctorId() {
        ArrayList<Result> resultList = new ArrayList<>();
        when(resultRepository.findByDoctorId((Long) any())).thenReturn(resultList);
        List<Result> actualAllResultsByDoctorId = resultService.getAllResultsByDoctorId(123L);
        assertSame(resultList, actualAllResultsByDoctorId);
        assertTrue(actualAllResultsByDoctorId.isEmpty());
        verify(resultRepository).findByDoctorId((Long) any());
    }
}

