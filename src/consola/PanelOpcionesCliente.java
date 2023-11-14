package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modelo.EmpresaVehiculos;

@SuppressWarnings("serial")
public class PanelOpcionesCliente extends JPanel {
	
	
	//Atributos
	@SuppressWarnings("unused")
	private EmpresaVehiculos empresa;
	@SuppressWarnings("unused")
	private String login;
	
	
	//Metodos
	public PanelOpcionesCliente(EmpresaVehiculos empresa, String login) {
		
		this.empresa = empresa;
		this.login = login;
		
		setLayout(new BorderLayout());
		
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		
		JLabel nombreUsuario = new JLabel();
		nombreUsuario.setText("Cliente");
		nombreUsuario.setForeground(Color.BLACK);
		nombreUsuario.setFont(fontMonserratBold);
		nombreUsuario.setHorizontalAlignment(JLabel.CENTER);
		add(nombreUsuario, BorderLayout.NORTH);
		
		JTabbedPane opcionesCliente = new JTabbedPane();
		
		JPanel crearReserva = new CrearReserva(empresa, login);
		JPanel editarReserva = new EditarReserva(empresa);

		opcionesCliente.addTab("Crear Reserva", crearReserva);
		opcionesCliente.addTab("Editar Reserva", editarReserva);

		
		
		add(opcionesCliente, BorderLayout.CENTER);
		
	}

}
