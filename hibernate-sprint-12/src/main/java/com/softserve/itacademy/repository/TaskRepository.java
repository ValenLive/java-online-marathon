package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// TODO
// implements methods for retrieving Tasks by todo_id
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<List<Task>> getAllByToDoId(Long todoId);

}
