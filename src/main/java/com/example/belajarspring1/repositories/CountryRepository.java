package com.example.belajarspring1.repositories;

import com.example.belajarspring1.beans.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer>
{
}
