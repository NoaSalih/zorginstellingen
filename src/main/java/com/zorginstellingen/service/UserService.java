package com.zorginstellingen.service;

import com.zorginstellingen.dto.UserDto;
import com.zorginstellingen.encryption.AES;
import com.zorginstellingen.exception.*;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.model.User;
import com.zorginstellingen.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import javax.transaction.Transactional;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    private boolean checkDuplicationUser(String username) {
        return userRepository.checkUsernameExists(username) != null;
    }
    private Boolean checkStrongPassword(String password) throws PasswordException {
        if (password.length() < 5)
            throw new PasswordException("Password length must be > 5");
        else {
            Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
            Matcher matcher = pattern.matcher(password);
            return matcher.matches();
        }
    }

    @Transactional(rollbackOn = {RoleNotFoundException.class, ResourceAlreadyExists.class, MailSendException.class, SocketTimeoutException.class, MessagingException.class, PasswordException.class, DuplicateException.class, Exception.class, ParseException.class, NotFoundException.class})
    public User register(UserDto userDto) throws PasswordException, ResourceAlreadyExists, NotFoundException {
        if (userDto.getUsername() == null)
            throw new NotFoundException("Vermeld gebruikersgegevens zoals gebruikersnaam, e-mail etc.");
        if (checkDuplicationUser(userDto.getUsername())) {
            throw new ResourceAlreadyExists("Username " + userDto.getUsername() + " Gebruikersnaam bestaat al");
        }
        if (userDto.getPassword() == null || (!checkStrongPassword(userDto.getPassword())))
            throw new PasswordException("Voer een sterk wachtwoord in zoals cijfers + kleine letter + hoofdletter + symbool\n");
        String password = AES.encrypt(userDto.getPassword(), "HEALTH");
        User user = new User();
        user.setActive(true);
        user.setPassword(password);
        user.setUsername(userDto.getUsername());
        Role role;
        if(userDto.getUserRole()==null)
            role = roleService.findRoleByName("DOCTOR");
        else
            role = roleService.findRoleByName(userDto.getUserRole());

        user.setRole(role);
        user.setUserDescription(userDto.getUserDescription());
        user.setQualification(userDto.getQualification());
        user.setEmail(userDto.getEmail());
        user.setContactNumber(userDto.getContactNumber());
        user.setActive(userDto.getActive());
        user.setGender(userDto.getGender());
        user = userRepository.save(user);
        //verification token
        return user;
    }


    public User findUserById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found for id = "+id));
    }


    public User updateUserInfo(User user) throws NotFoundException {
        User findUser = findUserById(user.getId());
        return userRepository.save(user);
    }


    public User removeUser(Long id) throws NotFoundException {
        User findUser = findUserById(id);
        userRepository.delete(findUser);
        return findUser;
    }


    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllDoctors() {
        return userRepository.getAllDoctors();
    }
}
