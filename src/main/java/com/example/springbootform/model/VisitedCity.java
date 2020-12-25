package com.example.springbootform.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "city_table")
@Data @NoArgsConstructor
public class VisitedCity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cityId;
    @Column
    private String cityName;

    public VisitedCity(String cityName) {
        this.cityName = cityName;
    }
}
