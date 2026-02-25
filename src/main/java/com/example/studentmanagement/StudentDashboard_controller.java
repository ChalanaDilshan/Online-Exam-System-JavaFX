package com.example.studentmanagement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentDashboard_controller {

    @FXML
    private Button MyExams_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Label Nameoutput_lbl;

    @FXML
    private Label Output_name;

    private String CurrentUsername;

    public void setDisplayName(String Username){
        this.CurrentUsername = Username;
        Nameoutput_lbl.setText("Welcome back, "+Username+" ! ");
        Output_name.setText(Username);
    }

    @FXML
    void OnMyExams(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("My-exam.fxml"));
            Parent root = loader.load();

            MyExams_controller examsController = loader.getController();

            examsController.setDisplayName(CurrentUsername);

            Stage stage = new Stage();
            stage.setTitle("Examinations");
            stage.setScene(new Scene(root));
            stage.show();

            ((Node)event.getSource()).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Cannot Load Page " + e.getMessage());
        }
    }

    @FXML
    void Onlogout(ActionEvent event) {
        try {

            Stage currentStage = (Stage) logout_btn.getScene().getWindow();
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

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}



