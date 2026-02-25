package com.example.studentmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MyExams_controller {

    @FXML
    private Button Dashboard_btn;

    @FXML
    private Label ExamName1_lbl;

    @FXML
    private Label ExamName2_lbl;

    @FXML
    private Button Logout_btn;

    @FXML
    private Button Start_btn;

    @FXML
    private Label Name_lbl;

    public void setDisplayName(String Username){
        Name_lbl.setText(Username);
    }

    @FXML
    void OnDashboardbtn(ActionEvent event) {
        loadPage("Student-Dashboard.fxml", "Student Dashboard");
    }

    @FXML
    void OnStart(ActionEvent event) {
        loadPage("Student-Answering.fxml", "Examination Center");
    }

    @FXML
    void Onlogout(ActionEvent event) {
        try {

            Stage currentStage = (Stage) Logout_btn.getScene().getWindow();
            currentStage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Login System");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPage(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Error! Can't load the page!");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
