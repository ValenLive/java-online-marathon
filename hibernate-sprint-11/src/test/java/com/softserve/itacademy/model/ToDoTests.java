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
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = ToDoTests.class)
public class ToDoTests {

    private static final String invalidTitle = new String(new char[256]).replace('\0', '*');
    private static ToDo validToDo;
    private static User validUser;

    @BeforeAll
    static void init() {
        validToDo = new ToDo();
        validUser = new User();

        validToDo.setTitle("ValidTitle");
        validToDo.setOwner(validUser);
    }


    @Test
    void createValidToDo() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(validToDo);
        assertEquals(0, violations.size());
    }

    @Test
    void testToDoToString() {
        ToDo toDo = new ToDo();
        toDo.setOwner(new User());
        System.out.println(toDo);
        assertEquals("ToDo null created at " + toDo.getCreatedAt() + " owner null null", toDo.toString());
    }

    @Test
    void testToDoGetters() {
        assertEquals(0L, validToDo.getId());
        assertEquals("ValidTitle", validToDo.getTitle());
        assertNull(validToDo.getCollaborators());
        assertNull(validToDo.getTasks());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTitleTodo")
    void constrainViolationInvalid(String input, String errorValue) {
        ToDo toDo = new ToDo();
        toDo.setTitle(input);
        toDo.setOwner(validUser);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<ToDo>> violations = validator.validate(toDo);

        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidTitleTodo() {
        return Stream.of(
                Arguments.of(" ", " "),
                Arguments.of(null, null),
                Arguments.of(invalidTitle, invalidTitle)
        );
    }
}
