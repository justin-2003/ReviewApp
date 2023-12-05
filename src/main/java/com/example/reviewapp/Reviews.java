package com.example.reviewapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Reviews {
    @FXML
    private Button home_btn;
    @FXML
    private Button account_btn;
    @FXML
    private Button reviews_btn;
    @FXML
    private Button logout_btn;
    @FXML
    private Button upload_btn;
    @FXML
    private TextField title_txt;
    @FXML
    private TextArea review_txt;
    @FXML
    private Label error_label;
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void onlogoutButtonClick(ActionEvent e){
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        stage.close();
    }

    public void HomeButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Secondary.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void AccountButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Account.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
