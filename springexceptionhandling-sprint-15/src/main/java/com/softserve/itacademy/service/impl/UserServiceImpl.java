package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.UserService;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        try {
            return userRepository.save(user);
        }catch (InvalidDataAccessApiUsageException exception){
            throw new NullEntityReferenceException("User cannot be null!");
        }
    }

    @Override
    public User readById(long id) {
        Optional<User> optional = userRepository.findById(id);
            return optional.orElseThrow(() -> new EntityNotFoundException("Can't find user with id " + id));
    }

    @Override
    public User update(User user) {
        if(user != null) {
            User oldUser = readById(user.getId());
            return userRepository.save(user);
        }else{
            throw new NullEntityReferenceException("User cannot be null!");
        }
    }

    @Override
    public void delete(long id) {
        User user = readById(id);
            userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

}
