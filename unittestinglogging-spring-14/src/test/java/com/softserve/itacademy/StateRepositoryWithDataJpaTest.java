package com.softserve.itacademy;

import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class StateRepositoryWithDataJpaTest {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void saveUserTest(){
        String searchedField = "Test";
        State state = new State();
        state.setName("Test");

        stateRepository.save(state);

        State actual = stateRepository.findById(state.getId()).get();
        Assertions.assertEquals(searchedField,actual.getName());
    }
}
