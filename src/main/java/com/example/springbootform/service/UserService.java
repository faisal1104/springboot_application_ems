package com.example.springbootform.service;

import com.example.springbootform.dto.DesignationDto;
import com.example.springbootform.dto.UserDto;
import com.example.springbootform.dto.VisitedCityDto;
import com.example.springbootform.exception.ResourceNotFoundException;
import com.example.springbootform.model.Designation;
import com.example.springbootform.model.User;
import com.example.springbootform.model.VisitedCity;
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
        List<User> userList = userRepository.findByEnabled();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User u: userList) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(u,userDto);

            Designation d = u.getDesignation();
            DesignationDto designationDto = new DesignationDto();
            BeanUtils.copyProperties(d,designationDto);
            userDto.setDesignationDto(designationDto);

            List<VisitedCity> visitedCityList = u.getVisitedCityList();
            List<VisitedCityDto> visitedCityDtos = new ArrayList<>();
            for (VisitedCity v: visitedCityList) {
                VisitedCityDto visitedCityDtoTemp = new VisitedCityDto();
                BeanUtils.copyProperties(v, visitedCityDtoTemp);
                visitedCityDtos.add(visitedCityDtoTemp);
            }
            userDto.setVisitedCityDtoList(visitedCityDtos);
            userDtoList.add(userDto);
          }
        return userDtoList;
       }

    public void saveUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(userDto.getId());
        User user = userOptional.orElseGet(User::new);
        BeanUtils.copyProperties(userDto,user);

        Designation d2 = new Designation();
        BeanUtils.copyProperties(userDto.getDesignationDto(),d2);
        user.setDesignation(d2);

        List<VisitedCity> visitedCitiesTemp= new ArrayList<>();
        for (VisitedCityDto cityDtoTemp: userDto.getVisitedCityDtoList()) {
           VisitedCity cityTemp = new VisitedCity();
           BeanUtils.copyProperties(cityDtoTemp, cityTemp);
           visitedCitiesTemp.add(cityTemp);
        }
        user.setVisitedCityList(visitedCitiesTemp);
        userRepository.save(user);
    }

    public UserDto getById(Long id) {
        Optional<User> userOpt= userRepository.findById(id);
        if (userOpt.isEmpty()){
            throw new ResourceNotFoundException(" User not found with thes id :"+id+" to update");
        }
        User user=userOpt.get();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        DesignationDto designationDto = new DesignationDto();
        BeanUtils.copyProperties(user.getDesignation(), designationDto);
        userDto.setDesignationDto(designationDto);
        return userDto;
    }

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
