package com.example.springbootform.controller;

import com.example.springbootform.dto.DesignationDto;
import com.example.springbootform.model.Designation;
import com.example.springbootform.model.User;
import com.example.springbootform.repository.DesignationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/designation")
public class DesignationController {
    @Autowired
    DesignationRepository repo;

    @RequestMapping("/")
    public String getAll(Model m) {
        List<Designation> designationList1 = repo.findAll();

        m.addAttribute("allD", designationList1);
        return "designation/viewDes";
    }

    @GetMapping("/add")
    public String get(Model m) {
        m.addAttribute("des", new DesignationDto());
        return "designation/addDes";
    }

    @PostMapping("/save")
   public  String save(@ModelAttribute("des") DesignationDto designationDto) {

        Optional<Designation> designationOptional = repo.findById(designationDto.getDesignationId());
        Designation designation = null;
        if(designationOptional.isPresent()){
            designation =designationOptional.get();
        } else{
            designation = new Designation();
        }
       // Designation designation = new Designation();
        BeanUtils.copyProperties(designationDto, designation);
        repo.save(designation);

        return "redirect:/designation/";
    }

    @GetMapping("/update/{id}")
    String updateDesignation(@PathVariable("id") Long id1, Model model) {
        Optional<Designation> optionalDesignation = repo.findById(id1);
        if (optionalDesignation.isEmpty()) {
            throw new RuntimeException("Designation not Found By this id");
        }
        Designation designation = optionalDesignation.get();
        DesignationDto designationDto = new DesignationDto();
        BeanUtils.copyProperties(designation, designationDto);
        model.addAttribute("des", designationDto);

        return "designation/addDes";
    }


    @GetMapping("/delete/{id}")
    String deleteDesignation(@PathVariable("id") Long id) {
        repo.deleteById(id);
        return "redirect:/designation/";
    }

}
