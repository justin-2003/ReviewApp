package com.example.reviewapp;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.database.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;

import static com.example.reviewapp.ReviewApplication.screenHeight;
import static com.example.reviewapp.ReviewApplication.screenWidth;

public class HomePage implements Initializable {

    @FXML
    private TextArea outputField;
        @FXML
        private Button logout_btn;
        @FXML
        private Stage stage;
        private Scene scene;
        private Parent root;
        @FXML
        private ListView<String> listView;
        private boolean key;
        private reviewClass reviews;
        @FXML
        private VBox vbox;
        @FXML
        private AnchorPane anchorPane1;
        @FXML
        private HBox hbox;
        @FXML
        private HBox hbox2;


    private ObservableList<reviewClass> listOfUsers = FXCollections.observableArrayList();
    public ObservableList<reviewClass> getListOfUsers() {
        return listOfUsers;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        anchorPane1.setPrefWidth(screenWidth);
        anchorPane1.setPrefHeight(screenHeight);
        hbox.setPrefWidth(screenWidth * 0.3);
        hbox.setPrefHeight(screenHeight);
        hbox2.setPrefWidth(screenWidth * 0.7);
        hbox2.setPrefHeight(screenHeight);
        hbox2.setLayoutX(hbox.getPrefWidth());
        vbox.setPrefWidth(screenWidth * 0.3);
        vbox.setPrefHeight(screenHeight);

        outputField.setPrefWidth(hbox2.getPrefWidth() * 0.8);
        outputField.setPrefHeight(screenHeight * 0.5);
        readFirebase();
    }
    public boolean readFirebase()
    {
        key = false;

        //asynchronously retrieve all documents
        ApiFuture<QuerySnapshot> future =  ReviewApplication.fstore.collection("reviews").get();
        // future.get() blocks on response
        List<QueryDocumentSnapshot> documents;
        try
        {
            documents = future.get().getDocuments();
            if(documents.size()>0)
            {
                System.out.println("Outing....");
                for (QueryDocumentSnapshot document : documents)
                {
                    outputField.setText(outputField.getText() + document.getData().get("Title")+": " +
                            document.getData().get("Description")+" --" + document.getData().get("userName") +"\n--------------------------------------------------------------------------------------\n");

                    System.out.println(document.getId() + " => " + document.getData().get("Title"));
                    reviews  = new reviewClass(String.valueOf(document.getData().get("Title")),
                            document.getData().get("Description").toString());
                    listOfUsers.add(reviews);
                }
            }else{
                System.out.println("No data");
            }
            key=true;
        }
        catch (InterruptedException | ExecutionException ex)
        {
            ex.printStackTrace();
        }
        return key;
    }



    public void onlogoutButtonClick(ActionEvent e){
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        stage.close();
    }

    public void AccountButtonClick(ActionEvent e) throws IOException {
            Parent root = FXMLLoader.load(getClass().getResource("Account.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            scene = new Scene(root, screenWidth, screenHeight);
            stage.setScene(scene);
            stage.show();
        }

    public void ReviewButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Reviews.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root, screenWidth, screenHeight);
        stage.setScene(scene);
        stage.show();
    }
}

