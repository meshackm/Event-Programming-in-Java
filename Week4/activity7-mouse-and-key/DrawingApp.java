// ============================================================
// SIMPLE DRAWING APP -- MouseListener + MouseMotionListener
// ============================================================
// MouseAdapter supplies empty implementations of all five
// MouseListener methods, so we override only the ones we need.
//
// Behaviour (as specified in the guide): each mousePressed
// starts a fresh stroke -- points.clear() wipes the canvas --
// and mouseDragged appends dots along the drag path.
// ============================================================

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DrawingApp extends JFrame {
    private List<Point> points = new ArrayList<>();

    public DrawingApp() {
        setTitle("Simple Drawing App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        // Custom JPanel that draws the points
        JPanel canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                for (Point p : points) {
                    g.fillOval(p.x - 3, p.y - 3, 6, 6);
                }
            }
        };

        canvas.setBackground(Color.WHITE);

        // MouseListener -- press, release, click, enter, exit
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.clear();
                points.add(e.getPoint());
                canvas.repaint();
            }
        });

        // MouseMotionListener -- moved and dragged
        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                points.add(e.getPoint());
                canvas.repaint();
            }
        });

        add(canvas);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DrawingApp::new);
    }
}
