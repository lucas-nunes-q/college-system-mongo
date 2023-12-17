/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author docker
 */
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

public class Student {

    private ObjectId mongoId;
    private String id;
    private String name;
    private List<String> courses;
    private List<Score> scores;
    
    public Student () {
        this.courses = new ArrayList<>();
        this.scores = new ArrayList<>();
    }
    
    public Student(final String id, final String name) {
        this.id = id;
        this.name = name;
        this.courses = new ArrayList<>();
        this.scores = new ArrayList<>();
    }

    public ObjectId getMongoId() {
        return mongoId;
    }

    public void setMongoId(final ObjectId mongoId) {
        this.mongoId = mongoId;
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(final List<String> classes) {
        this.courses = classes;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(final List<Score> scores) {
        this.scores = scores;
    }
    
}