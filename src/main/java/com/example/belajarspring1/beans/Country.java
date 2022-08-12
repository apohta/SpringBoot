package com.example.belajarspring1.beans;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Table(name = "country")
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotEmpty(message = "Capital Name is required")
    private String countryCapital;

    @NotEmpty(message = "Country Name is required")
    private String countryName;

    public Country(){

    }

    public Country(int id, String countryName,String countryCapital){
        this.id = id;
        this.countryName = countryName;
        this.countryCapital = countryCapital;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCapital() {
        return countryCapital;
    }

    public void setCountryCapital(String countryCapital) {
        this.countryCapital = countryCapital;
    }
}
