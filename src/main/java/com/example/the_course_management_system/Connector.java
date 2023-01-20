package com.example.the_course_management_system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String Password_name = "root";
    private static String url_Mysql = new String("jdbc:mysql://localhost:3306/Tution_Management_System");
    static Connection connection() throws SQLException {
        Connection connection_obj = DriverManager.getConnection(url_Mysql, Password_name,Password_name);
        return connection_obj;
    }
}
