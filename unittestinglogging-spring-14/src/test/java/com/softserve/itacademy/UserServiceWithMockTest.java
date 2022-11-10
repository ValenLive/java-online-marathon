package com.softserve.itacademy;


import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.impl.UserServiceImpl;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;
import javax.persistence.Transient;
import javax.security.auth.Subject;
import javax.validation.constraints.AssertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceWithMockTest {

    @MockBean
    private UserRepository userRepositoryMock;

    @InjectMocks
    private UserServiceImpl userServiceMock = new UserServiceImpl(userRepositoryMock);

    @Test
    public void createValidUser(){
        User user = new User();
        when(userRepositoryMock.save(any(User.class))).thenReturn(user);
        assertEquals(user, userServiceMock.create(user));
    }

    @Test
    public void createNullUser(){
        when(userRepositoryMock.save(any())).thenThrow(IllegalArgumentException.class);
        assertThrows(NullEntityReferenceException.class, () -> userServiceMock.create(null));
    }

    @Test
    public void UpdateExistUser(){
        User user = new User();
        when(userRepositoryMock.save(any(User.class))).thenReturn(user);
        when(userRepositoryMock.findById(any())).thenReturn(Optional.of(user));
        assertEquals(user, userServiceMock.update(user));
    }

    @Test
    public void UpdateNotExistUser(){
        assertThrows(NullEntityReferenceException.class, () -> userServiceMock.update(null));
    }

    @Test
    public void ReadByIdExistUser(){
        User user = new User();
        when(userRepositoryMock.findById(any())).thenReturn(Optional.of(user));
        assertEquals(user, userServiceMock.readById(1));
    }

    @Test
    public void ReadByIdNotExistUser(){
        when(userRepositoryMock.save(any())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> userServiceMock.readById(1));
    }

    @Test
    public void DeleteExistUser(){
        User user = new User();
        when(userRepositoryMock.findById(any(long.class))).thenReturn(Optional.of(user));
        userServiceMock.delete(1);
            assertDoesNotThrow(() -> userRepositoryMock.delete(user));
    }

    @Test
    public void deleteNullUser() {
        User user = null;
        when(userRepositoryMock.findById(any(long.class))).thenReturn(Optional.ofNullable(user));
        assertThrows(EntityNotFoundException.class, () -> userServiceMock.delete(1L));
    }

    @Test
    public void GetAllUser(){
        List<User> user = new ArrayList<User>();
        when(userRepositoryMock.findAll()).thenReturn(user);
        assertEquals(user, userServiceMock.getAll());
    }


}