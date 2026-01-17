import javax.swing.*;
import java.awt.*;

public class EulerCircle extends JPanel {
    private double angle;

    public EulerCircle(double angle) {
        this.angle = angle;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int centerX = 200, centerY = 200, radius = 100;
        g.drawOval(centerX - radius, centerY - radius, 2*radius, 2*radius);

        int x = centerX + (int)(radius * Math.cos(angle));
        int y = centerY - (int)(radius * Math.sin(angle));

        g.setColor(Color.RED);
        g.fillOval(x-5, y-5, 10, 10);

        g.drawString("e^(iθ) = cosθ + i·sinθ", 100, 20);
    }

    public static void main(String[] args) {
        double angle = Math.PI;
        JFrame frame = new JFrame("Euler Formula");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new EulerCircle(angle));
        frame.setVisible(true);
    }
}
