package com.example.reviewapp;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.example.reviewapp.ReviewApplication.*;

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
    @FXML
    private HBox hbox;
    @FXML
    private HBox hbox2;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vbox2;

    static List<reviewClass> reviews = new ArrayList<>();

    public void initialize() {
        hbox.setPrefWidth(screenWidth * 0.3);
        hbox.setPrefHeight(screenHeight);
        hbox2.setPrefWidth(screenWidth * 0.7);
        hbox2.setPrefHeight(screenHeight);
        hbox2.setLayoutX(hbox.getPrefWidth());
        vbox.setPrefWidth(screenWidth * 0.3);
        vbox.setPrefHeight(screenHeight);
        vbox2.setPrefWidth(hbox2.getPrefWidth() * 0.8);
        vbox2.setPrefHeight(hbox2.getPrefHeight());
    }

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
    public void onUploadButtonClick(ActionEvent e) throws ExecutionException, InterruptedException {
        String title = title_txt.getText();
        String reviewText = review_txt.getText();

        if (!title.isEmpty() && !reviewText.isEmpty()) {
            Map<String, Object> reviews = new HashMap<>();
            reviews.put("Title", title);
            reviews.put("Description", reviewText);

            ApiFuture<WriteResult> future = fstore.collection("reviews").document().set(reviews);
            System.out.println("Update time : " + future.get().getUpdateTime());

            try {
                HomeButtonClick(e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            error_label.setText("Please enter both title and review text.");
        }
    }
}
