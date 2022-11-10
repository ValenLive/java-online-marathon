package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        if (user != null){
            return userRepository.save(user);
        }
       throw new EntityNotFoundException("User cannot be null");
    }

    @Override
    public User readById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("User with this id %s not found", id)));
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(long id) {
        userRepository.delete(readById(id));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
