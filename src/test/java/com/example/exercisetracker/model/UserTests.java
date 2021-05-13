package com.example.exercisetracker.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTests {

    @Test
    void createUserTest() throws Exception{
        String name = "joyce";
        User joyce = new User(name);
        Assertions.assertEquals(name,joyce.getName());
    }
}
