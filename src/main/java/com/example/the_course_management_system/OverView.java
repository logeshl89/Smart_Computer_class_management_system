package com.example.the_course_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

import java.sql.*;
public class OverView implements Initializable {
    @FXML
    private TableView<Courses_And_Students> CourseAddedTable;
    @FXML
    private TableColumn<Courses_And_Students, String> NumberofStudents;
    @FXML
    private TableColumn<Courses_And_Students, String> CoursesAdder;
    @FXML
    private PieChart Courses;
    @FXML
    private PieChart Genders;
    @FXML
    private Label MaleCount;

    @FXML
    private Label OthersCount;
    @FXML
    private Label FemaleCount;

    Connection connection;
    private static final String Password_name = "root";
    String url_Mysql = new String("jdbc:mysql://localhost:3306/Tution_Management_System");
    Alert alert;
    Statement statement;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<PieChart.Data> DemoPieChart= FXCollections.observableArrayList(
        );ObservableList<PieChart.Data> Gender= FXCollections.observableArrayList(
        );
        ObservableList<Courses_And_Students> CourseAndCountOfStudents = FXCollections.observableArrayList();
        Courses.setData(DemoPieChart);
        Genders.setData(Gender);
        ResultSet genderResultSet;
        try {
            connection=DriverManager.getConnection(url_Mysql,Password_name,Password_name);
            statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select courses,count(id) from course_user group by courses;");
            while(resultSet.next())
            {
                Courses_And_Students courses_and_students=new Courses_And_Students();
                courses_and_students.setCourse(resultSet.getString(1));
                courses_and_students.setStudenstsCount(resultSet.getString(2));
                DemoPieChart.add(new PieChart.Data(resultSet.getString(1),Integer.parseInt(resultSet.getString(2))));
                CourseAndCountOfStudents.add(courses_and_students);
            }
            CourseAddedTable.setItems(CourseAndCountOfStudents);
            CoursesAdder.setCellValueFactory(e->e.getValue().courseProperty());
            NumberofStudents.setCellValueFactory(e->e.getValue().studenstsCountProperty());
            statement=connection.createStatement();
            genderResultSet=statement.executeQuery("select count(id) from course_user where gender=\"male\";");
            genderResultSet.next();
            String male = String.valueOf(genderResultSet.getString(1));
            System.out.println(male);
            Gender.add(new PieChart.Data("Male",Integer.parseInt(male)));
            MaleCount.setText(male);
            statement=connection.createStatement();
            genderResultSet=statement.executeQuery("select count(id) from course_user where gender=\"female\";");
            genderResultSet.next();
            String Female = String.valueOf(genderResultSet.getString(1));
            System.out.println(Female);
            FemaleCount.setText(Female);
            Gender.add(new PieChart.Data("Female",Integer.parseInt(Female)));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}