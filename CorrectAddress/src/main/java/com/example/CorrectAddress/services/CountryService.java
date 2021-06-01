package com.example.CorrectAddress.services;

import com.example.CorrectAddress.models.Country;
import com.example.CorrectAddress.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void saveCountries(List<Country> countries){
        countryRepository.saveAll(countries);
    }
}
