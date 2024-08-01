package lab10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DraggingArcs extends JFrame {

    public DraggingArcs() {
        setTitle("DraggingArcs");
        add(new ArcsPanel());
    }

    /**
     * Main method
     */
    public static void main(String[] args) throws InterruptedException {
        DraggingArcs frame = new DraggingArcs();
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 300);
        frame.setVisible(true);

    }

    class ArcsPanel extends JPanel {

        int xCenter;
        int yCenter;
        int x;
        int y;
        int degree;

        public ArcsPanel() {
            addMouseMotionListener(new MouseAdapter() {

                @Override
                public void mouseDragged(MouseEvent e) {
                    super.mouseDragged(e); //To change body of generated methods, choose Tools | Templates.
                    x = e.getX();
                    y = e.getY();
                    double radian = Math.acos((x - xCenter) / Math.sqrt(Math.pow(x - xCenter, 2) + Math.pow(y - yCenter, 2)));
                    degree = (int)Math.toDegrees(radian);
                    if (y > yCenter) {
                        degree = 360 - degree;
                    }
                    repaint();
                }

            });
        }

        // Draw four blazes of a fan
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            xCenter = getWidth() / 2;
            yCenter = getHeight() / 2;

            g.setColor(Color.BLACK);
            g.fillOval(xCenter - 4, yCenter - 4, 8, 8);
            g.drawString(String.valueOf(degree), xCenter, yCenter - 5);
            g.drawLine(xCenter, yCenter, (int)(getWidth() * 0.9), yCenter);
            g.setColor(Color.RED);
            g.fillOval(x - 4, y - 4, 8, 8);
            g.drawLine(x, y, xCenter, yCenter);
            g.drawArc(xCenter - 20, yCenter - 20, 40, 40, 0, degree);
            
            

        }
    }
}
