package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;

    @Override
    public ToDo create(ToDo todo) {
        if (todo != null){
            return toDoRepository.save(todo);
        }
        throw new EntityNotFoundException("Todo cannot be null");
    }

    @Override
    public ToDo readById(long id) {
        return toDoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Todo with this id %s not found", id)));
    }

    @Override
    public ToDo update(ToDo todo) {
        return toDoRepository.saveAndFlush(todo);

    }

    @Override
    public void delete(long id) {
        toDoRepository.delete(readById(id));

    }

    @Override
    public List<ToDo> getAll() {
        return toDoRepository.findAll();
    }

    @Override
    public List<ToDo> getByUserId(long userId) {
        return toDoRepository.getAllByOwnerId(userId)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("ToDo with this User id %s - not found", userId)));
    }
}
