package com.example.springbootform.repository;

import com.example.springbootform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT u from user_table u where u.enabled=true")
    List<User> findByEnabled();
    
}
