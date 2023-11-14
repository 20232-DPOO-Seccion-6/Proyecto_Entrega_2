package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modelo.EmpresaVehiculos;

@SuppressWarnings("serial")
public class PanelOpcionesRecepcion extends JPanel {
	
	//Atributos 
	@SuppressWarnings("unused")
	private EmpresaVehiculos empresa;
	
	//Metodos
	public PanelOpcionesRecepcion(EmpresaVehiculos empresa){
		this.empresa = empresa;
		
		setLayout(new BorderLayout());
		
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		
		JLabel nombreUsuario = new JLabel();
		nombreUsuario.setText("Recepcionista");
		nombreUsuario.setForeground(Color.BLACK);
		nombreUsuario.setFont(fontMonserratBold);
		nombreUsuario.setHorizontalAlignment(JLabel.CENTER);
		add(nombreUsuario, BorderLayout.NORTH);
		
		JTabbedPane opcionesRecepcionPaneles = new JTabbedPane();
		
		JPanel registrarEntregaVehiculo = new RegistrarEntregaVehiculo(empresa);
		JPanel registrarDevolucionVehiculo = new RegistrarDevolucionVehiculo(empresa);
		
		opcionesRecepcionPaneles.addTab("Registrar Entrega Vehiculo", registrarEntregaVehiculo);
		opcionesRecepcionPaneles.addTab("Registrar Devolución Vehiculo", registrarDevolucionVehiculo);
		
		
		
		add(opcionesRecepcionPaneles, BorderLayout.CENTER);
		
		
		
	}
}
