package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    @Override
    public Task create(Task task) {
        if (task != null){
            return taskRepository.save(task);
        }
        throw new EntityNotFoundException("Task cannot be null");
    }

    @Override
    public Task readById(long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Task with this id %s not found", id)));
    }

    @Override
    public Task update(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public void delete(long id) {
        taskRepository.delete(readById(id));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getByTodoId(long todoId) {
        return taskRepository.getAllByToDoId(todoId)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Task with this Todo id %s - not found", todoId)));
    }
}
