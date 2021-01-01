package com.example.springbootform.initialdata;

import com.example.springbootform.model.Designation;
import com.example.springbootform.model.User;
import com.example.springbootform.model.VisitedCity;
import com.example.springbootform.repository.DesignationRepository;
import com.example.springbootform.repository.UserRepository;
import com.example.springbootform.repository.VisitedCityRepository;
import org.springframework.boot.CommandLineRunner;

import java.util.ArrayList;
import java.util.List;

public class Initial implements CommandLineRunner {

    private final  UserRepository userRepository;
    private final VisitedCityRepository visitedCityRepository;
    private final  DesignationRepository designationRepository;

    public Initial(UserRepository userRepository, VisitedCityRepository visitedCityRepository, DesignationRepository designationRepository) {
        this.userRepository = userRepository;
        this.visitedCityRepository = visitedCityRepository;
        this.designationRepository = designationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        designationRepository.deleteAll();
        visitedCityRepository.deleteAll();

        User user1 = new User("Faisal", "Male", 100000);
        User user2 = new User("Fahim", "Male", 200000);

        Designation designation1 = new Designation("Manager");
        designationRepository.save(designation1);
        Designation designation2 = new Designation("Officer");
        designationRepository.save(designation2);

        VisitedCity v1 = new VisitedCity("Dhaka"); VisitedCity v2 = new VisitedCity("Ctg");
        VisitedCity v3 = new VisitedCity("Sylhet"); VisitedCity v4 = new VisitedCity("Cumilla");
        VisitedCity v5 = new VisitedCity("Barishal"); VisitedCity v6 = new VisitedCity("CoxBazar");
        visitedCityRepository.save(v1);visitedCityRepository.save(v2);visitedCityRepository.save(v3);visitedCityRepository.save(v4);
        visitedCityRepository.save(v5);visitedCityRepository.save(v6);

        List<VisitedCity> cityList1 = new ArrayList<>();
        cityList1.add(v1); cityList1.add(v2);
        List<VisitedCity> cityList2 = new ArrayList<>();
        cityList2.add(v3); cityList2.add(v4);

        user1.setVisitedCityList(cityList1);
        user2.setVisitedCityList(cityList2);

        user1.setDesignation(designation1); userRepository.save(user1);
        user2.setDesignation(designation2); userRepository.save(user2);
    }
}
