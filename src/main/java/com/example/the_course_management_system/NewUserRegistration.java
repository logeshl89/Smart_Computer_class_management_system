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

public class NewUserRegistration implements Initializable {
    String select_Q = "insert into Course_User(father_name,user_name,phone_number,Course_Prise,initial_payment,Gender,Courses) values(?,?,?,?,?,?,?);";

    Connection connection;
    PreparedStatement preparedStatement;
    @FXML
    private  ImageView MinimizeButton;
    @FXML
    private ImageView CloseButton;
    @FXML
    private Button AddTheUser;

    @FXML
    private Button Back_To_Home;

    @FXML
    private TextField CoursePrice;
    @FXML
    private ComboBox<String> Courses;

    @FXML
    private ComboBox<String> Genders;

    @FXML
    private TextField InitialPaymentOfUser;

    @FXML
    private TextField UserFatherName;

    @FXML
    private TextField UserPhoneNumber;
    Alert alert;
    @FXML
    private TextField UseranmeTextField;
    private Scene scene;
    private Stage stage;
    @FXML
    protected  void Validator(ActionEvent we) throws SQLException {
        if(UserPhoneNumber.getText().length()==10 && !Objects.equals(UseranmeTextField.getText(), "") && !Objects.equals(UserFatherName.getText(), "")) {
            preparedStatement = Connector.connection().prepareStatement(select_Q);
            preparedStatement.setString(1, UserFatherName.getText());
            preparedStatement.setString(2, UseranmeTextField.getText());
            preparedStatement.setString(3, UserPhoneNumber.getText());
            preparedStatement.setInt(4, Integer.parseInt(CoursePrice.getText()));
            preparedStatement.setInt(5, Integer.parseInt(InitialPaymentOfUser.getText()));
            preparedStatement.setString(6, Genders.getValue());
            preparedStatement.setString(7, Courses.getValue());
            preparedStatement.executeUpdate();
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Successful");
            alert.setContentText("Successfully added");
            alert.showAndWait();
            Clear();
        }
        else {
            alert =new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Wrong");
            alert.setHeaderText("Please enter the  valid data");
            alert.showAndWait();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Genders.getItems().addAll("Male", "Female", "Others");
        try {
            preparedStatement = Connector.connection().prepareStatement(select_Q);
            String Courses_to_show = "select courses_to_show from courses;";
            Statement statement=Connector.connection().createStatement();
            ResultSet resultSet = statement.executeQuery(Courses_to_show);
            while(resultSet.next())
            {
                Courses.getItems().add(resultSet.getString(1));
            }
            Clear();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CloseButton.setOnMouseClicked(event -> System.exit(0));
    }
    void Clear()
    {
        UseranmeTextField.clear();
        UserFatherName.clear();
        CoursePrice.clear();
        InitialPaymentOfUser.clear();
        UserPhoneNumber.clear();
        Genders.setValue("Select the gender");
        Courses.setValue("Select the course");
    }
    @FXML
    protected void Switch_To_Dashboard(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("DashBoard.fxml")));
        stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        MinimizeButton.setOnMouseClicked(e -> {
            ((Stage)((ImageView)e.getSource()).getScene().getWindow()).setIconified(true);
        });
        stage.show();
    }
}
