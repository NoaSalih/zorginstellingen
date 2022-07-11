package com.zorginstellingen.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppointmentTest {

    @Test
    void getId() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        Long id = appointment.getId();
        assertEquals(1L, id, "Expected id = 1L");
    }

    @Test
    void getAppointmentDescription() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        Long id = appointment.getId();
        appointment.setPatientName("john");
        String patientName = appointment.getPatientName();

        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("john", patientName, "Expected patient is = john");
    }

    @Test
    void getAppointmentDate() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        Long id = appointment.getId();
        appointment.setPatientName("john");
        String patientName = appointment.getPatientName();

        Date date = new Date();
        appointment.setAppointmentDate(date);
        Date checkDate = appointment.getAppointmentDate();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("john", patientName, "Expected patient is = john");
        assertEquals(date, checkDate, "expected created date is same as prev");
    }

    @Test
    void getDoctorId() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        Long id = appointment.getId();
        appointment.setPatientName("john");
        String patientName = appointment.getPatientName();
        appointment.setDoctorId(1L);
        Long doctor = appointment.getDoctorId();

        Date date = new Date();
        appointment.setAppointmentDate(date);
        Date checkDate = appointment.getAppointmentDate();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("john", patientName, "Expected patient is = john");
        assertEquals(date, checkDate, "expected created date is same as prev");
        assertEquals(appointment.getDoctorId(), doctor, "expected doctor id =1L");
    }

    @Test
    void getDoctorName() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);
        Long id = appointment.getId();
        appointment.setPatientName("john");
        String patientName = appointment.getPatientName();
        appointment.setDoctorName("noa");
        String doctorName = appointment.getDoctorName();
        Date date = new Date();
        appointment.setAppointmentDate(date);
        Date checkDate = appointment.getAppointmentDate();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("john", patientName, "Expected patient is = john");
        assertEquals(date, checkDate, "expected created date is same as prev");
        assertEquals("noa",doctorName , "expected doctor name = noa");
    }
}