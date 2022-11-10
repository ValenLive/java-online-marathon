package com.softserve.itacademy;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.service.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ToDoListApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ToDoService toDoService;

    @Autowired
    private StateService stateService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ToDoRepository toDoRepository;

    /**
     * UserService exceptions tests
     */
    @Test
    public void createNullUser() {
        assertThrows(NullEntityReferenceException.class, () -> userService.create(null));
    }

    @Test
    public void UpdateNotExistUser() {
        assertThrows(NullEntityReferenceException.class, () -> userService.update(null));
    }

    @Test
    public void ReadByIdNotExistUser() {
        assertThrows(EntityNotFoundException.class, () -> userService.readById(100L));
    }

    @Test
    public void deleteNullUser() {
        assertThrows(EntityNotFoundException.class, () -> userService.delete(100L));
    }

    /**
     * ToDoService exceptions tests
     */

    @Test
    public void createNullToDo() {
        assertThrows(NullEntityReferenceException.class, () -> toDoService.create(null));
    }

    @Test
    public void UpdateNotExistToDo() {
        assertThrows(NullEntityReferenceException.class, () -> toDoService.update(null));
    }

    @Test
    public void ReadByIdNotExistToDo() {
        assertThrows(EntityNotFoundException.class, () -> toDoService.readById(100L));
    }

    @Test
    public void deleteNullToDo() {
        assertThrows(EntityNotFoundException.class, () -> toDoService.delete(100L));
    }

    /**
     * TaskService exception tests
     */

    @Test
    public void createNullTask() {
        assertThrows(NullEntityReferenceException.class, () -> taskService.create(null));
    }

    @Test
    public void UpdateNotExistTask() {
        assertThrows(NullEntityReferenceException.class, () -> taskService.update(null));
    }

    @Test
    public void ReadByIdNotExistTask() {
        assertThrows(EntityNotFoundException.class, () -> taskService.readById(100L));
    }

    @Test
    public void deleteNullTask() {
        assertThrows(EntityNotFoundException.class, () -> taskService.delete(100L));
    }

    /**
     * RoleService exception tests
     */

    @Test
    public void createNullRole() {
        assertThrows(NullEntityReferenceException.class, () -> roleService.create(null));
    }

    @Test
    public void UpdateNotExistRole() {
        assertThrows(NullEntityReferenceException.class, () -> roleService.update(null));
    }

    @Test
    public void ReadByIdNotExistRole() {
        assertThrows(EntityNotFoundException.class, () -> roleService.readById(100L));
    }

    @Test
    public void deleteNullRole() {
        assertThrows(EntityNotFoundException.class, () -> roleService.delete(100L));
    }

    /**
     * RoleService exception tests
     */

    @Test
    public void createNullState() {
        assertThrows(NullEntityReferenceException.class, () -> stateService.create(null));
    }

    @Test
    public void UpdateNotExistState() {
        assertThrows(NullEntityReferenceException.class, () -> stateService.update(null));
    }

    @Test
    public void ReadByIdNotExistState() {
        assertThrows(EntityNotFoundException.class, () -> stateService.readById(100L));
    }

    @Test
    public void deleteNullState() {
        assertThrows(EntityNotFoundException.class, () -> stateService.delete(100L));
    }

}
