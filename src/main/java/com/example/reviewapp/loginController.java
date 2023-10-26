package com.example.reviewapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class loginController {
    @FXML
    private Label loginlabel;
    @FXML
    private Button login_btn;
    @FXML
    private TextField username_tf;
    @FXML
    private PasswordField password_tf;
    @FXML
    private Button cancel_btn;

    public void onLoginButtonClick(ActionEvent e){
        if((username_tf.getText().isEmpty() == false)&&(password_tf.getText().isEmpty()==false)){
            loginlabel.setText("Login Successful!");
        }
        else{
            loginlabel.setText("Please enter valid username and password");
        }
    }
    public void onCancelButtonClick(ActionEvent e){
    Stage stage = (Stage) cancel_btn.getScene().getWindow();
    stage.close();
    }


}