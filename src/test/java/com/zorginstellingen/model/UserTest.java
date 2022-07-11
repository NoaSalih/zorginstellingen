package com.zorginstellingen.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserTest {

    @Test
    void getSetId() {
       User user = new User();
       user.setId(1L);
       Long id = user.getId();
       assertEquals(1L, id, "Expected role id = 1L");
    }

    @Test
    void getSetUsername() {
        User user = new User();
        user.setId(1L);
        Long id = user.getId();
        user.setUsername("noa");
        String username = user.getUsername();
        assertEquals(1L, id, "Expected role id = 1L");
        assertEquals("noa", username, "Expected username is = noa");
    }

    @Test
    void getPassword() {
        User user = new User();
        user.setId(1L);
        Long id = user.getId();
        user.setUsername("noa");
        String username = user.getUsername();
        user.setPassword("Noa@1234");
        String password = user.getPassword();
        assertEquals(1L, id, "Expected role id = 1L");
        assertEquals("noa", username, "Expected username is = noa");
        assertEquals("Noa@1234", password, "Expected password is = Noa@1234");
    }

    @Test
    void getActive() {
        User user = new User();
        user.setId(1L);
        Long id = user.getId();
        user.setUsername("noa");
        String username = user.getUsername();
        user.setPassword("Noa@1234");
        String password = user.getPassword();
        user.setActive(true);
        Boolean active = user.getActive();

        assertEquals(1L, id, "Expected role id = 1L");
        assertEquals("noa", username, "Expected username is = noa");
        assertEquals("Noa@1234", password, "Expected password is = Noa@1234");
        assertNotEquals(false, active, "Expected status is not false");
    }

    @Test
    void getCreatedDate() {
        User user = new User();
        user.setId(1L);
        Long id = user.getId();
        user.setUsername("noa");
        String username = user.getUsername();
        user.setPassword("Noa@1234");
        String password = user.getPassword();
        user.setActive(true);
        Boolean active = user.getActive();
        Date date = new Date();
        user.setCreatedDate(date);
        Date checkDate = user.getCreatedDate();

        assertEquals(1L, id, "Expected role id = 1L");
        assertEquals("noa", username, "Expected username is = noa");
        assertEquals("Noa@1234", password, "Expected password is = Noa@1234");
        assertNotEquals(false, active, "Expected status is not false");
        assertEquals(date, checkDate, "expected created date is same as prev");
    }

    @Test
    void getQualification() {
        User user = new User();
        user.setId(1L);
        Long id = user.getId();
        user.setUsername("noa");
        String username = user.getUsername();
        user.setPassword("Noa@1234");
        String password = user.getPassword();
        user.setActive(true);
        Boolean active = user.getActive();
        Date date = new Date();
        user.setCreatedDate(date);
        Date checkDate = user.getCreatedDate();

        user.setQualification("MBBS");
        String qualification = user.getQualification();

        assertEquals(1L, id, "Expected role id = 1L");
        assertEquals("noa", username, "Expected username is = noa");
        assertEquals("Noa@1234", password, "Expected password is = Noa@1234");
        assertNotEquals(false, active, "Expected status is not false");
        assertEquals(date, checkDate, "expected created date is same as prev");
        assertEquals("MBBS", qualification, "Expected qualification is MBBS");
    }


    @Test
    void getUserDescription() {
        User user = new User();
        user.setId(1L);
        Long id = user.getId();
        user.setUsername("noa");
        String username = user.getUsername();
        user.setPassword("Noa@1234");
        String password = user.getPassword();
        user.setActive(true);
        Boolean active = user.getActive();
        Date date = new Date();
        user.setCreatedDate(date);
        Date checkDate = user.getCreatedDate();

        user.setQualification("MBBS");
        String qualification = user.getQualification();

        user.setUserDescription("First doctor");
        String description = user.getUserDescription();

        assertEquals(1L, id, "Expected role id = 1L");
        assertEquals("noa", username, "Expected username is = noa");
        assertEquals("Noa@1234", password, "Expected password is = Noa@1234");
        assertNotEquals(false, active, "Expected status is not false");
        assertEquals(date, checkDate, "expected created date is same as prev");
        assertEquals("MBBS", qualification, "Expected qualification is MBBS");
        assertNotEquals("Bad doctor", description, "Expected description is not equal");
    }

}