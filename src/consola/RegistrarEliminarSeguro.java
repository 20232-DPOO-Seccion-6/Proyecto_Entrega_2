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
import modelo.Seguro;

@SuppressWarnings("serial")
public class RegistrarEliminarSeguro extends JPanel implements ActionListener{
	
	//Atributos
	private EmpresaVehiculos empresa;
	private String nombreSeguro;
	private String precioSeguro;
	private String detallesSeguro;
	private String nombreSeguroEliminar;
	
	


	// Constantes asociadas a eventos
	public final static String REGISTRAR_SEGURO = "Registrar seguro";
	public final static String ELIMINAR_SEGURO = "Eliminar seguro";
	
	// Atributos asociados a botones de interacci�n
	private JButton registrarSeguroButton;
	private JButton eliminarSeguroButton;
	
	
	
	//Metodos
	
	public RegistrarEliminarSeguro(EmpresaVehiculos empresa) {
		this.empresa = empresa;
		setLayout(new BorderLayout());
		
		Color colorFondo = new Color(184, 185, 187);
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		
		
		JPanel total =  new JPanel();
		total.setLayout(null);
		total.setBackground(colorFondo);
		
		JPanel registro = new JPanel();
		registro.setLayout(new GridBagLayout());
		registro.setOpaque(true);
		registro.setBackground(grisCasiBlanco);
		registro.setBounds(10, 10, 500, 400);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JLabel titleLabel = new JLabel("Registrar Seguro");
        titleLabel.setFont(fontMonserratBold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Ocupa dos columnas
        registro.add(titleLabel, gbc);
        
        JLabel nombreSeguroLabel = setLabel("Nombre: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(nombreSeguroLabel, gbc);
        JTextField nombreSeguroTextField = new JTextField(20);
        this.nombreSeguro = nombreSeguroTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(nombreSeguroTextField, gbc);
		
		JLabel precioSeguroLabel = setLabel("Precio diario: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(precioSeguroLabel, gbc);
        JTextField precioSeguroTextField = new JTextField(20);
        this.precioSeguro = precioSeguroTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(precioSeguroTextField, gbc);
		
		JLabel detallesSeguroLabel = setLabel("Detalles/Descripcion: ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(detallesSeguroLabel, gbc);
        JTextField detallesSeguroTextField = new JTextField(20);
        this.detallesSeguro = detallesSeguroTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(detallesSeguroTextField, gbc);
		
		this.registrarSeguroButton = new JButton("Registrar Seguro");
		registrarSeguroButton.addActionListener(this);
		registrarSeguroButton.setActionCommand(REGISTRAR_SEGURO);
		registrarSeguroButton.setBackground(rojoOscuro);
		registrarSeguroButton.setForeground(Color.WHITE);
		registrarSeguroButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 7;
        registro.add(registrarSeguroButton, gbc);
        
        registrarSeguroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String nombreSeguroIng = nombreSeguroTextField.getText();
                String precioSeguroIng = precioSeguroTextField.getText();
                String detallesSeguroIng = detallesSeguroTextField.getText();
                
                setNombreSeguro(nombreSeguroIng);
                setPrecioSeguro(precioSeguroIng);
                setDetallesSeguro(detallesSeguroIng);
                
			}
        });
        
        total.add(registro);
        
        
        
        JPanel eliminar = new JPanel();
		eliminar.setLayout(new GridBagLayout());
		eliminar.setOpaque(true);
		eliminar.setBackground(grisCasiBlanco);
		eliminar.setBounds(610, 10, 300, 300);
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(5, 5, 5, 5);
        
        JLabel eliminarLabel = setLabel("<html> Seleccione el <br> seguro a eliminar</html>");
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        eliminar.add(eliminarLabel, gbc1);
        
        ArrayList<Seguro> posiblesSeguros = empresa.getSeguros();
        String[] seguros = new String[posiblesSeguros.size()];
		for (int i = 0; i<posiblesSeguros.size(); i++) {
			seguros[i] = posiblesSeguros.get(i).getNombre();	
		}
        
        JComboBox<String> sedesBox = new JComboBox<>(seguros);
        JTextField eliminarTextField = new JTextField(20);
        eliminarTextField.setEditable(false); 
        sedesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = sedesBox.getSelectedItem().toString();
                eliminarTextField.setText(seleccion);
                setNombreSeguroEliminar(seleccion);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        eliminar.add(sedesBox, gbc);

		gbc1.gridx = 0;
        gbc1.gridy = 2;
		eliminar.add(eliminarTextField, gbc1);
		
		this.eliminarSeguroButton = new JButton("Eliminar Seguro ");
		eliminarSeguroButton.addActionListener(this);
		eliminarSeguroButton.setActionCommand(ELIMINAR_SEGURO);
		eliminarSeguroButton.setBackground(rojoOscuro);
		eliminarSeguroButton.setForeground(Color.WHITE);
		eliminarSeguroButton.setFont(fontBotones);
        gbc1.gridx = 0;
        gbc1.gridy = 3;
        eliminar.add(eliminarSeguroButton, gbc1);
        
        eliminarSeguroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String nombreSeguroEliminarIng = getNombreSeguroEliminar();
                setNombreSeguroEliminar(nombreSeguroEliminarIng);
			}
        });
        
        
        
        total.add(eliminar);
        
        
        add(total, BorderLayout.CENTER);
		
		
	}
	
	
	
	
	
	public String getNombreSeguro() {
		return nombreSeguro;
	}





	public String getPrecioSeguro() {
		return precioSeguro;
	}





	public String getDetallesSeguro() {
		return detallesSeguro;
	}





	public void setNombreSeguro(String nombreSeguro) {
		this.nombreSeguro = nombreSeguro;
	}





	public void setPrecioSeguro(String precioSeguro) {
		this.precioSeguro = precioSeguro;
	}





	public void setDetallesSeguro(String detallesSeguro) {
		this.detallesSeguro = detallesSeguro;
	}


	public String getNombreSeguroEliminar() {
		return nombreSeguroEliminar;
	}





	public void setNombreSeguroEliminar(String nombreSeguroEliminar) {
		this.nombreSeguroEliminar = nombreSeguroEliminar;
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
		if (comando.equals(REGISTRAR_SEGURO)) {
			if (nombreSeguro.length() == 0 || precioSeguro.length() == 0 || detallesSeguro.length() == 0) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				try {
					empresa.agregarSeguro(new Seguro(nombreSeguro, precioSeguro, detallesSeguro));
					JOptionPane.showMessageDialog(null, "El seguro fue agregado.", "Info", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		else if(comando.equals(ELIMINAR_SEGURO)) {
			if (nombreSeguroEliminar.length() == 0) {
				JOptionPane.showMessageDialog(null, "No ha puesto el seguro que se debe eliminar.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				try {
					empresa.eliminarSeguro(nombreSeguroEliminar);
					JOptionPane.showMessageDialog(null, "La sede fue eliminado", "Info", JOptionPane.INFORMATION_MESSAGE);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		}
		
	}

}
