package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelFondoGeneral extends JPanel {
	
	//Atributos 
	@SuppressWarnings("unused")
	private Ventana ventanaPrincipal;
	@SuppressWarnings("unused")
	private JPanel panelCentro;
	
	
	public PanelFondoGeneral(Ventana ventanaPrincipal, JPanel panelCentro) {
		
		setLayout(new BorderLayout());
		this.ventanaPrincipal = ventanaPrincipal;
		
		
		Color rojoOscuro = new Color(184, 25, 25);
		
		
		JPanel superior = new JPanel();
		superior.setLayout(new BorderLayout());
		superior.setPreferredSize(new Dimension(ventanaPrincipal.getWidth(), 80));
		superior.setBackground(rojoOscuro);
		
		try {
		    BufferedImage image = ImageIO.read(new File("./Imagenes/logoReal.jpg"));
		    Image imagenEscalada = image.getScaledInstance(100, 50, Image.SCALE_SMOOTH);
		    ImageIcon icon = new ImageIcon(imagenEscalada);
		    JLabel label = new JLabel(icon);
		    superior.add(label, BorderLayout.EAST);
		} catch (IOException e) {
		  e.printStackTrace();
		}
		
		add(superior, BorderLayout.NORTH);
		add(panelCentro, BorderLayout.CENTER);		
	}

	
}
