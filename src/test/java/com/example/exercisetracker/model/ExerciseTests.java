package com.example.exercisetracker.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExerciseTests {

    @Test
    void testCreateExercise() throws Exception {
        User user = new User("joyce");
        String type = "badminton";
        long length = 1;
        long date = 101;
        Exercise exercise = new Exercise(user, type, length, date);

        Assertions.assertEquals(user, exercise.getUser());
        Assertions.assertEquals(type, exercise.getType());
        Assertions.assertEquals(length, exercise.getLength());
        Assertions.assertEquals(date, exercise.getDate());
    }

}
