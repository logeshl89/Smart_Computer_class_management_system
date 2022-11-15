package com.example.the_course_management_system;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Certified_Student {
    private StringProperty username;
    private StringProperty Exame_date;
    private StringProperty id;
    private StringProperty  gender;
    private  StringProperty course;
    private  StringProperty Grade;
    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public Certified_Student() {
        username=new SimpleStringProperty(this,"username");
        Exame_date = new SimpleStringProperty(this,"Exame_date");
        id=new SimpleStringProperty(this,"id");
        gender=new SimpleStringProperty(this,"gender");
        course=new SimpleStringProperty(this,"course");
        Grade=new SimpleStringProperty(this,"grade");
    }

    public String getUsername() {
        return username.get();
    }

    public StringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getExame_date() {
        return Exame_date.get();
    }

    public StringProperty exame_dateProperty() {
        return Exame_date;
    }

    public void setExame_date(String exame_date) {
        this.Exame_date.set(exame_date);
    }

    public String getGrade() {
        return Grade.get();
    }

    public StringProperty gradeProperty() {
        return Grade;
    }
    public void setGrade(String grade) {
        this.Grade.set(grade);
    }
    public String getCourse() {
        return course.get();
    }

    public StringProperty courseProperty() {
        return course;
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }
}
