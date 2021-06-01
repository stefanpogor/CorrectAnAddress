package com.example.CorrectAddress.repositories;

import com.example.CorrectAddress.models.Country;
import com.example.CorrectAddress.models.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class StateRepositoryTest {

    @Autowired
    StateRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void findStateByName() {
        //given
        State state = new State("Iași");
        underTest.save(state);
        //when
        Optional<State> stateToBeFound=underTest.findStateByName("Iași");
        State stateFound = new State(stateToBeFound.get().getId(), stateToBeFound.get().getName());
        //then
        assertThat(stateFound.toString()).isEqualTo(state.toString());
    }

    @Test
    void findStateById() {
        //given
        State state = new State("Iași");
        underTest.save(state);
        //when
        Optional<State> stateToBeFound=underTest.findStateById(2111L);
        State stateFound = new State(stateToBeFound.get().getId(), stateToBeFound.get().getName());
        //then
        assertThat(stateFound.toString()).isEqualTo(state.toString());
    }
}