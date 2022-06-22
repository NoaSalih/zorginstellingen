package zorginstellingen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zorginstellingen.dtos.UserDto;
import zorginstellingen.encryption.AES;
import zorginstellingen.exception.DuplicateException;
import zorginstellingen.exception.NotFoundException;
import zorginstellingen.exception.PasswordException;
import zorginstellingen.exception.ResourceAlreadyExists;
import zorginstellingen.model.User;
import zorginstellingen.repositories.UserRepository;

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

    /**
     * Method will return all the users
     * this method will only be called by ADMIN role
     * @return
     */
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

    @Transactional(rollbackOn = {RoleNotFoundException.class, ResourceAlreadyExists.class,  SocketTimeoutException.class,PasswordException.class, DuplicateException.class, Exception.class, ParseException.class, NotFoundException.class})
    public User register(UserDto userDto) throws PasswordException, ResourceAlreadyExists, NotFoundException {
        if (userDto.getUsername() == null)
            throw new NotFoundException("Please mention user details like username, email or more");
        if (checkDuplicationUser(userDto.getUsername())) {
            throw new ResourceAlreadyExists("Username " + userDto.getUsername() + " Already Exists, Please choose unique");
        }
        if (userDto.getPassword() == null || (!checkStrongPassword(userDto.getPassword())))
            throw new PasswordException("Please enter some strong password LIKE digit + lowercase char + uppercase char + punctuation + symbol\n");
        String password = AES.encrypt(userDto.getPassword(), "HEALTH");
        User user = new User();
        user.setActive(true);
        user.setPassword(password);
        user.setUsername(userDto.getUsername());
        user.setRole(roleService.findRoleById(userDto.getRoleId()));
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

    /**
     * function will take user id and will return user details if there else will return exception
     * @param id
     * @return
     * @throws NotFoundException
     */
    public User findUserById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new NotFoundException("User not found for id = "+id));
    }

    /**
     * function will take user data in object along with user id actual and will be updated as that
     * @param user
     * @return
     * @throws NotFoundException
     */
    public User updateUserInfo(User user) throws NotFoundException {
        User findUser = findUserById(user.getId());
        return userRepository.save(user);
    }

    /**
     * function will remove the user with user id passed to it
     * @param id
     * @return
     * @throws NotFoundException
     */
    public User removeUser(Long id) throws NotFoundException {
        User findUser = findUserById(id);
        userRepository.delete(findUser);
        return findUser;
    }


    public User getUserByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
