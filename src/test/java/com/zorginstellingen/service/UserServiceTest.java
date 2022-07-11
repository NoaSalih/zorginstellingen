package com.zorginstellingen.service;

import com.zorginstellingen.dto.UserDto;
import com.zorginstellingen.enums.Gender;
import com.zorginstellingen.exception.NotFoundException;
import com.zorginstellingen.exception.PasswordException;
import com.zorginstellingen.exception.ResourceAlreadyExists;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.model.User;
import com.zorginstellingen.repositories.UserRepository;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private RoleService roleService;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void testGetAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);
        List<User> actualAllUsers = userService.getAllUsers();
        assertSame(userList, actualAllUsers);
        assertTrue(actualAllUsers.isEmpty());
        verify(userRepository).findAll();
    }

    @Test
    void testRegister() throws NotFoundException, PasswordException, ResourceAlreadyExists {
        assertThrows(NotFoundException.class, () -> userService.register(new UserDto()));
    }

    @Test
    void testRegister3() throws NotFoundException, PasswordException, ResourceAlreadyExists {
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
        when(userRepository.checkUsernameExists((String) any())).thenReturn(user);
        assertThrows(ResourceAlreadyExists.class,
                () -> userService.register(new UserDto(123L, "janedoe", "iloveyou", "jane.doe@example.org", true,
                        "Vermeld gebruikersgegevens zoals gebruikersnaam, e-mail etc.",
                        "Vermeld gebruikersgegevens zoals gebruikersnaam, e-mail etc.", "42", Gender.man, "42",
                        "Vermeld gebruikersgegevens zoals gebruikersnaam, e-mail etc.")));
        verify(userRepository).checkUsernameExists((String) any());
    }

    @Test
    void testFindUserById() throws NotFoundException {
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
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, userService.findUserById(123L));
        verify(userRepository).findById((Long) any());
    }

    @Test
    void testFindUserById2() throws NotFoundException {
        when(userRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.findUserById(123L));
        verify(userRepository).findById((Long) any());
    }

    @Test
    void testUpdateUserInfo() throws NotFoundException {
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
        Optional<User> ofResult = Optional.of(user);

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
        when(userRepository.save((User) any())).thenReturn(user1);
        when(userRepository.findById((Long) any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");

        User user2 = new User();
        user2.setActive(true);
        user2.setContactNumber("42");
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        user2.setCreatedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        user2.setEmail("jane.doe@example.org");
        user2.setGender(Gender.man);
        user2.setId(123L);
        user2.setPassword("iloveyou");
        user2.setQualification("Qualification");
        user2.setRole(role2);
        user2.setRoleId(123L);
        user2.setToken("ABC123");
        user2.setUserDescription("User Description");
        user2.setUsername("janedoe");
        assertSame(user1, userService.updateUserInfo(user2));
        verify(userRepository).save((User) any());
        verify(userRepository).findById((Long) any());
    }


    @Test
    void testUpdateUserInfo2() throws NotFoundException {
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
        when(userRepository.save((User) any())).thenReturn(user);
        when(userRepository.findById((Long) any())).thenReturn(Optional.empty());

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
        assertThrows(NotFoundException.class, () -> userService.updateUserInfo(user1));
        verify(userRepository).findById((Long) any());
    }


    @Test
    void testRemoveUser() throws NotFoundException {
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
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(user, userService.removeUser(123L));
        verify(userRepository).findById((Long) any());
        verify(userRepository).delete((User) any());
    }

    @Test
    void testRemoveUser2() throws NotFoundException {
        doNothing().when(userRepository).delete((User) any());
        when(userRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.removeUser(123L));
        verify(userRepository).findById((Long) any());
    }

    @Test
    void testGetUserByUserName() {
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
        when(userRepository.findByUsername((String) any())).thenReturn(user);
        assertSame(user, userService.getUserByUserName("janedoe"));
        verify(userRepository).findByUsername((String) any());
    }

    @Test
    void testGetAllDoctors() {
        ArrayList<User> userList = new ArrayList<>();
        when(userRepository.getAllDoctors()).thenReturn(userList);
        List<User> actualAllDoctors = userService.getAllDoctors();
        assertSame(userList, actualAllDoctors);
        assertTrue(actualAllDoctors.isEmpty());
        verify(userRepository).getAllDoctors();
    }
}

