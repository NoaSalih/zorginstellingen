package com.zorginstellingen.dto;

import com.zorginstellingen.enums.Gender;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDtoTest {

    @Test
    void testConstructor() {
        UserDto actualUserDto = new UserDto();
        actualUserDto.setAccountNumber("42");
        actualUserDto.setActive(true);
        actualUserDto.setContactNumber("42");
        actualUserDto.setEmail("jane.doe@example.org");
        actualUserDto.setGender(Gender.man);
        actualUserDto.setId(123L);
        actualUserDto.setPassword("iloveyou");
        actualUserDto.setQualification("Qualification");
        actualUserDto.setUserDescription("User Description");
        actualUserDto.setUserRole("User Role");
        actualUserDto.setUsername("janedoe");
        String actualToStringResult = actualUserDto.toString();
        assertEquals("42", actualUserDto.getAccountNumber());
        assertTrue(actualUserDto.getActive());
        assertEquals("42", actualUserDto.getContactNumber());
        assertEquals("jane.doe@example.org", actualUserDto.getEmail());
        assertEquals(Gender.man, actualUserDto.getGender());
        assertEquals(123L, actualUserDto.getId().longValue());
        assertEquals("iloveyou", actualUserDto.getPassword());
        assertEquals("Qualification", actualUserDto.getQualification());
        assertEquals("User Description", actualUserDto.getUserDescription());
        assertEquals("User Role", actualUserDto.getUserRole());
        assertEquals("janedoe", actualUserDto.getUsername());
        assertEquals("UserDto(id=123, username=janedoe, password=iloveyou, email=jane.doe@example.org, active=true,"
                + " qualification=Qualification, userDescription=User Description, accountNumber=42, gender=man,"
                + " contactNumber=42, userRole=User Role)", actualToStringResult);
    }

    @Test
    void testConstructor2() {
        UserDto actualUserDto = new UserDto(123L, "janedoe", "iloveyou", "jane.doe@example.org", true, "Qualification",
                "User Description", "42", Gender.man, "42", "User Role");
        actualUserDto.setAccountNumber("42");
        actualUserDto.setActive(true);
        actualUserDto.setContactNumber("42");
        actualUserDto.setEmail("jane.doe@example.org");
        actualUserDto.setGender(Gender.man);
        actualUserDto.setId(123L);
        actualUserDto.setPassword("iloveyou");
        actualUserDto.setQualification("Qualification");
        actualUserDto.setUserDescription("User Description");
        actualUserDto.setUserRole("User Role");
        actualUserDto.setUsername("janedoe");
        String actualToStringResult = actualUserDto.toString();
        assertEquals("42", actualUserDto.getAccountNumber());
        assertTrue(actualUserDto.getActive());
        assertEquals("42", actualUserDto.getContactNumber());
        assertEquals("jane.doe@example.org", actualUserDto.getEmail());
        assertEquals(Gender.man, actualUserDto.getGender());
        assertEquals(123L, actualUserDto.getId().longValue());
        assertEquals("iloveyou", actualUserDto.getPassword());
        assertEquals("Qualification", actualUserDto.getQualification());
        assertEquals("User Description", actualUserDto.getUserDescription());
        assertEquals("User Role", actualUserDto.getUserRole());
        assertEquals("janedoe", actualUserDto.getUsername());
        assertEquals("UserDto(id=123, username=janedoe, password=iloveyou, email=jane.doe@example.org, active=true,"
                + " qualification=Qualification, userDescription=User Description, accountNumber=42, gender=man,"
                + " contactNumber=42, userRole=User Role)", actualToStringResult);
    }
}

