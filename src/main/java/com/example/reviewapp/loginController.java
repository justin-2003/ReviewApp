package com.example.reviewapp;
import com.example.reviewapp.Account;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
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
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.reviewapp.ReviewApplication.fstore;

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

    public void onLoginButtonClick(ActionEvent e) throws IOException, ExecutionException, InterruptedException {
        if((!username_tf.getText().isEmpty())&&(!password_tf.getText().isEmpty())){
            loginlabel.setText("Login Successful!");

            // Check login credentials
            String user = username_tf.getText();
            String pass = password_tf.getText();


            if (!getUser(user, pass)) {
                loginlabel.setText("Login Failed: Incorrect Username or Password");
            }

            else {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Account.fxml"));
                Parent accountRoot = loader.load();

                // Access the controller of the Account.fxml file
                Account accountController = loader.getController();

                // Set the text of emailLabel in the AccountController
                accountController.setUsername(user);
                Parent root = FXMLLoader.load(getClass().getResource("secondary.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else{
            loginlabel.setText("Please enter valid username and password");
        }
    }



    public void onCancelButtonClick(ActionEvent e){
        Stage stage = (Stage) cancel_btn.getScene().getWindow();
        stage.close();
    }

    public void registerButtonCllick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Get User From Database
    private boolean getUser(String user, String pass) throws ExecutionException, InterruptedException {

        // Get specific document

//        DocumentReference docRef = fstore.collection("Users").document("B0gEQecWBxJVvDgyHhnT");
//        ApiFuture<DocumentSnapshot> future = docRef.get();
//        DocumentSnapshot document = future.get();
//
//        if (document.exists()) {
//            System.out.println("Document data: " + document.getData());
//            return false;
//        } else {
//            System.out.println("No such document!");
//        }

        // Asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = fstore.collection("Users").whereEqualTo("Username", user).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            String docPass = document.get("Password").toString();

            if (pass.equals(docPass)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}