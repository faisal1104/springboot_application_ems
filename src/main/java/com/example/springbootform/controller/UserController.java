package com.example.springbootform.controller;


import com.example.springbootform.dto.UserDto;
import com.example.springbootform.service.DesignationService;
import com.example.springbootform.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final DesignationService designationService;

    public UserController(UserService userService, DesignationService designationService) {
        this.userService = userService;
        this.designationService = designationService;
    }

    @RequestMapping("/")
        public String s(Model m)
    {
        List<UserDto> userDtoList = userService.getAllEnabled();
        m.addAttribute("allUser", userDtoList);
        return "user/view";
    }

        @GetMapping("/add")
        public String add1(Model m){
        m.addAttribute("user", new UserDto());
        m.addAttribute("designationDtolist",designationService.getAll());
        m.addAttribute("genders", this.getGenderList());

        return "user/add";
        }

    @PostMapping("/save")
      public RedirectView add(@ModelAttribute("user") UserDto userDto){
             userService.saveUser(userDto);
            return new RedirectView("/user/");
        }

    @GetMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, Model model){
        model.addAttribute("user",userService.getById(id));
        model.addAttribute("designationDtolist",designationService.getAll());
        model.addAttribute("genders",this.getGenderList());

        return "user/add";
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") Long id1){
        userService.delete(id1);
        return new RedirectView("/user/");
    }

    private List<String> getGenderList() {
        List<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        return genderList;
    }
}
