package com.example.the_course_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Certified implements Initializable {
    private static final String Password_name = "root";
    @FXML
    public TableColumn<Certified_Student, String> Certification_Course;
    @FXML
    public TableColumn<Certified_Student, String> Certification_Gender;
    @FXML
    public TableColumn<Certified_Student, String> Certification_grade;
    String url_Mysql = new String("jdbc:mysql://localhost:3306/Tution_Management_System");
    Connection connection;
    PreparedStatement preparedStatement;
    @FXML
    private TableColumn<Certified_Student, String> Certification_Date;
    @FXML
    private TableColumn<Certified_Student, String> Certification_ID;

    @FXML
    private TableColumn<Certified_Student, String> Certification_Name;
    @FXML
    private TableView<Certified_Student> Certification_Table;
    @FXML
    void Table(ResultSet resultSet) throws SQLException {
        ObservableList<Certified_Student> Certification_Table_o = FXCollections.observableArrayList();
        while (resultSet.next()) {
            Certified_Student  all_students_table = new Certified_Student();
            all_students_table.setId(resultSet.getString("id"));
            all_students_table.setUsername(resultSet.getString("username"));
            all_students_table.setExame_date(resultSet.getString("Exam_date"));
            all_students_table.setGender(resultSet.getString("gender"));
            all_students_table.setGrade(resultSet.getString("grade"));
            all_students_table.setCourse(resultSet.getString("courses"));
            Certification_Table_o.add(all_students_table);
        }
        Certification_Table.setItems(Certification_Table_o);
        Certification_ID.setCellValueFactory(f -> f.getValue().idProperty());
        Certification_Name.setCellValueFactory(f -> f.getValue().usernameProperty());
        Certification_Date.setCellValueFactory(f-> f.getValue().exame_dateProperty());
        Certification_Course.setCellValueFactory(f->f.getValue().courseProperty());
        Certification_Gender.setCellValueFactory(f->f.getValue().genderProperty());
        Certification_grade.setCellValueFactory(f->f.getValue().gradeProperty());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection= DriverManager.getConnection(url_Mysql,Password_name,Password_name);
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from Certified;");
            Table(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
