package com.example.springbootform.initialdata;

import com.example.springbootform.model.Designation;
import com.example.springbootform.model.User;
import com.example.springbootform.repository.DesignationRepository;
import com.example.springbootform.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class Initial implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DesignationRepository designationRepository;
    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        designationRepository.deleteAll();

        User user1 = new User("Faisal", "Male", 100000);
        User user2 = new User("Fahim", "Male", 200000);
        Designation designation1 = new Designation("Manager");
        designationRepository.save(designation1);
        Designation designation2 = new Designation("Officer");
        designationRepository.save(designation2);

        user1.setDesignation(designation1);
        user2.setDesignation(designation2);
        userRepository.save(user1);
        userRepository.save(user2);
    }
}
