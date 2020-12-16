package com.example.springbootform.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
@Data
@NoArgsConstructor
public class DesignationDto implements Serializable {

    private long designationId;
    private String designationName;
}
