package com.example.studentmanagement;

import java.time.LocalDate;

public class ExamModel {
    // මේ නම්, ඔයාගේ Controller එකේ PropertyValueFactory එකේ නම් එක්ක සමාන වෙන්න ඕනේ
    private String examName;
    private String subject;
    private String duration;
    private String noOfQuestions;
    private LocalDate date;


    public ExamModel(String examName, String subject, String duration, String noOfQuestions, LocalDate date) {
        this.examName = examName;
        this.subject = subject;
        this.duration = duration;
        this.noOfQuestions = noOfQuestions;
        this.date = date;
    }

    public String getExamName() {
        return examName;
    }

    public String getSubject() {
        return subject;
    }

    public String getDuration() {
        return duration;
    }

    public String getNoOfQuestions() {
        return noOfQuestions;
    }

    public LocalDate getDate() {
        return date;
    }
}