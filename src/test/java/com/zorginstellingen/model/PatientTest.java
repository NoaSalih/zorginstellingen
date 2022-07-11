package com.zorginstellingen.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PatientTest {

    @Test
    void getSetId() {
        Patient patient = new Patient();
        patient.setId(1L);
        Long id = patient.getId();
        assertEquals(1L, id, "Expected id = 1L");
    }

    @Test
    void getSetPatientName() {
        Patient patient = new Patient();
        patient.setId(1L);
        Long id = patient.getId();
        patient.setPatientName("fahad");
        String username = patient.getPatientName();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("fahad", username, "Expected patient is = fahad");
    }

    @Test
    void getPatientAddress() {
        Patient patient = new Patient();
        patient.setId(1L);
        Long id = patient.getId();
        patient.setPatientName("fahad");
        String username = patient.getPatientName();
        patient.setPatientAddress("nethernald");
        String address = patient.getPatientAddress();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("fahad", username, "Expected patient is = fahad");
        assertEquals("nethernald", address, "Expected address is = nethernald");
    }

    @Test
    void getContactNumber() {
        Patient patient = new Patient();
        patient.setId(1L);
        Long id = patient.getId();
        patient.setPatientName("fahad");
        String username = patient.getPatientName();
        patient.setPatientAddress("nethernald");
        String address = patient.getPatientAddress();
        patient.setContactNumber("12012012");
        String contact = patient.getContactNumber();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("fahad", username, "Expected patient is = fahad");
        assertEquals("nethernald", address, "Expected address is = nethernald");
        assertEquals("12012012", contact, "Expected contact is = 12012012");
    }

    @Test
    void getAccountNumber() {
        Patient patient = new Patient();
        patient.setId(1L);
        Long id = patient.getId();
        patient.setPatientName("fahad");
        String username = patient.getPatientName();
        patient.setPatientAddress("nederland");
        String address = patient.getPatientAddress();
        patient.setContactNumber("12012012");
        String contact = patient.getContactNumber();
        patient.setAccountNumber("000000");
        String account = patient.getAccountNumber();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("fahad", username, "Expected patient is = fahad");
        assertEquals("nederland", address, "Expected address is = nederland");
        assertEquals("12012012", contact, "Expected contact is = 12012012");
        assertEquals("000000", account, "Expected account is = 000000");
    }

    @Test
    void getBloodGroup() {
        Patient patient = new Patient();
        patient.setId(1L);
        Long id = patient.getId();
        patient.setPatientName("fahad");
        String username = patient.getPatientName();
        patient.setPatientAddress("nederland");
        String address = patient.getPatientAddress();
        patient.setContactNumber("12012012");
        String contact = patient.getContactNumber();
        patient.setAccountNumber("000000");
        String account = patient.getAccountNumber();
        patient.setBloodGroup("AB+");
        String bloodGroup = patient.getBloodGroup();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("fahad", username, "Expected patient is = fahad");
        assertEquals("nederland", address, "Expected address is = nederland");
        assertEquals("12012012", contact, "Expected contact is = 12012012");
        assertEquals("000000", account, "Expected Blood group is = AB+");
        assertEquals("AB+", bloodGroup, "Expected account is = 000000");
    }

    @Test
    void getPassword() {
        Patient patient = new Patient();
        patient.setId(1L);
        Long id = patient.getId();
        patient.setPatientName("fahad");
        String username = patient.getPatientName();
        patient.setPatientAddress("nederland");
        String address = patient.getPatientAddress();
        patient.setContactNumber("12012012");
        String contact = patient.getContactNumber();
        patient.setAccountNumber("000000");
        String account = patient.getAccountNumber();
        patient.setBloodGroup("AB+");
        String bloodGroup = patient.getBloodGroup();
        patient.setPassword("12234Bas+");
        String password = patient.getPassword();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("fahad", username, "Expected patient is = fahad");
        assertEquals("nederland", address, "Expected address is = nederland");
        assertEquals("12012012", contact, "Expected contact is = 12012012");
        assertEquals("000000", account, "Expected account is = 000000");
        assertEquals("AB+", bloodGroup, "Expected Blood group is = AB+");
        assertEquals("12234Bas+", password, "Expected password is = 12234Bas+");
    }
}