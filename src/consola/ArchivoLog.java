package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.EmpresaVehiculos;

@SuppressWarnings("serial")
public class ArchivoLog extends JPanel implements ActionListener{
	
	//Atributos
	private EmpresaVehiculos empresa;
	private String placa;
	
	// Constantes asociadas a eventos
	public final static String GENERAR_ARCHIVO = "Generar Archivo";
	
	// Atributos asociados a botones de interacci�n
	private JButton generarArchivoButton;
	
	
	
	//Metodos
	public ArchivoLog(EmpresaVehiculos empresa) throws IOException {
		this.empresa = empresa;
		setLayout(new BorderLayout());

		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);

		
		JPanel total =  new JPanel();
		total.setLayout(new GridBagLayout());
		total.setBackground(grisCasiBlanco);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JLabel titleLabel = setLabel("Placa del vehiculo para generar archivo Log");
        gbc.gridx = 0;
        gbc.gridy = 0;
        total.add(titleLabel, gbc);
        
        ArrayList<String> posiblesVehiculos = empresa.getVehiculos(); 
        String[] vehiculos = new String[posiblesVehiculos.size()];
		for (int i = 0; i<posiblesVehiculos.size(); i++) {
			vehiculos[i] = posiblesVehiculos.get(i);	
		}
        
        JComboBox<String> vehiculosBox = new JComboBox<>(vehiculos);
        JTextField placaTextField = new JTextField(20);
        placaTextField.setEditable(false); 
        vehiculosBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = vehiculosBox.getSelectedItem().toString();
                placaTextField.setText(seleccion);
                setPlaca(seleccion);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        total.add(vehiculosBox, gbc);

		gbc.gridx = 0;
        gbc.gridy = 2;
		total.add(placaTextField, gbc);
		
		this.generarArchivoButton = new JButton("Generar Archivo .Log");
		generarArchivoButton.addActionListener(this);
		generarArchivoButton.setActionCommand(GENERAR_ARCHIVO);
		generarArchivoButton.setBackground(rojoOscuro);
		generarArchivoButton.setForeground(Color.WHITE);
		generarArchivoButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 3;
        total.add(generarArchivoButton, gbc);
        
        generarArchivoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String nombreSeguroEliminarIng = getPlaca();
                setPlaca(nombreSeguroEliminarIng);
			}
        });
        
        add(total, BorderLayout.CENTER);
		
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(GENERAR_ARCHIVO)) {
			
			try {
				empresa.logPlaca(placa);
				JOptionPane.showMessageDialog(null, "El archivo fue creado dentro de la carpeta Proyecto_Entrega2", "Info", JOptionPane.INFORMATION_MESSAGE);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
	

}
