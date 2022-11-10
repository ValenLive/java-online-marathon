package com.softserve.itacademy.service;

import com.softserve.itacademy.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class UserServiceTests {

    UserService userService;

    @Autowired
    public UserServiceTests(UserService userService) {
        this.userService = userService;
    }

    @Test
    public void getAllUsersTest() {
        int expectedSize = 3;
        List<User> users = userService.getAll();
        assertTrue(expectedSize <= users.size(), String.format("At least %d users should be in users table", expectedSize));
    }

    @Test
    @Transactional
    public void createUserTest() {
        User user = new User();
        user.setEmail("NotNew");
        user = userService.create(user);
        assertNotEquals(0, user.getId());
    }

    @Test
    @Transactional
    public void createInvalidUserTest(){
        assertThrows(javax.persistence.EntityNotFoundException.class, () -> userService.create(null));
    }


    @Test
    void getUserById() {
        User expect = new User();
        expect.setEmail("nick@mail.com");
        User actual = userService.readById(5L);
        Assertions.assertEquals(expect.getEmail(), actual.getEmail());
    }

    @Test
    @Transactional
    public void updateUserTest() {
        User expected = userService.readById(6L);
        User actual = userService.update(expected);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    public void getAllUserTest() {
        List<User> users = userService.getAll();
        assertNotNull(users);
    }

    @Test
    public void exceptionWhenDeleteUserWithTodoTest() {
        assertThrows(javax.persistence.EntityNotFoundException.class, () -> {
            userService.delete(-1L);
        });
    }

    @Test
    @Transactional
    public void deleteUserWithTodoTest() {
        int usersBefore = userService.getAll().size();
        userService.delete(6L);
        int usersAfter = userService.getAll().size();
        assertNotEquals(usersBefore, usersAfter);
    }

    @Test
    @Transactional
    void deleteUserById() {
        userService.delete(6L);
        Assertions.assertThrows(EntityNotFoundException.class, () -> userService.readById(6L));
    }

}