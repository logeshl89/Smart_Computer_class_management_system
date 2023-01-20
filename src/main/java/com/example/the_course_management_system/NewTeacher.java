package com.example.the_course_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;

public class NewTeacher implements Initializable {

    @FXML
    private Button CreateTheButton;

    @FXML
    private Button DeleteButton;
    @FXML
    private  TextField PasswordOftheTeacher;

    @FXML
    private ComboBox<String> Genders;

    @FXML
    private TextField NameOfTheTeacher;

    @FXML
    private TextField PhoneNumberOfTheTeacher;
    @FXML
    private ComboBox<String> Sepacilization;
    @FXML
    private Button UpdfateButton;

    @FXML
    private TextField UserNameOfTheTeacher;

    @FXML
    private TextField UsernameOfTheTeacher;
    @FXML
    private TextField IdNumber;
    Stage stage;
    Scene scene;
    PreparedStatement preparedStatement;
    Alert alert;
    @FXML
    void EventHandler(ActionEvent event) throws IOException, SQLException {
        String Qure;
        if (event.getSource()==DeleteButton) {
            Qure="delete from teachers where username=?;";
            preparedStatement= Connector.connection().prepareStatement(Qure);
            preparedStatement.setString(1,UserNameOfTheTeacher.getText());
            int i = preparedStatement.executeUpdate();
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setHeaderText("The data is successfully deleted");
            alert.showAndWait();

            } else if (event.getSource()==UpdfateButton) {
                Qure="update Teachers set Teacher_Name=?,phone_Number=?,Teacher_Gender=?,username=?,password_teacher=?,Specification=? where id=?;";

                preparedStatement= Connector.connection().prepareStatement(Qure);
                preparedStatement.setString(1, NameOfTheTeacher.getText());
                preparedStatement.setString(2, PhoneNumberOfTheTeacher.getText());
                preparedStatement.setString(3, Genders.getValue());
                preparedStatement.setString(4, UserNameOfTheTeacher.getText());
                preparedStatement.setString(5, PasswordOftheTeacher.getText());
                preparedStatement.setString(6, Sepacilization.getValue());
                preparedStatement.setString(7,IdNumber.getText());
                preparedStatement.executeUpdate();
                alert =new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Successfully Updated");
                alert.setTitle("Successfully");
                alert.showAndWait();

            } else if (event.getSource()==CreateTheButton) {
            Qure="insert into teachers(teacher_name,phone_number,teacher_gender,username,password_teacher,specification) values(?,?,?,?,?,?,?);";
            preparedStatement= Connector.connection().prepareStatement(Qure);
            preparedStatement.setString(1, NameOfTheTeacher.getText());
            preparedStatement.setString(2, PhoneNumberOfTheTeacher.getText());
            preparedStatement.setString(3, Genders.getValue());
            preparedStatement.setString(4, UserNameOfTheTeacher.getText());
            preparedStatement.setString(5, PasswordOftheTeacher.getText());
            preparedStatement.setString(6, Sepacilization.getValue());
            preparedStatement.setString(7,IdNumber.getText());
            preparedStatement.executeUpdate();
            alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Successfully Updated");
            alert.setTitle("Successfully");
            alert.showAndWait();
            }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Genders.getItems().addAll("Male", "Female", "Others");
            String Coursess_to_show = "select courses_to_show from courses;";
            Statement statement=Connector.connection().createStatement();
            ResultSet resultSet = statement.executeQuery(Coursess_to_show);
            while(resultSet.next())
            {
                Sepacilization.getItems().add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
