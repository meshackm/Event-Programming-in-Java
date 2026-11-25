package com.example;

// ============================================================
// ACTIVITY 1 -- EVENT FILTERS vs. EVENT HANDLERS
// ============================================================
// A JavaFX event travels in two phases:
//
//   Capturing phase (filters)   root -> ... -> target
//   Bubbling  phase (handlers)  target -> ... -> root
//
// Swing has no equivalent of the capturing phase. This is the
// single biggest difference between the two event models.
//
// The airport analogy: a filter is the security guard at the
// entrance, who can stop you before you ever reach the gate
// (e.consume()); a handler is the gate agent, who deals with
// you once you have arrived.
//
// IMPORTANT -- correction to the guide's stated output.
// The guide says clicking the button prints 1, 2 and 3. It does
// not: it prints 1 and 2 only. A JavaFX Button CONSUMES the
// MOUSE_CLICKED event once it has handled it, so the event
// never continues bubbling up to the root's handler. (Verified
// with a real OS-level click, not just a synthesised event.)
//
// That is worth knowing rather than hiding, so this version
// keeps the guide's button exactly as written AND adds a second
// target -- a plain styled box, which does not consume -- so
// you can watch a full 1 -> 2 -> 3 round trip actually happen.
// ============================================================

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EventPropagationDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label logLabel = new Label("Click the button, then click the box...");
        Button btn = new Button("Click Me! (Button -- consumes)");

        // The extra, non-consuming target.
        Label boxText = new Label("Click This Box (bubbles all the way)");
        StackPane bubbleBox = new StackPane(boxText);
        bubbleBox.setStyle("-fx-background-color: #d6eaf8; -fx-border-color: #2980b9; "
                + "-fx-border-width: 2; -fx-padding: 20;");

        VBox root = new VBox(20, logLabel, btn, bubbleBox);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        // 1. EVENT FILTER (Capturing Phase - Top Down)
        // This fires BEFORE the event reaches the target
        root.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("1. [FILTER] Root intercepted the click!");

            // Uncomment the line below to stop the event from reaching the target!
            // e.consume();
        });

        // 2. EVENT HANDLER ON ROOT (Bubbling Phase - Bottom Up)
        // This fires AFTER the target handles the event, as it bubbles up --
        // but only if the target did not consume it.
        root.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, e -> {
            System.out.println("3. [HANDLER] Root saw the click bubbling up!");
        });

        // 3. EVENT HANDLER ON BUTTON (Bubbling Phase - Target)
        btn.setOnMouseClicked(e -> {
            System.out.println("2. [HANDLER] Button was clicked!");
            logLabel.setText("Button was clicked (no step 3 -- Button consumed it)");
        });

        // 3b. EVENT HANDLER ON THE NON-CONSUMING TARGET
        bubbleBox.setOnMouseClicked(e -> {
            System.out.println("2. [HANDLER] Box was clicked!");
            logLabel.setText("Box was clicked (watch step 3 follow)");
        });

        Scene scene = new Scene(root, 460, 340);
        primaryStage.setTitle("Event Propagation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
