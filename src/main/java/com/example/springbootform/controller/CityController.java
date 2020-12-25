package com.example.springbootform.controller;

import com.example.springbootform.dto.VisitedCityDto;
import com.example.springbootform.model.VisitedCity;
import com.example.springbootform.repository.VisitedCityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/city")
public class CityController {
    @Autowired
    private VisitedCityRepository visitedCityRepository;

    @RequestMapping("/")
    public String addCity(Model m){
        m.addAttribute("allCity", visitedCityRepository.findAll());
        System.out.println(visitedCityRepository.findAll());
        return "city/v";
    }

    @GetMapping("/add")
    public String addC(Model m){
        m.addAttribute("city", new VisitedCity());
        return "city/addCity";

    }

    @PostMapping("/save")
    public RedirectView save(@ModelAttribute("city") VisitedCityDto visitedCityDto){
        VisitedCity visitedCity = new VisitedCity();
        BeanUtils.copyProperties(visitedCityDto, visitedCity);
        visitedCityRepository.save(visitedCity);
        return new RedirectView("/city/");
    }

}
