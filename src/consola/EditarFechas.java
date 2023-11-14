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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.EmpresaVehiculos;
import modelo.Reserva;

@SuppressWarnings("serial")
public class EditarFechas extends JPanel implements ActionListener{
	
	//Atributo 
	@SuppressWarnings("unused")
	private EditarReserva panel;
	private EmpresaVehiculos empresa;
	private Reserva reserva;
	private String fechaInit;
	private String fechaFin;
	
	public final static String CAMBIAR = "Cambiar Fechas";
	
	private JButton cambiarButton;
	
	
	public EditarFechas(EditarReserva panel) {
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
        
        JLabel fechaInitLabel = setLabel("Fecha Inicial: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        todo.add(fechaInitLabel, gbc);
        JTextField fechaInitTextField = new JTextField(20);
        this.fechaInit = fechaInitTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 1;
        todo.add(fechaInitTextField, gbc);
        
        JLabel fechaFinLabel = setLabel("Fecha Fin: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        todo.add(fechaFinLabel, gbc);
        JTextField fechaFinTextField = new JTextField(20);
        this.fechaFin = fechaFinTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 3;
        todo.add(fechaFinTextField, gbc);
        
        
        this.cambiarButton = new JButton("Guardar Cambios");
        cambiarButton.addActionListener(this);
        cambiarButton.setActionCommand(CAMBIAR);
        cambiarButton.setBackground(rojoOscuro);
        cambiarButton.setForeground(Color.WHITE);
        cambiarButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 4;
        todo.add(cambiarButton,gbc);
        
        cambiarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserva reserva = panel.getReserva();
				String fechaInitIng = fechaInitTextField.getText();
				String fechaFinIng = fechaFinTextField.getText();
				
				setReserva(reserva);
				setFechaInit(fechaInitIng);
				setFechaFin(fechaFinIng);
                
			}
        });
        
		add(todo, BorderLayout.CENTER);
		
		
	}
	
	
	
	
	public Reserva getReserva() {
		return reserva;
	}




	public String getFechaInit() {
		return fechaInit;
	}




	public String getFechaFin() {
		return fechaFin;
	}




	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}




	public void setFechaInit(String fechaInit) {
		this.fechaInit = fechaInit;
	}




	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
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
		if (comando.equals(CAMBIAR)) {
			if (fechaInit.length()==0 || fechaFin.length()==0 || reserva == null) {
					JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar. Asegurese de validar reserva", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
			else {
				boolean valido = empresa.validarCambioFecha(reserva, fechaInit, fechaFin);
				if (valido == false) {
					JOptionPane.showMessageDialog(null, "No hay vehiculos disponibles para esta fecha. No se puede editar la reserva."
							+ "\n Primero intente editar la categoria o la sede de inicio." , "Info", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else {
					Reserva reservaEdit = new Reserva(reserva.getIdReserva(),fechaInit,fechaFin, reserva.getConductorPrincipal(),
							reserva.getVehiculoArrendado(), reserva.getCliente(),
							reserva.getCobroExtraEntrega(), reserva.getSedeRecogida(), reserva.getSedeEntrega(), "activa");
					
					try {
						empresa.eliminarReserva(reserva);
						empresa.guardarReserva(reservaEdit);
						JOptionPane.showMessageDialog(null, "Su reserva fue actualizada. Ahora sus datos son: " + reservaEdit.getInformacion(), "Info", JOptionPane.INFORMATION_MESSAGE);
						panel.setReserva(reserva);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
		}
		
		
		
	}
	

}
