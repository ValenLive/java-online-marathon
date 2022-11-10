package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.ToDo;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class RoleServiceTests {

    RoleService roleService;

    @Autowired
    public RoleServiceTests(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    @Transactional
    public void createRoleTest() {
        assertThrows(javax.persistence.EntityNotFoundException.class, () -> roleService.create(null));
    }

    @Test
    @Transactional
    public void updateRoleTest() {
        Role expected = roleService.readById(1L);
        expected.setName("Name");
        Role actual = roleService.update(expected);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    public void deleteRoleTest() {
        roleService.delete(2L);
        assertThrows(javax.persistence.EntityNotFoundException.class, () -> roleService.readById(2L));
    }

    @Test
    public void getAllRolesTest(){
        List<Role> roleList = roleService.getAll();
        assertNotNull(roleList);
    }

    @Test
    public void readByIdRoleTest() {
        Role role = roleService.readById(2L);
        String actual = role.getName();
        String expected = "USER";
        assertEquals(expected, actual);
    }



}
