package com.example.exercisetracker.controller;

import java.net.URI;
import com.example.exercisetracker.model.Exercise;
import com.example.exercisetracker.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//this resets database between tests
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ExerciseControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getExercises() throws Exception {
        // setting up url and exercise objects to be used
        URI url = new URI("http://localhost:"+port+"/api/exercises");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);
        Exercise test1 = new Exercise(user,"swimming",100,1);

        ArrayList<Exercise> list = new ArrayList<Exercise>();
        list.add(test);
        list.add(test1);

        // adding users and exercises
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntityUser =  new HttpEntity<>(user, headers);
        restTemplate.postForEntity("http://localhost:"+port+"/api/users", requestEntityUser, User.class);
        HttpEntity<Exercise> requestEntity =  new HttpEntity<>(test, headers);
        HttpEntity<Exercise> requestEntity1 =  new HttpEntity<>(test1, headers);
        URI createUrl = new URI("http://localhost:"+port+"/api/exercises/user/1");
        restTemplate.postForEntity(createUrl, requestEntity, Exercise.class);
        restTemplate.postForEntity(createUrl, requestEntity1, Exercise.class);

        //testing response, returning list of exercise
        ResponseEntity<List<Exercise>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {});
        List<Exercise> responseList = response.getBody();

        Assertions.assertEquals(list.size(), responseList.size());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(test.getType(), responseList.get(0).getType());
        Assertions.assertEquals(test.getLength(), responseList.get(0).getLength());
        Assertions.assertEquals(test.getDate(), responseList.get(0).getDate());
        Assertions.assertEquals(test.getUser().getName(), responseList.get(0).getUser().getName());

        Assertions.assertEquals(test1.getType(), responseList.get(1).getType());
        Assertions.assertEquals(test1.getLength(), responseList.get(1).getLength());
        Assertions.assertEquals(test1.getDate(), responseList.get(1).getDate());
        Assertions.assertEquals(test1.getUser().getName(), responseList.get(1).getUser().getName());
    }

    @Test
    public void getExercisesWithType() throws Exception {
        // setting up url and exercise objects to be used
        URI url = new URI("http://localhost:"+port+"/api/exercises?type=running");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);

        ArrayList<Exercise> list = new ArrayList<Exercise>();
        list.add(test);

        // adding users and exercises
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntityUser =  new HttpEntity<>(user, headers);
        restTemplate.postForEntity("http://localhost:"+port+"/api/users", requestEntityUser, User.class);
        HttpEntity<Exercise> requestEntity =  new HttpEntity<>(test, headers);
        URI createUrl = new URI("http://localhost:"+port+"/api/exercises/user/1");
        restTemplate.postForEntity(createUrl, requestEntity, Exercise.class);

        //testing response, returning list of exercise
        ResponseEntity<List<Exercise>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {});
        List<Exercise> responseList = response.getBody();

        Assertions.assertEquals(list.size(), responseList.size());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(test.getType(), responseList.get(0).getType());
        Assertions.assertEquals(test.getLength(), responseList.get(0).getLength());
        Assertions.assertEquals(test.getDate(), responseList.get(0).getDate());
        Assertions.assertEquals(test.getUser().getName(), responseList.get(0).getUser().getName());
    }

    @Test
    public void getExercisesWithDate() throws Exception {
        // setting up url and exercise objects to be used
        URI url = new URI("http://localhost:"+port+"/api/exercises?date=1");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);

        ArrayList<Exercise> list = new ArrayList<Exercise>();
        list.add(test);

        // adding users and exercises
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntityUser =  new HttpEntity<>(user, headers);
        restTemplate.postForEntity("http://localhost:"+port+"/api/users", requestEntityUser, User.class);
        HttpEntity<Exercise> requestEntity =  new HttpEntity<>(test, headers);
        URI createUrl = new URI("http://localhost:"+port+"/api/exercises/user/1");
        restTemplate.postForEntity(createUrl, requestEntity, Exercise.class);

        //testing response, returning list of exercise
        ResponseEntity<List<Exercise>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {});
        List<Exercise> responseList = response.getBody();

        Assertions.assertEquals(list.size(), responseList.size());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(test.getType(), responseList.get(0).getType());
        Assertions.assertEquals(test.getLength(), responseList.get(0).getLength());
        Assertions.assertEquals(test.getDate(), responseList.get(0).getDate());
        Assertions.assertEquals(test.getUser().getName(), responseList.get(0).getUser().getName());
    }

    @Test
    public void getExercisesWithDateAndType() throws Exception {
        // setting up url and exercise objects to be used
        URI url = new URI("http://localhost:"+port+"/api/exercises?date=1&type=running");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);

        ArrayList<Exercise> list = new ArrayList<Exercise>();
        list.add(test);

        // adding users and exercises
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntityUser =  new HttpEntity<>(user, headers);
        restTemplate.postForEntity("http://localhost:"+port+"/api/users", requestEntityUser, User.class);
        HttpEntity<Exercise> requestEntity =  new HttpEntity<>(test, headers);
        URI createUrl = new URI("http://localhost:"+port+"/api/exercises/user/1");
        restTemplate.postForEntity(createUrl, requestEntity, Exercise.class);

        //testing response, returning list of exercise
        ResponseEntity<List<Exercise>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {});
        List<Exercise> responseList = response.getBody();

        Assertions.assertEquals(list.size(), responseList.size());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertEquals(test.getType(), responseList.get(0).getType());
        Assertions.assertEquals(test.getLength(), responseList.get(0).getLength());
        Assertions.assertEquals(test.getDate(), responseList.get(0).getDate());
        Assertions.assertEquals(test.getUser().getName(), responseList.get(0).getUser().getName());
    }

    @Test
    public void getExercisesWithId() throws Exception{
        //setting up url and exercise objects to be used
        URI urlAddingUsers = new URI("http://localhost:"+port+"/api/users");
        URI urlAddingExercises = new URI("http://localhost:"+port+"/api/exercises/user/1");
        URI url = new URI("http://localhost:"+port+"/api/exercises/1");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);
        //we have to set id because the default is 0 since we are only testing
        test.setId(1);
        User user1 = new User("sanford");
        Exercise test1 = new Exercise(user,"swimming",100,1);
        test1.setId(2);

        // adding Users
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity =  new HttpEntity<>(user, headers);
        HttpEntity<User> requestEntity1 =  new HttpEntity<>(user1, headers);
        restTemplate.postForEntity(urlAddingUsers, requestEntity, User.class);
        restTemplate.postForEntity(urlAddingUsers, requestEntity1, User.class);

        //adding Exercises
        HttpEntity<Exercise> requestEntityExercise =  new HttpEntity<>(test, headers);
        HttpEntity<Exercise> requestEntityExercise1 =  new HttpEntity<>(test1, headers);
        restTemplate.postForEntity(urlAddingExercises, requestEntityExercise, Exercise.class);
        restTemplate.postForEntity(urlAddingExercises, requestEntityExercise1, Exercise.class);

        //testing response, returning exercise
        ResponseEntity<Exercise> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<Exercise>() {});
        Exercise responseExercise = response.getBody();
        Assertions.assertEquals(test.getType(),responseExercise.getType());
        Assertions.assertEquals(test.getLength(),responseExercise.getLength());
        Assertions.assertEquals(test.getDate(),responseExercise.getDate());
        Assertions.assertEquals(test.getUser().getName(),responseExercise.getUser().getName());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void postExercises() throws Exception{
        //setting up url and exercise objects to be used
        URI urlAddingUsers = new URI("http://localhost:"+port+"/api/users");
        URI urlAddingExercises = new URI("http://localhost:"+port+"/api/exercises/user/1");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);
        //we have to set id because the default is 0 since we are only testing
        test.setId(1);

        // adding Users
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity =  new HttpEntity<>(user, headers);
        restTemplate.postForEntity(urlAddingUsers, requestEntity, User.class);

        //adding Exercises
        HttpEntity<Exercise> requestEntityExercise =  new HttpEntity<>(test, headers);
        ResponseEntity<Exercise> response = restTemplate.exchange(
                urlAddingExercises, HttpMethod.POST, requestEntityExercise, new ParameterizedTypeReference<Exercise>() {});

        Exercise responseExercise = response.getBody();
        Assertions.assertEquals(test.getType(),responseExercise.getType());
        Assertions.assertEquals(test.getLength(),responseExercise.getLength());
        Assertions.assertEquals(test.getDate(),responseExercise.getDate());
        Assertions.assertEquals(test.getUser().getName(),responseExercise.getUser().getName());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void putExercises() throws Exception {
        //setting up url and exercise objects to be used
        URI urlAddingUsers = new URI("http://localhost:"+port+"/api/users");
        URI urlAddingExercises = new URI("http://localhost:"+port+"/api/exercises/user/1");
        URI urlPuttingExercises = new URI("http://localhost:"+port+"/api/exercises/1");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);
        //we have to set id because the default is 0 since we are only testing
        test.setId(1);

        // adding Users
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity =  new HttpEntity<>(user, headers);
        restTemplate.postForEntity(urlAddingUsers, requestEntity, User.class);

        //adding Exercise, testing
        HttpEntity<Exercise> requestEntityExercise =  new HttpEntity<>(test, headers);
        ResponseEntity<Exercise> response = restTemplate.exchange(
                urlAddingExercises, HttpMethod.POST, requestEntityExercise, new ParameterizedTypeReference<Exercise>() {});

        Exercise responseExercise = response.getBody();
        Assertions.assertEquals(test.getType(),responseExercise.getType());
        Assertions.assertEquals(test.getLength(),responseExercise.getLength());
        Assertions.assertEquals(test.getDate(),responseExercise.getDate());
        Assertions.assertEquals(test.getUser().getName(),responseExercise.getUser().getName());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        //create new exercise
        Exercise newTest = new Exercise(user,"badminton",50,2);
        HttpEntity<Exercise> requestEntity1 =  new HttpEntity<>(newTest, headers);
        //putting new exercise
        ResponseEntity<Exercise> responsePut = restTemplate.exchange(
                urlPuttingExercises, HttpMethod.PUT, requestEntity1, new ParameterizedTypeReference<Exercise>() {});
        //testing put
        Exercise responsePutExercise = responsePut.getBody();
        Assertions.assertEquals(newTest.getType(),responsePutExercise.getType());
        Assertions.assertEquals(newTest.getLength(),responsePutExercise.getLength());
        Assertions.assertEquals(newTest.getDate(),responsePutExercise.getDate());
        Assertions.assertEquals(newTest.getUser().getName(),responsePutExercise.getUser().getName());
        Assertions.assertEquals(HttpStatus.OK, responsePut.getStatusCode());
    }

    @Test
    public void deleteExercises() throws Exception {
        //setting up url and exercise objects to be used
        URI url = new URI("http://localhost:"+port+"/api/exercises");
        URI urlAddingUsers = new URI("http://localhost:"+port+"/api/users");
        URI urlAddingExercises = new URI("http://localhost:"+port+"/api/exercises/user/1");
        URI urlDeletingExercises = new URI("http://localhost:"+port+"/api/exercises/1");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);
        //we have to set id because the default is 0 since we are only testing
        test.setId(1);

        // adding Users
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity =  new HttpEntity<>(user, headers);
        restTemplate.postForEntity(urlAddingUsers, requestEntity, User.class);

        //adding Exercise, testing
        HttpEntity<Exercise> requestEntityExercise =  new HttpEntity<>(test, headers);
        ResponseEntity<Exercise> response = restTemplate.exchange(
                urlAddingExercises, HttpMethod.POST, requestEntityExercise, new ParameterizedTypeReference<Exercise>() {});

        Exercise responseExercise = response.getBody();
        Assertions.assertEquals(test.getType(),responseExercise.getType());
        Assertions.assertEquals(test.getLength(),responseExercise.getLength());
        Assertions.assertEquals(test.getDate(),responseExercise.getDate());
        Assertions.assertEquals(test.getUser().getName(),responseExercise.getUser().getName());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        //deleting Exercise
        restTemplate.delete(urlDeletingExercises);
        //testing if deleted
        ResponseEntity<List<Exercise>> responseDelete = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Exercise>>() {});
        List<User> emptyList = Collections.emptyList();
        Assertions.assertEquals(emptyList,responseDelete.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

}

