package com.softserve.itacademy;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RoleServiceWithMockTest {

    @MockBean
    private RoleRepository roleRepositoryMock;

    @InjectMocks
    private RoleServiceImpl roleServiceImpl = new RoleServiceImpl(roleRepositoryMock);

    @Test
    public void ReadByIdExistRole(){
        Role role = new Role();
        when(roleRepositoryMock.findById(any())).thenReturn(Optional.of(role));
        assertEquals(role, roleServiceImpl.readById(1));
    }

    @Test
    public void ReadByIdNotExistRole(){
        when(roleRepositoryMock.findById(any())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> roleServiceImpl.readById(1L));
    }

    @Test
    public void createValidRoleTest(){
        Role role = new Role();
        when(roleRepositoryMock.save(any(Role.class))).thenReturn(role);
        assertEquals(role, roleServiceImpl.create(role));
    }

    @Test
    public void createNullRoleTest(){
        when(roleRepositoryMock.save(any())).thenThrow(IllegalArgumentException.class);
        assertThrows(NullEntityReferenceException.class, () -> roleServiceImpl.create(null));
    }

    @Test
    public void UpdateExistRoleTest(){
        Role role = new Role();
        when(roleRepositoryMock.save(any(Role.class))).thenReturn(role);
        when(roleRepositoryMock.findById(any())).thenReturn(Optional.of(role));
        assertEquals(role, roleServiceImpl.update(role));
    }

    @Test
    public void UpdateNotExistRoleTest(){
        assertThrows(NullEntityReferenceException.class, () -> roleServiceImpl.update(null));
    }

    @Test
    public void DeleteExistRoleTest(){
        Role role = new Role();
        when(roleRepositoryMock.findById(any(long.class))).thenReturn(Optional.of(role));
        roleServiceImpl.delete(1);
        assertDoesNotThrow(() -> roleRepositoryMock.delete(role));
    }

    @Test
    public void DeleteNullRoleTest() {
        when(roleRepositoryMock.findById(any(long.class))).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> roleServiceImpl.delete(1L));
    }

    @Test
    public void GetAllRolesTest(){
        List<Role> roles = new ArrayList<Role>();
        when(roleRepositoryMock.findAll()).thenReturn(roles);
        assertEquals(roles, roleServiceImpl.getAll());
    }

}
