package com.softserve.itacademy;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(JUnitPlatform.class)
public class ToDoServiceTest {
    private static UserService userService;
    private static ToDoService toDoService;

    @BeforeAll
    public static void setupBeforeClass() {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        annotationConfigContext.close();
    }


    @AfterEach
    public void afterEach() {
        userService.getAll().forEach(user -> user.getMyTodos().clear());
        userService.getAll().clear();
    }


    @Test
    public void checkAddToDo() {
        assertNull(toDoService.addTodo(null, null));

        User user = new User("Geralt", "Wenberg", "mail@email.com",
                "newPassword123");

        assertNull(toDoService.addTodo(null, user));

        userService.addUser(user);

        ToDo toDo = new ToDo("Title1", user);

        assertNull(toDoService.addTodo(toDo, null));

        assertEquals(toDo, toDoService.addTodo(toDo, user));
        assertNull(toDoService.addTodo(toDo, user));
    }

    @Test
    public void checkUpdateToDo() {
        User user = new User("Oksana", "Pupkina", "mail@email.com",
                "secretPass");

        userService.addUser(user);

        ToDo toDo = new ToDo("Title1", user);
        toDoService.addTodo(toDo, user);

        ToDo updatedToDo = new ToDo(toDo.getTitle(), user);

        assertNull(toDoService.addTodo(updatedToDo, user));

        assertNull(toDoService.updateTodo(null));
        assertEquals(toDo, toDoService.updateTodo(updatedToDo));
    }


    @Test
    public void checkDeleteToDo() {
        User user = new User("Leonid", "Pupkin", "abobus@email.com",
                "qwerty123");

        userService.addUser(user);

        ToDo toDo = new ToDo("Title1", user);
        ToDo toDo1 = new ToDo("Title2", user);

        toDoService.addTodo(toDo, user);
        toDoService.addTodo(toDo1, user);

        toDoService.deleteTodo(null);
        assertEquals(2, toDoService.getAll().size());

        toDoService.deleteTodo(toDo);
        assertEquals(1, toDoService.getAll().size());

        assertEquals(toDo1, toDoService.getByUserTitle(user, "Title2"));
        assertNull(toDoService.getByUserTitle(user, "Title1"));
    }


    @Test
    public void checkGetAll() {
        User user = new User("Valentyn", "Pupkin", "mail@email.com",
                "qwerty123");

        userService.addUser(user);

        List<ToDo> toDoList = getToDoList(user, 10);
        toDoList.forEach(todo -> assertEquals(todo, toDoService.addTodo(todo, user)));

        assertEquals(toDoList.size(), toDoService.getAll().size());
        assertEquals(toDoList, toDoService.getAll());
        assertEquals(toDoList, toDoService.getByUser(user));
    }


    @Test
    public void checkGetAllMultipleUsers() {
        List<ToDo> toDoList = new ArrayList<>();

        for (int userIndex = 0; userIndex < 10; ++userIndex) {
            User user = new User("Cassidy" + userIndex, "Pupkin" + userIndex,
                    "gmail" + userIndex + "@mail.com", "21324321");

            assertEquals(user, userService.addUser(user));

            for (int toDoIndex = 0; toDoIndex < 10; ++toDoIndex) {

                ToDo toDo = new ToDo("Title" + toDoIndex, user);
                assertEquals(toDo, toDoService.addTodo(toDo, user));

                toDoList.add(toDo);
            }
        }

        assertEquals(toDoList, toDoService.getAll());
    }


    @Test
    public void checkGetAllByUser() {
        User user = new User("Amelia", "Pupkin", "colton@email.com",
                "123456799");

        userService.addUser(user);
        assertNull(toDoService.getByUser(null));
        assertEquals(new ArrayList<>(), toDoService.getByUser(user));

        List<ToDo> toDoList = getToDoList(user, 10);
        toDoList.forEach(todo -> assertEquals(todo, toDoService.addTodo(todo, user)));

        assertEquals(toDoList, toDoService.getByUser(user));
    }


    private static List<ToDo> getToDoList(User user, int limit) {
        return Stream.iterate(0, i -> ++i)
                .limit(limit)
                .map(i -> new ToDo("Title" + i, user))
                .collect(Collectors.toList());
    }

}