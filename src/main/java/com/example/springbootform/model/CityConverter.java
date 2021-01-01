package com.example.springbootform.model;

import com.example.springbootform.dto.VisitedCityDto;
import com.example.springbootform.repository.VisitedCityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityConverter implements Converter<String, VisitedCityDto> {

    @Autowired
    private VisitedCityRepository visitedCityRepository;
    @Override
    public VisitedCityDto convert(String id) {

        System.out.println("Converting");

        List<VisitedCity> visitedCityList = visitedCityRepository.findAll();
        List<VisitedCityDto> visitedCityDtos = new ArrayList<>();
        for (VisitedCity v: visitedCityList) {
            VisitedCityDto visitedCityDtoTemp = new VisitedCityDto();
            BeanUtils.copyProperties(v, visitedCityDtoTemp);
            visitedCityDtos.add(visitedCityDtoTemp);
        }

        int indexTemp = Integer.parseInt(id);
        return visitedCityDtos.get(indexTemp-1);
    }
}
