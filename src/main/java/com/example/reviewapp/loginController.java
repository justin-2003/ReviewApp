package com.example.reviewapp;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
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
    @FXML
    boolean flag = false;

    public void onLoginButtonClick(ActionEvent e) throws IOException {
        if((!username_tf.getText().isEmpty())&&(!password_tf.getText().isEmpty())){
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

    //Works in progress, it makes the texfield border green if the username is in the right format or will indicate that it's not by changing the color to red.
    /**public void initialize() {
        username_tf.setOnKeyPressed(event -> {

            if (event.getCode() != KeyCode.TAB && flag) {
                username_tf.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
                loginlabel.setText("");

                flag = false;
            }
        });
        username_tf.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                System.out.println("Welcome text is focused");
            } else {
                if (username_tf.getText().matches("^(.+)(.+)$*")) {
                    username_tf.setEditable(false);
                    username_tf.setBorder(null);
                    username_tf.setStyle("-fx-border-color: green ; -fx-border-width: 4px ;");
                } else {
                    username_tf.setStyle("-fx-border-color: red ; -fx-border-width: 4px ;");
                    username_tf.setVisible(true);
                    username_tf.requestFocus();
                    loginlabel.setText(username_tf.getText() + " is not valid username");
                    flag = true;
                }
            }
        });
    }*/

    public void onCancelButtonClick(ActionEvent e){
    Stage stage = (Stage) cancel_btn.getScene().getWindow();
    stage.close();
    }


}