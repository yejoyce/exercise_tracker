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

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//this resets database between tests
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserControllerTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/api/hello").toString(), String.class);

        Assertions.assertEquals("Hello Controller", response.getBody());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getUsers() throws Exception {
        //setting up url and exercise objects to be used
        URI url = new URI("http://localhost:"+port+"/api/users");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);
        User user1 = new User("sanford");
        Exercise test1 = new Exercise(user,"swimming",100,1);

        ArrayList<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user1);

        // adding Users
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity =  new HttpEntity<>(user, headers);
        HttpEntity<User> requestEntity1 =  new HttpEntity<>(user1, headers);
        restTemplate.postForEntity(url, requestEntity, User.class);
        restTemplate.postForEntity(url, requestEntity1, User.class);

        //testing response, returning list of users
        ResponseEntity<List<User>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> responseList = response.getBody();
        Assertions.assertEquals(list.size(), responseList.size());
        Assertions.assertEquals(user.getName(),responseList.get(0).getName());
        Assertions.assertEquals(user1.getName(),responseList.get(1).getName());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    public void getUsersWithName() throws Exception {
        //setting up url and exercise objects to be used
        URI url = new URI("http://localhost:"+port+"/api/users?name=joyce");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);

        ArrayList<User> list = new ArrayList<User>();
        list.add(user);

        // adding Users
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity =  new HttpEntity<>(user, headers);
        restTemplate.postForEntity(url, requestEntity, User.class);

        //testing response, returning list of users
        ResponseEntity<List<User>> response = restTemplate.exchange(
                url, HttpMethod.GET,null, new ParameterizedTypeReference<List<User>>() {});
        List<User> responseList = response.getBody();

        Assertions.assertEquals(list.size(), responseList.size());
        Assertions.assertEquals(user.getName(),responseList.get(0).getName());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void getUsersId() throws Exception {
        //setting up url and exercise objects to be used
        URI urlAddingUsers = new URI("http://localhost:"+port+"/api/users");
        URI url = new URI("http://localhost:"+port+"/api/users/2");
        User user = new User("joyce");
        //we have to set id because the default is 0 since we are only testing
        user.setId(1);
        Exercise test = new Exercise(user,"running",100,1);
        User user1 = new User("sanford");
        user1.setId(2);
        Exercise test1 = new Exercise(user,"swimming",100,1);

        ArrayList<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user1);

        // adding Users
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity =  new HttpEntity<>(user, headers);
        HttpEntity<User> requestEntity1 =  new HttpEntity<>(user1, headers);
        restTemplate.postForEntity(urlAddingUsers, requestEntity, User.class);
        restTemplate.postForEntity(urlAddingUsers, requestEntity1, User.class);

        //testing response, returning list of users
        ResponseEntity<User> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<User>() {});
        User responseUser = response.getBody();
        Assertions.assertEquals(user1.getName(),responseUser.getName());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void addUser() throws Exception {
        //setting up url and exercise objects to be used
        URI url = new URI("http://localhost:"+port+"/api/users");
        User user = new User("joyce");
        Exercise test = new Exercise(user,"running",100,1);


        // adding Users
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity =  new HttpEntity<>(user, headers);
        ResponseEntity<User> response = restTemplate.postForEntity(url, requestEntity, User.class);

        //testing response
        User responseUser = response.getBody();
        Assertions.assertEquals(user.getName(),responseUser.getName());
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void putUser() throws Exception{
        //setting up url and exercise objects to be used
        URI url = new URI("http://localhost:"+port+"/api/users");
        User user = new User("joyce");
        user.setId(1);
        User user1 = new User("sanford");
        user.setId(2);

        ArrayList<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user1);

        // adding Users
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity =  new HttpEntity<>(user, headers);
        HttpEntity<User> requestEntity1 =  new HttpEntity<>(user1, headers);
        restTemplate.postForEntity(url, requestEntity, User.class);
        restTemplate.postForEntity(url, requestEntity1, User.class);

        //testing response, returning list of users
        ResponseEntity<List<User>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> responseList = response.getBody();
        Assertions.assertEquals(list.size(), responseList.size());
        Assertions.assertEquals(user.getName(),responseList.get(0).getName());
        Assertions.assertEquals(user1.getName(),responseList.get(1).getName());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        //putting new information in
        URI urlPut = new URI("http://localhost:"+port+"/api/users/1");
        User user2 = new User("bob");
        HttpEntity<User> requestEntity2 =  new HttpEntity<>(user2, headers);
        ResponseEntity<User> response1 = restTemplate.exchange(
                urlPut, HttpMethod.PUT, requestEntity2, new ParameterizedTypeReference<User>() {});

        //testing put response
        User responsePut = response1.getBody();

        Assertions.assertEquals(user2.getName(),responsePut.getName());
        Assertions.assertEquals(HttpStatus.OK, response1.getStatusCode());
    }

    @Test
    public void deleteUser() throws Exception{
        //setting up url and exercise objects to be used
        URI url = new URI("http://localhost:"+port+"/api/users");
        User user = new User("joyce");
        user.setId(1);

        // adding Users
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestEntity =  new HttpEntity<>(user, headers);
        restTemplate.postForEntity(url, requestEntity, User.class);

        //testing response, returning list of users
        ResponseEntity<List<User>> response = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> responseList = response.getBody();
        Assertions.assertEquals(user.getName(),responseList.get(0).getName());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        //deleting user
        URI urlDelete = new URI("http://localhost:"+port+"/api/users/1");
        restTemplate.delete(urlDelete);

        //testing delete response

        ResponseEntity<List<User>> responseDelete = restTemplate.exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> emptyList = Collections.emptyList();
        Assertions.assertEquals(emptyList,responseDelete.getBody());
        Assertions.assertEquals(HttpStatus.OK, responseDelete.getStatusCode());
    }

}
