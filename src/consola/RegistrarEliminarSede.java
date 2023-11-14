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
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Administrador;
import modelo.EmpresaVehiculos;
import modelo.Sede;
import modelo.Usuario;

@SuppressWarnings("serial")
public class RegistrarEliminarSede extends JPanel implements ActionListener{
	
	//Atributos 
	private EmpresaVehiculos empresa;
	private String nombre;
	private String ubicacion;
	private String horarioLV;
	private String horarioS;
	private String horarioDF;
	private String nombreAdmin;
	private String cedulaAdmin;
	private String loginAdmin;
	private String contrasenaAdmin;
	private String nombreSedeEliminar;
	
	// Constantes asociadas a eventos
	public final static String REGISTRAR_SEDE = "Registrar sede";
	public final static String ELIMINAR_SEDE = "Eliminar sede";
	
	
	// Atributos asociados a botones de interacci�n
	private JButton registrarSedeButton;
	private JButton eliminarSedeButton;
	
	
	public RegistrarEliminarSede(EmpresaVehiculos empresa) {
		
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
        
        JLabel titleLabel = new JLabel("Registrar Sede ");
        titleLabel.setFont(fontMonserratBold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Ocupa dos columnas
        registro.add(titleLabel, gbc);
        
        JLabel nombreLabel = setLabel("Nombre: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(nombreLabel, gbc);
        JTextField nombreTextField = new JTextField(20);
        this.nombre = nombreTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(nombreTextField, gbc);
		
		JLabel ubicacionLabel = setLabel("Ubicacion: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(ubicacionLabel, gbc);
        JTextField ubicacionTextField = new JTextField(20);
        this.ubicacion = ubicacionTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(ubicacionTextField, gbc);
		
		JLabel horarioLVLabel = setLabel("<html>Horario Lunes-Viernes <br> (formato 24 horas. Ejemplo: 8-16) : </html> ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(horarioLVLabel, gbc);
        JTextField horarioLVTextField = new JTextField(20);
        this.horarioLV = horarioLVTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(horarioLVTextField, gbc);
		
		
		JLabel horarioSLabel = setLabel("<html>Horario Sabados <br> (formato 24 horas. Ejemplo: 8-16) : </html> ");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(horarioSLabel, gbc);
        JTextField horarioSTextField = new JTextField(20);
        this.horarioS = horarioSTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(horarioSTextField, gbc);
		
		JLabel horarioDFLabel = setLabel("<html>Horario Domingo-Festivos <br> (formato 24 horas. Ejemplo: 8-16) : </html> ");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(horarioDFLabel, gbc);
        JTextField horarioDFTextField = new JTextField(20);
        this.horarioDF = horarioDFTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(horarioDFTextField, gbc);
		
		JLabel nombreAdminLabel = setLabel("<html> Nombre del administrador <br> de la sede: </html>");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(nombreAdminLabel, gbc);
        JTextField nombreAdminTextField = new JTextField(20);
        this.nombreAdmin = nombreAdminTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(nombreAdminTextField, gbc);
		
		JLabel cedulaAdminLabel = setLabel("<html> Cedula del administrador <br> de la sede: </html>");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(cedulaAdminLabel, gbc);
        JTextField cedulaAdminTextField = new JTextField(20);
        this.cedulaAdmin = cedulaAdminTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(cedulaAdminTextField, gbc);
		
		this.registrarSedeButton = new JButton("Registrar Sede ");
		registrarSedeButton.addActionListener(this);
		registrarSedeButton.setActionCommand(REGISTRAR_SEDE);
		registrarSedeButton.setBackground(rojoOscuro);
		registrarSedeButton.setForeground(Color.WHITE);
		registrarSedeButton.setFont(fontBotones);
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(registrarSedeButton, gbc);
        
        registrarSedeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String nombreIng = nombreTextField.getText();
                String ubicacionIng = ubicacionTextField.getText();
                String horarioLVIng = horarioLVTextField.getText();
                String horarioSIng = horarioSTextField.getText();
                String horarioDFIng = horarioDFTextField.getText();
                String nombreAdminIng = nombreAdminTextField.getText();
                String cedulaAdminIng = cedulaAdminTextField.getText();
                
                setNombre(nombreIng);
                setUbicacion(ubicacionIng);
                setHorarioLV(horarioLVIng);
                setHorarioS(horarioSIng);
                setHorarioDF(horarioDFIng);
                setNombreAdmin(nombreAdminIng);
                setCedulaAdmin(cedulaAdminIng);
                
                
			}
        });
		
        
        JPanel eliminar = new JPanel();
		eliminar.setLayout(new GridBagLayout());
		eliminar.setOpaque(true);
		eliminar.setBackground(grisCasiBlanco);
		eliminar.setBounds(610, 10, 300, 300);
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(5, 5, 5, 5);
        
        JLabel eliminarLabel = setLabel("<html> Seleccione la <br> sede a eliminar</html>");
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        eliminar.add(eliminarLabel, gbc1);
        ArrayList<Sede> posiblesSedes = empresa.getSedes();
		String[] sedes = new String[posiblesSedes.size()];
		for (int i = 0; i<posiblesSedes.size(); i++) {
			sedes[i] = posiblesSedes.get(i).getNombre();	
		}
        
        JComboBox<String> sedesBox = new JComboBox<>(sedes);
        JTextField eliminarTextField = new JTextField(20);
        eliminarTextField.setEditable(false); 
        sedesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = sedesBox.getSelectedItem().toString();
                eliminarTextField.setText(seleccion);
                setNombreSedeEliminar(seleccion);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        eliminar.add(sedesBox, gbc);

		gbc1.gridx = 0;
        gbc1.gridy = 2;
		eliminar.add(eliminarTextField, gbc1);
		
		
		this.eliminarSedeButton = new JButton("Eliminar Sede ");
		eliminarSedeButton.addActionListener(this);
		eliminarSedeButton.setActionCommand(ELIMINAR_SEDE);
		eliminarSedeButton.setBackground(rojoOscuro);
		eliminarSedeButton.setForeground(Color.WHITE);
		eliminarSedeButton.setFont(fontBotones);
        gbc1.gridx = 0;
        gbc1.gridy = 3;
        eliminar.add(eliminarSedeButton, gbc1);
        
        eliminarSedeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String placaEliminarIng = getNombreSedeEliminar();
                setNombreSedeEliminar(placaEliminarIng);
			}
        });
		
		total.add(registro);
		total.add(eliminar);
		add(total, BorderLayout.CENTER);
		
		
	}
	
	
	
	
	
	public String getNombre() {
		return nombre;
	}





	public String getUbicacion() {
		return ubicacion;
	}





	public String getHorarioLV() {
		return horarioLV;
	}





	public String getHorarioS() {
		return horarioS;
	}





	public String getHorarioDF() {
		return horarioDF;
	}





	public String getNombreAdmin() {
		return nombreAdmin;
	}





	public String getCedulaAdmin() {
		return cedulaAdmin;
	}





	public String getLoginAdmin() {
		return loginAdmin;
	}





	public String getContrasenaAdmin() {
		return contrasenaAdmin;
	}





	public String getNombreSedeEliminar() {
		return nombreSedeEliminar;
	}





	public void setNombre(String nombre) {
		this.nombre = nombre;
	}





	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}





	public void setHorarioLV(String horarioLV) {
		this.horarioLV = horarioLV;
	}





	public void setHorarioS(String horarioS) {
		this.horarioS = horarioS;
	}





	public void setHorarioDF(String horarioDF) {
		this.horarioDF = horarioDF;
	}





	public void setNombreAdmin(String nombreAdmin) {
		this.nombreAdmin = nombreAdmin;
	}





	public void setCedulaAdmin(String cedulaAdmin) {
		this.cedulaAdmin = cedulaAdmin;
	}





	public void setLoginAdmin(String loginAdmin) {
		this.loginAdmin = loginAdmin;
	}





	public void setContrasenaAdmin(String contrasenaAdmin) {
		this.contrasenaAdmin = contrasenaAdmin;
	}





	public void setNombreSedeEliminar(String nombreSedeEliminar) {
		this.nombreSedeEliminar = nombreSedeEliminar;
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
		if (comando.equals(REGISTRAR_SEDE)) {
			if (nombre.length() == 0 || ubicacion.length() == 0 || horarioLV.length() == 0 ||
				horarioLV.length() == 0 || horarioDF.length() == 0 || nombreAdmin.length() == 0 ||
				cedulaAdmin.length() == 0 ) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Usuario userAdmin = null;
				int respuesta = JOptionPane.showConfirmDialog(null, "¿El administrador ya tiene usuario registrado?", "Confirmación", JOptionPane.YES_NO_OPTION);
				if (respuesta == JOptionPane.YES_OPTION) {
					String loginIng = JOptionPane.showInputDialog("Ingrese el login: ");
					this.loginAdmin = loginIng;
					userAdmin = empresa.getUsuario(loginAdmin);

		        } else if (respuesta == JOptionPane.NO_OPTION) {
		        	String loginIng = JOptionPane.showInputDialog("Ingrese el login: ");
		        	String contrasenaIng = JOptionPane.showInputDialog("Ingrese la contraseña: ");
		        	this.loginAdmin = loginIng;
		        	this.contrasenaAdmin = contrasenaIng;
		        	userAdmin = new Usuario(loginAdmin, contrasenaAdmin, "admin");
					try {
						empresa.setUsuario(userAdmin);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        } else {
		            JOptionPane.showMessageDialog(null, "Ha cancelado la operación.");
		        }
				
				Map<String, String> horarios = new HashMap<>();
				horarios.put("L-V", horarioLV);
				horarios.put("S", horarioS);
				horarios.put("D-F", horarioDF);
				
				Administrador admin = new Administrador(nombreAdmin, cedulaAdmin, userAdmin, nombre);
				try {
					empresa.anadirSede(nombre, ubicacion, horarios, admin, userAdmin);
					JOptionPane.showMessageDialog(null, "La sede fue creada con exito.");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
			
		}
		else if (comando.equals(ELIMINAR_SEDE)) {
			if (nombreSedeEliminar.length() == 0) {
				JOptionPane.showMessageDialog(null, "No ha puesto la sede que se debe eliminar.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				try {
					empresa.eliminarSede(nombreSedeEliminar);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "La sede fue eliminada", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		
	}
	

}
