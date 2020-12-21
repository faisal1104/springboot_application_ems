package com.example.springbootform.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private long id;
    private String name;
    private String gender;
    private DesignationDto designationDto;
    private int salary;
}
