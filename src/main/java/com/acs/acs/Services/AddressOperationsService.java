package com.acs.acs.Services;

import com.acs.acs.Enitities.City;
import com.acs.acs.Enitities.Country;
import com.acs.acs.Enitities.State;
import com.acs.acs.Repository.CityRepository;
import com.acs.acs.Repository.CountryRepository;
import com.acs.acs.Repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressOperationsService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CountryRepository countryRepository;

    // Get all cities of a particular state

    public List<City> getCitiesByState(Long stateId) {
        return cityRepository.findByStateId(stateId);
    }

    // Get all states of a particular country
    public List<State> getStatesByCountry(Long countryId) {
        return stateRepository.findByCountryId(countryId);
    }

    // Get all countries
    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }
}
