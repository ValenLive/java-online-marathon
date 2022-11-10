package com.softserve.itacademy;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.model.ToDo;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.repository.ToDoRepository;
import com.softserve.itacademy.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
@AllArgsConstructor
public class ToDoListApplication implements CommandLineRunner {

    UserRepository userRepository;
    RoleRepository roleRepository;
    ToDoRepository toDoRepository;

    public static void main(String[] args) {
        SpringApplication.run(ToDoListApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running Spring Boot Application");

//       Role role = roleRepository.getOne(2L);
        Role role = new Role();
        role.setName("Boss");
        User validUser  = new User();
        validUser.setEmail("valid@cv.edu.ua");
        validUser.setFirstName("Valid-Name");
        validUser.setLastName("Valid-Name");
        validUser.setPassword("qwQW12!@");
        validUser.setRole(role);

//        validUser = userRepository.save(validUser);

        ToDo toDo = new ToDo();
        toDo.setTitle("Other");
        toDo.setOwner(validUser);
//        toDo = toDoRepository.save(toDo);

        LocalDate localDate = toDo.getCreatedAt().toLocalDate();
        LocalDate today = LocalDate.now();
        System.out.println(localDate.equals(today));
    }
}

