package com.example.CorrectAddress.repositories;

import com.example.CorrectAddress.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StateRepository extends JpaRepository<State, Long> {

    @Query("SELECT s FROM State s WHERE s.name = ?1")
    Optional<State> findStateByName(String name);

    @Query("SELECT s FROM State s WHERE s.id = ?1")
    Optional<State> findStateById(Long stateId);
}
