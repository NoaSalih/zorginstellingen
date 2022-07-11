package com.zorginstellingen.service;

import com.zorginstellingen.dto.UserDto;
import com.zorginstellingen.enums.Gender;
import com.zorginstellingen.exception.DuplicateException;
import com.zorginstellingen.exception.EmptyException;
import com.zorginstellingen.exception.NotFoundException;
import com.zorginstellingen.model.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
@Slf4j
public class InitService {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;


    @PostConstruct
    @Transactional(rollbackOn = {EmptyException.class, DuplicateException.class, NotFoundException.class, InterruptedException.class})
    public void initBasicAccounts() {
        try {
            if (roleService.getAllRoles().isEmpty() && userService.getAllUsers().isEmpty()) {
                log.info("\n____________Basic Initiating Accounts_______________\n");
                Role adminRole = new Role("ADMIN");
                Role patientRole = new Role("PATIENT");
                Role dentistRole = new Role("DOCTOR");
                //account opslaan
                adminRole = roleService.addRole(adminRole);
                patientRole = roleService.addRole(patientRole);
                dentistRole = roleService.addRole(dentistRole);
                log.info("---------> All roles created");

                Thread.sleep(500);

                UserDto user = new UserDto();
                user.setUsername("admin");
                user.setPassword("D238cfvv@123");
                user.setEmail("admin@gmail.com");
                user.setUserRole("ADMIN");
                user.setContactNumber("0612345678");
                user.setQualification("ZORG ADMIN");
                user.setGender(Gender.man);
                user.setActive(true);
                user.setQualification("Techniek");
                user.setUserDescription("Admin rol om alles te controleren");
                log.info("---------> admin user aangemaakt -> " + userService.register(user));
                log.info("\n____________Account Initiated_______________\n");
            } else {
                log.info("\n____________Accounts already Initiated_______________\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Exception: " + e.getMessage());
        }
    }
}
