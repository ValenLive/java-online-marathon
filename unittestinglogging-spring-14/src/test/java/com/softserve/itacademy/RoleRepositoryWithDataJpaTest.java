package com.softserve.itacademy;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class RoleRepositoryWithDataJpaTest {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveUserTest(){
        String searchedField = "TestRole";
        Role role = new Role();
        role.setName("TestRole");
        roleRepository.save(role);

        Role actual = roleRepository.findById(role.getId()).get();
        Assertions.assertEquals(searchedField,actual.getName());
    }
}
