package com.softserve.itacademy;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.service.impl.RoleServiceImpl;
import com.softserve.itacademy.service.impl.StateServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StateServiceWithMockTest {

    @MockBean
    private StateRepository stateRepositoryMock;

    @InjectMocks
    private StateServiceImpl stateServiceImplMock = new StateServiceImpl(stateRepositoryMock);

    @Test
    public void ReadByIdExistState(){
        State state = new State();
        when(stateRepositoryMock.findById(any())).thenReturn(Optional.of(state));
        assertEquals(state, stateServiceImplMock.readById(1));
    }

    @Test
    public void ReadByIdNotExistState(){
        when(stateRepositoryMock.findById(any())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> stateServiceImplMock.readById(1L));
    }

    @Test
    public void createValidStateTest(){
        State state = new State();
        when(stateRepositoryMock.save(any(State.class))).thenReturn(state);
        assertEquals(state, stateServiceImplMock.create(state));
    }

    @Test
    public void createNullStateTest(){
        when(stateRepositoryMock.save(any())).thenThrow(IllegalArgumentException.class);
        assertThrows(NullEntityReferenceException.class, () -> stateServiceImplMock.create(null));
    }

    @Test
    public void UpdateExistStateTest(){
        State state = new State();
        when(stateRepositoryMock.save(any(State.class))).thenReturn(state);
        when(stateRepositoryMock.findById(any())).thenReturn(Optional.of(state));
        assertEquals(state, stateServiceImplMock.update(state));
    }

    @Test
    public void UpdateNotExistStateTest(){
        assertThrows(NullEntityReferenceException.class, () -> stateServiceImplMock.update(null));
    }

    @Test
    public void DeleteExistStateTest(){
        State state = new State();
        when(stateRepositoryMock.findById(any(long.class))).thenReturn(Optional.of(state));
        stateServiceImplMock.delete(1);
        assertDoesNotThrow(() -> stateRepositoryMock.delete(state));
    }

    @Test
    public void DeleteNullStateTest() {
        when(stateRepositoryMock.findById(any(long.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> stateServiceImplMock.delete(1L));
    }

    @Test
    public void GetExistStateByNameTest(){
        State state = new State();
        when(stateRepositoryMock.getByName(any(String.class))).thenReturn(state);
        assertEquals(state, stateServiceImplMock.getByName("TestName"));
    }

    @Test
    public void GetNotExistStateByNameTest(){
        when(stateRepositoryMock.getByName(any(String.class))).thenReturn(null);
        assertThrows(EntityNotFoundException.class, () -> stateServiceImplMock.getByName("TestName"));
    }

    @Test
    public void GetAllStatesTest(){
        List<State> states = new ArrayList<State>();
        when(stateRepositoryMock.findAll()).thenReturn(states);
        assertEquals(states, stateServiceImplMock.getAll());
    }
}
