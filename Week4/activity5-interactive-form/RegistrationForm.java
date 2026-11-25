// ============================================================
// ACTIVITY 5 -- INTERACTIVE REGISTRATION FORM
// ============================================================
// The guide builds this form with the NetBeans drag-and-drop
// designer. NetBeans is not required to run it: the layout code
// below is written in the same shape NetBeans generates -- a
// single initComponents() method that creates and positions
// every component -- using the exact variable names from the
// Step 5.3 component table so that the three event handlers
// from Step 5.4 are reproduced verbatim.
//
// Component table (Step 5.3):
//   titleLabel, nameLabel, nameField, emailLabel, emailField,
//   passwordLabel, passwordField, genderLabel, maleRadio,
//   femaleRadio, otherRadio, sportsCheck, musicCheck,
//   readingCheck, countryLabel, countryBox, commentsLabel,
//   commentsArea, submitButton, clearButton, exitButton,
//   statusLabel, and the genderGroup ButtonGroup.
// ============================================================

import javax.swing.*;
import java.awt.*;

public class RegistrationForm extends javax.swing.JFrame {

    public RegistrationForm() {
        initComponents();
    }

    // ------------------------------------------------------------
    // UI CONSTRUCTION (the NetBeans "Design" tab, in code)
    // ------------------------------------------------------------
    private void initComponents() {
        setTitle("Registration Form");
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        setSize(600, 560);
        setLocationRelativeTo(null);

        titleLabel = new javax.swing.JLabel("Registration Form");
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        nameLabel = new javax.swing.JLabel("Full Name:");
        nameField = new javax.swing.JTextField(30);

        emailLabel = new javax.swing.JLabel("Email:");
        emailField = new javax.swing.JTextField(30);

        passwordLabel = new javax.swing.JLabel("Password:");
        passwordField = new javax.swing.JPasswordField(30);

        genderLabel = new javax.swing.JLabel("Gender:");
        maleRadio = new javax.swing.JRadioButton("Male");
        femaleRadio = new javax.swing.JRadioButton("Female");
        otherRadio = new javax.swing.JRadioButton("Other");

        // The Button Group dragged in from the palette. It makes the
        // three radio buttons mutually exclusive.
        genderGroup = new javax.swing.ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderGroup.add(otherRadio);

        javax.swing.JPanel genderPanel =
                new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        genderPanel.add(otherRadio);

        interestsLabel = new javax.swing.JLabel("Interests:");
        sportsCheck = new javax.swing.JCheckBox("Sports");
        musicCheck = new javax.swing.JCheckBox("Music");
        readingCheck = new javax.swing.JCheckBox("Reading");

        javax.swing.JPanel interestsPanel =
                new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 0));
        interestsPanel.add(sportsCheck);
        interestsPanel.add(musicCheck);
        interestsPanel.add(readingCheck);

        countryLabel = new javax.swing.JLabel("Country:");
        countryBox = new javax.swing.JComboBox<>(
                new String[]{"Kenya", "Tanzania", "Uganda", "Rwanda"});

        commentsLabel = new javax.swing.JLabel("Comments:");
        commentsArea = new javax.swing.JTextArea(3, 30);
        commentsArea.setLineWrap(true);
        commentsArea.setWrapStyleWord(true);
        // "Right-click the text area -> Enclose in -> Scroll Pane"
        javax.swing.JScrollPane commentsScroll = new javax.swing.JScrollPane(commentsArea);

        submitButton = new javax.swing.JButton("Submit");
        submitButton.setBackground(new java.awt.Color(0, 153, 76));
        submitButton.setForeground(java.awt.Color.WHITE);
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);

        clearButton = new javax.swing.JButton("Clear");

        exitButton = new javax.swing.JButton("Exit");
        exitButton.setBackground(new java.awt.Color(204, 0, 0));
        exitButton.setForeground(java.awt.Color.WHITE);
        exitButton.setOpaque(true);
        exitButton.setBorderPainted(false);

        javax.swing.JPanel buttonPanel =
                new javax.swing.JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 15, 5));
        buttonPanel.add(submitButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(exitButton);

        statusLabel = new javax.swing.JLabel("Status: Ready");
        statusLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(8, 12, 8, 12));

        // ---- Grid Bag Layout, as chosen in Step 5.3 ----
        javax.swing.JPanel form = new javax.swing.JPanel(new java.awt.GridBagLayout());
        form.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 25, 15, 25));
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(6, 6, 6, 6);
        gbc.anchor = java.awt.GridBagConstraints.WEST;

        int row = 0;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        form.add(titleLabel, gbc);
        gbc.gridwidth = 1;

        row++;
        addRow(form, gbc, row, nameLabel, nameField);
        row++;
        addRow(form, gbc, row, emailLabel, emailField);
        row++;
        addRow(form, gbc, row, passwordLabel, passwordField);
        row++;
        addRow(form, gbc, row, genderLabel, genderPanel);
        row++;
        addRow(form, gbc, row, interestsLabel, interestsPanel);
        row++;
        addRow(form, gbc, row, countryLabel, countryBox);

        row++;
        gbc.gridx = 0; gbc.gridy = row;
        gbc.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        form.add(commentsLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = java.awt.GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        form.add(commentsScroll, gbc);
        gbc.weighty = 0.0;
        gbc.anchor = java.awt.GridBagConstraints.WEST;

        row++;
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        form.add(buttonPanel, gbc);

        setLayout(new java.awt.BorderLayout());
        add(form, java.awt.BorderLayout.CENTER);
        add(statusLabel, java.awt.BorderLayout.SOUTH);

        // ---- Wire the events ("Right-click -> Events -> Action -> actionPerformed") ----
        submitButton.addActionListener(this::submitButtonActionPerformed);
        clearButton.addActionListener(this::clearButtonActionPerformed);
        exitButton.addActionListener(this::exitButtonActionPerformed);
    }

    // Small helper so each label/field pair is laid out identically.
    private void addRow(javax.swing.JPanel parent, java.awt.GridBagConstraints gbc,
                        int row, javax.swing.JComponent label, javax.swing.JComponent field) {
        gbc.gridx = 0; gbc.gridy = row; gbc.weightx = 0.0;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        parent.add(label, gbc);

        gbc.gridx = 1; gbc.weightx = 1.0;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        parent.add(field, gbc);
    }

    // ============================================================
    // STEP 5.4 -- EVENT HANDLERS
    // ============================================================

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        // Validation
        if (name.isEmpty()) {
            statusLabel.setText("Error: Please enter your name");
            statusLabel.setForeground(java.awt.Color.RED);
            return;
        }

        if (email.isEmpty() || !email.contains("@")) {
            statusLabel.setText("Error: Please enter a valid email");
            statusLabel.setForeground(java.awt.Color.RED);
            return;
        }

        if (password.length() < 6) {
            statusLabel.setText("Error: Password must be at least 6 characters");
            statusLabel.setForeground(java.awt.Color.RED);
            return;
        }

        // Gather gender
        String gender = "Not specified";
        if (maleRadio.isSelected()) gender = "Male";
        else if (femaleRadio.isSelected()) gender = "Female";
        else if (otherRadio.isSelected()) gender = "Other";

        // Gather interests
        StringBuilder interests = new StringBuilder();
        if (sportsCheck.isSelected()) interests.append("Sports ");
        if (musicCheck.isSelected()) interests.append("Music ");
        if (readingCheck.isSelected()) interests.append("Reading ");

        // Gather country
        String country = (String) countryBox.getSelectedItem();

        // Gather comments
        String comments = commentsArea.getText().trim();

        // Show confirmation
        String message = "Registration Successful!\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Gender: " + gender + "\n" +
                "Interests: " + (interests.length() > 0 ? interests.toString() : "None") + "\n" +
                "Country: " + country + "\n" +
                "Comments: " + (comments.isEmpty() ? "None" : comments);

        javax.swing.JOptionPane.showMessageDialog(this, message,
                "Registration Complete",
                javax.swing.JOptionPane.INFORMATION_MESSAGE);

        statusLabel.setText("Registration submitted successfully!");
        statusLabel.setForeground(new java.awt.Color(0, 128, 0));
    }

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        genderGroup.clearSelection();
        sportsCheck.setSelected(false);
        musicCheck.setSelected(false);
        readingCheck.setSelected(false);
        countryBox.setSelectedIndex(0);
        commentsArea.setText("");
        statusLabel.setText("Status: Form cleared");
        statusLabel.setForeground(java.awt.Color.BLACK);
    }

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        int choice = javax.swing.JOptionPane.showConfirmDialog(this,
                "Are you sure you want to exit?",
                "Confirm Exit",
                javax.swing.JOptionPane.YES_NO_OPTION);
        if (choice == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new RegistrationForm().setVisible(true));
    }

    // ---- Variables declaration (NetBeans puts these at the bottom) ----
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel genderLabel;
    private javax.swing.JRadioButton maleRadio;
    private javax.swing.JRadioButton femaleRadio;
    private javax.swing.JRadioButton otherRadio;
    private javax.swing.ButtonGroup genderGroup;
    private javax.swing.JLabel interestsLabel;
    private javax.swing.JCheckBox sportsCheck;
    private javax.swing.JCheckBox musicCheck;
    private javax.swing.JCheckBox readingCheck;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JComboBox<String> countryBox;
    private javax.swing.JLabel commentsLabel;
    private javax.swing.JTextArea commentsArea;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JLabel statusLabel;
    // ---- End of variables declaration ----
}
