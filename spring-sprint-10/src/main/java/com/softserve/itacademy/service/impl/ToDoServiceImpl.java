package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.service.ToDoService;
import com.softserve.itacademy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

@Service
public class ToDoServiceImpl implements ToDoService {


    private UserService userService;

    @Autowired
    public ToDoServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ToDo addTodo(ToDo todo, User user) {

        if (todo == null || user == null) {
            return null;
        }

        boolean flag = user.getMyTodos()
                .stream()
                .anyMatch(value -> value.getTitle().equals(todo.getTitle()));
        if (flag) {
            return null;
        }

        user.getMyTodos().add(todo);
        return todo;
    }

    @Override
    public ToDo updateTodo(ToDo todo) {
        if (todo == null || todo.getOwner() == null) {
            return null;
        }

        ListIterator<ToDo> listIterator = todo.getOwner().getMyTodos().listIterator();
        ToDo oldToDo = null;
        ToDo current;

        while (listIterator.hasNext()) {
            current = listIterator.next();
            if (current.getTitle().equals(todo.getTitle())) {
                oldToDo = current;
                listIterator.set(todo);
                break;
            }
        }

        return oldToDo;
    }

    @Override
    public void deleteTodo(ToDo todo) {
        if (todo != null) {
            todo.getOwner().getMyTodos().remove(todo);
        }
    }

    @Override
    public List<ToDo> getAll() {
        return userService.getAll()
                .stream()
                .flatMap(user -> user.getMyTodos().stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<ToDo> getByUser(User user) {
        if (user == null) {
            return null;
        }

        return new ArrayList<>(user.getMyTodos());
    }

    @Override
    public ToDo getByUserTitle(User user, String title) {
        if (user == null) {
            return null;
        }

        return user.getMyTodos()
                .stream()
                .filter(todo -> todo.getTitle().equals(title))
                .findAny()
                .orElse(null);
    }

}
