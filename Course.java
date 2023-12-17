/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author docker
 */
import org.bson.types.ObjectId ;


public class Course {
    
    private ObjectId mongoId;
    private String id;
    private String name;
    private String teacher;
    
    public Course() {}

    public Course(final String id, final String name, final String teacher) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
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

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(final String teacher) {
        this.teacher = teacher;
    }
    
}