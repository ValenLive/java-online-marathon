package com.softserve.itacademy;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.service.impl.ToDoServiceImpl;
import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ToDoServiceTest {

    @MockBean
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoServiceImpl toDoService = new ToDoServiceImpl(toDoRepository);

    @Test
    public void createValidToDoTest() {
        ToDo toDo = new ToDo();
        toDo.setTitle("Title");
        when(toDoRepository.save(any(ToDo.class))).thenReturn(toDo);
        String expected = toDo.getTitle();
        String actual = toDoService.create(toDo).getTitle();

        assertEquals(expected, actual);
    }

    @Test
    public void createInvalidToDoTest() {
        ToDo toDo = new ToDo();
        when(toDoRepository.save(any(ToDo.class))).thenThrow(NullEntityReferenceException.class);
        assertThrows(NullEntityReferenceException.class, () -> toDoService.create(toDo));
    }

    @Test
    public void readByIdValidToDoTest() {
        ToDo expected = new ToDo();
        when(toDoRepository.findById(any())).thenReturn(Optional.of(expected));
        ToDo actual = toDoService.readById(1L);
        assertEquals(expected, actual);
    }

    @Test
    public void readByIdInvalidToDoTest() {
        when(toDoRepository.save(any())).thenThrow(NullEntityReferenceException.class);
        assertThrows(EntityNotFoundException.class, () -> toDoService.readById(1L));
    }

    @Test
    public void updateValidToDoTest() {
        ToDo expected = new ToDo();
        when(toDoRepository.save(any(ToDo.class))).thenReturn(expected);
        when(toDoRepository.findById(any())).thenReturn(Optional.of(expected));
        ToDo actual = toDoService.update(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void updateInvalidToDoTest() {
        ToDo toDo = new ToDo();
        when(toDoRepository.findById(any())).thenThrow(EntityNotFoundException.class);
        assertThrows(EntityNotFoundException.class, () -> toDoService.update(toDo));
    }

    @Test
    public void deleteValidToDoTest() {
        ToDo toDo = new ToDo();
        when(toDoRepository.findById(any())).thenReturn(Optional.of(toDo));
        toDoService.delete(1L);
        verify(toDoRepository).delete(toDo);
    }

    @Test
    public void deleteInvalidToDoTest() {
        when(toDoRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> toDoService.delete(1L));
    }

    @Test
    public void getAllToDosTest() {
        List<ToDo> expected = new ArrayList<>();
        when(toDoRepository.findAll()).thenReturn(expected);
        List<ToDo> actual = toDoService.getAll();
        assertEquals(expected, actual);
    }

    @Test
    public void getToDosByUserIdTest() {
        List<ToDo> expected = new ArrayList<>();
        when(toDoRepository.getByUserId(1L)).thenReturn(expected);
        List<ToDo> actual = toDoService.getByUserId(1L);
        assertEquals(expected, actual);
    }
}
