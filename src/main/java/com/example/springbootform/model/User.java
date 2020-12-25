package com.example.springbootform.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name ="user_table")
@Data @NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String gender;

    @Column
    private int salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="designation_id")
    private Designation designation;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_city",
            joinColumns = @JoinColumn(name = "city_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<VisitedCity> visitedCityList;

    @Column
    private Boolean enabled=true;

    public User(String name, String gender, int salary) {
        this.name = name;
        this.gender = gender;
        this.salary = salary;
    }
}
