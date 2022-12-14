package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role create(Role role) {
        try {
            return roleRepository.save(role);
        }catch (InvalidDataAccessApiUsageException exception){
            throw new NullEntityReferenceException("Role cannot be null!");
        }
    }

    @Override
    public Role readById(long id) {
        Optional<Role> optional = roleRepository.findById(id);
            return optional.orElseThrow(() -> new EntityNotFoundException("Can't find role with id " + id));
    }

    @Override
    public Role update(Role role) {
        if(role != null) {
            Role oldRole = readById(role.getId());
            return roleRepository.save(role);
        }else{
            throw new NullEntityReferenceException("Role cannot be null!");
        }
    }

    @Override
    public void delete(long id) {
        Role role = readById(id);
            roleRepository.delete(role);
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}
