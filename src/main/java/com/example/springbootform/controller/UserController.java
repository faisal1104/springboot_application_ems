package com.example.springbootform.controller;

import com.example.springbootform.dto.DesignationDto;
import com.example.springbootform.dto.UserDto;
import com.example.springbootform.model.Designation;
import com.example.springbootform.model.User;
import com.example.springbootform.repository.DesignationRepository;
import com.example.springbootform.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DesignationRepository designationRepository;

    @RequestMapping("/")
        public String s(Model m)
    {
        List<User> userList = userRepository.findByEnabled();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User u: userList) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(u,userDto);
            if(u.getDesignation()!= null){
                userDto.setDesignationName(u.getDesignation().getDesignationName());
            }
            userDtoList.add(userDto);
        }
        m.addAttribute("allUser", userDtoList);
        return "user/view";
        }

        @GetMapping("/add")
        public String add1(Model m){
        m.addAttribute("em", new UserDto());

        List<Designation> designationList =  designationRepository.findAll();
        List<DesignationDto> designationDtolist = new ArrayList<>();
            for (Designation d: designationList) {
                DesignationDto designationDtoTemp = new DesignationDto();
                BeanUtils.copyProperties(d,designationDtoTemp);
                designationDtolist.add(designationDtoTemp);
            }
            m.addAttribute("designationDtolist",designationDtolist);

        List<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        m.addAttribute("genders", genderList);

        return "user/add";
        }

        @PostMapping("/save")
      public RedirectView add(@ModelAttribute("em") UserDto userDto){

            Optional<User> userOptional =userRepository.findById(userDto.getId());
            User user = null;
            if(userOptional.isPresent()){
                user=userOptional.get();
            }else {
                user = new User();
            }
            //User user = new User();
             BeanUtils.copyProperties(userDto,user);
             user.setDesignation(designationRepository.getOne(userDto.getDesignationId()));
             userRepository.save(user);

            return new RedirectView("/user/");
        }

    @GetMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, Model model){

        Optional<User> userOpt=  userRepository.findById(id);
        if (userOpt.isEmpty()){
            throw new RuntimeException("User not found with this id");
        }
        User user=userOpt.get();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        model.addAttribute("em",userDto);

        List<Designation> designationList =  designationRepository.findAll();
        List<DesignationDto> designationDtolist = new ArrayList<>();
        for (Designation d: designationList) {
            DesignationDto designationDtoTemp = new DesignationDto();
            BeanUtils.copyProperties(d,designationDtoTemp);
            designationDtolist.add(designationDtoTemp);
        }
        model.addAttribute("designationDtolist",designationDtolist);

        List<String> genderList=new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        model.addAttribute("genders",genderList);

        return "user/add";
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable("id") Long id1){
        Optional<User> userOpt=  userRepository.findById(id1);
        if (userOpt.isEmpty()){
            throw new RuntimeException("User not found with this id");
        }
        User user=userOpt.get();
        user.setEnabled(false);

        userRepository.save(user);

        return new RedirectView("/user/");
    }
}
