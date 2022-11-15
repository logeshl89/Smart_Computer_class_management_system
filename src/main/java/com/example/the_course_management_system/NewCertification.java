package com.example.the_course_management_system;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class NewCertification implements Initializable {
    Connection connection;
    PreparedStatement preparedStatement;

    Alert alert;
    @FXML
    private Button CertifyButton;
    @FXML
    private DatePicker DateOfExam;
    @FXML
    private TextField CoursePrice;
    @FXML
    private ComboBox<String> GradeComboBox;
    @FXML
    private TextField Courses;

    @FXML
    private TextField Genders;

    @FXML
    private TextField InitialPaymentOfUser;

    @FXML
    private Button SearchButton;
    @FXML
    private TextField UserFatherName;
    @FXML
    private TextField UserPhoneNumber;

    @FXML
    private TextField UsernameTextField;

    @FXML
    private TextField UsernameTextFieldid;

    @FXML
    void Validator(ActionEvent event) throws SQLException {
        if (event.getSource() == SearchButton) {
            String Qure = "select * from Course_User where id=?;";
            preparedStatement = connection.prepareStatement(Qure);
            preparedStatement.setInt(1, Integer.parseInt(UsernameTextFieldid.getText()));
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UsernameTextField.setText(resultSet.getString("user_name"));
                UserFatherName.setText(resultSet.getString("Father_name"));
                UserPhoneNumber.setText(resultSet.getString("phone_number"));
                CoursePrice.setText(resultSet.getString("Course_Prise"));
                InitialPaymentOfUser.setText(resultSet.getString("initial_payment"));
                Genders.setText(resultSet.getString("gender"));
                Courses.setText(resultSet.getString("courses"));
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning!");
                alert.setHeaderText("Enter the valid data");
                alert.showAndWait();
            }
        } else if (event.getSource() == CertifyButton) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Alert");
            alert.setContentText("You cannot retrieve the data after this");
            Optional<ButtonType> po = alert.showAndWait();
            if (po.get() == ButtonType.OK) {
                if (Objects.equals(CoursePrice.getText(), InitialPaymentOfUser.getText())) {
                    preparedStatement = connection.prepareStatement("insert into certified(id,username,exam_date,gender,courses,grade) values(?,?,?,?,?,?);");
                    preparedStatement.setString(1, UsernameTextFieldid.getText());
                    preparedStatement.setString(2, UsernameTextField.getText());
                    preparedStatement.setString(3, String.valueOf(DateOfExam.getValue()));
                    preparedStatement.setString(4, Genders.getText());
                    preparedStatement.setString(5, Courses.getText());
                    preparedStatement.setString(6, GradeComboBox.getValue());
                    preparedStatement.executeUpdate();
                    preparedStatement = connection.prepareStatement("delete from course_user where id=?;");
                    preparedStatement.setString(1, UsernameTextFieldid.getText());
                    preparedStatement.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Succesful");
                    alert.setContentText(UsernameTextField.getText() + " is certified for the course " + Courses.getText());
                    alert.showAndWait();
                    clear();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Invalid");
                    alert.setHeaderText("The Course price is not completed.");
                    alert.showAndWait();
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Genders.setEditable(false);
        Courses.setEditable(false);
        UserPhoneNumber.setEditable(false);
        UserFatherName.setEditable(false);
        UsernameTextField.setEditable(false);
        Genders.setEditable(false);
        Courses.setEditable(false);
        CoursePrice.setEditable(false);
        InitialPaymentOfUser.setEditable(false);
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tution_Management_System", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        GradeComboBox.getItems().addAll("O", "A", "A+", "B", "B+", "C");
    }

    void clear() {
        UsernameTextField.clear();
        UsernameTextFieldid.clear();
        UserFatherName.clear();
        UserPhoneNumber.clear();
        Courses.clear();
        Genders.clear();
        CoursePrice.clear();
        InitialPaymentOfUser.clear();
        DateOfExam.setValue(LocalDate.now());
        GradeComboBox.setValue("Grade");
    }
}
