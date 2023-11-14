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
import modelo.Sede;
import modelo.Usuario;

@SuppressWarnings("serial")
public class RegistrarUsuario extends JPanel implements ActionListener{
	
	//Atributo 
	private EmpresaVehiculos empresa;
	private String nombre;
	private String cedula;
	private String login;
	private String contrasena;
	private String tipo;
	private String sede;
	
	
	// Constantes asociadas a eventos
	public final static String REGISTRAR_USUARIO = "Registrar usuario";
	
	
	
	// Atributos asociados a botones de interacci�n
	private JButton registrarUsuarioButton;
	
	
	//Metodos
	
	public RegistrarUsuario(EmpresaVehiculos empresa) {
		this.empresa = empresa;
		
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		
		
		JPanel registro = new JPanel();
		registro.setLayout(new GridBagLayout());
		registro.setOpaque(true);
		registro.setBackground(grisCasiBlanco);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        
        JLabel titleLabel = new JLabel("Registrar Usuario");
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
        
		
		JLabel cedulaLabel = setLabel("Cedula: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(cedulaLabel, gbc);
        JTextField cedulaTextField = new JTextField(20);
        this.cedula = cedulaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(cedulaTextField, gbc);
		
		JLabel loginLabel = setLabel("Login: ");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(loginLabel, gbc);
        JTextField loginTextField = new JTextField(20);
        this.login = loginTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(loginTextField, gbc);
		
		JLabel contrasenaLabel = setLabel("Contraseña: ");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(contrasenaLabel, gbc);
        JTextField contrasenaTextField = new JTextField(20);
        this.contrasena = contrasenaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(contrasenaTextField, gbc);
		
		
		JLabel tipoLabel = setLabel("Seleccione el tipo de empleado: ");
        gbc.gridx = 1;
        gbc.gridy = 1;
        registro.add(tipoLabel, gbc);
        String[] tipos = {"recepcionista", "limpiador"};
        
        JComboBox<String> tipoBox = new JComboBox<>(tipos);
        JTextField tipoTextField = new JTextField(20);
        tipoTextField.setEditable(false); 
        tipoBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = tipoBox.getSelectedItem().toString();
                tipoTextField.setText(seleccion);
                setTipo(seleccion);
                
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        registro.add(tipoBox, gbc);

		gbc.gridx = 1;
        gbc.gridy = 3;
        registro.add(tipoTextField, gbc);
        
        
        
        JLabel sedeLabel = setLabel("Sede donde va a trabajar");
        gbc.gridx = 1;
        gbc.gridy = 4;
        registro.add(sedeLabel, gbc);
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
                setSede(seleccion);
                
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        registro.add(sedesBox, gbc);

		gbc.gridx = 1;
        gbc.gridy = 6;
        registro.add(eliminarTextField, gbc);

        
        this.registrarUsuarioButton = new JButton("Registrar Usuario");
        registrarUsuarioButton.addActionListener(this);
        registrarUsuarioButton.setActionCommand(REGISTRAR_USUARIO);
        registrarUsuarioButton.setBackground(rojoOscuro);
        registrarUsuarioButton.setForeground(Color.WHITE);
        registrarUsuarioButton.setFont(fontBotones);
        gbc.gridx = 1;
        gbc.gridy = 7;
        registro.add(registrarUsuarioButton, gbc);
        
        registrarUsuarioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String nombreIng = nombreTextField.getText();
                String cedulaIng = cedulaTextField.getText();
                String loginIng = loginTextField.getText();
                String contrasenaIng = contrasenaTextField.getText();
                String tipoIng = getTipo();
                String sedeIng = getSede();
                

                setNombre(nombreIng);
                setCedula(cedulaIng);
                setLogin(loginIng);
                setContrasena(contrasenaIng);
                setTipo(tipoIng);
                setSede(sedeIng);
			}
        });
        
        
        add(registro, BorderLayout.CENTER);

		
	}
	
	
	
	
	public String getNombre() {
		return nombre;
	}




	public String getCedula() {
		return cedula;
	}




	public String getLogin() {
		return login;
	}




	public String getContrasena() {
		return contrasena;
	}




	public String getTipo() {
		return tipo;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public void setCedula(String cedula) {
		this.cedula = cedula;
	}




	public void setLogin(String login) {
		this.login = login;
	}




	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}




	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	
	

	public String getSede() {
		return sede;
	}




	public void setSede(String sede) {
		this.sede = sede;
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
		if (comando.equals(REGISTRAR_USUARIO)) {
			if (nombre.length() == 0 || cedula.length() == 0 || login.length() == 0 || contrasena.length() == 0 ||
					tipo.length() == 0 || sede.length() == 0) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar.", "Info", JOptionPane.INFORMATION_MESSAGE);
				
			}
			else {
				
				Usuario user = new Usuario(login, contrasena, tipo);
				
				try {
					empresa.agregarUsuario(user, cedula, sede, nombre);
					JOptionPane.showMessageDialog(null, "El usuario se registro con exito.", "Info", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		}
		
	}

}
