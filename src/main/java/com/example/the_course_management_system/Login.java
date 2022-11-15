package com.example.the_course_management_system;
import java.io.IOException;
import java.sql.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Login implements Initializable {
    String select_Q="select Username,password_teacher from Teachers where Username=? and password_Teacher=?;";
    private static final String Password_name = "root";
    String url_Mysql = new String("jdbc:mysql://localhost:3306/Tution_Management_System");
    Connection connection;
    PreparedStatement preparedStatement;
    @FXML
    private ImageView CloseButton;
    @FXML
    private TextField UsernameTextField;
    Statement statement;
    @FXML
    private PasswordField UserpasswordTextField;
    @FXML
    private  ImageView MinimizeButton;
    Alert alert;
    Stage stage;
    Scene scene;
    @FXML
    void Validation(ActionEvent event) throws SQLException, IOException {

        if(UsernameTextField.getText().equals("") ||UserpasswordTextField.getText().equals(""))
        {
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Not Valid");
            alert.setHeaderText("Enter the valid data");
            alert.showAndWait();
        } else {
            preparedStatement.setString(1,UsernameTextField.getText());
            preparedStatement.setString(2,UserpasswordTextField.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                if(resultSet.getString(1).equals(UsernameTextField.getText())&& resultSet.getString(2).equals(UserpasswordTextField.getText())) {
                    String Username = UsernameTextField.getText();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DashBoard.fxml"));
                    Parent root = fxmlLoader.load();
                    DashBoard dashBoard = fxmlLoader.getController();
                    dashBoard.SetText(Username);
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                }
                else
                {
                    alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("User");
                    alert.setHeaderText("Enter the correct data");
                    alert.showAndWait();
                }
            }
            else {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("User");
                alert.setHeaderText("You are not the user");
                alert.showAndWait();
            }
        }
    }
    void Clear()
    {
        UserpasswordTextField.clear();
        UserpasswordTextField.clear();
    }
    void Click()
    {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            connection =DriverManager.getConnection(url_Mysql,Password_name,Password_name);
            preparedStatement=connection.prepareStatement(select_Q);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        CloseButton.setOnMouseClicked(event->System.exit(0));
        MinimizeButton.setOnMouseClicked(e -> {
            ((Stage)((ImageView)e.getSource()).getScene().getWindow()).setIconified(true);
        });

    }
}
