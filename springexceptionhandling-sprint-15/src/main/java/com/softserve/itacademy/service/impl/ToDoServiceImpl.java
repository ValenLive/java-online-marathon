package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.service.ToDoService;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {

    private ToDoRepository todoRepository;

    public ToDoServiceImpl(ToDoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public ToDo create(ToDo todo) {
        try {
            return todoRepository.save(todo);
        }catch (InvalidDataAccessApiUsageException exception){
            throw new NullEntityReferenceException("ToDo cannot be null!");
        }
    }

    @Override
    public ToDo readById(long id) {
        Optional<ToDo> optional = todoRepository.findById(id);
            return optional.orElseThrow(() -> new EntityNotFoundException("Can't find todo with id " + id));
    }

    @Override
    public ToDo update(ToDo todo) {
        if(todo != null) {
            ToDo oldTodo = readById(todo.getId());
            return todoRepository.save(todo);
        }else{
            throw new NullEntityReferenceException("ToDo cannot be null!");
        }
    }

    @Override
    public void delete(long id) {
        ToDo todo = readById(id);
            todoRepository.delete(todo);
    }

    @Override
    public List<ToDo> getAll() {
        List<ToDo> todos = todoRepository.findAll();
        return todos.isEmpty() ? new ArrayList<>() : todos;
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        List<ToDo> todos = todoRepository.getByUserId(userId);
        return todos.isEmpty() ? new ArrayList<>() : todos;
    }
}
