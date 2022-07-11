package com.zorginstellingen.controller;

import com.zorginstellingen.enums.AppointmentStatus;
import com.zorginstellingen.enums.Gender;
import com.zorginstellingen.enums.UserStatus;
import com.zorginstellingen.model.Appointment;
import com.zorginstellingen.model.Patient;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.model.User;
import com.zorginstellingen.service.AppointmentService;
import com.zorginstellingen.service.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
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

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {AppointmentController.class})
@ExtendWith(SpringExtension.class)
class AppointmentControllerTest {
    @Autowired
    private AppointmentController appointmentController;

    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private PatientService patientService;

    /**
     * Method under test: {@link AppointmentController#findAll()}
     */
    @Test
    void testFindAll() throws Exception {
        when(appointmentService.getAllAppointments()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/appointment/getAll");
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link AppointmentController#findAll()}
     */
    @Test
    void testFindAll2() throws Exception {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("?");

        User user = new User();
        user.setActive(true);
        user.setContactNumber("42");
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        user.setCreatedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        user.setEmail("jane.doe@example.org");
        user.setGender(Gender.man);
        user.setId(123L);
        user.setPassword("iloveyou");
        user.setQualification("?");
        user.setRole(role);
        user.setRoleId(123L);
        user.setToken("ABC123");
        user.setUserDescription("?");
        user.setUsername("janedoe");

        Patient patient = new Patient();
        patient.setAccountNumber("42");
        patient.setBloodGroup("?");
        patient.setContactNumber("42");
        patient.setDoctorName("?");
        patient.setEmail("jane.doe@example.org");
        patient.setGender(Gender.man);
        patient.setId(123L);
        patient.setPassword("iloveyou");
        patient.setPatientAddress("42 Main St");
        patient.setPatientName("?");
        patient.setUser(user);
        patient.setUserId(123L);
        patient.setUserStatus(UserStatus.ACTIVE);
        patient.setUsername("janedoe");

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("?");

        User user1 = new User();
        user1.setActive(true);
        user1.setContactNumber("42");
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user1.setCreatedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        user1.setEmail("jane.doe@example.org");
        user1.setGender(Gender.man);
        user1.setId(123L);
        user1.setPassword("iloveyou");
        user1.setQualification("?");
        user1.setRole(role1);
        user1.setRoleId(123L);
        user1.setToken("ABC123");
        user1.setUserDescription("?");
        user1.setUsername("janedoe");

        Appointment appointment = new Appointment();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        appointment.setAppointmentDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        appointment.setAppointmentDescription("?");
        appointment.setAppointmentStatus(AppointmentStatus.ACCEPTED);
        appointment.setDoctorId(123L);
        appointment.setDoctorName("?");
        appointment.setId(123L);
        appointment.setPatient(patient);
        appointment.setPatientId(123L);
        appointment.setPatientName("?");
        appointment.setUser(user1);

        ArrayList<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment);
        when(appointmentService.getAllAppointments()).thenReturn(appointmentList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/appointment/getAll");
        MockMvcBuilders.standaloneSetup(appointmentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"appointmentDescription\":\"?\",\"appointmentDate\":0,\"doctorId\":123,\"doctorName\":\"janedoe\","
                                        + "\"patientName\":\"?\",\"appointmentStatus\":\"ACCEPTED\",\"patientId\":123}]"));
    }
}

