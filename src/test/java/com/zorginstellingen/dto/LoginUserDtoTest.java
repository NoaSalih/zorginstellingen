package com.zorginstellingen.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginUserDtoTest {

    @Test
    void getSetId() {
        LoginUserDto dto = new LoginUserDto();
        dto.setId(1L);
        Long id = dto.getId();
        assertEquals(1L, id, "Expected id = 1L");
    }

    @Test
    void getSetUsername() {
        LoginUserDto dto = new LoginUserDto();
        dto.setId(1L);
        Long id = dto.getId();
        dto.setUsername("noa");
        String username = dto.getUsername();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("noa", username, "Expected username = noa");
    }

    @Test
    void getSetPassword() {
        LoginUserDto dto = new LoginUserDto();
        dto.setId(1L);
        Long id = dto.getId();
        dto.setUsername("noa");
        String username = dto.getUsername();
        dto.setPassword("D238cfvv@123");
        String password = dto.getPassword();
        assertEquals(1L, id, "Expected id = 1L");
        assertEquals("noa", username, "Expected username = noa");
        assertEquals("D238cfvv@123", password, "Expected password = D238cfvv@123");
    }
}