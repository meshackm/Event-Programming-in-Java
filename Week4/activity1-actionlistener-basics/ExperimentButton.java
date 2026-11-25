// ============================================================
// STEP 1.3 -- EXPERIMENTS
// ============================================================
// The guide asks you to modify FirstButton four ways:
//   * a different popup message
//   * a different popup title
//   * print to the console instead of showing a popup
//   * add a second button
// This file keeps FirstButton.java pristine and collects all
// four experiments in one runnable program.
// ============================================================

import javax.swing.*;
import java.awt.*;

public class ExperimentButton {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ExperimentButton::createAndShow);
    }

    private static void createAndShow() {
        JFrame frame = new JFrame("Experiments -- Activity 1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(460, 220);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 40));

        // Experiment 1 + 2: different message, different title
        JButton popupButton = new JButton("Show Popup");
        popupButton.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "Event handling works -- this handler ran on the EDT.",
                "Activity 1 Experiment",
                JOptionPane.INFORMATION_MESSAGE));

        // Experiment 3: print to the console instead of a popup
        JButton consoleButton = new JButton("Print to Console");
        consoleButton.addActionListener(e -> System.out.println("Clicked!"));

        // Experiment 4: a second (third) button with its own handler
        JButton countingButton = new JButton("Count My Clicks");
        int[] clicks = {0}; // array so the lambda can mutate the value
        countingButton.addActionListener(e -> {
            clicks[0]++;
            countingButton.setText("Clicked " + clicks[0] + " time(s)");
        });

        frame.add(popupButton);
        frame.add(consoleButton);
        frame.add(countingButton);
        frame.setVisible(true);
    }
}
