package com.softserve.itacademy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.service.impl.TaskServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

	@MockBean
	private TaskRepository taskRepository;

	@InjectMocks
	private TaskServiceImpl taskService = new TaskServiceImpl(taskRepository);

	@Test
	public void createValidTaskTest() {
		Task task = new Task();
		task.setName("Test");
		when(taskRepository.save(any(Task.class))).thenReturn(task);
		assertEquals(task.getName(), taskService.create(task).getName());
	}

	@Test
	public void createNullTaskTest() {
		when(taskRepository.save(any())).thenThrow(IllegalArgumentException.class);
		assertThrows(NullEntityReferenceException.class, () -> taskService.create(null));
	}

	@Test
	public void readByIdExistTaskTest() {
		Task task = new Task();
		when(taskRepository.findById(any())).thenReturn(Optional.of(task));
		assertEquals(task, taskService.readById(1L));
	}

	@Test
	public void readByWrongIdTaskTest() {
		when(taskRepository.save(any())).thenThrow(EntityNotFoundException.class);
		assertThrows(EntityNotFoundException.class, () -> taskService.readById(1));
	}

	@Test
	public void updateExistTaskTest() {
		Task task = new Task();
		when(taskRepository.findById(any())).thenReturn(Optional.of(task));
		when(taskRepository.save(any(Task.class))).thenReturn(task);
		assertEquals(task, taskService.update(task));
	}

	@Test
	public void updateWithNullTaskTest() {
		assertThrows(NullEntityReferenceException.class, () -> taskService.update(null));
	}

	@Test
	public void deleteExistTaskTest() {
		Task task = new Task();
		when(taskRepository.findById(any())).thenReturn(Optional.of(task));
		taskService.delete(1);
		verify(taskRepository).delete(task);
	}

	@Test
	public void deleteNotExistTaskTest() {
		Task task = null;
		when(taskRepository.findById(any(long.class))).thenReturn(Optional.ofNullable(task));
		assertThrows(EntityNotFoundException.class, () -> taskService.delete(1L));
	}

	@Test
	public void getEmptyTasksByTodoIdTest() {
		List<Task> tasks = new ArrayList<Task>();
		when(taskRepository.getByTodoId(1L)).thenReturn(tasks);
		assertEquals(tasks, taskService.getByTodoId(1L));
	}
	
	@Test
	public void getTasksByTodoIdTest() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task());
		when(taskRepository.getByTodoId(1L)).thenReturn(tasks);
		assertEquals(tasks, taskService.getByTodoId(1L));
	}
	
	@Test
	public void getEmptyAllTasksTest() {
		List<Task> tasks = new ArrayList<Task>();
		when(taskRepository.findAll()).thenReturn(tasks);
		assertEquals(tasks, taskService.getAll());
	}
	
	@Test
	public void getAllTasksTest() {
		List<Task> tasks = new ArrayList<Task>();
		tasks.add(new Task());
		when(taskRepository.findAll()).thenReturn(tasks);
		assertEquals(tasks, taskService.getAll());
	}
}
