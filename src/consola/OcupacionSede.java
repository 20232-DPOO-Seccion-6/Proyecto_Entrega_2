package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.EmpresaVehiculos;
import modelo.Sede;

@SuppressWarnings("serial")
public class OcupacionSede extends JPanel implements ActionListener{
	
	//Atributos
	@SuppressWarnings("unused")
	private EmpresaVehiculos empresa;
	private String nombreSede;
	
	public final static String GRAFICAR = "Graficar Ocupacion";
	
	private JButton graficarButton;
	
	
	public OcupacionSede(EmpresaVehiculos empresa) {
		
		this.empresa = empresa;
		setLayout(new BorderLayout());
		
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		
		JPanel total =  new JPanel();
		total.setLayout(new GridBagLayout());
		total.setOpaque(true);
		total.setBackground(grisCasiBlanco);
		total.setBounds(10, 10, 500, 400);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
		
		JLabel eliminarLabel = setLabel("Seleccione la sede");
        gbc.gridx = 0;
        gbc.gridy = 0;
        total.add(eliminarLabel, gbc);
        ArrayList<Sede> posiblesSedes = empresa.getSedes();
		String[] sedes = new String[posiblesSedes.size()];
		for (int i = 0; i<posiblesSedes.size(); i++) {
			sedes[i] = posiblesSedes.get(i).getNombre();	
		}
        
        JComboBox<String> sedesBox = new JComboBox<>(sedes);
        JTextField eliminarTextField = new JTextField(20);
        eliminarTextField.setEditable(false); 
        sedesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = sedesBox.getSelectedItem().toString();
                eliminarTextField.setText(seleccion);
                setNombreSede(seleccion);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        total.add(sedesBox, gbc);

		gbc.gridx = 0;
        gbc.gridy = 2;
        total.add(eliminarTextField, gbc);
        
        
        this.graficarButton = new JButton("Graficar");
        graficarButton.addActionListener(this);
        graficarButton.setActionCommand(GRAFICAR);
        graficarButton.setBackground(rojoOscuro);
        graficarButton.setForeground(Color.WHITE);
        graficarButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 3;
        total.add(graficarButton, gbc);
        
        graficarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String sedeEliminarIng = getNombreSede();
                setNombreSede(sedeEliminarIng);
			}
        });
        
        add(total, BorderLayout.CENTER);
	
	}
	
	
	public String getNombreSede() {
		return nombreSede;
	}


	public void setNombreSede(String nombreSede) {
		this.nombreSede = nombreSede;
	}


	public JLabel setLabel(String palabra) {
		Font fontMonserratButtons = new Font("Monserrat", Font.PLAIN, 15);
		JLabel loginPrint = new JLabel();
		loginPrint.setText(palabra);
		loginPrint.setFont(fontMonserratButtons);
		loginPrint.setForeground(Color.BLACK);
		loginPrint.setHorizontalAlignment(JLabel.CENTER);
		return loginPrint;
	}
	
	
	private static int[] generateRandomArray(int length, int minValue, int maxValue) {
        int[] array = new int[length];
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt((maxValue - minValue) + 1) + minValue;
        }

        return array;
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(GRAFICAR)) {
			if (nombreSede.length() == 0) {
				JOptionPane.showMessageDialog(null, "No ha seleccionado la sede", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				
				JFrame heatmapFrame = new JFrame("Ocupacion Sede");
				String[] labels = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
						"Octubre", "Noviembre", "Diciembre"};
				String xAxisTitle = "Meses";
				String yAxisTitle = "Carros";

		        heatmapFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        heatmapFrame.setSize(1000, 700);
		        heatmapFrame.setLocationRelativeTo(null);
		        
		        int[] values = generateRandomArray(12, 2, 4);
		        Graficador chartPanel = new Graficador(values, labels, xAxisTitle, yAxisTitle);

		        heatmapFrame.getContentPane().add(chartPanel);
		        heatmapFrame.setVisible(true);
				
			}
		}
		
		
	}

}
