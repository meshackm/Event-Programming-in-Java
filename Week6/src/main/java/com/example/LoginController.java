package com.example;

// ============================================================
// ACTIVITY 4 -- THE CONTROLLER
// ============================================================
// FXML is the View; this class is the Controller. The @FXML
// annotation lets FXMLLoader inject the components it built
// from login.fxml into these private fields, matching on name
// against each element's fx:id.
// ============================================================

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    // The @FXML annotation injects the UI component from the FXML file
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;

    // Bound to the Button via onAction="#handleLoginButtonAction"
    @FXML
    private void handleLoginButtonAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Error: Fields cannot be empty.");
            return;
        }

        System.out.println("Attempting login for: " + username);

        // In a real app, you would check the database here
        if (username.equals("admin") && password.equals("password123")) {
            System.out.println("Login Successful!");
            usernameField.setText("");
            passwordField.setText("");
        } else {
            System.out.println("Login Failed!");
        }
    }
}
