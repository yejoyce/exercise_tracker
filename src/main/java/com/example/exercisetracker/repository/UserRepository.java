package com.example.exercisetracker.repository;

import java.util.List;
import com.example.exercisetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);
}
