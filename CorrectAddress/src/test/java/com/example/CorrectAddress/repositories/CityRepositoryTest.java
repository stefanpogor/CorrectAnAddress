package com.example.CorrectAddress.repositories;

import com.example.CorrectAddress.models.City;
import com.example.CorrectAddress.models.Country;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CityRepositoryTest {

    @Autowired
    CityRepository underTest;

    @AfterEach
    void tearDown(){
        underTest.deleteAll();
    }
    
    @Test
    void findCityByName() {
        //given
        City city = new City("Iași");
        City city1 = new City("București");
        underTest.save(city);
        underTest.save(city1);
        //when
        Optional<City> cityToBeFound=underTest.findCityByName("Iași");
        City cityFound = new City(cityToBeFound.get().getId(), cityToBeFound.get().getName());
        //then
        assertThat(cityFound.toString()).isEqualTo(city.toString());
    }
}