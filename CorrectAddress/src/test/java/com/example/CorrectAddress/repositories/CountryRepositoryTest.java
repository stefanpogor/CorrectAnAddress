package com.example.CorrectAddress.repositories;

import com.example.CorrectAddress.models.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CountryRepositoryTest {

    @Autowired
    CountryRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }

    @Test
    void findCountryByName() {

        //given
        Country country = new Country("România");
        Country country1 = new Country("Italia");
        underTest.save(country);
        underTest.save(country1);
        //when
        Optional<Country> countryToBeFound=underTest.findCountryByName("România");
        Country countryFound = new Country(countryToBeFound.get().getId(), countryToBeFound.get().getName());
        //then
        assertThat(countryFound.toString()).isEqualTo(country.toString());
    }

    @Test
    void findCountryByStateForeignKey() {
        //given
        Country country = new Country("România");
        Country country1 = new Country("Italia");
        underTest.save(country);
        underTest.save(country1);
        //when
        Optional<Country> countryToBeFound=underTest.findCountryByStateForeignKey(2L);
        Country countryFound = new Country(countryToBeFound.get().getId(), countryToBeFound.get().getName());
        //then
        assertThat(countryFound.toString()).isEqualTo(country1.toString());
    }
}