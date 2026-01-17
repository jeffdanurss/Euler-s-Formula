import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EulerCircle extends JPanel implements ActionListener{
    private double angle =0;
    private final List<Point> trail = new ArrayList<>();
    private Timer timer;

    public EulerCircle() {
        timer = new Timer(30,this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int centerX = 200, centerY = 200, radius = 100;

        g2d.setColor(new Color(200, 200, 200));
        g2d.drawLine(50, centerY, 350, centerY);
        g2d.drawLine(centerX, 50, centerX, 350);
        

        g2d.setColor(new Color(255, 100, 100, 100)); 
        for (int i = 1; i < trail.size(); i++) {
            Point p1 = trail.get(i - 1);
            Point p2 = trail.get(i);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

       
        int x = centerX + (int)(radius * Math.cos(angle));
        int y = centerY - (int)(radius * Math.sin(angle));
        trail.add(new Point(x,y));
        if (trail.size() > 500) trail.remove(0);
        g2d.setColor(Color.BLUE);
        g2d.drawLine(centerX, centerY, x, y);

        g2d.setColor(Color.RED);
        g2d.fillOval(x-6, y-6, 12, 12);

        g2d.setColor(Color.BLACK);
        g2d.drawString("Imaginario (i sinθ)", centerX + 5, 60);
        g2d.drawString("Real (cosθ)", 300, centerY - 5);
        g2d.drawString(String.format("Ángulo θ: %.2f rad", angle), 20, 40);
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
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
