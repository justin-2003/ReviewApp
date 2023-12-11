package com.example.reviewapp;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
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
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.reviewapp.ReviewApplication.*;

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
    private TextField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private Label errorLabel;
    @FXML
   public  Label username_txt;
    private Stage stage;
    private Scene scene;
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
        username_txt.setText("Welcome, " + User.getUser().getName());
    }

    public void onlogoutButtonClick(ActionEvent e){
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        stage.close();
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
    private boolean updatePasswordIfMatch(String user, String oldPassword, String newPassword) throws ExecutionException, InterruptedException {
        // Create a query to find the document with the specified username and old password
        Query query = fstore.collection("Users").whereEqualTo("Username", user).whereEqualTo("Password", oldPassword);

        // Execute the query to get the matching documents
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();

        // Check if any documents match the query
        if (!documents.isEmpty()) {

            DocumentReference docRef = documents.get(0).getReference();

            // Update the "Password" field to the new password synchronously
            ApiFuture<WriteResult> updateFuture = docRef.update("Password", newPassword);

            // Wait for the update operation to complete
            WriteResult updateResult = updateFuture.get();
            System.out.println("Password update result: " + updateResult);

            errorLabel.setText("Password changed successfully!!");
            return true;
        } else {
            errorLabel.setText("Password change unsuccessful. Please try again.");
            return false;
        }
    }
    @FXML
    private void setChange_btn() throws ExecutionException, InterruptedException {
        updatePasswordIfMatch(user_txt.getText(),oldPassword.getText(), newPassword.getText());
    }

}
