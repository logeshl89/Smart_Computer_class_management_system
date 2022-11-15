package com.example.the_course_management_system;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class DashBoard implements Initializable {
    private static final String Password_name = "root";
    String url_Mysql = new String("jdbc:mysql://localhost:3306/Tution_Management_System");
    PreparedStatement preparedStatement;
    Connection connection;
    @FXML
    private StackPane backgroundPane;
    Alert alert;
    @FXML
    private Button UpdateButton;
    @FXML
    private  Button AboutTheProject;
    @FXML
    private Button CertifiedButton;
    @FXML
    private  Button NewCertification;
    @FXML
    private Button OverAllViewButton;
    @FXML
    private Button NewUserButton;
    @FXML
    private Button NewTeacherUserButton;
   @FXML
    private ImageView CloseButton;
    private Stage stage;
    private Scene scene;

    @FXML
    private  ImageView MinimizeButton;
    @FXML
    private Label UsernameUpload;
    @FXML
    private Button OnPressAllTheUsers;
    @FXML
    private Button ClickTheNewCourse;
    static String usernamestr;
    Parent root;

    void SetText(String username) {
        usernamestr = username;
        UsernameUpload.setText("Welcome,back " + usernamestr);
    }
    void OrginalText() {
        UsernameUpload.setText("Welcome,back " + usernamestr);
    }


    @FXML
    void LogOut(ActionEvent event) throws IOException {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("LogOut!");
        alert.setHeaderText("Are you sure to logout");
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Login.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    void NewSceneChanger(ActionEvent event) throws IOException {
        if(event.getSource()==NewUserButton)
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewUser_Registration.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==UpdateButton)
        {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UserUpdateSection.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    @FXML
    protected void ChangingTheScene(ActionEvent event) throws IOException {
        if (event.getSource() == OnPressAllTheUsers) {
            root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Person.fxml")));
            backgroundPane.getChildren().removeAll();
            backgroundPane.getChildren().setAll(root);
        } else if (event.getSource()==CertifiedButton) {
            root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Certified.fxml")));
            backgroundPane.getChildren().removeAll();
            backgroundPane.getChildren().setAll(root);
        } else if (event.getSource()==AboutTheProject) {
            root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AboutPage.fxml")));
            backgroundPane.getChildren().removeAll();
            backgroundPane.getChildren().setAll(root);
        } else if(event.getSource()==ClickTheNewCourse)
        {
            root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewCourse.fxml")));
            backgroundPane.getChildren().removeAll();
            backgroundPane.getChildren().setAll(root);
        } else if (event.getSource()==NewTeacherUserButton) {
            root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewTeacher.fxml")));
            backgroundPane.getChildren().removeAll();
            backgroundPane.getChildren().setAll(root);
        }
        else if(event.getSource()==OverAllViewButton)
        {
            root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("OverView.fxml")));
            backgroundPane.getChildren().removeAll();
            backgroundPane.getChildren().setAll(root);
        } else if ( event.getSource()==UpdateButton) {
            Parent root= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("UserUpdateSection.fxml")));
            stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==NewCertification)
        {
            root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("NewCertification.fxml")));
            backgroundPane.getChildren().removeAll();
            backgroundPane.getChildren().setAll(root);
        }
    }
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        CloseButton.setOnMouseClicked(event -> System.exit(0));
        OrginalText();
        try {
            root=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Person.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        backgroundPane.getChildren().removeAll();
        backgroundPane.getChildren().setAll(root);
        MinimizeButton.setOnMouseClicked(e -> {
            ((Stage)((ImageView)e.getSource()).getScene().getWindow()).setIconified(true);
        });
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });

    }
}