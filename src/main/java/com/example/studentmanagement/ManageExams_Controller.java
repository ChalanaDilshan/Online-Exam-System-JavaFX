package com.example.studentmanagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;

public class ManageExams_Controller implements Initializable {

    @FXML
    private Button Addquestions_btn;

    @FXML
    private Button CreateExam_btn;

    @FXML
    private TextField Duration_tf;

    @FXML
    private DatePicker ExamDate_D;

    @FXML
    private TableView<ExamModel> ExamList_tbl;

    @FXML
    private TextField ExamName_tf;

    @FXML
    private Button Logout_btn;

    @FXML
    private Button Myclasses_btn;

    @FXML
    private TextField NoofQuestions_tf;

    @FXML
    private Button Studentresults_btn;

    @FXML
    private TextField Subject_tf;

    @FXML
    private TableColumn<ExamModel, String> col_date;

    @FXML
    private TableColumn<ExamModel, String> col_duration;

    @FXML
    private TableColumn<ExamModel, String> col_examName;

    @FXML
    private TableColumn<ExamModel, String> col_questions;

    @FXML
    private TableColumn<ExamModel, String> col_subject;

    private ObservableList<ExamModel> examList = FXCollections.observableArrayList();

    private Connection connectToDB() {
        try {
            String url = "jdbc:mysql://localhost:3308/users_db";
            String user = "root";
            String pass = "root";
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("DB Error", "Database Connection Failed!");
            return null;
        }
    }

    @FXML
    void OnMyClasses(ActionEvent event) {
        loadPage("Myclasses.fxml", "My Classes");
    }

    @FXML
    void OnResults(ActionEvent event) {
        loadPage("My-results.fxml", "Student Results");
    }

    @FXML
    void OnaddQuestions(ActionEvent event) {
        loadPage("Add-questions.fxml", "Add Questions");
    }

    @FXML
    void OncreateExam(ActionEvent event) {
        String name = ExamName_tf.getText();
        String duration = Duration_tf.getText();
        String subject = Subject_tf.getText();
        String questions = NoofQuestions_tf.getText();
        LocalDate date = ExamDate_D.getValue();

        if(name.isEmpty() || duration.isEmpty() || subject.isEmpty() || questions.isEmpty() || date == null) {
            showAlert("Error","Please fill all fields");
            return;
        }

        try{
            Connection conn = connectToDB();
            if (conn == null) return;

            String sql = "INSERT INTO exams (exam_name, subject, duration, questions, exam_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, subject);
            pstmt.setString(3, duration);
            pstmt.setString(4, questions);
            pstmt.setDate(5, Date.valueOf(date));

            int rows = pstmt.executeUpdate();

            if (rows > 0) {

                ExamModel newExam = new ExamModel(name, subject, duration, questions, date);
                examList.add(newExam);

                clearfields();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setContentText("Exam Saved to Database Successfully!");
                alert.show();
            }
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
            showAlert("Error", "Failed to save Exam" );
        }

        ExamModel newExam = new ExamModel(name, subject, duration, questions, date);

        clearfields();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("Exam Created Successfully!");
        alert.show();
    }

    public void clearfields() {
        ExamName_tf.clear();
        Duration_tf.clear();
        Subject_tf.clear();
        NoofQuestions_tf.clear();
        ExamDate_D.setValue(null);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_examName.setCellValueFactory(new PropertyValueFactory<>("examName"));
        col_subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        col_questions.setCellValueFactory(new PropertyValueFactory<>("noOfQuestions"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        ExamList_tbl.setItems(examList);
    }
}


