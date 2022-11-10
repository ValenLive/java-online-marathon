package com.softserve.itacademy.service;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.Task;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class TaskServiceTests {

    TaskService taskService;

    @Autowired
    public TaskServiceTests(TaskService taskService) {
        this.taskService = taskService;
    }

    @Test
    @Transactional
    public void createTaskTest() {
        assertThrows(javax.persistence.EntityNotFoundException.class, () -> taskService.create(null));
    }

    @Test
    @Transactional
    public void updateRoleTest() {
        Task expected = taskService.readById(6L);
        expected.setName("Name");
        Task actual = taskService.update(expected);
        assertEquals(expected, actual);
    }

    @Test
    @Transactional
    public void deleteRoleTest() {
        taskService.delete(6L);
        assertThrows(javax.persistence.EntityNotFoundException.class, () -> taskService.readById(6L));
    }

    @Test
    public void readByIdRoleTest() {
        Task task = taskService.readById(6L);
        String actual = task.getName();
        String expected = "Task #2";
        assertEquals(expected, actual);
    }

    @Test
    public void getAllTasksTest(){
        int expected = 3;
        int actual = taskService.getAll().size();
        assertEquals(expected, actual);
    }

    @Test
    public void getByToDoId(){
        assertThrows(javax.persistence.EntityNotFoundException.class, () -> taskService.getByTodoId(10L));
    }
}
