import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EulerCircle extends JPanel implements ActionListener {
    private double angle = 0;
    private final List<Point> trail = new ArrayList<>();
    private Timer timer;
    
    // Variables para Basilea
    private double sumaBasilea = 0;
    private int nTermino = 1;
    private final double valorObjetivo = Math.PI * Math.PI / 6;

    public EulerCircle() {
        timer = new Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int centerX = 250, centerY = 200, radius = 100;

        // --- 1. DIBUJAR CÍRCULO Y FÓRMULA DE EULER ---
        g2d.setColor(new Color(230, 230, 230));
        g2d.drawLine(centerX - 120, centerY, centerX + 120, centerY);
        g2d.drawLine(centerX, centerY - 120, centerX, centerY + 120);

        // Estela de la partícula
        g2d.setColor(new Color(255, 100, 100, 80));
        for (int i = 1; i < trail.size(); i++) {
            Point p1 = trail.get(i - 1);
            Point p2 = trail.get(i);
            g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
        }

        // Partícula e^(iθ)
        int x = centerX + (int) (radius * Math.cos(angle));
        int y = centerY - (int) (radius * Math.sin(angle));
        trail.add(new Point(x, y));
        if (trail.size() > 400) trail.remove(0);

        g2d.setColor(Color.BLUE);
        g2d.drawLine(centerX, centerY, x, y);
        g2d.setColor(Color.RED);
        g2d.fillOval(x - 6, y - 6, 12, 12);

        // --- 2. GRÁFICO DEL PROBLEMA DE BASILEA ---
        int barX = 30, barY = 100, barWidth = 30, barHeight = 200;
        
        // Contenedor de la barra
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.drawRect(barX, barY, barWidth, barHeight);
        
        // Relleno de la barra (proporcional al progreso)
        double progreso = (sumaBasilea / valorObjetivo) * barHeight;
        g2d.setColor(new Color(50, 150, 50));
        g2d.fillRect(barX + 1, barY + barHeight - (int)progreso, barWidth - 1, (int)progreso);

        // Textos
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Monospaced", Font.BOLD, 12));
        g2d.drawString("BASILEA", barX - 5, barY - 10);
        g2d.drawString(String.format("%.4f", sumaBasilea), barX - 10, barY + barHeight + 20);
        g2d.drawString("Objetivo: π²/6", barX - 10, barY + barHeight + 40);
        
        g2d.setFont(new Font("SansSerif", Font.ITALIC, 14));
        g2d.drawString("e^(iθ) = cosθ + i·sinθ", centerX - 70, 50);
        g2d.drawString(String.format("θ = %.2f rad", angle), centerX - 40, 350);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Actualizar Círculo
        angle += 0.05;

        // Actualizar Basilea (sumamos 50 términos por cada cuadro para que sea rápido)
        for(int i = 0; i < 50; i++) {
            sumaBasilea += 1.0 / (Math.pow(nTermino, 2));
            nTermino++;
        }
        
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Euler: Círculo e Identidad de Basilea");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 450);
        frame.add(new EulerCircle());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}