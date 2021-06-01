package com.example.CorrectAddress.controllers;

import com.example.CorrectAddress.models.Address;
import com.example.CorrectAddress.models.City;
import com.example.CorrectAddress.models.Country;
import com.example.CorrectAddress.models.State;
import com.example.CorrectAddress.repositories.CityRepository;
import com.example.CorrectAddress.repositories.CountryRepository;
import com.example.CorrectAddress.repositories.StateRepository;

import java.util.Optional;

public class CorrectTheAddressController {

    private Address address;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;
    private StateRepository stateRepository;

    public CorrectTheAddressController(Address address, CountryRepository countryRepository, CityRepository cityRepository, StateRepository stateRepository) {
        this.address = address;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
    }

    public Address solve(Address correctAddress) {
        Optional<Country> countryByName = countryRepository.findCountryByName(address.getCountryName());
        Optional<State> stateByName = stateRepository.findStateByName(address.getStateName());
        Optional<City> cityByName = cityRepository.findCityByName(address.getCityName());

        if (cityByName.isPresent()) {
            correctAddress.setCityName(address.getCityName());
            if (stateByName.isPresent()) {
                if (cityByName.get().getStateId().equals(stateByName.get().getId())) {
                    correctAddress.setStateName(address.getStateName());
                    if (countryByName.isPresent()) {
                        if (stateByName.get().getCountryId().equals(countryByName.get().getId())) {
                            correctAddress.setCountryName(address.getCountryName());
                        } else {
                            Optional<Country> countryById = countryRepository.findCountryByStateForeignKey(stateByName.get().getCountryId());
                            System.out.println(countryById.get().getId());
                            correctAddress.setCountryName(countryById.get().getName());
                        }
                    } else {
                        Optional<Country> countryById = countryRepository.findCountryByStateForeignKey(stateByName.get().getCountryId());
                        System.out.println(countryById.get().getId());
                        correctAddress.setCountryName(countryById.get().getName());
                    }
                } else {
                    Optional<State> stateById = stateRepository.findStateById(cityByName.get().getStateId());
                    System.out.println(stateById.get().getId());
                    correctAddress.setStateName(stateById.get().getName());
                    if (countryByName.isPresent()) {
                        if (stateById.get().getCountryId().equals(countryByName.get().getId())) {
                            correctAddress.setCountryName(address.getCountryName());
                        } else {
                            Optional<Country> countryById = countryRepository.findCountryByStateForeignKey(stateById.get().getCountryId());
                            System.out.println(countryById.get().getId());
                            correctAddress.setCountryName(countryById.get().getName());
                        }
                    } else {
                        Optional<Country> countryById = countryRepository.findCountryByStateForeignKey(stateById.get().getCountryId());
                        System.out.println(countryById.get().getId());
                        correctAddress.setCountryName(countryById.get().getName());
                    }
                }
            } else {
                Optional<State> stateById = stateRepository.findStateById(cityByName.get().getStateId());
                System.out.println(stateById.get().getId());
                correctAddress.setStateName(stateById.get().getName());
                if (countryByName.isPresent()) {
                    if (stateById.get().getCountryId().equals(countryByName.get().getId())) {
                        correctAddress.setCountryName(address.getCountryName());
                    } else {
                        Optional<Country> countryById = countryRepository.findCountryByStateForeignKey(stateById.get().getCountryId());
                        System.out.println(countryById.get().getId());
                        correctAddress.setCountryName(countryById.get().getName());
                    }
                } else {
                    Optional<Country> countryById = countryRepository.findCountryByStateForeignKey(stateById.get().getCountryId());
                    System.out.println(countryById.get().getId());
                    correctAddress.setCountryName(countryById.get().getName());
                }
            }
        } else {
            correctAddress.setCountryName("Orașul nu există în Baza de Date!   ");
            System.out.println("Orașul nu este în Baza de Date");
            correctAddress.setCityName("(◕_◕)");
            correctAddress.setStateName("(ಠ_ಠ)");
            return  correctAddress;
        }

        System.out.println(correctAddress.toString());
        return correctAddress;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CountryRepository getCountryRepository() {
        return countryRepository;
    }

    public void setCountryRepository(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CityRepository getCityRepository() {
        return cityRepository;
    }

    public void setCityRepository(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public StateRepository getStateRepository() {
        return stateRepository;
    }

    public void setStateRepository(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }
}
