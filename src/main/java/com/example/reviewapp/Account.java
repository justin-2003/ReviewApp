package com.example.reviewapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.reviewapp.ReviewApplication.screenHeight;
import static com.example.reviewapp.ReviewApplication.screenWidth;

public class Account {
    @FXML
    private Button home_btn;
    @FXML
    private Button account_btn;
    @FXML
    private Button reviews_btn;
    @FXML
    private Button logout_btn;
    @FXML
    private Button change_btn;
    @FXML
    private TextField user_txt;
    @FXML
    private PasswordField pass_txt;
    @FXML
    private Label errorLabel;
    @FXML
   public  Label emailLabel;
    @FXML
    private Label no_of_reviewsLabel;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private HBox hbox;
    @FXML
    private HBox hbox2;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vbox2;


    public void initialize() {
        hbox.setPrefWidth(screenWidth * 0.3);
        hbox.setPrefHeight(screenHeight);
        hbox2.setPrefWidth(screenWidth * 0.7);
        hbox2.setPrefHeight(screenHeight);
        hbox2.setLayoutX(hbox.getPrefWidth());
        vbox.setPrefWidth(screenWidth * 0.3);
        vbox.setPrefHeight(screenHeight);
        vbox2.setPrefWidth(hbox2.getPrefWidth());
        vbox2.setPrefHeight(hbox2.getPrefHeight());
    }

    public void onlogoutButtonClick(ActionEvent e){
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        stage.close();
    }
    public void setUsername(String user) {
        emailLabel.setText(user);
    }


    public void HomeButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Secondary.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
    }

    public void ReviewsButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Reviews.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
    }



}
