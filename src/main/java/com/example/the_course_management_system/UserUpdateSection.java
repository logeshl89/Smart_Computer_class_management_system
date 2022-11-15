package com.example.the_course_management_system;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserUpdateSection implements Initializable {
    PreparedStatement preparedStatement;
    Connection connection;
    private static final String Password_name = "root";
    String url_Mysql = new String("jdbc:mysql://localhost:3306/Tution_Management_System");
    @FXML
    private Button Back_To_Home;
    @FXML
    private ImageView CloseButton;

    @FXML
    private TextField CoursePrice;

    @FXML
    private ComboBox<String> Courses;

    @FXML
    private ComboBox<String> Genders;

    @FXML
    private TextField InitialPaymentOfUser;

    @FXML
    private Button SearchButton;
    Parent root;
    @FXML
    private Button UpdateTheUser;
    @FXML
    private TextField UserFatherName;
    @FXML
    private ImageView MinimizeButton;
    @FXML
    private TextField UserPhoneNumber;
    @FXML
    private ImageView statusImage;
    @FXML
    private TextField UseranmeTextField1;
    @FXML
    private TextField UseranmeTextFieldid;
    @FXML
    private Button deleteTheUser;
    Stage stage;
    Scene scene;
    Alert alert;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    void Switch_To_Dashboard(ActionEvent event)throws IOException {
        root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DashBoard.fxml")));
        stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void Validator(ActionEvent event) throws IOException, SQLException {
        String Qure;
        if(event.getSource()==deleteTheUser)
        {
            Qure="delete from course_user where id=?;";
            preparedStatement= connection.prepareStatement(Qure);
            preparedStatement.setInt(1,Integer.parseInt(UseranmeTextFieldid.getText()));
            int i = preparedStatement.executeUpdate();
            //  statusImage.setImage(Tick);
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            UseranmeTextFieldid.setEditable(false);
            alert.setHeaderText("The data is successfully deleted");
            alert.showAndWait();
        } else if (event.getSource()==UpdateTheUser) {
            Qure="update course_user set Father_name=?,user_name=?,phone_number=?,Course_Prise=?,initial_payment=?,Gender=?,Courses=? where id=?;";
            preparedStatement= connection.prepareStatement(Qure);
            preparedStatement.setString(1, UserFatherName.getText());
            preparedStatement.setString(2, UseranmeTextField1.getText());
            preparedStatement.setString(3, UserPhoneNumber.getText());
            preparedStatement.setInt(4, Integer.parseInt(CoursePrice.getText()));
            preparedStatement.setInt(5, Integer.parseInt(InitialPaymentOfUser.getText()));
            preparedStatement.setString(6, Genders.getValue());
            preparedStatement.setString(7, Courses.getValue());
            preparedStatement.setString(8,UseranmeTextFieldid.getText());
            preparedStatement.executeUpdate();
            //statusImage.setImage(Tick);
            alert =new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sucessfully");
            alert.setHeaderText("Sucessfully updated");
            alert.showAndWait();

        } else if (event.getSource()==SearchButton) {
            Qure="select * from Course_User where id=?;";
            preparedStatement= connection.prepareStatement(Qure);
            preparedStatement.setInt(1,Integer.parseInt(UseranmeTextFieldid.getText()));
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                UseranmeTextField1.setText(resultSet.getString("user_name"));
                UserFatherName.setText(resultSet.getString("Father_name"));
                UserPhoneNumber.setText(resultSet.getString("phone_number"));
                CoursePrice.setText(resultSet.getString("Course_Prise"));
                InitialPaymentOfUser.setText(resultSet.getString("initial_payment"));
                Genders.setValue(resultSet.getString("gender"));
                Courses.setValue(resultSet.getString("courses"));
            }
            else {
                alert =new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning!");
                alert.setHeaderText("Enter the valid data");
                alert.showAndWait();
            }
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Genders.getItems().addAll("Male", "Female", "Others");
        CloseButton.setOnMouseClicked(event -> System.exit(0));
        MinimizeButton.setOnMouseClicked(e -> {
            ((Stage)((ImageView)e.getSource()).getScene().getWindow()).setIconified(true);
        });
        try {
            connection = DriverManager.getConnection(url_Mysql, Password_name, Password_name);
            String Courses_to_show = "select courses_to_show from courses;";
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Courses_to_show);
            while(resultSet.next())
            {
                Courses.getItems().add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}