package com.example.the_course_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Person implements Initializable {
    @FXML
    private TableColumn<AllTheUserTOTheTable, String> All_Students_Table_Mobile_Number;

    @FXML
    private TableColumn<AllTheUserTOTheTable, String> All_Students_Table_Username;
    @FXML
    private Button filterButton;
    private static final String Password_name = "root";
    @FXML
    private ComboBox<String> Gender,Courses;
    String url_Mysql = new String("jdbc:mysql://localhost:3306/Tution_Management_System");
    @FXML
    private TableColumn<AllTheUserTOTheTable, String> All_Students_Table_id;
    PreparedStatement preparedStatement;
    @FXML
    private Button Reload;
    @FXML
    private TableView<AllTheUserTOTheTable> All_Students_Table;
    @FXML
    private TableColumn<AllTheUserTOTheTable, String> All_Students_Table_Course;
    @FXML
    private TableColumn<AllTheUserTOTheTable, String> All_Students_Table_Gender;
    Connection connection;
    @FXML
    private TableColumn<AllTheUserTOTheTable, String> All_Students_Table_paid;
    @FXML
    private TableColumn<AllTheUserTOTheTable, String> All_Students_Table_Father;
    @FXML
    private TableColumn<AllTheUserTOTheTable, String> All_Students_Table_CoursePrice;
    @FXML
    public void Table(ResultSet resultSet) throws Exception {
        ObservableList<AllTheUserTOTheTable> Main_Table = FXCollections.observableArrayList();
        //Statement statement = connection.createStatement();
        //ResultSet resultSet = statement.executeQuery(Q.toString());
        while (resultSet.next()) {
            AllTheUserTOTheTable all_students_table = new AllTheUserTOTheTable();
            all_students_table.setId(resultSet.getString("id"));
            all_students_table.setUsername(resultSet.getString("user_name"));
            all_students_table.setCoursePrice(resultSet.getString("Course_Prise"));
            all_students_table.setCoursePricePaid(resultSet.getString("initial_payment"));
            all_students_table.setGender(resultSet.getString("gender"));
            all_students_table.setMobileNumber(resultSet.getString("phone_number"));
            all_students_table.setFather_name(resultSet.getString("father_name"));
            all_students_table.setCourse(resultSet.getString("Courses"));
            Main_Table.add(all_students_table);
        }
        All_Students_Table.setItems(Main_Table);
        All_Students_Table_id.setCellValueFactory(f -> f.getValue().idProperty());
        All_Students_Table_Username.setCellValueFactory(f -> f.getValue().usernameProperty());
        All_Students_Table_Father.setCellValueFactory(f -> f.getValue().father_nameProperty());
        All_Students_Table_Gender.setCellValueFactory(f -> f.getValue().genderProperty());
        All_Students_Table_Mobile_Number.setCellValueFactory(f -> f.getValue().mobileNumberProperty());
        All_Students_Table_CoursePrice.setCellValueFactory(f -> f.getValue().coursePriceProperty());
        All_Students_Table_paid.setCellValueFactory(f -> f.getValue().coursePricePaidProperty());
        All_Students_Table_Course.setCellValueFactory(f -> f.getValue().courseProperty());
    }
    @FXML
    protected void OnClickFilter() throws Exception {
        if(Gender.getValue()==null) {
            preparedStatement = connection.prepareStatement("select * from course_user where courses=?;");
            preparedStatement.setString(1, Courses.getValue());
            ResultSet resultSet1 = preparedStatement.executeQuery();
            Table(resultSet1);
            System.out.println("Course selected");
        } else if (Courses.getValue()==null) {
            preparedStatement=connection.prepareStatement("select * from course_user where gender=?;");
            preparedStatement.setString(1,Gender.getValue());
            ResultSet resultSet1 = preparedStatement.executeQuery();
            Table(resultSet1);
            System.out.println("gender selected");
        }
        else
        {
            preparedStatement=connection.prepareStatement("select * from course_user where gender=? and courses=?;");
            preparedStatement.setString(1,Gender.getValue());
            preparedStatement.setString(2,Courses.getValue());
            ResultSet resultSet1 = preparedStatement.executeQuery();
            Table(resultSet1);
            System.out.println("Gender and course are selected");
        }
    }
    ResultSet resultSet;
    Statement statement;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection = DriverManager.getConnection(url_Mysql, Password_name, Password_name);
            Reloads();
            Gender.getItems().addAll("Male","Female");
            statement= connection.createStatement();
            ResultSet resultSet1 = statement.executeQuery("select * from course_user;");
            Table(resultSet1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void  Reloads() throws SQLException {
        String Courses_to_show = "select courses_to_show from courses;";
        statement=connection.createStatement();
        resultSet = statement.executeQuery(Courses_to_show);
        while(resultSet.next())
        {
            Courses.getItems().add(resultSet.getString(1));
        }
    }
    @FXML
    private  void All_user() throws Exception {
        statement= connection.createStatement();
        ResultSet resultSet1 = statement.executeQuery("select * from course_user;");
        Table(resultSet1);
        Courses.setValue(null);
        Gender.setValue(null);
    }
}
