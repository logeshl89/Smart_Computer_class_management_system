module com.example.the_course_management_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.the_course_management_system to javafx.fxml;
    exports com.example.the_course_management_system;
}