package com.example.CorrectAddress.controllers;

import com.example.CorrectAddress.models.Address;
import com.example.CorrectAddress.repositories.CityRepository;
import com.example.CorrectAddress.repositories.CountryRepository;
import com.example.CorrectAddress.repositories.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {


    CountryRepository countryRepository;
    StateRepository stateRepository;
    CityRepository cityRepository;

    @Autowired
    public HomeController(CountryRepository countryRepository, StateRepository stateRepository, CityRepository cityRepository) {
        this.countryRepository = countryRepository;
        this.stateRepository = stateRepository;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/")
    public String goHome(Model model) {
        model.addAttribute("address", new Address());
        return "index";
    }

    @PostMapping("/verify")
    public String verify(Model model, Address address) {
        CorrectTheAddressController algorithm = new CorrectTheAddressController(address, countryRepository, cityRepository, stateRepository);
        algorithm.solve(address);
        return "verifyAddress";
    }
}
