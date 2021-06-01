package com.example.CorrectAddress.controllers;

import com.example.CorrectAddress.models.Address;
import com.example.CorrectAddress.models.City;
import com.example.CorrectAddress.models.Country;
import com.example.CorrectAddress.models.State;
import com.example.CorrectAddress.repositories.CityRepository;
import com.example.CorrectAddress.repositories.CountryRepository;
import com.example.CorrectAddress.repositories.StateRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CorrectTheAddressControllerTest {

    @Autowired
    CountryRepository countryRepository;
    @Autowired
    StateRepository stateRepository;
    @Autowired
    CityRepository cityRepository;

    @AfterEach
    void tearDown(){
        countryRepository.deleteAll();
        stateRepository.deleteAll();
        cityRepository.deleteAll();
    }

    @BeforeEach
    void delete(){
        countryRepository.deleteAll();
        stateRepository.deleteAll();
        cityRepository.deleteAll();
    }

    @Test
    //Presupunem ca orasul introdus exista in baza de date => 3 posibilitati
    //1. Statul/Judetul este gresit si tara corecta
    //2. Tara este corecta si statul/judetul gresit
    //3. Ambele sunt gresite

    //1.
    void correctState() {

        //given
        Country country = new Country("România");
        State state= new State ("Iași",1L);
        City city = new City("Iași", 2111L);

        countryRepository.save(country);
        stateRepository.save(state);
        cityRepository.save(city);

        Address addressToBeCorrected = new Address("România", "Judet gresit", "Iași");
        CorrectTheAddressController algorithm = new CorrectTheAddressController(addressToBeCorrected,countryRepository, cityRepository, stateRepository);
        //when
        Address correctAddress = algorithm.solve(addressToBeCorrected);
        //then
        Address expected = new Address("România", "Iași", "Iași");
        assertThat(correctAddress.toString()).isEqualTo(expected.toString());
    }

    @Test
    //2.
    void correctCountry() {

        //given
        Country country = new Country("România");
        State state= new State ("Iași",1L);
        City city = new City("Iași", 2111L);

        countryRepository.save(country);
        stateRepository.save(state);
        cityRepository.save(city);

        Address addressToBeCorrected = new Address("Tara gresita", "Iași", "Iași");
        CorrectTheAddressController algorithm = new CorrectTheAddressController(addressToBeCorrected,countryRepository, cityRepository, stateRepository);
        //when
        Address correctAddress = algorithm.solve(addressToBeCorrected);
        //then
        Address expected = new Address("România", "Iași", "Iași");
        assertThat(correctAddress.toString()).isEqualTo(expected.toString());
    }

    @Test
        //2.
    void correctCountryAndState() {

        //given
        Country country = new Country("România");
        State state= new State ("Iași",1L);
        City city = new City("Iași", 2111L);

        countryRepository.save(country);
        stateRepository.save(state);
        cityRepository.save(city);

        Address addressToBeCorrected = new Address("Tara gresita", "Judet gresit", "Iași");
        CorrectTheAddressController algorithm = new CorrectTheAddressController(addressToBeCorrected,countryRepository, cityRepository, stateRepository);
        //when
        Address correctAddress = algorithm.solve(addressToBeCorrected);
        //then
        Address expected = new Address("România", "Iași", "Iași");
        assertThat(correctAddress.toString()).isEqualTo(expected.toString());
    }
}