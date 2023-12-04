package com.example.reviewapp;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.example.reviewapp.ReviewApplication.fstore;

public class RegisterController {

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

    public void onCancelButtonClick(ActionEvent e){
        Stage stage = (Stage) cancel_btn.getScene().getWindow();
        stage.close();
    }

    public void registerButtonCllick(ActionEvent e) throws ExecutionException, InterruptedException, IOException {
        if((!username_tf.getText().isEmpty())&&(!password_tf.getText().isEmpty())){
            loginlabel.setText("Registration Successful!");

            // Check login credentials
            String user = username_tf.getText();
            String pass = password_tf.getText();

            if (!getUser(user, pass)) {
                loginlabel.setText("Registration Failed: Try a different username");
            }

            else {
                createUser(user, pass);
                Parent root = FXMLLoader.load(getClass().getResource("secondary.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
        else {
            loginlabel.setText("Please enter valid username and password");
        }

    }


    public void onLoginButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void createUser(String user, String pass) throws ExecutionException, InterruptedException {
        Map<String, Object> docData = new HashMap<>();
        docData.put("Username", user);
        docData.put("Password", pass);

        ApiFuture<WriteResult> future = fstore.collection("Users").document().set(docData);
        System.out.println("Update time : " + future.get().getUpdateTime());
    }

    private boolean getUser(String user, String pass) throws ExecutionException, InterruptedException {
        // Asynchronously retrieve multiple documents
        ApiFuture<QuerySnapshot> future = fstore.collection("Users").whereEqualTo("Username", user).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        if (documents.size() > 0) {
            return false;
        }

        else {
            return true;
        }
    }
}
