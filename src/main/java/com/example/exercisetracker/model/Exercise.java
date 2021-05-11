package com.example.exercisetracker.model;
import com.example.exercisetracker.model.User;
import javax.persistence.*;

@Entity
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "length")
    private long length;

    @Column(name = "date")
    private long date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public long getId(){
        return id;
    }
    public String getType(){
        return type;
    }
    public long getLength(){
        return length;
    }
    public long getDate() {
        return date;
    }
    public User getUser() {
        return user;
    }

    public void setType(String type){
        this.type = type;
    }
    public void setLength(long length){
        this.length = length;
    }
    public void setDate(long date){
        this.date = date;
    }
    public void setUser(User user){
        this.user = user;
    }

    public Exercise () {
    }

    public Exercise (User user, String type, long length, long date) {
        this.user = user;
        this.type = type;
        this.length = length;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Exercise [id=" + id + ", type=" + type + ", length=" + length + ", date=" + date + ", user_id=" + user + "]";
    }
}
