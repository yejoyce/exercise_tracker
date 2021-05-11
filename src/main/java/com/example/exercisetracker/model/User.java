package com.example.exercisetracker.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    public long getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public User () {
    }

    public User (String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "user")
    private List<Exercise> exercises;

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name +  "]";
    }
}

