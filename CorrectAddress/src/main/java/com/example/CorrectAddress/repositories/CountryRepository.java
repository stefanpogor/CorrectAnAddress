package com.example.CorrectAddress.repositories;

import com.example.CorrectAddress.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c FROM Country c WHERE c.name = ?1")
    Optional<Country> findCountryByName(String name);

    @Query("SELECT c FROM Country c WHERE c.id = ?1")
    Optional<Country> findCountryByStateForeignKey(Long countryId);
}
