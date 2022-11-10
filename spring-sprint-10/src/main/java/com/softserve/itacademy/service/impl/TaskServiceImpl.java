package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.TaskService;
import com.softserve.itacademy.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

	private ToDoService toDoService;

	@Autowired
	public TaskServiceImpl(ToDoService toDoService) {
		this.toDoService = toDoService;
	}

	public Task addTask(Task task, ToDo todo) {
		if (task.getName() == null || task.getName().isEmpty()) {
			throw new IllegalArgumentException("The expected field value cannot be empty!");
		}
		if (todo.getTasks().contains(task)) {
			throw new IllegalArgumentException("Task already exist!");
		}
		todo.getTasks().add(task);
		return task;
	}

	public Task updateTask(Task task) {
		if(task.getPriority() != null && task.getName() != null) {
			int i = getAll().indexOf(task);
			if(i < 0) {
				throw new IllegalArgumentException(task + " Not found");
			}
			getAll().get(i).setPriority(task.getPriority());
		}else{
			throw new IllegalArgumentException("The expected field value cannot be empty!");
		}
		return task;
	}

	public void deleteTask(Task task) {
		boolean done = false;
		for(ToDo todo: toDoService.getAll()) {
			done = todo.getTasks().remove(task);
		}
		if (!done) {
			throw new IllegalArgumentException(task.toString() + " Not found");
		}
	}

	public List<Task> getAll() {
		return toDoService.getAll().stream()
				.map(ToDo::getTasks)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	public List<Task> getByToDo(ToDo todo) {
		return toDoService.getByUser(todo.getOwner()).stream()
				.map(ToDo::getTasks)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	public Task getByToDoName(ToDo todo, String name) {
		return todo.getTasks().stream()
				.filter(t -> t.getName().equalsIgnoreCase(name))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Task with name = " + name + " not found"));
				
	}

	public Task getByUserName(User user, String name) {
		return 	user.getMyTodos().stream()
				.map(ToDo::getTasks)
				.flatMap(Collection::stream)
				.filter(t -> t.getName().equalsIgnoreCase(name))
				.findAny()
				.orElseThrow(() -> new IllegalArgumentException("Task with name = " + name + " not found"));
	}

}
