package com.example.CorrectAddress.beans;

import com.example.CorrectAddress.models.Country;
import com.example.CorrectAddress.services.CountryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class CountryConfig {

    @Bean
    CommandLineRunner commandLineRunner(CountryService countryService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Country>> typeReference = new TypeReference<List<Country>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/countries.json");
            try {
                List<Country> countries = mapper.readValue(inputStream, typeReference);
                countryService.saveCountries(countries);
                System.out.println("Countries saved");
            } catch (IOException e) {
                System.out.println("Unable to save countries: " + e.getMessage());
            }
        };
    }
}
