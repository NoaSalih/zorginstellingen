package com.zorginstellingen.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoleTest {

    @Test
    public void getSetRoleName(){
        Role role = new Role();
        role.setRoleName("PATIENT");
        String checkRoleName = role.getRoleName();
        assertEquals("PATIENT", checkRoleName, "Expected role is PATIENT as same as of role");
    }
    @Test
    public void getSetId(){
        Role role = new Role();
        role.setId(1L);
        Long checkId = role.getId();
        assertEquals(1L, checkId, "Expected role is PATIENT as same as of role");
    }
}

