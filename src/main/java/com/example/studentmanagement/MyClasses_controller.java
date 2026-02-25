package com.example.studentmanagement;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MyClasses_controller {

    @FXML
    private Label Name_lbl;

    public void setDisplayName(String Username){
        Name_lbl.setText(Username);
    }
}
