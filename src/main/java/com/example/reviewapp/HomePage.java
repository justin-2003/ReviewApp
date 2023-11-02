package com.example.reviewapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomePage {
    @FXML
    private Button home_btn;
    @FXML
    private Button account_btn;
    @FXML
    private Button reviews_btn;
    @FXML
    private Button logout_btn;

    public void onlogoutButtonClick(ActionEvent e){
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        stage.close();
    }
}
