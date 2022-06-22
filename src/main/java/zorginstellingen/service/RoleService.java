package zorginstellingen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zorginstellingen.exception.DuplicateException;
import zorginstellingen.exception.EmptyException;
import zorginstellingen.exception.NotFoundException;
import zorginstellingen.model.Role;
import zorginstellingen.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    /**
     * Method will return all the roles in the system
     * accessible to all users
     * @return
     */
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    /**
     * Method will add a new role in the system
     * Only ADMIN can add the role
     * @return
     */
    public Role addRole(Role role) throws EmptyException, DuplicateException {
        if (role.getRoleName()==null)
            throw new EmptyException("Please mention role name");
        Role findRole = roleRepository.findByRoleName(role.getRoleName());
        if (findRole!=null)
            throw new DuplicateException("Role already exists");
        return roleRepository.save(role);
    }

    /**
     * Method will update the role
     * accessible to only admin
     * @return
     */
    public Role updateRole(Role role) throws EmptyException, DuplicateException, NotFoundException {
        if (role.getId()==null)
            throw new EmptyException("Please mention id of role for update");
        if (role.getRoleName()==null)
            throw new EmptyException("Please mention role name");
        Role findRole = findRoleById(role.getId());
        findRole.setRoleName(role.getRoleName());
        return roleRepository.save(role);
    }

    /**
     * Method will delete the role
     * accessible to only admin
     * @return
     */
    public Role deleteById(Long id) throws EmptyException, DuplicateException, NotFoundException {
        if (id ==null)
            throw new EmptyException("Please mention id of role for update");
        Role findRole = findRoleById(id);
        roleRepository.delete(findRole);
        return findRole;
    }

    /**
     * find role by user id
     * @param id
     * @return
     * @throws NotFoundException
     */
    public Role findRoleById(Long id) throws NotFoundException {
        return roleRepository.findById(id).orElseThrow(()->  new NotFoundException("Record not found"));
    }

    public Role findRoleByName(String patient) {
        return  roleRepository.findByRoleName(patient);
    }
}
