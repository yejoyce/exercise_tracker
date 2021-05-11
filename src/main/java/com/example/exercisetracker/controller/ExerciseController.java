package com.example.exercisetracker.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.exercisetracker.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.exercisetracker.model.Exercise;
import com.example.exercisetracker.repository.ExerciseRepository;
import com.example.exercisetracker.model.User;
import com.example.exercisetracker.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ExerciseController {

    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/exercises")
    public ResponseEntity<List<Exercise>> getAllExercises(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Integer date) {
        try {
            List<Exercise> exercises = new ArrayList<Exercise>();

            if (type == null && date == null) {
                exerciseRepository.findAll().forEach(exercises::add);
            }
            else if (type != null && date == null) {
                exerciseRepository.findByType(type).forEach(exercises::add);
            }
            else if (type == null && date != null) {
                exerciseRepository.findByDate(date.longValue()).forEach(exercises::add);
            }
            else { // contains both type & date
                exerciseRepository.findByTypeAndDate(type, date.longValue()).forEach(exercises::add);
            }

            return new ResponseEntity<>(exercises, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/exercises/{id}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable("id") long id) {
        Optional<Exercise> exerciseData = exerciseRepository.findById(id);

        if (exerciseData.isPresent()) {
            return new ResponseEntity<>(exerciseData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/exercises/user/{user_id}")
    public ResponseEntity<Exercise> createExercise(@PathVariable("user_id") long userId, @RequestBody Exercise exercise) {
        try {
            Optional<User> _user = userRepository.findById(userId);
            // Use this to test- copy paste this if on windows: {"user_id":1,"type":"exception","length":100,"date":1}
            Exercise _exercise = exerciseRepository
                    .save(new Exercise(_user.get(), exercise.getType(), exercise.getLength(), exercise.getDate()));
            return new ResponseEntity<>(_exercise, HttpStatus.CREATED);
        } catch (Exception e) {
            //e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/exercises/{id}")
    public ResponseEntity<Exercise> updateExercise(@PathVariable("id") long id, @RequestBody Exercise exercise) {
        Optional<Exercise> exerciseData = exerciseRepository.findById(id);

        if (exerciseData.isPresent()) {
            Exercise _exercise = exerciseData.get();
            _exercise.setType(exercise.getType());
            _exercise.setLength(exercise.getLength());
            _exercise.setDate(exercise.getDate());
            return new ResponseEntity<>(exerciseRepository.save(_exercise), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/exercises/{id}")
    public ResponseEntity<HttpStatus> deleteExercise(@PathVariable("id") long id) {
        try {
            exerciseRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

