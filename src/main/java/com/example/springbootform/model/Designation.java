package com.example.springbootform.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name ="designation_table")
@Data @NoArgsConstructor
public class Designation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long designationId;

    @Column
     private String designationName;

    public Designation(String designationName) {
        this.designationName = designationName;
    }
}
