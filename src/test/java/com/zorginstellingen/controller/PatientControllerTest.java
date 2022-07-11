package com.zorginstellingen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zorginstellingen.enums.Gender;
import com.zorginstellingen.enums.UserStatus;
import com.zorginstellingen.model.Patient;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.model.User;
import com.zorginstellingen.service.PatientService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {PatientController.class})
@ExtendWith(SpringExtension.class)
class PatientControllerTest {
    @Autowired
    private PatientController patientController;

    @MockBean
    private PatientService patientService;

    /**
     * Method under test: {@link PatientController#delete(Long)}
     */
    @Test
    void testDelete() throws Exception {
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
        when(patientService.deleteById((Long) any())).thenReturn(patient);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/patients/delete/{id}", 123L);
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"patientName\":\"Patient Name\",\"patientAddress\":\"42 Main St\",\"contactNumber\":\"42\",\"accountNumber"
                                        + "\":\"42\",\"bloodGroup\":\"Blood Group\",\"password\":\"iloveyou\",\"email\":\"jane.doe@example.org\",\"username\":"
                                        + "\"janedoe\",\"doctorName\":\"Doctor Name\",\"gender\":\"man\",\"userStatus\":\"ACTIVE\",\"userId\":123}"));
    }


    /**
     * Method under test: {@link PatientController#activePatientAccount(Patient)}
     */
    @Test
    void testActivePatientAccount() throws Exception {
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
        when(patientService.activePatientAccount((Patient) any())).thenReturn(patient);

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
        String content = (new ObjectMapper()).writeValueAsString(patient1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/patients/activePatientAccount")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"patientName\":\"Patient Name\",\"patientAddress\":\"42 Main St\",\"contactNumber\":\"42\",\"accountNumber"
                                        + "\":\"42\",\"bloodGroup\":\"Blood Group\",\"password\":\"iloveyou\",\"email\":\"jane.doe@example.org\",\"username\":"
                                        + "\"janedoe\",\"doctorName\":\"Doctor Name\",\"gender\":\"man\",\"userStatus\":\"ACTIVE\",\"userId\":123}"));
    }

    /**
     * Method under test: {@link PatientController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(patientService.getAllPatients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patients/getAll");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PatientController#findAll()}
     */
    @Test
    void testFindAll2() throws Exception {
        when(patientService.getAllPatients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/patients/getAll");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PatientController#findById(Long)}
     */
    @Test
    void testFindById() throws Exception {
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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patients/findById/{id}", 123L);
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"patientName\":\"Patient Name\",\"patientAddress\":\"42 Main St\",\"contactNumber\":\"42\",\"accountNumber"
                                        + "\":\"42\",\"bloodGroup\":\"Blood Group\",\"password\":\"iloveyou\",\"email\":\"jane.doe@example.org\",\"username\":"
                                        + "\"janedoe\",\"doctorName\":\"Doctor Name\",\"gender\":\"man\",\"userStatus\":\"ACTIVE\",\"userId\":123}"));
    }

    /**
     * Method under test: {@link PatientController#update(Patient)}
     */
    @Test
    void testUpdate() throws Exception {
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
        when(patientService.updatePatientInfo((Patient) any())).thenReturn(patient);

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
        String content = (new ObjectMapper()).writeValueAsString(patient1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/patients/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(patientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"patientName\":\"Patient Name\",\"patientAddress\":\"42 Main St\",\"contactNumber\":\"42\",\"accountNumber"
                                        + "\":\"42\",\"bloodGroup\":\"Blood Group\",\"password\":\"iloveyou\",\"email\":\"jane.doe@example.org\",\"username\":"
                                        + "\"janedoe\",\"doctorName\":\"Doctor Name\",\"gender\":\"man\",\"userStatus\":\"ACTIVE\",\"userId\":123}"));
    }
}

