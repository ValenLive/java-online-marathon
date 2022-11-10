package com.softserve.itacademy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.softserve.itacademy.repository.TaskRepository;

@DataJpaTest
public class TaskRepositoryTest {
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void repositoryDbConnection() {
		
		taskRepository.deleteById(5L);
		int actual = taskRepository.findAll().size();
		
		Assertions.assertEquals(2, actual);
	}
	
	@Test
	public void repositoryGetByTodoIdTest() {
		
		taskRepository.getByTodoId(7L);
		int actual = taskRepository.findAll().size();
		
		Assertions.assertEquals(3, actual);
	}
	
}
