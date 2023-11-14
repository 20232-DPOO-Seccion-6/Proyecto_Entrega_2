package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modelo.EmpresaVehiculos;

@SuppressWarnings("serial")
public class PanelOpcionesAdminSede extends JPanel {
	//Atributos 
	@SuppressWarnings("unused")
	private EmpresaVehiculos empresa;
	
	public PanelOpcionesAdminSede(EmpresaVehiculos empresa, String login) {
		
		this.empresa = empresa;
		
		setLayout(new BorderLayout());
		
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		
		JLabel nombreUsuario = new JLabel();
		nombreUsuario.setText("Administrador Sede");
		nombreUsuario.setForeground(Color.BLACK);
		nombreUsuario.setFont(fontMonserratBold);
		nombreUsuario.setHorizontalAlignment(JLabel.CENTER);
		add(nombreUsuario, BorderLayout.NORTH);
		
		JTabbedPane opcionesAdminSedePaneles = new JTabbedPane();
		
		JPanel registrarUsuario = new RegistrarUsuario(empresa);
		JPanel eliminarUsuario = new EliminarUsuario(empresa, login);

		opcionesAdminSedePaneles.addTab("Registrar Usuario", registrarUsuario);
		opcionesAdminSedePaneles.addTab("Eliminar Usuario", eliminarUsuario);
		
		
		add(opcionesAdminSedePaneles, BorderLayout.CENTER);
		
		
	}
		

}
