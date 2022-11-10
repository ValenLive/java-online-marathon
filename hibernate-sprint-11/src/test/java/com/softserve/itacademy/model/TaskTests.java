package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = TaskTests.class)
public class TaskTests {
    private static final String invalidName = new String(new char[201]).replace('\0', '*');
    private static Task validTask;
    private static ToDo validToDo;
    private static State validState;

    @BeforeAll
    static void init() {
        validTask = new Task();
        validTask.setName("Task");
        validTask.setPriority(Priority.LOW);

        validToDo = new ToDo();
        validToDo.setTitle("To Do list!");
        validToDo.setOwner(new User());
        validTask.setToDo(validToDo);

        validState = new State();
        validState.setName("Done");
        validTask.setState(validState);
    }

    @Test
    void createValidTask() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(validTask);
        assertEquals(0, violations.size());
    }

    @Test
    void testToString() {
        Task task = new Task();
        String expected = "Task{id=0, name='null', priority=null, state=null, toDo=null}";
        assertEquals(expected, task.toString());
    }

    @Test
    void testTaskGetters() {
        assertEquals(0L, validTask.getId());
        assertEquals("Task", validTask.getName());
        assertEquals(Priority.LOW, validTask.getPriority());
        assertEquals(validState, validTask.getState());
        assertEquals(validToDo, validTask.getToDo());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNameTask")
    void constraintViolationInvalid(String input, String errorValue) {
        Task task = new Task();
        task.setName(input);
        task.setPriority(Priority.LOW);
        task.setToDo(validToDo);
        task.setState(validState);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Task>> violations = validator.validate(task);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidNameTask() {
        return Stream.of(
                Arguments.of("   ", "   "),
                Arguments.of("uu", "uu"),
                Arguments.of(null, null),
                Arguments.of(invalidName, invalidName)
        );
    }
}
