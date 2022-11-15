package com.example.the_course_management_system;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class AllTheUserTOTheTable {
    private StringProperty username = null;
    private StringProperty id = null;
    private StringProperty CoursePrice=null;
    private  StringProperty Father_name=null;
    private StringProperty CoursePricePaid=null;
    private StringProperty gender = null;
    private StringProperty course=null;
    private StringProperty MobileNumber=null;

    public String getFather_name() {
        return Father_name.get();
    }

    public StringProperty father_nameProperty() {
        return Father_name;
    }

    public void setFather_name(String father_name) {
        this.Father_name.set(father_name);
    }

    public AllTheUserTOTheTable() {

        username=new SimpleStringProperty(this,"username");
        id = new SimpleStringProperty(this,"id");
        CoursePrice=new SimpleStringProperty(this,"CoursePrice");
        CoursePricePaid=new SimpleStringProperty(this,"CoursePricePaid");
        gender =new SimpleStringProperty(this,"gender");
        course=new SimpleStringProperty(this,"course");
        MobileNumber=new SimpleStringProperty(this,"MobileNumber");
        Father_name=new SimpleStringProperty(this,"Father_name");
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

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getCoursePrice() {
        return CoursePrice.get();
    }

    public StringProperty coursePriceProperty() {
        return CoursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.CoursePrice.set(coursePrice);
    }

    public String getCoursePricePaid() {
        return CoursePricePaid.get();
    }

    public StringProperty coursePricePaidProperty() {
        return CoursePricePaid;
    }

    public void setCoursePricePaid(String coursePricePaid) {
        this.CoursePricePaid.set(coursePricePaid);
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

    public String getCourse() {
        return course.get();
    }

    public StringProperty courseProperty() {
        return course;
    }

    public void setCourse(String course) {
        this.course.set(course);
    }

    public String getMobileNumber() {
        return MobileNumber.get();
    }

    public StringProperty mobileNumberProperty() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.MobileNumber.set(mobileNumber);
    }
}
