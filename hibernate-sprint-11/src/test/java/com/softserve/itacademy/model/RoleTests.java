package com.softserve.itacademy.model;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RoleTests.class)
class RoleTests {

    @Test
    void constraintViolationOnEmptyRoleName() {
        Role emptyRole = new Role();
        emptyRole.setName("");

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(emptyRole);
        assertEquals(1, violations.size());
    }


    @Test
    void createValidRole() {
    	Role validRole = new Role();
        validRole.setName("ValidRole");
        validRole.setUsers(new ArrayList<>());
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Role>> violations = validator.validate(validRole);
        assertEquals(0, violations.size());
    }

    @Test
    void testGetUsers(){
        Role role = new Role();
        role.setUsers(new ArrayList<>());
        List<User> expected = new ArrayList<>();
        assertEquals(expected, role.getUsers());
    }

    @Test
    void testGetName(){
        Role role = new Role();
        role.setName("Name");
        assertEquals("Name", role.getName());
    }

    @Test
    void testToString(){
        Role role = new Role();
        String expected = "Role {id = 0, name = 'null'} ";
        assertEquals(expected, role.toString());
    }


}
