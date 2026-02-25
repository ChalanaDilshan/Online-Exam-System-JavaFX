package com.example.studentmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {

    @FXML
    private Button btn_login;

    @FXML
    private PasswordField password_tf;

    @FXML
    private TextField username_tf;

    @FXML
    void onLoginBtn(ActionEvent event) {

        String username = username_tf.getText();
        String password = password_tf.getText();


        if (username.isEmpty() || password.isEmpty()) {
            showMessage("Error", "Please fill username and password!");
            return;
        }

        try {

            String url = "jdbc:mysql://localhost:3308/users_db";
            String user = "root";
            String pass = "root";

            Connection conn = DriverManager.getConnection(url, user, pass);

            String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                String role = rs.getString("role");


                if (role.equals("Student")) {
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Student-Dashboard.fxml"));
                        Parent root = loader.load();
                        StudentDashboard_controller StudentDashboard_controller = loader.getController();
                        StudentDashboard_controller.setDisplayName(username);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Student Dashboard");
                        stage.show();
                        btn_login.getScene().getWindow().hide();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (role.equals("Teacher")) {
                    try{
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeacherAdmin.fxml"));
                        Parent root = loader.load();
                        TeacherAdmin_controller teacherAdmin_controller = loader.getController();
                        teacherAdmin_controller.setDisplayName(username);
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root));
                        stage.setTitle("Teacher Dashboard");
                        stage.show();
                        btn_login.getScene().getWindow().hide();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    showMessage("Error", "Unknown User! Please enter a valid username and password!");
                }

                btn_login.getScene().getWindow().hide();

            } else {

                showMessage("Login Failed", "Invalid Username or Password!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Database Error", "Connection failed!");
        }
    }

    public void loadPage(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}