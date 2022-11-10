package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.service.StateService;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {
    private StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State create(State state) {
        try {
            return stateRepository.save(state);
        }catch (InvalidDataAccessApiUsageException e){
            throw new NullEntityReferenceException("State cannot be null!");
        }
    }

    @Override
    public State readById(long id) {
        Optional<State> optional = stateRepository.findById(id);
            return optional.orElseThrow(() -> new EntityNotFoundException("Can't find state with id " + id));
    }

    @Override
    public State update(State state) {
        if(state != null) {
            State oldState = readById(state.getId());
            return stateRepository.save(state);
        }else{
            throw new NullEntityReferenceException("State cannot be null!");
        }
    }

    @Override
    public void delete(long id) {
        State state = readById(id);
            stateRepository.delete(state);
    }

    @Override
    public State getByName(String name) {
        Optional<State> optional = Optional.ofNullable(stateRepository.getByName(name));
            return optional.orElseThrow(() -> new EntityNotFoundException("Can't find state with name " + name));
    }

    @Override
    public List<State> getAll() {
        List<State> states = stateRepository.getAll();
        return states.isEmpty() ? new ArrayList<>() : states;
    }
}
