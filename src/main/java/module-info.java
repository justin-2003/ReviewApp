module com.example.reviewapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.reviewapp to javafx.fxml;
    exports com.example.reviewapp;
}