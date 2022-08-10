package com.example.belajarspring1;

import com.example.belajarspring1.beans.Country;
import com.example.belajarspring1.repositories.CountryRepository;
import com.example.belajarspring1.services.CountryService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMackitoTest.class})
public class ServiceMackitoTest {
    @Mock
    CountryRepository countryrep;

    @InjectMocks
    CountryService countryService;

    public List<Country> mycountries;

    @Test
    @Order(1)
    public void test_getAllCountries()
    {
        List<Country> mycountries=new ArrayList<Country>();
        mycountries.add(new Country(1, "Indonesia","Jakarta"));
        mycountries.add(new Country(2, "UK", "London"));


        when(countryrep.findAll()).thenReturn(mycountries);
        assertEquals(2,countryService.getAllCountries().size());
    }

    @Test
    @Order(2)
    public void test_getCountryByID()
    {
        List<Country> mycountries=new ArrayList<Country>();
        mycountries.add(new Country(1, "Indonesia", "Jakarta"));
        mycountries.add(new Country(2, "UK", "London"));
        int countryID=1;

        when(countryrep.findAll()).thenReturn(mycountries);
        assertEquals(countryID, countryService.getCountrybyID(countryID).getId());
    }

    @Test
    @Order(3)
    public void test_getCountrybyName()
    {
        List<Country> mycountries=new ArrayList<Country>();
        mycountries.add(new Country(1, "Indonesia", "Jakarta"));
        mycountries.add(new Country(2, "UK", "London"));
        String countryName="Indonesia";

        when(countryrep.findAll()).thenReturn(mycountries);
        assertEquals(countryName, countryService.getCountrybyName(countryName).getCountryName());
    }

    @Test
    @Order(4)
    public void test_addCountry()
    {
        Country country=new Country(3,"Washington", "USA");

        when(countryrep.save(country)).thenReturn(country);
        assertEquals(country, countryService.addCountry(country));
    }

    @Test
    @Order(5)
    public void test_updateCountry()
    {
        Country country=new Country(3,"Washington", "USA");

        when(countryrep.save(country)).thenReturn(country);
        assertEquals(country, countryService.updateCountry(country));
    }

    @Test
    @Order(6)
    public void test_deleteCountry()
    {
        Country country=new Country(3,"Washington", "USA");
        countryService.deleteCountry(country);
        verify(countryrep,times(1)).delete(country);
    }
}
