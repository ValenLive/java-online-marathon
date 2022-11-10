package com.softserve.itacademy;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;


@DataJpaTest
public class ToDoRepositoryTest {

    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void repositoryDbConnection() {
        toDoRepository.deleteById(7L);
        int actual = toDoRepository.findAll().size();

        Assertions.assertEquals(6, actual);
    }

    @Test
    public void getToDosByUserIdTest() {
        List<ToDo> toDoList = toDoRepository.getByUserId(5L);
        String actual = toDoList.get(0).getTitle();

        Assertions.assertEquals("Mike's To-Do #1", actual);
    }
}
