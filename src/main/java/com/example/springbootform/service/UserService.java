package com.example.springbootform.service;

import com.example.springbootform.dto.DesignationDto;
import com.example.springbootform.dto.UserDto;
import com.example.springbootform.exception.ResourceNotFoundException;
import com.example.springbootform.model.User;
import com.example.springbootform.repository.DesignationRepository;
import com.example.springbootform.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final DesignationRepository designationRepository;

    public UserService(UserRepository userRepository, DesignationRepository designationRepository) {
        this.userRepository = userRepository;
        this.designationRepository = designationRepository;
    }

    public List<UserDto> getAllEnabled() {
        List<User> userList = new ArrayList<>();
        userList= userRepository.findByEnabled();

        List<UserDto> userDtoList = new ArrayList<>();
        for (User u: userList) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(u,userDto);
            if(u.getDesignation()!= null){
                userDto.setDesignationName(u.getDesignation().getDesignationName());
            }
            userDtoList.add(userDto);
          }
        return userDtoList;
       }


    public void saveUser(UserDto userDto) {

        Optional<User> userOptional = userRepository.findById(userDto.getId());
        User user = null;
        if(userOptional.isPresent()){
            user=userOptional.get();
        }else {
            user = new User();
        }
        BeanUtils.copyProperties(userDto,user);
        user.setDesignation(designationRepository.getOne(userDto.getDesignationId()));
        userRepository.save(user);
    }

    public UserDto getById(Long id) {
       // Optional<User> userOpt= Optional.ofNullable(userRepository.getOne(id));
        Optional<User> userOpt= userRepository.findById(id);
        if (userOpt.isEmpty()){
            throw new ResourceNotFoundException(" User not found with thes id :"+id+" to update");
        }
        User user=userOpt.get();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        DesignationDto designationDto = new DesignationDto();
        BeanUtils.copyProperties(user.getDesignation(), designationDto);
        userDto.setDesignationId(designationDto.getDesignationId());
        userDto.setDesignationName(designationDto.getDesignationName());
        return userDto;
    }
//    DesignationDto designationDto = new DesignationDto();
//        BeanUtils.copyProperties(employee.getDesignation(), designationDto);
//        employeeDto.setDesignationDto(designationDto);
//        model.addAttribute("employeeDto", employeeDto);

    public void delete(Long id1) {
        Optional<User> userOpt=  userRepository.findById(id1);
        if (userOpt.isEmpty()){
            throw new ResourceNotFoundException("  User not found with the id :"+id1+" to delete");
        }
        User user=userOpt.get();
        user.setEnabled(false);
        userRepository.save(user);
        System.out.println(user);
    }
}
