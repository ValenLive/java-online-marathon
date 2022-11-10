package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    

    @Override
    public Role create(Role role) {
        if (role != null){
            return roleRepository.save(role);
        }
       throw new EntityNotFoundException("Role cannot be null");
    }

    @Override
    public Role readById(long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Role with %s id not found",id)));
    }

    @Override
    public Role update(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void delete(long id) {
        roleRepository.delete(readById(id));
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
