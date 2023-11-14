package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import modelo.EmpresaVehiculos;

@SuppressWarnings("serial")
public class PanelOpcionesAdminGeneral extends JPanel {
	
	//Atributos 
	@SuppressWarnings("unused")
	private EmpresaVehiculos empresa;
	
	//Metodos
	
	public PanelOpcionesAdminGeneral(EmpresaVehiculos empresa) throws IOException {
		this.empresa = empresa;
		
		setLayout(new BorderLayout());
		
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		
		JLabel nombreUsuario = new JLabel();
		nombreUsuario.setText("Administrador General");
		nombreUsuario.setForeground(Color.BLACK);
		nombreUsuario.setFont(fontMonserratBold);
		nombreUsuario.setHorizontalAlignment(JLabel.CENTER);
		add(nombreUsuario, BorderLayout.NORTH);
		
		JTabbedPane opcionesAdminGeneralPaneles = new JTabbedPane();
		
		JPanel registrarEliminarVehiculo = new RegistrarEliminarVehiculo(empresa);
		JPanel registrarEliminarSede = new RegistrarEliminarSede(empresa);
		JPanel modificarPreciosCat = new ModificarPreciosCategoria(empresa);
		JPanel registrarEliminarSeguro = new RegistrarEliminarSeguro(empresa);
		JPanel archivoLog = new ArchivoLog(empresa);
		JPanel registrarCategoria = new RegistrarCategoria(empresa);
		JPanel generarTraslado = new GenerarTraslado(empresa);
		JPanel ocupacionSede = new OcupacionSede(empresa);
		
		
        opcionesAdminGeneralPaneles.addTab("Crear/Borrar Carro", registrarEliminarVehiculo);
        opcionesAdminGeneralPaneles.addTab("Crear/Borrar Sede", registrarEliminarSede);
        opcionesAdminGeneralPaneles.addTab("Modificar Precios", modificarPreciosCat);
        opcionesAdminGeneralPaneles.addTab("Crear/Borrar Seguro", registrarEliminarSeguro);
        opcionesAdminGeneralPaneles.addTab("Archivo .log de un carro", archivoLog);
        opcionesAdminGeneralPaneles.addTab("Crear categoria", registrarCategoria);
        opcionesAdminGeneralPaneles.addTab("Generar Traslado", generarTraslado);
        opcionesAdminGeneralPaneles.addTab("Ocupacion Sede", ocupacionSede);
        
        
        add(opcionesAdminGeneralPaneles, BorderLayout.CENTER);
		
		
		
		
		
	}
	
}
