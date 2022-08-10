package com.example.belajarspring1;


import com.example.belajarspring1.beans.Country;
import com.example.belajarspring1.repositories.CountryRepository;
import com.example.belajarspring1.services.CountryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
@TestPropertySource("classpath:application.properties")
public class LoggingTest {
    @Mock
    CountryRepository countryrep;
    @InjectMocks
    CountryService  countryService;
    @Test
    void testLogging(){
        log.info("Logging Info");
        log.warn("Logging  Warning");
        log.error("Logging Error");
    }

    @Test
    void test_getAllCountries(){
        for(int i=0; i<15; i++){
            List<Country> mycountries=new ArrayList<Country>();
            mycountries.add(new Country(1, "Indonesia","Jakarta"));
            mycountries.add(new Country(2, "UK", "London"));
            mycountries.add(new Country(3,"Washington", "USA"));

            when(countryrep.findAll()).thenReturn(mycountries);
            log.info("Return ->{}", i);
            assertEquals(3, countryService.getAllCountries().size());
        }
    }

    @Test
    void test_getCountryByID(){
        for(int i=0;  i<15; i++)
        {
            List<Country> mycountries=new ArrayList<Country>();
            mycountries.add(new Country(1, "Indonesia", "Jakarta"));
            mycountries.add(new Country(2, "UK", "London"));
            mycountries.add(new Country(3,"Washington", "USA"));
            int countryID=1;

            when(countryrep.findAll()).thenReturn(mycountries);
            log.info("Return ->{}", i);
            assertEquals(countryID, countryService.getCountrybyID(countryID).getId());
        }
    }

    @Test
    void test_getCountrybyName(){
        for(int i=0; i<15; i++)
        {
            List<Country> mycountries=new ArrayList<Country>();
            mycountries.add(new Country(1, "Indonesia", "Jakarta"));
            mycountries.add(new Country(2, "UK", "London"));
            mycountries.add(new Country(3,"Washington", "USA"));
            String countryName="Indonesia";

            when(countryrep.findAll()).thenReturn(mycountries);
            log.info("Return ->{}", i);
            assertEquals(countryName, countryService.getCountrybyName(countryName).getCountryName());
        }
    }

    @Test
    void   test_addCountry(){
        for (int i=0; i<15; i++)
        {
            Country country=new Country(4,"Canberra", "Australia");

            when(countryrep.save(country)).thenReturn(country);
            assertEquals(country, countryService.addCountry(country));
        }
    }

    @Test
    void test_updateCountry(){
        for (int i=0; i<15; i++)
        {
            Country country=new Country(4,"Canberra", "Australia");

            when(countryrep.save(country)).thenReturn(country);
            log.info("Return ->{}", i);
            assertEquals(country, countryService.updateCountry(country));
        }
    }

    @Test
    void test_deleteCountry(){
        for (int i=0; i<15; i++)
        {
            Country country=new Country(4,"Canberra", "Australia");
            countryService.deleteCountry(country);
            verify(countryrep,times(1)).delete(country);
        }
    }
}
