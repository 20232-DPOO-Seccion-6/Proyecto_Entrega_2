package consola;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Graficador extends JPanel {

	//Atributos
	private int[] values;
	private String[] labels;
	private String xAxisTitle;
    private String yAxisTitle;
    
	//Metodos
	public Graficador(int[] vals, String[] labels, String xAxisTitle, String yAxisTitle) {
		this.values = vals;
		this.labels = labels;
		this.xAxisTitle = xAxisTitle;
        this.yAxisTitle = yAxisTitle;
	}

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int barWidth = getWidth() / values.length;
        int maxValue = getMaxValue();

        // Dibujar barras
        for (int i = 0; i < values.length; i++) {
            int barHeight = (int) ((double) values[i] / maxValue * (getHeight() - 40)); 
            int x = i * barWidth;
            int y = getHeight() - barHeight;

            g.setColor(Color.BLUE);
            g.fillRect(x, y, barWidth, barHeight);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth, barHeight);

            // Etiquetas en el eje x
            g.drawString(labels[i], x + barWidth / 2, getHeight() - 20);

            // Etiquetas en las barras
            g.drawString(String.valueOf(values[i]), x + barWidth / 2, y - 5);
        }

        // Títulos de los ejes
        g.drawString(xAxisTitle, getWidth() / 2 - 20, getHeight() - 5);
        g.drawString(yAxisTitle, 5, getHeight() / 2);
    }

    private int getMaxValue() {
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
}
