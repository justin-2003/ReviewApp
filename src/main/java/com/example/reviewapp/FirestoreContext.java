package com.example.reviewapp;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

import java.io.IOException;
import java.io.InputStream;

public class FirestoreContext {
    public Firestore firebase() {
        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(this.getClass().getClassLoader().getResourceAsStream("package.json")))
                    .build();
            FirebaseApp.initializeApp(options);
            System.out.println("Firebase is initialized");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return FirestoreClient.getFirestore();
    }
}
