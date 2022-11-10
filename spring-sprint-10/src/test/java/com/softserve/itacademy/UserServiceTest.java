package com.softserve.itacademy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RunWith(JUnitPlatform.class)
public class UserServiceTest {
    private static UserService userService;

    @BeforeAll
    public static void setupBeforeClass() {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        annotationConfigContext.close();
    }

    @AfterEach
    public void clearBeforeNext() {
        userService.getAll().forEach(user -> user.getMyTodos().clear());
        userService.getAll().clear();
    }

    @Test
    public void checkAddUser() {
        int sizeUsersListBegin = userService.getAll().size();
        User user = new User("Alice", "Wilson", "awilson@gmail.com","4567");
        User actual = userService.addUser(user);
        int sizeUsersListEnd = userService.getAll().size();
        Assertions.assertEquals(user, actual, "user added");
        Assertions.assertEquals(sizeUsersListEnd, sizeUsersListBegin+1);
    }

    @Test
    public void checkAddNotExistUser(){
        User user = null;
        Assertions.assertThrows(IllegalArgumentException.class,()-> userService.addUser(user));
    }

    @Test
    public void checkAddUserWithExistEmail(){
        User user = new User("John","Jones","jonesj@gmail.com","1234");
        userService.addUser(user);
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> userService.addUser(user));
    }

    @Test
    public void checkUpdateUserByEmail(){
        User user = new User("Noah","Taylor", "rggf007.gmail.com","idisww44");
        userService.addUser(user);
        user = new User ("Amelia", "Taylor","rggf007.gmail.com", "jkal8597");
        User expected =  new User ("Amelia", "Taylor","rggf007.gmail.com", "jkal8597");
        User actual = userService.updateUser(user);
        Assertions.assertEquals(expected,actual);
    }

    @Test
    public void checkUpdateUserByNotExistEmail(){
        User user = new User("FirstName","LastName","flflf@gmail.com","56468548");
        Assertions.assertThrows(IllegalArgumentException.class,() -> userService.updateUser(user));
    }

    @Test
    public void checkUpdateNotExistUser(){
        Assertions.assertThrows(IllegalArgumentException.class,() -> userService.updateUser(null));
    }

    @Test
    public void checkDeleteUser(){
        User user = new User("Amelia", "Johnson","rg007.gmail.com", "jkal8597");
        userService.addUser(user);
        userService.addUser(new User("FirstName","LastName","fl@gmail.com","56468548"));
        userService.addUser(new User("Noah","Taylor", "taylor007.gmail.com","idisww44"));
        int sizeListBegin = userService.getAll().size();
        userService.deleteUser(user);
        int sizeListEnd = userService.getAll().size();
        Assertions.assertTrue(!userService.getAll().contains(user));
        Assertions.assertEquals(sizeListEnd,sizeListBegin-1);
    }

    @Test
    public void checkDeleteNotExistUser(){
        User user = new User("John","Taylor", "jojotaylor.gmail.com","fcuyiuhu");
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(user));
    }

    @Test
    public void checkGetAll(){
        User user = new User("John","Taylor", "jojotaylor.gmail.com","fcuyiuhu");
        User user1 = new User("Amelia", "Johnson","rg007.gmail.com", "jkal8597");
        userService.addUser(user);
        userService.addUser(user1);
        List<User> expected = new ArrayList<>();
        expected.add(user);
        expected.add(user1);

        List<User> actual = userService.getAll();

        Assertions.assertEquals(expected, actual);
    }
}
