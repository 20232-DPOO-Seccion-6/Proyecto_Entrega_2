package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import modelo.EmpresaVehiculos;
import modelo.Usuario;




@SuppressWarnings("serial")
public class Ventana extends JFrame{
	
	//Atributos Graficos
	private PanelPrincipal panelPrincipal;
	private int height;
	private int width;
	
	//Atributo empresa
	static Usuario userAdmin = new Usuario("dlmanrique","1234","adminGeneral");
	private static EmpresaVehiculos empresa = new EmpresaVehiculos("Car Rental", "Leonardo Manrique", "1001168408", userAdmin);
	
	public Ventana(int h, int w){
		
		this.height = h;
		this.width = w;
		setTitle("RentaDrive"); // T�tulo de la ventana
		setSize(w, h); // Tama�o de 800px x 600px
		setResizable(true); // Evitar que se le cambie el tama�o (*puede cambiarse)
		setDefaultCloseOperation(EXIT_ON_CLOSE); // Cierre de la ventana habitual
		setLocationRelativeTo(null); // Centrar la ventana en la pantalla
		setLayout(new BorderLayout()); // Establece el layout como BorderLayout
		
		Color colorFondo = new Color(184, 185, 187);
		
		this.panelPrincipal = new PanelPrincipal(this);
		panelPrincipal.setBackground(colorFondo);
		add(panelPrincipal);
		
		
	}
	
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public EmpresaVehiculos getEmpresa() {
		return empresa;
	}
	
	public static void main(String[] args) throws IOException
	{
		//Carga Archivos
		File archivoVehiculos = new File("./data/vehiculos.txt");
		File archivoSedes = new File("./data/sedes.txt");
		File archivoUsuarios = new File("./data/usuarios.txt");
		File archivoCategorias = new File("./data/categorias.txt");
		File archivoSeguros = new File("./data/seguros.txt");
		File archivoClientes = new File("./data/clientes.txt");
		File archivoReservas = new File("./data/reservas.txt");
		File archivoConductores = new File("./data/conductores.txt");
		empresa.cargarArchivos(archivoVehiculos,archivoSedes, archivoUsuarios, archivoCategorias, archivoSeguros,
								archivoClientes, archivoReservas, archivoConductores);
		
		//Parte Interfaz Grafica
		Ventana ventana = new Ventana(600, 1100);
		ventana.setVisible(true);
		
		
	}
	
	
}
