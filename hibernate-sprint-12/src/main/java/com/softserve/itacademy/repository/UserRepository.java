package com.softserve.itacademy.repository;

import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// implement method for retrieve user by email (email is unique - so we have only one user)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByEmail(String email);
}
