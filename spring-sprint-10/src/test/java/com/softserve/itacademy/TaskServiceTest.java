package com.softserve.itacademy;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.softserve.itacademy.model.Priority;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;

@RunWith(JUnitPlatform.class)
public class TaskServiceTest {
    private static UserService userService;
    private static TaskService taskService;
    private static ToDoService toDoService;

    @BeforeAll
    public static void setupBeforeClass() throws Exception {
        AnnotationConfigApplicationContext annotationConfigContext = new AnnotationConfigApplicationContext(Config.class);
        userService = annotationConfigContext.getBean(UserService.class);
        taskService = annotationConfigContext.getBean(TaskService.class);
        toDoService = annotationConfigContext.getBean(ToDoService.class);
        annotationConfigContext.close();
    }
    
    @AfterEach
    public void afterEach() {
        userService.getAll().clear();
    }
    
    @Test
    public void checkAddTask() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
       	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(new Task("test", Priority.HIGH), todo);
    	String expected = Arrays.asList(new Task("test", Priority.HIGH)).toString();
        String actual = taskService.getAll().toString();
        Assertions.assertEquals(expected, actual, "check addTask (test, HIGH)");
    }

    @Test
    public void checkAddSameTask() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	Task task = new Task("test", Priority.HIGH);  	
    	taskService.addTask(task, todo);
    	Assertions.assertThrows(IllegalArgumentException.class,
                () -> taskService.addTask(task, todo));
    }
    
    @Test
    public void checkAddNullNameTask() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	Task task = new Task(null, Priority.HIGH);  	
    	Assertions.assertThrows(IllegalArgumentException.class,
    			() -> taskService.addTask(task, todo));
    }
    
    @Test
    public void checkAddEmptyNameTask() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	Task task = new Task("", Priority.HIGH);  	
    	Assertions.assertThrows(IllegalArgumentException.class,
    			() -> taskService.addTask(task, todo));
    }
    
    @Test
    public void checkUpdateTask() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
       	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(new Task("test", Priority.LOW), todo);
    	taskService.updateTask(new Task("test", Priority.HIGH)).toString();
    	String expected = Arrays.asList(new Task("test", Priority.HIGH)).toString();
        String actual = taskService.getAll().toString();
        Assertions.assertEquals(expected, actual, "check updateTask from LOW to HIGH)");
    }
    
    @Test
    public void checkUpdateTaskNullPriority() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(new Task("test", Priority.LOW), todo);
    	Assertions.assertThrows(IllegalArgumentException.class,
    			() -> taskService.updateTask(new Task("test", null)).toString());
    }
    
    @Test
    public void checkUpdateTaskNullName() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(new Task("test", Priority.LOW), todo);
    	Assertions.assertThrows(IllegalArgumentException.class,
    			() -> taskService.updateTask(new Task(null, Priority.HIGH)).toString());
    }
    
    @Test
    public void checkUpdateTaskIncorrectName() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(new Task("test", Priority.LOW), todo);
    	Assertions.assertThrows(IllegalArgumentException.class,
    			() -> taskService.updateTask(new Task("gg", Priority.HIGH)).toString());
    }
    
    @Test
    public void checkDeleteTask() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(new Task("test", Priority.LOW), todo);
    	taskService.deleteTask(new Task ("test", null));
    	Assertions.assertEquals(0, taskService.getAll().size());
    }
    
    @Test
    public void checkDeleteTaskIncorrectName() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(new Task("test", Priority.LOW), todo);
    	Assertions.assertThrows(IllegalArgumentException.class,
    			() -> taskService.deleteTask(new Task("gg", Priority.HIGH)));
    }
    
    @Test
    public void checkGetAll() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	Task task = new Task("test", Priority.HIGH);
    	Task task1 = new Task("test1", Priority.MEDIUM);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(task, todo);
    	taskService.addTask(task1, todo);
    	String expected = Arrays.asList(new Task("test", Priority.HIGH), new Task("test1", Priority.MEDIUM)).toString();
        String actual = taskService.getAll().toString();
    	Assertions.assertEquals(expected, actual, "check addTask (test, HIGH)");
    }
    
    @Test
    public void checkGetByToDo() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	Task task = new Task("test", Priority.HIGH);
    	Task task1 = new Task("test1", Priority.MEDIUM);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(task, todo);
    	taskService.addTask(task1, todo);
    	String expected = Arrays.asList(new Task("test", Priority.HIGH), new Task("test1", Priority.MEDIUM)).toString();
    	String actual = taskService.getByToDo(todo).toString();
    	Assertions.assertEquals(expected, actual);
    }
    
    @Test
    public void checkGetByToDoName() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	Task task = new Task("test", Priority.HIGH);
    	Task task1 = new Task("test1", Priority.MEDIUM);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(task, todo);
    	taskService.addTask(task1, todo);
    	Task actual = taskService.getByToDoName(todo, "Test1");
    	Assertions.assertEquals(task1, actual);
    }
    
    @Test
    public void checkGetByUserName() {
    	User user = new User("Alex", "Smith", "ss@hh.com", "1111");
    	ToDo todo = new ToDo("todo", user);
    	Task task = new Task("test", Priority.HIGH);
    	Task task1 = new Task("test1", Priority.MEDIUM);
    	userService.addUser(user);
    	toDoService.addTodo(todo, user);
    	taskService.addTask(task, todo);
    	taskService.addTask(task1, todo);
    	Task actual = taskService.getByUserName(user, "Test1");
    	Assertions.assertEquals(task1, actual);
    }
    

    
    
}
