package com.softserve.itacademy.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;

@SpringBootTest(classes = UserTests.class)
public class UserTests {

    private static Role mentorRole;
    private static Role traineeRole;
    private static User validUser;

    @BeforeAll
    static void init(){
        mentorRole = new Role();
        mentorRole.setName("MENTOR");
        traineeRole = new Role();
        traineeRole.setName("TRAINEE");
        validUser  = new User();
        validUser.setEmail("valid@cv.edu.ua");
        validUser.setFirstName("Valid-Name");
        validUser.setLastName("Valid-Name");
        validUser.setPassword("qwQW12!@");
        validUser.setRole(traineeRole);
    }

    @Test
    void userWithValidEmail() {
        User user = new User();
        user.setEmail("rty5@i.ua");
        user.setFirstName("Valid-Name");
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);


        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        assertEquals(0, violations.size());
    }

    @Test
    void createValidUser() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @Test
    void createValidUserToString() {
    	assertEquals("User[id=0, firsName=Valid-Name, lastName=Valid-Name, Role {id = 0, name = 'TRAINEE'} ]", validUser.toString());
    }
    
    @ParameterizedTest
    @MethodSource("provideInvalidEmailUser")
    void constraintViolationInvalid(String input, String errorValue) {
        User user = new User();
        user.setEmail(input);
        user.setFirstName("Valid-Name");
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        System.out.println(violations);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidEmailUser(){
        return Stream.of(
                Arguments.of("invalidEmail", "invalidEmail"),
                Arguments.of("email@", "email@"),
                Arguments.of("", ""),
                Arguments.of("invalid", "invalid")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidFirstNameUser")
    void constraintViolationInvalidFirstName(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(input);
        user.setLastName("Valid-Name");
        user.setPassword("qwQW12!@");
        user.setRole(traineeRole);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidFirstNameUser(){
        return Stream.of(
                Arguments.of("invalid", "invalid"),
                Arguments.of("Invalid-", "Invalid-"),
                Arguments.of("Invalid-invalid", "Invalid-invalid")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidLastNameUser")
    void constraintViolationInvalidLastName(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(input);
        user.setPassword(validUser.getPassword());
        user.setRole(validUser.getRole());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidLastNameUser(){
        return Stream.of(
                Arguments.of("invalid", "invalid"),
                Arguments.of("Invalid-", "Invalid-"),
                Arguments.of("Invalid-invalid", "Invalid-invalid"),
                Arguments.of(null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidPasswordUser")
    void constraintViolationInvalidPassword(String input, String errorValue) {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword(input);
        user.setRole(validUser.getRole());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        assertEquals(errorValue, violations.iterator().next().getInvalidValue());
    }

    private static Stream<Arguments> provideInvalidPasswordUser(){
        return Stream.of(
                Arguments.of("12345", "12345"),
                Arguments.of("1234567890qwertyuiopas", "1234567890qwertyuiopas"),
                Arguments.of("123Adfqw-", "123Adfqw-")
        );
    }

    @Test
    void constraintViolationInvalidRole() {
        User user = new User();
        user.setEmail(validUser.getEmail());
        user.setFirstName(validUser.getFirstName());
        user.setLastName(validUser.getLastName());
        user.setPassword(validUser.getPassword());
        user.setRole(null);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
    }

    @Test
    void getIdUser(){
        assertEquals(validUser.getId(),0L);
    }

    @Test
    void getFirstnameUser() {
        assertEquals(validUser.getFirstName(), "Valid-Name");
    }

    @Test
    void getLastnameUser() {
        assertEquals(validUser.getLastName(), "Valid-Name");
    }

    @Test
    void getEmailUser() {
        assertEquals(validUser.getEmail(), "valid@cv.edu.ua");
    }

    @Test
    void getPasswordUser() {
        assertEquals(validUser.getPassword(), "qwQW12!@");
    }

    @Test
    void getRoleUser() {
        assertEquals(validUser.getRole().getName(), "TRAINEE");
    }

    @Test
    void toStringUser(){
        assertEquals(validUser.toString(),"User[id=0, firsName=Valid-Name, " +
                "lastName=Valid-Name, Role {id = 0, name = 'TRAINEE'} ]");
    }
    @Test
    void getMyTodosUser() {
        assertNull(validUser.getMyTodos());
    }

    @Test
    void getCollaboratedTodosUser() {
        assertNull(validUser.getCollaboratedTodos());
    }
    
    @Test
    void testEqualssUser() {
    	User user = new User();
        user.setFirstName("Valid-Name");
    	assertTrue(validUser.equals(user));
    }
}
