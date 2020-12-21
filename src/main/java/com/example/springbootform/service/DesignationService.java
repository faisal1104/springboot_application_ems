package com.example.springbootform.service;

import com.example.springbootform.dto.DesignationDto;
import com.example.springbootform.exception.ResourceNotFoundException;
import com.example.springbootform.model.Designation;
import com.example.springbootform.model.User;
import com.example.springbootform.repository.DesignationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesignationService {
    private final DesignationRepository designationRepository;
    public DesignationService(DesignationRepository designationRepository) {
        this.designationRepository = designationRepository;
    }

    public List<DesignationDto> getAll() {
        List<Designation> designationList = designationRepository.findAll();
        List<DesignationDto> designationDtolist = new ArrayList<>();
        for (Designation d : designationList) {
            DesignationDto designationDtoTemp = new DesignationDto();
            BeanUtils.copyProperties(d, designationDtoTemp);
            designationDtolist.add(designationDtoTemp);
        }
        return designationDtolist;
    }


    public void save(DesignationDto designationDto) {
        Optional<Designation> designationOptional = designationRepository.findById(designationDto.getDesignationId());
        Designation designation = designationOptional.orElseGet(Designation::new);
        BeanUtils.copyProperties(designationDto, designation);
        designationRepository.save(designation);
    }

    public DesignationDto getOne(Long id1) {
        Optional<Designation> optionalDesignation = designationRepository.findById(id1);
        if (optionalDesignation.isEmpty()) {
            throw new ResourceNotFoundException(" Designatioin not found with the id :"+id1+" to update");
        }
        Designation designation = optionalDesignation.get();
        DesignationDto designationDto = new DesignationDto();
        BeanUtils.copyProperties(designation, designationDto);
        return designationDto;
    }

    public void delete(Long id) {
        Optional<Designation> designationOptional=  designationRepository.findById(id);
        if (designationOptional.isEmpty()){
            throw new ResourceNotFoundException(" Designatioin not found with the id :"+id+" to delete");
        }
        designationRepository.deleteById(id);
    }
}
