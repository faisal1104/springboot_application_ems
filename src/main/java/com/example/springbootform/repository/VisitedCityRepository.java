package com.example.springbootform.repository;


import com.example.springbootform.model.VisitedCity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitedCityRepository extends JpaRepository<VisitedCity, Long> {

}
