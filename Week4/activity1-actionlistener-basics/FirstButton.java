// ============================================================
// FIRST INTERACTIVE BUTTON
// ============================================================
// This is the simplest interactive Swing application.
// When the user clicks the button, a message appears.
//
// Anatomy of the event (the four key terms):
//   Event Source   -> the JButton that fires the event
//   Event          -> the ActionEvent object created on click
//   Event Listener -> the ActionListener waiting for the event
//   Event Handler  -> the actionPerformed() body that responds
// ============================================================

import javax.swing.*;
import java.awt.*;

public class FirstButton {

    public static void main(String[] args) {
        // All Swing UI work must happen on the Event Dispatch Thread.
        SwingUtilities.invokeLater(FirstButton::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("First Interactive Button");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

        // 1. Create the event source (the button)
        JButton clickButton = new JButton("Click Me!");

        // 2. Create the event listener (a lambda expression)
        //    The lambda implements ActionListener.actionPerformed()
        clickButton.addActionListener(e -> {
            // This code runs when the button is clicked -- the event handler
            JOptionPane.showMessageDialog(frame,
                    "You clicked the button!",
                    "Button Clicked",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        frame.add(clickButton);
        frame.setVisible(true);
    }
}
