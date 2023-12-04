package com.example.reviewapp;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

public class ReviewApplication extends Application {

    public static Firestore fstore;
    public static FirebaseAuth fauth;
    private final FirestoreContext contxtFirebase = new FirestoreContext();


    @Override
    public void start(Stage stage) throws IOException {
        fstore = contxtFirebase.firebase();
        fauth = FirebaseAuth.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(ReviewApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 400);
        stage.setTitle("ReviewApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
