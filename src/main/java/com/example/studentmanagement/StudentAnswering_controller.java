package com.example.studentmanagement;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class StudentAnswering_controller implements Initializable {

    @FXML
    private Label Timerlbl;

    @FXML
    private Label OptionA_lbl;

    @FXML
    private Label OptionB_lbl;

    @FXML
    private Label OptionC_lbl;

    @FXML
    private Label OptionD_lbl;

    @FXML
    private Button Save_btn;

    @FXML
    private Button Previous_btn;

    @FXML
    private Label noofQuestions_lbl;

    @FXML
    private Label subjectName_lbl;


    @FXML
    void OnPreviousBtn(ActionEvent event) {

    }

    @FXML
    void OnsaveNext(ActionEvent event) {

    }

    @FXML
    private Label Question_lbl;

    private Timeline timeline;
    private int totalSeconds;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        startTimer(90);
        loadQuestion();
    }

    private Connection connectToDB() {
        try {
            String url = "jdbc:mysql://localhost:3308/users_db";
            String user = "root";
            String pass = "root";
            return DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void loadQuestion() {
        try {
            Connection conn = connectToDB();
            if (conn == null) return;

            String sql = "SELECT * FROM questions LIMIT 1";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {

                String questionText = rs.getString("question_text");
                String optA = rs.getString("option_a");
                String optB = rs.getString("option_b");
                String optC = rs.getString("option_c");
                String optD = rs.getString("option_d");

                String examName = rs.getString("exam_name");


                Question_lbl.setText(questionText);
                OptionA_lbl.setText("A) " + optA);
                OptionB_lbl.setText("B) " + optB);
                OptionC_lbl.setText("C) " + optC);
                OptionD_lbl.setText("D) " + optD);

                loadExamDetails(conn, examName);
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            Question_lbl.setText("Error loading question!");
        }
    }

    private void loadExamDetails(Connection conn, String examName) {
        try {

            String sql = "SELECT subject, questions FROM exams WHERE exam_name = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, examName);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String subject = rs.getString("subject");
                String totalQuestions = rs.getString("questions");

                subjectName_lbl.setText(subject);
                noofQuestions_lbl.setText(totalQuestions);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void startTimer(int durationInMinutes) {

        this.totalSeconds = durationInMinutes * 60;
        updateTimerLabel();

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

            totalSeconds--;
            updateTimerLabel();

            if (totalSeconds <= 0) {
                timeline.stop();
                System.out.println("Time is Up!");
                submitExam();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateTimerLabel() {
        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;


        Timerlbl.setText(String.format("%02d : %02d : %02d", hours, minutes, seconds));
    }


    private void submitExam() {
        Timerlbl.setText("00 : 00 : 00");
    }

    public void stopTimer() {
        if (timeline != null) {
            timeline.stop();
        }
    }
}

