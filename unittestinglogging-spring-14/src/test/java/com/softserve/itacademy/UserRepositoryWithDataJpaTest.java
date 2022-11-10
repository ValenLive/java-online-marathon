package com.softserve.itacademy;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
public class UserRepositoryWithDataJpaTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveUserTest(){
        String searchedField = "TestEmail@gmail.com";
        User model = new User();
        model.setRole(roleRepository.findById(1L).get());
        model.setFirstName("Firstname");
        model.setLastName("Lastname");
        model.setEmail("TestEmail@gmail.com");
        model.setPassword("pass");
        userRepository.save(model);

        User actual = userRepository.getUserByEmail(searchedField);
        Assertions.assertEquals(searchedField,actual.getEmail());
    }

}
