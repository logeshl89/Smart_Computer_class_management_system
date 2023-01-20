package com.example.the_course_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class NewCourse implements Initializable {

    PreparedStatement preparedStatement;
    private static final String Password_name = "root";
    String url_Mysql = new String("jdbc:mysql://localhost:3306/Tution_Management_System");
    @FXML
    private ListView<String> CoursesListVIew;

    @FXML
    private Button CreateButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private TextField New_Course;

    @FXML
    private TextField New_Course_Name;

    @FXML
    private AnchorPane New_Course_anchor;

    @FXML
    private AnchorPane New_Course_anchor1;

    @FXML
    private Button UpdateButton;
    Alert alert;
    @FXML
    void EventHandler(ActionEvent event) throws SQLException {

        if(event.getSource()==CreateButton)
        {

            preparedStatement= Connector.connection().prepareStatement("insert  into courses(COURSES_TO_SHOW) values(?);");
            preparedStatement.setString(1,New_Course.getText());
            preparedStatement.executeUpdate();
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Course is successfully added");
            alert.setTitle("Successful");
            alert.showAndWait();
            Display_Course();

        }
        else if(event.getSource()==UpdateButton)
        {
            preparedStatement =Connector.connection().prepareStatement("update courses SET COURSES_TO_SHOW=? WHERE COURSES_TO_SHOW=?;");
            if(New_Course_Name.getText() == null) {
             alert=new Alert(Alert.AlertType.WARNING);
             alert.setTitle("Error");
             alert.setContentText("Enter the requied text field");alert.showAndWait();
            }
            else {
                preparedStatement.setString(1, New_Course_Name.getText());
                preparedStatement.setString(2, CoursesListVIew.getSelectionModel().getSelectedItem());
                preparedStatement.executeUpdate();
                Display_Course();
                alert=new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setContentText("Successfully Updated");alert.showAndWait();
            }
            } else if ( event.getSource()==DeleteButton) {
            preparedStatement= Connector.connection().prepareStatement("delete from courses where courses_to_show=?;");
            preparedStatement.setString(1,CoursesListVIew.getSelectionModel().getSelectedItem());
            preparedStatement.executeUpdate();
            Display_Course();
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Course is successfully deleted");
            alert.setTitle("Successful");
            alert.showAndWait();
        }
    }
    void  Display_Course() throws SQLException {
        String Courses_to_show = "select courses_to_show from courses;";
        Statement statement=Connector.connection().createStatement();
        resultSet = statement.executeQuery(Courses_to_show);
        while(resultSet.next())
        {
            CoursesListVIew.getItems().add(resultSet.getString(1));
        }
    }
    ResultSet resultSet;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Display_Course();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}