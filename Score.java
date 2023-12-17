/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author docker
 */
public class Score {
    
    private String courseId;
    private String type;
    private Double score ;
    
    public Score() {}

    public Score(final String courseId, final String type, final Double score) {
        this.courseId = courseId;
        this.type = type;
        this.score = score;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(final String courseId) {
        this.courseId = courseId;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(final Double score) {
        this.score = score;
    }
}
