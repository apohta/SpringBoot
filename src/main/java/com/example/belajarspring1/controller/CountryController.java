package com.example.belajarspring1.controller;

import com.example.belajarspring1.beans.Country;
import com.example.belajarspring1.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class CountryController {


    @Autowired
    CountryService countryService;

    @GetMapping("/getcountries")
    public ResponseEntity<List<Country>> getcountries(){
        try {
            List<Country> countries=countryService.getAllCountries();
            return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/{id}")
    public ResponseEntity<Country> getcountryById(@PathVariable(value = "id") int id)
    {
        try {
            Country country=countryService.getCountrybyID(id);
            return new ResponseEntity<Country>(country,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getcountries/countryname")
    public ResponseEntity<Country> getcountryByName(@RequestParam(value = "name") String countryName)
    {
        try {
            Country country=countryService.getCountrybyName(countryName);
            return new ResponseEntity<Country>(country,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/addcountry")
    public Country addCountry(@RequestBody Country country)
    {
        return countryService.addCountry(country);
    }

    @PutMapping("/updatecountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id, @RequestBody Country country)
    {
        try {
            Country  existCountry=countryService.getCountrybyID(id);

            existCountry.setCountryName(country.getCountryName());
            existCountry.setCountryCapital(country.getCountryCapital());

            Country updated_country=countryService.updateCountry(existCountry);
            return new ResponseEntity<Country>(updated_country,HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path = "/deletecountry/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable("id") int id) {
        Country country = null;
        try {
            country=countryService.getCountrybyID(id);
            countryService.deleteCountry(country);
        }
        catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Country>(country, HttpStatus.OK);
        }
    }
