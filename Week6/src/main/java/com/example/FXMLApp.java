package com.example;

// ============================================================
// ACTIVITY 4 -- LOADING THE FXML
// ============================================================
// FXMLLoader reads login.fxml, builds the whole node tree, and
// instantiates the fx:controller class, wiring the @FXML fields
// and the onAction methods. load() returns the root node.
// ============================================================

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load the FXML file. The leading slash makes this an
        // absolute classpath lookup, matching the file's location
        // under src/main/resources/com/example/.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/login.fxml"));
        Parent root = loader.load();

        // Create the scene
        Scene scene = new Scene(root, 400, 350);

        primaryStage.setTitle("JavaFX Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
