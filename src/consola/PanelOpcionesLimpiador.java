package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modelo.EmpresaVehiculos;

@SuppressWarnings("serial")
public class PanelOpcionesLimpiador extends JPanel {
	
	//Atributos
		@SuppressWarnings("unused")
		private EmpresaVehiculos empresa;
		@SuppressWarnings("unused")
		private String login;
		
		
		//Metodos
		public PanelOpcionesLimpiador(EmpresaVehiculos empresa, String login) {
			
			this.empresa = empresa;
			this.login = login;
			
			setLayout(new BorderLayout());
			
			Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
			
			JLabel nombreUsuario = new JLabel();
			nombreUsuario.setText("Empleado Limpiador");
			nombreUsuario.setForeground(Color.BLACK);
			nombreUsuario.setFont(fontMonserratBold);
			nombreUsuario.setHorizontalAlignment(JLabel.CENTER);
			add(nombreUsuario, BorderLayout.NORTH);
			
			JTabbedPane opcionesLimpiador = new JTabbedPane();
			
			JPanel vehiculoMantenimiento = new VehiculoMantenimiento(empresa, login);
			JPanel actualizarEstadoVehiculo = new ActualizarEstadoVehiculo(empresa, login);

			opcionesLimpiador.addTab("Reportar vehiculo a mantenimiento", actualizarEstadoVehiculo);
			opcionesLimpiador.addTab("Actualizar estado vehiculo", vehiculoMantenimiento);

			
			
			add(opcionesLimpiador, BorderLayout.CENTER);
			
		}

}
