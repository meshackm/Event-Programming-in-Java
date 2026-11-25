package com.example;

// ============================================================
// ACTIVITY 1 -- YOUR FIRST JAVAFX WINDOW (PROGRAMMATIC)
// ============================================================
// The JavaFX hierarchy, outermost first:
//   Stage -- the top-level window (title bar, min/max buttons)
//   Scene -- the content inside the window; one Scene per Stage
//            at a time, but Scenes can be swapped
//   Node  -- any individual component: a Label, a Button, or a
//            layout pane (which is itself a Node holding Nodes)
// ============================================================

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {

        // 1. Create Nodes (Components)
        Label titleLabel = new Label("My First JavaFX App");
        Button clickButton = new Button("Click Me!");

        // Add an action to the button.
        // setOnAction is JavaFX's equivalent of Swing's addActionListener.
        clickButton.setOnAction(e -> {
            titleLabel.setText("Button was clicked!");
            System.out.println("Button clicked on: " + Thread.currentThread().getName());
        });

        // 2. Create a Layout Pane and add Nodes to it
        VBox root = new VBox(15); // 15 is the spacing between elements
        root.getChildren().addAll(titleLabel, clickButton);

        // Center the elements -- JavaFX styling is CSS, not setter calls
        root.setStyle("-fx-alignment: center; -fx-padding: 20px; -fx-background-color: #f0f0f0;");

        // 3. Create a Scene and attach the root layout
        Scene scene = new Scene(root, 400, 300);

        // 4. Set the Scene on the Stage (Window)
        primaryStage.setTitle("JavaFX Programmatic App");
        primaryStage.setScene(scene);

        // 5. Show the Window
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
