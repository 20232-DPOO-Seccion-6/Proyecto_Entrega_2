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
import modelo.Reserva;
import modelo.Seguro;

@SuppressWarnings("serial")
public class SeguroExtra extends JPanel implements ActionListener{
	
	//Atributos 
	private RegistrarEntregaVehiculo panel;
	private EmpresaVehiculos empresa;
	private Reserva reserva;
	private String seguro;
	
	public final static String ANADIR = "Añadir Seguro";
	
	private JButton registrarSeguroButton;
	
	
	//Metodos
	public SeguroExtra(RegistrarEntregaVehiculo panel) {
		this.panel = panel;
		this.reserva = panel.getReserva();
		this.empresa = panel.getEmpresa();
		setLayout(new BorderLayout());
		

		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);

		
		JPanel seguro = new JPanel();
		seguro.setLayout(new GridBagLayout());
		seguro.setOpaque(true);
		seguro.setBackground(grisCasiBlanco);
		seguro.setBounds(10, 10, 300, 300);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        
        JLabel seguroLabel = setLabel("Seleccione el seguro que quiere añadir");
        gbc.gridx = 0;
        gbc.gridy = 0;
        seguro.add(seguroLabel, gbc);
        
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
                setSeguro(seleccion);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        seguro.add(sedesBox, gbc);

		gbc.gridx = 0;
        gbc.gridy = 2;
        seguro.add(eliminarTextField, gbc);
        
        
        this.registrarSeguroButton = new JButton("Añadir seguro");
        registrarSeguroButton.addActionListener(this);
        registrarSeguroButton.setActionCommand(ANADIR);
        registrarSeguroButton.setBackground(rojoOscuro);
        registrarSeguroButton.setForeground(Color.WHITE);
        registrarSeguroButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 3;
        seguro.add(registrarSeguroButton, gbc);
        
        
        
        registrarSeguroButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserva reserva = panel.getReserva();
				String seguroIng = eliminarTextField.getText();
                
                setReserva(reserva);
                setSeguro(seguroIng);
                
                
			}
        });
        
        
        add(seguro, BorderLayout.CENTER);

	}
	
	
	
	public Reserva getReserva() {
		return reserva;
	}



	public String getSeguro() {
		return seguro;
	}



	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}



	public void setSeguro(String seguro) {
		this.seguro = seguro;
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
		if (comando.equals(ANADIR)) {
			if (seguro.length() == 0 || reserva == null) {
				JOptionPane.showMessageDialog(null, "Seleccione un seguro y asegurese de validar reserva", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				ArrayList<Seguro> seguros = empresa.getSeguros();
				Seguro segAnadir = null;
				for (int i=0; i<seguros.size(); i++) {
					if (seguros.get(i).getNombre().equals(seguro)) {
						segAnadir = seguros.get(i);
					}
				}
				
				try {
					
					reserva.setSeguroAdicional(segAnadir);
					empresa.guardarReserva(reserva);
					panel.setReserva(reserva);
					JOptionPane.showMessageDialog(null, "El seguro fue añadido.", "Info", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
	

}
