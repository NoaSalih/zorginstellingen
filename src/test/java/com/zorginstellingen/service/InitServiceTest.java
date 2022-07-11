package com.zorginstellingen.service;

import com.zorginstellingen.dto.UserDto;
import com.zorginstellingen.enums.Gender;
import com.zorginstellingen.exception.*;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.model.User;
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

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {InitService.class})
@ExtendWith(SpringExtension.class)
class InitServiceTest {
    @Autowired
    private InitService initService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private UserService userService;

    @Test
    void testInitBasicAccounts()
            throws DuplicateException, EmptyException, NotFoundException, PasswordException, ResourceAlreadyExists {

        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");
        when(roleService.addRole((Role) any())).thenReturn(role);
        when(roleService.getAllRoles()).thenReturn(new ArrayList<>());

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");

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
        user.setRole(role1);
        user.setRoleId(123L);
        user.setToken("ABC123");
        user.setUserDescription("User Description");
        user.setUsername("janedoe");
        when(userService.register((UserDto) any())).thenReturn(user);
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());
        initService.initBasicAccounts();
    }

    @Test
    void testInitBasicAccounts2()
            throws DuplicateException, EmptyException, NotFoundException, PasswordException, ResourceAlreadyExists {

        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("\n____________Basic Initiating Accounts_______________\n");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role1);
        when(roleService.addRole((Role) any())).thenReturn(role);
        when(roleService.getAllRoles()).thenReturn(roleList);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");

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
        user.setRole(role2);
        user.setRoleId(123L);
        user.setToken("ABC123");
        user.setUserDescription("User Description");
        user.setUsername("janedoe");
        when(userService.register((UserDto) any())).thenReturn(user);
        when(userService.getAllUsers()).thenReturn(new ArrayList<>());
        initService.initBasicAccounts();
    }
}

