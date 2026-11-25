package com.example;

// ============================================================
// ACTIVITY 5 -- CAPSTONE: A JAVAFX DASHBOARD
// ============================================================
// The top bar / sidebar / center-content shape used by AWS,
// Stripe, Shopify and most other SaaS consoles, assembled from
// a BorderPane and styled entirely with JavaFX CSS.
// ============================================================

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DashboardApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. TOP BAR
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: #2c3e50;");
        Label title = new Label("My SaaS Dashboard");
        title.setTextFill(Color.WHITE);
        title.setFont(Font.font("Arial", 20));
        topBar.getChildren().add(title);

        // 2. SIDEBAR
        VBox sidebar = new VBox(10);
        sidebar.setPadding(new Insets(15));
        sidebar.setPrefWidth(200);
        sidebar.setStyle("-fx-background-color: #ecf0f1;");

        Button overviewBtn = new Button("Overview");
        Button settingsBtn = new Button("Settings");
        Button logoutBtn = new Button("Logout");
        // Make the sidebar buttons fill its width
        for (Button b : new Button[]{overviewBtn, settingsBtn, logoutBtn}) {
            b.setMaxWidth(Double.MAX_VALUE);
        }
        sidebar.getChildren().addAll(overviewBtn, settingsBtn, logoutBtn);

        // 3. CENTER CONTENT
        VBox centerContent = new VBox(20);
        centerContent.setPadding(new Insets(30));

        Label welcome = new Label("Welcome back, User!");
        welcome.setFont(Font.font("Arial", 24));

        HBox cards = new HBox(20);
        cards.getChildren().addAll(
                createCard("Revenue", "$12,450", "#e74c3c"),
                createCard("Users", "1,204", "#3498db"),
                createCard("Orders", "342", "#2ecc71")
        );

        centerContent.getChildren().addAll(welcome, cards);

        // 4. ASSEMBLE
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(centerContent);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // One helper, three cards -- the same "write the row once"
    // idea as a Swing renderer, just returning a real node.
    private VBox createCard(String title, String value, String color) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(20));
        card.setPrefWidth(180);
        card.setStyle("-fx-background-color: " + color + "; -fx-background-radius: 10;");

        Label titleLbl = new Label(title);
        titleLbl.setTextFill(Color.WHITE);

        Label valueLbl = new Label(value);
        valueLbl.setTextFill(Color.WHITE);
        valueLbl.setFont(Font.font("Arial", 28));

        card.getChildren().addAll(titleLbl, valueLbl);
        return card;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
