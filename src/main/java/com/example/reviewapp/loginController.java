package com.example.reviewapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;

import java.io.IOException;

public class loginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

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

    public void onLoginButtonClick(ActionEvent e) throws IOException {
        if((username_tf.getText().isEmpty() == false)&&(password_tf.getText().isEmpty()==false)){
            loginlabel.setText("Login Successful!");
            Parent root = FXMLLoader.load(getClass().getResource("secondary.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
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