package com.example.exercisetracker.repository;

import java.util.Date;
import java.util.List;
import com.example.exercisetracker.model.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByType(String type);
    List<Exercise> findByDate(long date);
    List<Exercise> findByTypeAndDate(String type, long date);
}
