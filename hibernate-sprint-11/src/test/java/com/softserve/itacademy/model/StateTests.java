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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = StateTests.class)
public class StateTests {

    private static final String invalidName = new String(new char[21]).replace('\0', '*');
    private static State validState;
    private static List<Task> validTaskList;

    @BeforeAll
    static void init() {
        validState = new State();
        validTaskList = new ArrayList<>();

        validState.setName("Name");
        validState.setTasks(validTaskList);
    }

    @Test
    void createValidState() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(validState);
        assertEquals(0, violations.size());
    }

    @Test
    void testToString() {
        State state = new State();
        String expected = "State{id=0, name='null'}";
        assertEquals("State{id=0, name='null'}", state.toString());
    }

    @Test
    void testStateGetters(){
        State state = new State();
        state.setName("Name");
        state.setTasks(new ArrayList<>());

        assertEquals(0L, state.getId());
        assertEquals("Name", state.getName());
        assertEquals(new ArrayList<>(), state.getTasks());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNameState")
    void constraintViolationInvalid(String input, String errorValue) {
        State state = new State();
        state.setName(input);
        state.setTasks(validTaskList);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<State>> violations = validator.validate(state);

        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidNameState() {
        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(invalidName, invalidName),
                Arguments.of("   ", "   ")
        );
    }
}
