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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.EmpresaVehiculos;
import modelo.Reserva;
import modelo.Sede;
import modelo.Vehiculo;

@SuppressWarnings("serial")
public class EditarSedes extends JPanel implements ActionListener {

	//Atributo 
	private EditarReserva panel;
	private EmpresaVehiculos empresa;
	private Reserva reserva;
	private String sedeInicial;
	private String sedeFinal;
	
	public final static String CAMBIAR_INICIAL = "Cambiar Sede Inicial";
	public final static String CAMBIAR_FINAL = "Cambiar Sede Final";

	
	private JButton cambiarSedeInicialButton;
	private JButton cambiarSedeFinalButton;
	
	
	public EditarSedes(EditarReserva panel) {
		this.panel = panel;
		this.reserva = panel.getReserva();
		this.empresa = panel.getEmpresa();
		setLayout(new BorderLayout());
		
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		
		JPanel todo = new JPanel();
		todo.setLayout(new GridBagLayout());
		todo.setOpaque(true);
		todo.setBackground(grisCasiBlanco);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        
        JLabel eliminarLabel = setLabel("Sede Inicial");
        gbc.gridx = 0;
        gbc.gridy = 0;
        todo.add(eliminarLabel, gbc);
        ArrayList<Sede> posiblesSedes = empresa.getSedes();
		String[] sedes = new String[posiblesSedes.size()];
		for (int i = 0; i<posiblesSedes.size(); i++) {
			sedes[i] = posiblesSedes.get(i).getNombre();	
		}
        
        JComboBox<String> sedeInicialBox = new JComboBox<>(sedes);
        JTextField sedeInicialTextField = new JTextField(20);
        sedeInicialTextField.setEditable(false); 
        sedeInicialBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = sedeInicialBox.getSelectedItem().toString();
                sedeInicialTextField.setText(seleccion);
                setSedeInicial(seleccion);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        todo.add(sedeInicialBox, gbc);

		gbc.gridx = 0;
        gbc.gridy = 2;
        todo.add(sedeInicialTextField, gbc);
        
        
        this.cambiarSedeInicialButton = new JButton("Cambiar Sede Inicial");
        cambiarSedeInicialButton.addActionListener(this);
        cambiarSedeInicialButton.setActionCommand(CAMBIAR_INICIAL);
        cambiarSedeInicialButton.setBackground(rojoOscuro);
        cambiarSedeInicialButton.setForeground(Color.WHITE);
        cambiarSedeInicialButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 3;
        todo.add(cambiarSedeInicialButton,gbc);
        
        cambiarSedeInicialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserva reserva = panel.getReserva();
				String sedeInicialIng = sedeInicialTextField.getText();
				String sedeFinalIng = reserva.getSedeEntrega();
				
				setReserva(reserva);
				setSedeInicial(sedeInicialIng);
				setSedeFinal(sedeFinalIng);
                
			}
        });
        
        
        
        JLabel sedeFinalLabel = setLabel("Sede Final");
        gbc.gridx = 1;
        gbc.gridy = 0;
        todo.add(sedeFinalLabel, gbc);
   
        JComboBox<String> sedeFinalBox = new JComboBox<>(sedes);
        JTextField sedeFinalTextField = new JTextField(20);
        sedeFinalTextField.setEditable(false); 
        sedeFinalBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = sedeFinalBox.getSelectedItem().toString();
                sedeFinalTextField.setText(seleccion);
                setSedeFinal(seleccion);
                
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 1;
        todo.add(sedeFinalBox, gbc);

		gbc.gridx = 1;
        gbc.gridy = 2;
        todo.add(sedeFinalTextField, gbc);
		
        this.cambiarSedeFinalButton = new JButton("Cambiar Sede Final");
        cambiarSedeFinalButton.addActionListener(this);
        cambiarSedeFinalButton.setActionCommand(CAMBIAR_FINAL);
        cambiarSedeFinalButton.setBackground(rojoOscuro);
        cambiarSedeFinalButton.setForeground(Color.WHITE);
        cambiarSedeFinalButton.setFont(fontBotones);
        gbc.gridx = 1;
        gbc.gridy = 3;
        todo.add(cambiarSedeFinalButton,gbc);
        
        cambiarSedeFinalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserva reserva = panel.getReserva();
				String sedeInicialIng = reserva.getSedeRecogida();
				String sedeFinalIng = sedeFinalTextField.getText();
				
				setReserva(reserva);
				setSedeInicial(sedeInicialIng);
				setSedeFinal(sedeFinalIng);
                
			}
        });
        
        add(todo, BorderLayout.CENTER);
		
	}
	
	
	
	
	public Reserva getReserva() {
		return reserva;
	}




	public String getSedeInicial() {
		return sedeInicial;
	}




	public String getSedeFinal() {
		return sedeFinal;
	}




	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}




	public void setSedeInicial(String sedeInicial) {
		this.sedeInicial = sedeInicial;
	}




	public void setSedeFinal(String sedeFinal) {
		this.sedeFinal = sedeFinal;
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
		if (comando.equals(CAMBIAR_INICIAL)) {
			if (sedeInicial.length()==0 || reserva == null) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar. Asegurese de validar reserva", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				ArrayList<Sede> sedes = empresa.getSedes();
				Sede sedeInit = null;
				for (Sede sede: sedes) {
					if (sede.getNombre().equals(sedeInicial)) {
						sedeInit = sede;
					}
				}
				Set<String> posiblesCats = sedeInit.getInventario().getVehiculos().keySet();
				boolean encontrado = false;
				for (String cat: posiblesCats) {
					if (cat.equals(reserva.getVehiculoArrendado().getCategoria().getIdCategoria())) {
						encontrado = true;
					}
				}
				if (encontrado) {
					DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					String fechaInicio = reserva.getFechaRecogida().format(formato);
					String fechaFin = reserva.getFechaEntrega().format(formato);
					Sede sedeFin = null;
					for (Sede sede: empresa.getSedes()) {
						if (sede.getNombre().equals(reserva.getSedeEntrega())) {
							sedeFin = sede;
						}
					}
					String placaVehiculoValido = empresa.revisarFechasReserva(sedeInit, sedeFin , reserva.getVehiculoArrendado().getCategoria(), 
							fechaInicio, fechaFin);

					if (placaVehiculoValido.equals("")) {
						JOptionPane.showMessageDialog(null, "No es posible editar la reserva, no hay vehiculos disposibles para las fechas de la reserva original", "Info", JOptionPane.INFORMATION_MESSAGE);

					}
					else if(placaVehiculoValido.equals("-")) {
						JOptionPane.showMessageDialog(null, "No es posible editar la reserva, no hay vehiculos disposibles para las fechas de la reserva original.", "Info", JOptionPane.INFORMATION_MESSAGE);
						
					}
					else {
						Vehiculo vehiculoArrendado = empresa.encontrarVehiculo(placaVehiculoValido);
						
						Reserva reservaEdit = new Reserva(reserva.getIdReserva(),fechaInicio,fechaFin, reserva.getConductorPrincipal(),
								vehiculoArrendado, reserva.getCliente(),
								reserva.getCobroExtraEntrega(), sedeInit.getNombre(), reserva.getSedeEntrega(), "activa");
						try {
							empresa.eliminarReserva(reserva);
							empresa.guardarReserva(reservaEdit);
							JOptionPane.showMessageDialog(null, "Su reserva ha sido actualizada. Info de su reserva: \n " + reservaEdit.getInformacion(), "Info", JOptionPane.INFORMATION_MESSAGE);
							panel.setReserva(reserva);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					
				}
				
			}
		}
		else if(comando.equals(CAMBIAR_FINAL)) {
			if (sedeFinal.length()==0 || reserva == null) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar. Asegurese de validar reserva", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				ArrayList<Sede> sedes = empresa.getSedes();
				Sede sedeFin = null;
				for (Sede sede: sedes) {
					if (sede.getNombre().equals(sedeFinal)) {
						sedeFin = sede;
					}
				}
				
				String horas = JOptionPane.showInputDialog(null, "Ingrese en que horas devolveria el vehiculo (formato 24h. Ejemplo: 9-11. Recuerde "
						+ "\n ingresar el intervalo, no horas sueltas y separado por '-'): ", "Entrada de Texto", JOptionPane.PLAIN_MESSAGE);

				boolean valHoras = empresa.validarHorasDevuelta(sedeFin, horas, reserva.getFechaEntrega());
				DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String fechaInicio = reserva.getFechaRecogida().format(formato);
				String fechaFin1 = reserva.getFechaEntrega().format(formato);
				
				if (valHoras == true) {
					Reserva reservaNueva = new Reserva(reserva.getIdReserva(),fechaInicio,fechaFin1, reserva.getConductorPrincipal(),
							reserva.getVehiculoArrendado(), reserva.getCliente(),
							reserva.getCobroExtraEntrega(), reserva.getSedeRecogida(), sedeFin.getNombre(), "activa");

					try {
						empresa.eliminarReserva(reserva);
						empresa.guardarReserva(reservaNueva);
						JOptionPane.showMessageDialog(null, "Su reserva ha sido actualizada. Info de su reserva: \n " + reservaNueva.getInformacion(), "Info", JOptionPane.INFORMATION_MESSAGE);
						panel.setReserva(reserva);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Las horas de devolucion no son validas.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
					
					
				
			}
			
			
		}
			
		
	}

}
