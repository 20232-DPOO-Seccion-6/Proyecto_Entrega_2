package consola;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class PruebaMapa extends JFrame {

    private double[][] data = {
            {0.1, 0.2, 0.3},
            {0.4, 0.5, 0.6},
            {0.7, 0.8, 0.9}
    };

    public PruebaMapa() {
        super("Mapa de Calor con Swing");

        JButton drawButton = new JButton("Dibujar Mapa de Calor");
        drawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                drawHeatmap();
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(drawButton, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void drawHeatmap() {
        JFrame heatmapFrame = new JFrame("Mapa de Calor");
        heatmapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        heatmapFrame.setSize(400, 400);
        heatmapFrame.setLocationRelativeTo(null);

        JPanel heatmapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawHeatmap(g);
            }
        };

        heatmapFrame.getContentPane().add(heatmapPanel);
        heatmapFrame.setVisible(true);
    }

    private void drawHeatmap(Graphics g) {
        int width = data[0].length;
        int height = data.length;

        int cellWidth = getWidth() / width;
        int cellHeight = getHeight() / height;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                double value = data[i][j];
                int colorValue = (int) (value * 255);
                Color color = new Color(colorValue, 0, 0);

                g.setColor(color);
                g.fillRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PruebaMapa::new);
    }
}

