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

public class TeacherAdmin_controller {

    @FXML
    private Button AddQuestions_btn;

    @FXML
    private Button ManageExams_btn;

    @FXML
    private Button Myclasses_btn;

    @FXML
    private Button Studentresults_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private Button CreateExamBtn;

    @FXML
    private Label TeacherNamelbl;


    @FXML
    void OnaddQuestionsbtn(ActionEvent event) {
        loadPage("Add-questions.fxml", "Add Questions");
    }


    @FXML
    void OnclassesBtn(ActionEvent event) {
        loadPage("Myclasses.fxml", "My Classes");
    }


    @FXML
    void OnmanageExamsbtn(ActionEvent event) {
        loadPage("Manage-Exams.fxml", "Manage Exams");
    }


    @FXML
    void OnstudentResultsBtn(ActionEvent event) {
        loadPage("My-results.fxml", "Student Results");
    }

    @FXML
    void OnCreateExam(ActionEvent event) {
        loadPage("Manage-Exams.fxml", "Create Exam");
    }


    @FXML
    void Onlogoutbtn(ActionEvent event) {
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

    public void setDisplayName(String Username){
        TeacherNamelbl.setText("Welcome back, "+Username+" ! \uD83D\uDC4B");
    }

}