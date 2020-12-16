package com.example.springbootform.controller;

import com.example.springbootform.dto.DesignationDto;
import com.example.springbootform.service.DesignationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/designation")
public class DesignationController {

    private final DesignationService designationService;

    public DesignationController(DesignationService designationService) {
        this.designationService = designationService;
    }

    @RequestMapping("/")
    public String getAll(Model m) {
        m.addAttribute("allD", designationService.getAll());
        return "designation/viewDes";
    }

    @GetMapping("/add")
    public String get(Model m) {
        m.addAttribute("des", new DesignationDto());
        return "designation/addDes";
    }

    @PostMapping("/save")
   public  String save(@ModelAttribute("des") DesignationDto designationDto) {
        designationService.save(designationDto);
        return "redirect:/designation/";
    }

    @GetMapping("/update/{id}")
    String updateDesignation(@PathVariable("id") Long id1, Model model) {
        model.addAttribute("des", designationService.getOne(id1));
        return "designation/addDes";
    }


    @GetMapping("/delete/{id}")
    String deleteDesignation(@PathVariable("id") Long id) {
        designationService.delette(id);
        return "redirect:/designation/";
    }

}
