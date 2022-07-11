package com.zorginstellingen.service;

import com.zorginstellingen.exception.DuplicateException;
import com.zorginstellingen.exception.EmptyException;
import com.zorginstellingen.exception.NotFoundException;
import com.zorginstellingen.model.Role;
import com.zorginstellingen.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }


    public Role addRole(Role role) throws EmptyException, DuplicateException {
        if (role.getRoleName()==null)
            throw new EmptyException("Gelieve een functienaam invoeren");
        Role findRole = roleRepository.findByRoleName(role.getRoleName());
        if (findRole!=null)
            throw new DuplicateException("functienaam bestaat al");
        return roleRepository.save(role);
    }


    public Role updateRole(Role role) throws EmptyException, DuplicateException, NotFoundException {
        if (role.getId()==null)
            throw new EmptyException("Graag alleen een bestaande functienaam invoeren");
        if (role.getRoleName()==null)
            throw new EmptyException("Graag een functienaam invoeren");
        Role findRole = findRoleById(role.getId());
        findRole.setRoleName(role.getRoleName());
        return roleRepository.save(role);
    }


    public Role deleteById(Long id) throws EmptyException, DuplicateException, NotFoundException {
        if (id ==null)
            throw new EmptyException("Graag een bestaande functienaam of ID invoeren");
        Role findRole = findRoleById(id);
        roleRepository.delete(findRole);
        return findRole;
    }


    public Role findRoleById(Long id) throws NotFoundException {
        return roleRepository.findById(id).orElseThrow(()->  new NotFoundException("Record not found"));
    }

    public Role findRoleByName(String roleName) {
        return  roleRepository.findByRoleName(roleName);
    }
}
