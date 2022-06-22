package zorginstellingen.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zorginstellingen.dtos.UserDto;
import zorginstellingen.enums.Gender;
import zorginstellingen.exception.DuplicateException;
import zorginstellingen.exception.EmptyException;
import zorginstellingen.exception.NotFoundException;
import zorginstellingen.model.Role;

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
        try{
            if (roleService.getAllRoles().isEmpty() && userService.getAllUsers().isEmpty()){
                log.info("\n____________Basic Initiating Accounts_______________\n");
                Role adminRole = new Role("ADMIN");
                Role physiothropistRole = new Role("PHYSIOTHERAPIST");
                Role dentistRole = new Role("DENTIST");
                Role patientRole = new Role("PATIENT");
                //saving accounts
                physiothropistRole = roleService.addRole(physiothropistRole);
                dentistRole = roleService.addRole(dentistRole);
                patientRole = roleService.addRole(patientRole);
                log.info("---------> All roles created");

                Thread.sleep(500);

                UserDto user = new UserDto();
                user.setUsername("admin");
                user.setPassword("D238cfvv@123");
                user.setEmail("admin@gmail.com");
                user.setContactNumber("+XX-XXXXXXXX");
                user.setQualification("IT ADMIN");
                user.setGender(Gender.MALE);
                user.setRoleId(roleService.addRole(adminRole).getId());
                user.setActive(true);
                user.setQualification("MBBS");
                user.setUserDescription("Admin role for complete Healthcare Management System");
                log.info("---------> admin user created -> "+userService.register(user));
                log.info("\n____________Accounts Initiated_______________\n");
            }else{
                log.info("\n____________Accounts already Initiated_______________\n");
            }
        }catch (Exception e){
            e.printStackTrace();
            log.info("Exception: "+e.getMessage());
        }
    }
}
