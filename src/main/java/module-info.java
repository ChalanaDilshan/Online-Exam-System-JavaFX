module com.example.studentmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.studentmanagement to javafx.fxml;
    exports com.example.studentmanagement;
}