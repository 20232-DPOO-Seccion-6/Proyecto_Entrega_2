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

import modelo.ClienteNormal;
import modelo.Conductor;
import modelo.EmpresaVehiculos;
import modelo.Usuario;

@SuppressWarnings("serial")
public class PanelRegistroNuevoUsuario extends JPanel implements ActionListener {
	
	//Atributos 
	private String nombre;
	private String cedula;
	private String fechaNacimiento;
	private String celular;
	private String nacionalidad;
	private String correoElectronico;
	private String login;
	private String contrasena;
	private String numeroLicencia;
	private String paisLicencia;
	private String fechaVencimientoLicencia;
	private String numeroTarjeta;
	private String fechaVencimientoTarjeta;
	private String codigoSeguridad;
	private String tipoTarjeta;
	private EmpresaVehiculos empresa;
	
	

	// Constantes asociadas a eventos
	public final static String CREAR_CUENTA = "<html>Crear <br> cuenta</html>";
		
			
	// Atributos asociados a botones de interacci�n
	private JButton crearCuenta;
	
	public PanelRegistroNuevoUsuario(EmpresaVehiculos empresa) {
		this.empresa = empresa;
		setLayout(new BorderLayout());
		
		Color colorFondo = new Color(184, 185, 187);
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		
		
		JPanel totalCentro = new JPanel();
		totalCentro.setBackground(colorFondo);
		totalCentro.setLayout(null);
		
		JPanel izquierda = new JPanel();
		izquierda.setBackground(grisCasiBlanco);
		izquierda.setBounds(10,10, 400, 465);
		izquierda.setLayout(new GridBagLayout());
		izquierda.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(3, 3, 3, 3);
        
        JLabel nombreLabel = setLabel("Nombre: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        izquierda.add(nombreLabel, gbc);
        JTextField nombreTextField = new JTextField(20);
        this.nombre = nombreTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 1;
		izquierda.add(nombreTextField, gbc);
		
		JLabel cedulaLabel = setLabel("Cédula: ");
		gbc.gridx = 0;
        gbc.gridy = 2;
        izquierda.add(cedulaLabel, gbc);
        JTextField cedulaTextField = new JTextField(20);
        this.cedula = cedulaTextField.getText();
        gbc.gridx = 0;
        gbc.gridy = 3;
        izquierda.add(cedulaTextField, gbc);
        
        JLabel fechaNacimientoLabel = setLabel("Fecha Nacimiento (formato dd/mm/yyyy): ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        izquierda.add(fechaNacimientoLabel, gbc);
        JTextField fechaNacimientoTextField = new JTextField(20);
        this.fechaNacimiento = fechaNacimientoTextField.getText();
        gbc.gridx = 0;
        gbc.gridy = 5;
        izquierda.add(fechaNacimientoTextField, gbc);
        
        JLabel celularLabel = setLabel("Celular: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        izquierda.add(celularLabel, gbc);
        JTextField celularTextField = new JTextField(20);
        this.celular = celularTextField.getText();
        gbc.gridx = 0;
        gbc.gridy = 7;
        izquierda.add(celularTextField, gbc);
        
        JLabel nacionalidadLabel = setLabel("Nacionalidad: ");
        gbc.gridx = 0;
        gbc.gridy = 8;
        izquierda.add(nacionalidadLabel, gbc);
        JTextField nacionalidadTextField = new JTextField(20);
        this.nacionalidad = nacionalidadTextField.getText();
        gbc.gridx = 0;
        gbc.gridy = 9;
        izquierda.add(nacionalidadTextField, gbc);
        
        JLabel correoLabel = setLabel("Correo electronico: ");
        gbc.gridx = 0;
        gbc.gridy = 10;
        izquierda.add(correoLabel, gbc);
        JTextField correoTextField = new JTextField(20);
        this.correoElectronico = correoTextField.getText();
        gbc.gridx = 0;
        gbc.gridy = 11;
        izquierda.add(correoTextField, gbc);
        
        JLabel loginLabel = setLabel("Usuario: ");
        gbc.gridx = 0;
        gbc.gridy = 12;
        izquierda.add(loginLabel, gbc);
        JTextField loginTextField = new JTextField(20);
        this.login = loginTextField.getText();
        gbc.gridx = 0;
        gbc.gridy = 13;
        izquierda.add(loginTextField, gbc);
        
        JLabel contrasenaLabel = setLabel("Contraseña: ");
        gbc.gridx = 0;
        gbc.gridy = 14;
        izquierda.add(contrasenaLabel, gbc);
        JTextField contrasenaTextField = new JTextField(20);
        this.contrasena = contrasenaTextField.getText();
        gbc.gridx = 0;
        gbc.gridy = 15;
        izquierda.add(contrasenaTextField, gbc);
        
        
    
        JPanel derecha = new JPanel();
		derecha.setBackground(grisCasiBlanco);
		derecha.setBounds(420,10, 400, 465);
		derecha.setLayout(new GridBagLayout());
		derecha.setOpaque(true);
		GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(3, 3, 3, 3);
        
        
		JLabel numeroLicenciaLabel = setLabel("Numero de Licencia: ");
		gbc1.gridx = 0;
        gbc1.gridy = 0;
        derecha.add(numeroLicenciaLabel, gbc1);
        JTextField numeroLicenciaTextField = new JTextField(20);
        this.numeroLicencia = numeroLicenciaTextField.getText();
        gbc1.gridx = 0;
        gbc1.gridy = 1;
        derecha.add(numeroLicenciaTextField, gbc1);
        
        JLabel paisExpedicionLicenciaLabel = setLabel("Pais de expedición: ");
        gbc1.gridx = 0;
        gbc1.gridy = 2;
        derecha.add(paisExpedicionLicenciaLabel, gbc1);
        JTextField paisExpedicionLicenciaTextField = new JTextField(20);
        this.paisLicencia = paisExpedicionLicenciaTextField.getSelectedText();
        gbc1.gridx = 0;
        gbc1.gridy = 3;
        derecha.add(paisExpedicionLicenciaTextField, gbc1);
        
        JLabel fechaVencimientoLicenciaLabel = setLabel("Fecha de vencimiento de su licencia (formato dd/mm/yyyy): ");
        gbc1.gridx = 0;
        gbc1.gridy = 4;
        derecha.add(fechaVencimientoLicenciaLabel, gbc1);
        JTextField fechaVencimientoLicenciaTextField = new JTextField(20);
        this.fechaVencimientoLicencia = fechaVencimientoLicenciaTextField.getText();
        gbc1.gridx = 0;
        gbc1.gridy = 5;
        derecha.add(fechaVencimientoLicenciaTextField, gbc1);
        
        JLabel numeroTarjetaLabel = setLabel("Numero de la tarjeta: ");
        gbc1.gridx = 0;
        gbc1.gridy = 6;
        derecha.add(numeroTarjetaLabel, gbc1);
        JTextField numeroTarjetaTextField = new JTextField(20);
        this.numeroTarjeta = numeroTarjetaTextField.getText();
        gbc1.gridx = 0;
        gbc1.gridy = 7;
        derecha.add(numeroTarjetaTextField, gbc1);
        
        JLabel fechaVencimientoTarjetaLabel = setLabel("Fecha de vencimiento de su tarjeta (formato dd/mm/yyyy): ");
        gbc1.gridx = 0;
        gbc1.gridy = 8;
        derecha.add(fechaVencimientoTarjetaLabel, gbc1);
        JTextField fechaVencimientoTarjetaTextField = new JTextField(20);
        this.fechaVencimientoTarjeta = fechaVencimientoTarjetaTextField.getText();
        gbc1.gridx = 0;
        gbc1.gridy = 9;
        derecha.add(fechaVencimientoTarjetaTextField, gbc1);
        
        JLabel codigoSeguridadLabel = setLabel("Codigo de seguridad de la tarjeta: ");
        gbc1.gridx = 0;
        gbc1.gridy = 10;
        derecha.add(codigoSeguridadLabel, gbc1);
        JTextField codigoSeguridadTextField = new JTextField(20);
        this.codigoSeguridad = codigoSeguridadTextField.getText();
        gbc1.gridx = 0;
        gbc1.gridy = 11;
        derecha.add(codigoSeguridadTextField, gbc1);
        
        JLabel tipoTarjetaLabel = setLabel("Tipo de tarjeta (Visa, MasterCard): ");
        gbc1.gridx = 0;
        gbc1.gridy = 12;
        derecha.add(tipoTarjetaLabel, gbc1);
        JTextField tipoTarjetaTextField = new JTextField(20);
        this.tipoTarjeta = tipoTarjetaTextField.getText();
        gbc1.gridx = 0;
        gbc1.gridy = 13;
        derecha.add(tipoTarjetaTextField, gbc1);
        
        
        this.crearCuenta = new JButton(CREAR_CUENTA);
        crearCuenta.addActionListener(this);
        crearCuenta.setActionCommand(CREAR_CUENTA);
        crearCuenta.setBackground(rojoOscuro);
        crearCuenta.setForeground(Color.WHITE);
        crearCuenta.setFont(fontBotones);
        crearCuenta.setBounds(850, 200, 100, 80);
		totalCentro.add(crearCuenta, gbc);
        
		crearCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String nombreIng = nombreTextField.getText();
                String cedulaIng = cedulaTextField.getText();
                String fechaNacimientoIng = fechaNacimientoTextField.getText();
                String celularIng = celularTextField.getText();
                String nacionalidadIng = nacionalidadTextField.getText();
                String correoElectronicoIng = correoTextField.getText();
                String loginIng = loginTextField.getText();
                String contrasenaIng = contrasenaTextField.getText();
                String numeroLicenciaIng = numeroLicenciaTextField.getText();
                String paisLicenciaIng = paisExpedicionLicenciaTextField.getText();
                String fechaVencimientoLicenciaIng = fechaVencimientoLicenciaTextField.getText();
                String numeroTarjetaIng = numeroTarjetaTextField.getText();
                String fechaVencimientoTarjetaIng = fechaVencimientoTarjetaTextField.getText();
                String codigoSeguridadIng = codigoSeguridadTextField.getText();
                String tipoTarjetaIng = tipoTarjetaTextField.getText();
                
                setNombre(nombreIng);
                setCedula(cedulaIng);
                setFechaNacimiento(fechaNacimientoIng);
                setCelular(celularIng);
                setNacionalidad(nacionalidadIng);
                setCorreoElectronico(correoElectronicoIng);
                setLogin(loginIng);
                setContrasena(contrasenaIng);
                setNumeroLicencia(numeroLicenciaIng);
                setPaisLicencia(paisLicenciaIng);
                setFechaVencimientoLicencia(fechaVencimientoLicenciaIng);
                setNumeroTarjeta(numeroTarjetaIng);
                setFechaVencimientoTarjeta(fechaVencimientoTarjetaIng);
                setCodigoSeguridad(codigoSeguridadIng);
                setTipoTarjeta(tipoTarjetaIng);
                
                }
        });
        
        
		totalCentro.add(izquierda);
		totalCentro.add(derecha);
		add(totalCentro, BorderLayout.CENTER);
		
		
		
		
		
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
	
	
	public String getNombre() {
		return nombre;
	}


	public String getCedula() {
		return cedula;
	}


	public String getFechaNacimiento() {
		return fechaNacimiento;
	}


	public String getCelular() {
		return celular;
	}


	public String getNacionalidad() {
		return nacionalidad;
	}


	public String getCorreoElectronico() {
		return correoElectronico;
	}


	public String getLogin() {
		return login;
	}


	public String getContrasena() {
		return contrasena;
	}


	public String getNumeroLicencia() {
		return numeroLicencia;
	}


	public String getPaisLicencia() {
		return paisLicencia;
	}


	public String getFechaVencimientoLicencia() {
		return fechaVencimientoLicencia;
	}


	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}


	public String getFechaVencimientoTarjeta() {
		return fechaVencimientoTarjeta;
	}


	public String getCodigoSeguridad() {
		return codigoSeguridad;
	}


	public String getTipoTarjeta() {
		return tipoTarjeta;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public void setCelular(String celular) {
		this.celular = celular;
	}


	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	public void setNumeroLicencia(String numeroLicencia) {
		this.numeroLicencia = numeroLicencia;
	}


	public void setPaisLicencia(String paisLicencia) {
		this.paisLicencia = paisLicencia;
	}


	public void setFechaVencimientoLicencia(String fechaVencimientoLicencia) {
		this.fechaVencimientoLicencia = fechaVencimientoLicencia;
	}


	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}


	public void setFechaVencimientoTarjeta(String fechaVencimientoTarjeta) {
		this.fechaVencimientoTarjeta = fechaVencimientoTarjeta;
	}


	public void setCodigoSeguridad(String codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}


	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}


	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(CREAR_CUENTA)) {
			if (nombre.length() == 0 || cedula.length() == 0 || fechaNacimiento.length() == 0 || celular.length() == 0 
				|| nacionalidad.length() == 0 || correoElectronico.length() == 0 || login.length() == 0 || contrasena.length() == 0 || numeroLicencia.length() == 0 ||
				paisLicencia.length() == 0 || fechaVencimientoLicencia.length() == 0 || numeroTarjeta.length() == 0 || fechaVencimientoTarjeta.length() == 0 || codigoSeguridad.length() == 0
				|| tipoTarjeta.length() == 0) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Usuario user = new Usuario(login, contrasena, "cliente");
				try {
					empresa.agregarUsuarioCliente(user);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				empresa.agregarConductor(new Conductor(nombre,cedula,numeroLicencia,paisLicencia,fechaVencimientoLicencia, 
						"fotico.png", celular, correoElectronico));
				try {
					empresa.agregarCliente(new ClienteNormal(nombre,cedula,celular,fechaNacimiento,nacionalidad,
							numeroLicencia,paisLicencia, fechaVencimientoLicencia, numeroTarjeta,
							fechaVencimientoTarjeta,codigoSeguridad,tipoTarjeta, user,correoElectronico, "libre"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "El cliente ha sido registrado", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
		
		
	}

	
}
