package com.example.CorrectAddress.repositories;

import com.example.CorrectAddress.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    @Query("SELECT c FROM City c WHERE c.name = ?1")
    Optional<City> findCityByName(String name);
}
