package com.zorginstellingen.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zorginstellingen.dto.UserDto;
import com.zorginstellingen.enums.Gender;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.model.User;
import com.zorginstellingen.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {LoginController.class})
@ExtendWith(SpringExtension.class)
class LoginControllerTest {
    @Autowired
    private LoginController loginController;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link LoginController#authenticateUser(String, String)}
     */
    @Test
    void testAuthenticateUser() throws Exception {
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

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
        when(userService.getUserByUserName((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signin")
                .param("password", "foo")
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Invalid Credentials"));
    }

    /**
     * Method under test: {@link LoginController#authenticateUser(String, String)}
     */
    @Test
    void testAuthenticateUser2() throws Exception {
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(null);

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
        when(userService.getUserByUserName((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signin")
                .param("password", "foo")
                .param("username", "foo");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400))
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Invalid Credentials"));
    }

    /**
     * Method under test: {@link LoginController#authenticateUser(String, String)}
     */
    @Test
    void testAuthenticateUser3() throws Exception {
        when(restTemplate.postForEntity((String) any(), (Object) any(), (Class<Object>) any(), (Object[]) any()))
                .thenReturn(new ResponseEntity<>("Body", HttpStatus.CONTINUE));

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
        when(userService.getUserByUserName((String) any())).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signin")
                .param("password", "foo")
                .param("username", "foo");
        MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":123,\"username\":\"janedoe\",\"active\":true,\"createdDate\":0,\"qualification\":\"Qualification\","
                                        + "\"userDescription\":\"User Description\",\"contactNumber\":\"42\",\"email\":\"jane.doe@example.org\",\"gender\":\"man"
                                        + "\",\"roleId\":123,\"token\":\"Body\",\"role\":{\"id\":123,\"roleName\":\"Role Name\"}}]"));
    }

    /**
     * Method under test: {@link LoginController#create(UserDto)}
     */
    @Test
    void testCreate() throws Exception {
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
        when(userService.register((UserDto) any())).thenReturn(user);

        UserDto userDto = new UserDto();
        userDto.setAccountNumber("42");
        userDto.setActive(true);
        userDto.setContactNumber("42");
        userDto.setEmail("jane.doe@example.org");
        userDto.setGender(Gender.man);
        userDto.setId(123L);
        userDto.setPassword("iloveyou");
        userDto.setQualification("Qualification");
        userDto.setUserDescription("User Description");
        userDto.setUserRole("User Role");
        userDto.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(userDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"username\":\"janedoe\",\"active\":true,\"createdDate\":0,\"qualification\":\"Qualification\","
                                        + "\"userDescription\":\"User Description\",\"contactNumber\":\"42\",\"email\":\"jane.doe@example.org\",\"gender\":\"man"
                                        + "\",\"roleId\":123,\"token\":\"ABC123\",\"role\":{\"id\":123,\"roleName\":\"Role Name\"}}"));
    }
}

