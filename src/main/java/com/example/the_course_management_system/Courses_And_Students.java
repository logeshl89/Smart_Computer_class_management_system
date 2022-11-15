package com.example.the_course_management_system;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Courses_And_Students {
    private StringProperty course;
    private StringProperty StudentsCount;

    public Courses_And_Students() {
        course=new SimpleStringProperty(this,"course");
        StudentsCount = new SimpleStringProperty(this,"StudentsCount");
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

    public String getStudenstsCount() {
        return StudentsCount.get();
    }

    public StringProperty studenstsCountProperty() {
        return StudentsCount;
    }

    public void setStudenstsCount(String studenstsCount) {
        this.StudentsCount.set(studenstsCount);
    }
}
