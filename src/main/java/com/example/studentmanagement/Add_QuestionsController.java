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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class Add_QuestionsController implements Initializable {

    @FXML
    private ComboBox<String> CorrectCombo;

    @FXML
    private Button CreateStudents_btn;

    @FXML
    private ComboBox<String> ExamName_combo;

    @FXML
    private Button ManageExams_btn;

    @FXML
    private TextField Marks_tf;

    @FXML
    private Button Myclasses_btn;

    @FXML
    private TextField OptionA_tf;

    @FXML
    private TextField OptionB_tf;

    @FXML
    private TextField OptionC_tf;

    @FXML
    private TextField OptionD_tf;

    @FXML
    private TextArea Question_txtArea;

    @FXML
    private Button StudentsResults_btn;

    @FXML
    private Button btn_cancel;

    @FXML
    private Button btn_save;

    @FXML
    private Button logout_btn;

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
    void OnCreateStudents(ActionEvent event) {

    }

    private void loadExamNames() {
        try {
            Connection conn = connectToDB();
            if (conn == null) return;

            String sql = "SELECT exam_name FROM exams";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            ObservableList<String> list = FXCollections.observableArrayList();

            while (rs.next()) {
                list.add(rs.getString("exam_name"));
            }

            ExamName_combo.setItems(list);

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Exam Data Load කරන්න බැරි වුනා!");
        }
    }

    @FXML
    void OnManageExams(ActionEvent event) {
        loadPage("Manage-Exams.fxml", "My Classes");
    }

    @FXML
    void OnMyClasses(ActionEvent event) {
        loadPage("Myclasses.fxml", "My Classes");
    }

    @FXML
    void OnSave(ActionEvent event) {
        String examName = ExamName_combo.getValue();
        String question = Question_txtArea.getText();
        String optA = OptionA_tf.getText();
        String optB = OptionB_tf.getText();
        String optC = OptionC_tf.getText();
        String optD = OptionD_tf.getText();
        String correctAnswer = CorrectCombo.getValue();
        String marksStr = Marks_tf.getText();

        // 2. Validation (හිස්ද කියලා බැලීම)
        if (examName == null || question.isEmpty() || optA.isEmpty() || optB.isEmpty() ||
                optC.isEmpty() || optD.isEmpty() || correctAnswer == null || marksStr.isEmpty()) {

            showAlert("Error", "Please fill all fields!");
            return;
        }

        try {
            Connection conn = connectToDB();
            if (conn == null) return;


            String sql = "INSERT INTO questions (exam_name, question_text, option_a, option_b, option_c, option_d, correct_answer, marks) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, examName);
            pstmt.setString(2, question);
            pstmt.setString(3, optA);
            pstmt.setString(4, optB);
            pstmt.setString(5, optC);
            pstmt.setString(6, optD);
            pstmt.setString(7, correctAnswer);
            pstmt.setInt(8, Integer.parseInt(marksStr));

            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                showAlert("Success", "Question Added Successfully!");
                clearFields();
            }

            conn.close();

        } catch (NumberFormatException e) {
            showAlert("Error", "Marks should be a number!");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Failed to save question: " + e.getMessage());
        }
    }

    private void clearFields() {
        Question_txtArea.clear();
        OptionA_tf.clear();
        OptionB_tf.clear();
        OptionC_tf.clear();
        OptionD_tf.clear();
        Marks_tf.clear();
        CorrectCombo.setValue(null);
    }

    @FXML
    void OnStudentResults(ActionEvent event) {
        loadPage("My-results.fxml", "My Classes");
    }

    @FXML
    void Oncancel(ActionEvent event) {
        clearFields();
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadExamNames();
        CorrectCombo.setItems(FXCollections.observableArrayList("A", "B", "C", "D"));
    }
}
