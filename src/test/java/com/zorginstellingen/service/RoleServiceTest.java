package com.zorginstellingen.service;

import com.zorginstellingen.exception.DuplicateException;
import com.zorginstellingen.exception.EmptyException;
import com.zorginstellingen.exception.NotFoundException;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.repositories.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {RoleService.class})
@ExtendWith(SpringExtension.class)
class RoleServiceTest {
    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Test
    void testGetAllRoles() {
        ArrayList<Role> roleList = new ArrayList<>();
        when(roleRepository.findAll()).thenReturn(roleList);
        List<Role> actualAllRoles = roleService.getAllRoles();
        assertSame(roleList, actualAllRoles);
        assertTrue(actualAllRoles.isEmpty());
        verify(roleRepository).findAll();
    }

    @Test
    void testAddRole() throws DuplicateException, EmptyException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");
        when(roleRepository.findByRoleName((String) any())).thenReturn(role);
        when(roleRepository.save((Role) any())).thenReturn(role1);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");
        assertThrows(DuplicateException.class, () -> roleService.addRole(role2));
        verify(roleRepository).findByRoleName((String) any());
    }

    @Test
    void testAddRole2() throws DuplicateException, EmptyException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");
        when(roleRepository.findByRoleName((String) any())).thenReturn(role);
        when(roleRepository.save((Role) any())).thenReturn(role1);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName(null);
        assertThrows(EmptyException.class, () -> roleService.addRole(role2));
    }

    @Test
    void testUpdateRole() throws DuplicateException, EmptyException, NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");
        when(roleRepository.save((Role) any())).thenReturn(role1);
        when(roleRepository.findById((Long) any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName("Role Name");
        assertSame(role1, roleService.updateRole(role2));
        verify(roleRepository).save((Role) any());
        verify(roleRepository).findById((Long) any());
    }


    @Test
    void testUpdateRole3() throws DuplicateException, EmptyException, NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");
        when(roleRepository.save((Role) any())).thenReturn(role);
        when(roleRepository.findById((Long) any())).thenReturn(Optional.empty());

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");
        assertThrows(NotFoundException.class, () -> roleService.updateRole(role1));
        verify(roleRepository).findById((Long) any());
    }

    @Test
    void testUpdateRole4() throws DuplicateException, EmptyException, NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");
        when(roleRepository.save((Role) any())).thenReturn(role1);
        when(roleRepository.findById((Long) any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setId(null);
        role2.setRoleName("Role Name");
        assertThrows(EmptyException.class, () -> roleService.updateRole(role2));
    }

    @Test
    void testUpdateRole5() throws DuplicateException, EmptyException, NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);

        Role role1 = new Role();
        role1.setId(123L);
        role1.setRoleName("Role Name");
        when(roleRepository.save((Role) any())).thenReturn(role1);
        when(roleRepository.findById((Long) any())).thenReturn(ofResult);

        Role role2 = new Role();
        role2.setId(123L);
        role2.setRoleName(null);
        assertThrows(EmptyException.class, () -> roleService.updateRole(role2));
    }

    @Test
    void testDeleteById() throws DuplicateException, EmptyException, NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);
        doNothing().when(roleRepository).delete((Role) any());
        when(roleRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(role, roleService.deleteById(123L));
        verify(roleRepository).findById((Long) any());
        verify(roleRepository).delete((Role) any());
    }


    @Test
    void testDeleteById3() throws DuplicateException, EmptyException, NotFoundException {
        doNothing().when(roleRepository).delete((Role) any());
        when(roleRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> roleService.deleteById(123L));
        verify(roleRepository).findById((Long) any());
    }

    @Test
    void testDeleteById4() throws DuplicateException, EmptyException, NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);
        doNothing().when(roleRepository).delete((Role) any());
        when(roleRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(EmptyException.class, () -> roleService.deleteById(null));
    }


    @Test
    void testFindRoleById() throws NotFoundException {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(role, roleService.findRoleById(123L));
        verify(roleRepository).findById((Long) any());
    }

    @Test
    void testFindRoleById2() throws NotFoundException {
        when(roleRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> roleService.findRoleById(123L));
        verify(roleRepository).findById((Long) any());
    }

    @Test
    void testFindRoleByName() {
        Role role = new Role();
        role.setId(123L);
        role.setRoleName("Role Name");
        when(roleRepository.findByRoleName((String) any())).thenReturn(role);
        assertSame(role, roleService.findRoleByName("Role Name"));
        verify(roleRepository).findByRoleName((String) any());
    }
}

