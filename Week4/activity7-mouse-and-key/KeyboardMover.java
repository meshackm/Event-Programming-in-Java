// ============================================================
// KEYBOARD MOVER -- KeyListener
// ============================================================
// A KeyListener only fires on the component that currently has
// keyboard focus, so the panel must be setFocusable(true) and
// must be asked for focus with requestFocusInWindow().
// ============================================================

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardMover extends JFrame {
    private int x = 250;
    private int y = 200;
    private JPanel gamePanel;

    public KeyboardMover() {
        setTitle("Keyboard Mover -- Arrow Keys to Move");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillOval(x, y, 40, 40);
                g.setColor(Color.BLACK);
                g.drawString("Use arrow keys to move the ball", 20, 20);
            }
        };
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setFocusable(true);
        add(gamePanel);

        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                int step = 10;

                if (key == KeyEvent.VK_UP) y -= step;
                if (key == KeyEvent.VK_DOWN) y += step;
                if (key == KeyEvent.VK_LEFT) x -= step;
                if (key == KeyEvent.VK_RIGHT) x += step;

                // Keep ball inside the window
                x = Math.max(0, Math.min(x, gamePanel.getWidth() - 40));
                y = Math.max(0, Math.min(y, gamePanel.getHeight() - 40));

                gamePanel.repaint();
            }
        });

        // Request focus after showing, otherwise the key events go nowhere
        SwingUtilities.invokeLater(gamePanel::requestFocusInWindow);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(KeyboardMover::new);
    }
}
