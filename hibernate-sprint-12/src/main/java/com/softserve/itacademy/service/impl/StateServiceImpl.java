package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Override
    public State create(State state) {
        if (state != null){
            return stateRepository.save(state);
        }
       throw new EntityNotFoundException("State cannot be null");
    }

    @Override
    public State readById(long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("State with this id %s not found", id)));
    }

    @Override
    public State update(State state) {
        return stateRepository.saveAndFlush(state);
    }

    @Override
    public void delete(long id) {
        stateRepository.delete(readById(id));

    }

    @Override
    public List<State> getAll() {
        return stateRepository.findAll();
    }

    @Override
    public State getByName(String name) {
        return stateRepository.getByName(name)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("State with name %s not found", name)));
    }

    @Override
    public List<State> getSortAsc() {
        return stateRepository.findAll(Sort.by("name").ascending());
    }
}
