package com.acs.acs.Controllers;

import com.acs.acs.Enitities.City;
import com.acs.acs.Enitities.Country;
import com.acs.acs.Enitities.State;
import com.acs.acs.Services.AddressOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/get")
public class AddressOperationsController {

    @Autowired
    private AddressOperationsService addressOperationsService;

    // 1. Get all cities of a particular state
    @GetMapping("/citiesInState/{stateId}")
    public ResponseEntity<List<City>> getCitiesByState(@PathVariable Long stateId) {
        List<City> cities = addressOperationsService.getCitiesByState(stateId);
        if (cities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cities);
    }

    // 2. Get all states of a particular country
    @GetMapping("/statesInCountry/{countryId}")
    public ResponseEntity<List<State>> getStatesByCountry(@PathVariable Long countryId) {
        List<State> states = addressOperationsService.getStatesByCountry(countryId);
        if (states.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(states);
    }

    // 3. Get all countries
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = addressOperationsService.getAllCountries();
        if (countries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(countries);
    }
}
