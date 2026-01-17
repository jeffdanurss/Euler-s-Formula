import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EulerCircle extends JPanel implements ActionListener{
    private double angle =0;
    private Timer timer;

    public EulerCircle() {
        timer = new Timer(20,this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int centerX = 200, centerY = 200, radius = 100;

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawLine(centerX - 150, centerY, centerX + 150, centerY);
        g2d.drawLine(centerX, centerY - 150, centerX, centerY + 150);

        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        int x = centerX + (int)(radius * Math.cos(angle));
        int y = centerY - (int)(radius * Math.sin(angle));
        g2d.setColor(Color.BLUE);
        g2d.drawLine(centerX, centerY, x, y);

        g2d.setColor(Color.RED);
        g2d.fillOval(x-5, y-5, 10, 10);

        g2d.setColor(Color.BLACK);
        g2d.drawString(String.format("Ángulo: %.2f rad", angle), 20, 40);
        g2d.drawString("Fórmula: e^(iθ) = cosθ + i·sinθ", 100, 20);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        angle += 0.05; 
        repaint();    
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Euler Formula");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.add(new EulerCircle());
        frame.setVisible(true);
    }
}
