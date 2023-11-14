package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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


@SuppressWarnings("serial")
public class PanelPrincipal extends JPanel implements ActionListener {

	//Atributos 
	private Ventana ventanaPrincipal;
	private String usuario = "";
	private String contrasena = "";
	private EmpresaVehiculos empresa;
	
	// Constantes asociadas a eventos
	public final static String CREAR_CUENTA = "Crear cuenta";
	public final static String LOGIN = "Iniciar sesion";
		
	// Atributos asociados a botones de interacci�n
	private JButton crearCuenta;
	private JButton loginButton;
	
	
	//Metodos
	
	public PanelPrincipal (Ventana ventanaPrincipal) {
		
		setLayout(new BorderLayout());
		this.ventanaPrincipal = ventanaPrincipal;
		this.empresa = ventanaPrincipal.getEmpresa();
		
		// Establecer los subpaneles dentro del panel para visualizaci�n adecuada
		
		Color colorBanda = new Color(184, 25, 25);
		Color colorFondo = new Color(184, 185, 187);
		Font fontArial = new Font("Arial", Font.BOLD, 25);
		Font fontMonserrat = new Font("Monserrat", Font.ITALIC, 20);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		Font fontMonserratButtons = new Font("Monserrat", Font.PLAIN, 15);
				
		JPanel izquierda = new JPanel();
		izquierda.setPreferredSize(new Dimension(80, ventanaPrincipal.getHeight()));
		izquierda.setBackground(colorFondo);
		izquierda.setOpaque(true);
		
		JPanel banda = new JPanel();
		banda.setPreferredSize(new Dimension(350, ventanaPrincipal.getHeight()));
		banda.setBackground(colorBanda);
		banda.setOpaque(true);
		banda.setLayout(null);
		
		JLabel labelLogo = new JLabel();
		labelLogo.setText("RentaDrive");
		labelLogo.setBounds(50, 10, 200, 100);
		labelLogo.setForeground(Color.WHITE);
		labelLogo.setFont(fontMonserrat);
		banda.add(labelLogo);
		
		JLabel labelBanda = new JLabel();
		labelBanda.setText("<html>¿Quieres rentar un vehículo? <br> Y no tienes una cuenta.</html>");
        labelBanda.setBounds(30, 50, 200, 200); // Define coordenadas x, y, ancho y alto para el JLabel
        labelBanda.setForeground(Color.WHITE);
        labelBanda.setFont(fontArial);
        banda.add(labelBanda);
		
        this.crearCuenta = new JButton(CREAR_CUENTA);
        crearCuenta.addActionListener(this);
		crearCuenta.setActionCommand(CREAR_CUENTA);
		crearCuenta.setBackground(Color.WHITE);
		crearCuenta.setForeground(Color.BLACK);
		crearCuenta.setFont(fontBotones);
		crearCuenta.setBounds(60, 250, 200, 30);
		banda.add(crearCuenta);
		
		
		JPanel derecha = new JPanel();
		derecha.setPreferredSize(new Dimension(ventanaPrincipal.getWidth() - 350 - 80, ventanaPrincipal.getHeight()));
		derecha.setBackground(colorFondo);
		derecha.setLayout(null);
		derecha.setOpaque(true);
		
		JPanel login = new JPanel();
		Color grisCasiBlanco = new Color(220,220,220);
		login.setBounds(150, 150, 300, 300);
		login.setBackground(grisCasiBlanco);
		login.setLayout(new GridBagLayout());
		login.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 6, 6, 6);
        
		JLabel loginPrint = new JLabel();
		loginPrint.setText("LOGIN");
		loginPrint.setFont(fontMonserratBold);
		loginPrint.setForeground(Color.BLACK);
		loginPrint.setHorizontalAlignment(JLabel.CENTER);
		gbc.gridx = 0;
        gbc.gridy = 0;
		login.add(loginPrint, gbc);
		
		JLabel usuarioPrint = new JLabel();
		usuarioPrint.setText("Usuario: ");
		usuarioPrint.setFont(fontMonserratButtons);
		usuarioPrint.setForeground(Color.BLACK);
		gbc.gridx = 0;
        gbc.gridy = 1;
		login.add(usuarioPrint, gbc);
		JTextField textFieldUsuario = new JTextField(20);
		
		this.usuario = textFieldUsuario.getText();
		gbc.gridx = 0;
        gbc.gridy = 2;
		login.add(textFieldUsuario, gbc);
		
		JLabel contrasenaPrint = new JLabel();
		contrasenaPrint.setText("Contraseña: ");
		contrasenaPrint.setFont(fontMonserratButtons);
		contrasenaPrint.setForeground(Color.BLACK);
		gbc.gridx = 0;
        gbc.gridy = 3;
		login.add(contrasenaPrint, gbc);
		JTextField textFieldContrasena = new JTextField(20);
		gbc.gridx = 0;
        gbc.gridy = 4;
        this.contrasena = textFieldContrasena.getText();
		login.add(textFieldContrasena, gbc);
		
		
		this.loginButton = new JButton(LOGIN);
		loginButton.addActionListener(this);
		loginButton.setActionCommand(LOGIN);
		loginButton.setBackground(colorBanda);
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(fontBotones);
		gbc.gridx = 0;
        gbc.gridy = 5;
		login.add(loginButton, gbc);
		
		derecha.add(login);
		
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String usuarioIng = textFieldUsuario.getText();
                String contrasenaIng = textFieldContrasena.getText();
                setUsuario(usuarioIng);
                setContrasena(contrasenaIng);
			}
        });
		
		
		JPanel panelMenu = new JPanel();
		panelMenu.setLayout(new BorderLayout());
		panelMenu.setBackground(colorFondo);
		
		
		panelMenu.add(banda, BorderLayout.CENTER);
		panelMenu.add(izquierda, BorderLayout.WEST);
		panelMenu.add(derecha, BorderLayout.EAST);
		add(panelMenu);
		
		
		
	}
	
	public void setUsuario(String user) {
		this.usuario = user;
	}
	
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public void actionPerformed(ActionEvent pEvento) {
		
		String comando = pEvento.getActionCommand();
		
		if (comando.equals(CREAR_CUENTA)) {
			
			//Mostrar panel para nuevo cliente
			ventanaPrincipal.getContentPane().removeAll();
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
            
            JPanel panelRegistroNuevoUsuario = new PanelRegistroNuevoUsuario(empresa);
            JPanel panelFondoGeneral = new PanelFondoGeneral(ventanaPrincipal, panelRegistroNuevoUsuario);
            ventanaPrincipal.add(panelFondoGeneral, BorderLayout.CENTER);
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
            
		}
		else if(comando.equals(LOGIN)) {
			
			if (usuario.length() == 0 || contrasena.length() == 0) {
				JOptionPane.showMessageDialog(null, "El usuario o contraseña no han sido ingresados", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				String rol = empresa.verificarUsuario(usuario, contrasena);
				if (rol.equals("")) {
					JOptionPane.showMessageDialog(null, "El usuario o contraseña no son correctos.", "Error", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					if (rol.equals("adminGeneral")) {
						
						//Mostrar panel de opciones del admin general
						ventanaPrincipal.getContentPane().removeAll();
			            ventanaPrincipal.revalidate();
			            ventanaPrincipal.repaint();
			            
			            JPanel panelOpcionesAdminGeneral =  null;
						try {
							panelOpcionesAdminGeneral = new PanelOpcionesAdminGeneral(empresa);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            JPanel panelFondoGeneral = new PanelFondoGeneral(ventanaPrincipal, panelOpcionesAdminGeneral);
			            ventanaPrincipal.add(panelFondoGeneral, BorderLayout.CENTER);
			            ventanaPrincipal.revalidate();
			            ventanaPrincipal.repaint();
			            
						
					}
					else if (rol.equals("admin")) {
						//Mostrar panel de opciones del admin general
						ventanaPrincipal.getContentPane().removeAll();
			            ventanaPrincipal.revalidate();
			            ventanaPrincipal.repaint();
			            
			            JPanel panelOpcionesAdminSede =  null;
						panelOpcionesAdminSede = new PanelOpcionesAdminSede(empresa, usuario);
			            JPanel panelFondoGeneral = new PanelFondoGeneral(ventanaPrincipal, panelOpcionesAdminSede);
			            ventanaPrincipal.add(panelFondoGeneral, BorderLayout.CENTER);
			            ventanaPrincipal.revalidate();
			            ventanaPrincipal.repaint();
						
						
					}
					else if(rol.equals("recepcion")) {
						//Mostrar panel de opciones del admin general
						ventanaPrincipal.getContentPane().removeAll();
			            ventanaPrincipal.revalidate();
			            ventanaPrincipal.repaint();
			            
			            JPanel panelOpcionesRecepcion =  null;
			            panelOpcionesRecepcion = new PanelOpcionesRecepcion(empresa);
			            JPanel panelFondoGeneral = new PanelFondoGeneral(ventanaPrincipal, panelOpcionesRecepcion);
			            ventanaPrincipal.add(panelFondoGeneral, BorderLayout.CENTER);
			            ventanaPrincipal.revalidate();
			            ventanaPrincipal.repaint();
						
						
					}
					else if(rol.equals("cliente")) {
						//Mostrar panel de opciones del admin general
						ventanaPrincipal.getContentPane().removeAll();
			            ventanaPrincipal.revalidate();
			            ventanaPrincipal.repaint();
			            
			            JPanel panelOpcionesCliente =  null;
			            panelOpcionesCliente = new PanelOpcionesCliente(empresa, usuario);
			            JPanel panelFondoGeneral = new PanelFondoGeneral(ventanaPrincipal, panelOpcionesCliente);
			            ventanaPrincipal.add(panelFondoGeneral, BorderLayout.CENTER);
			            ventanaPrincipal.revalidate();
			            ventanaPrincipal.repaint();
					}
					else if(rol.equals("limpiador")) {
						//Mostrar panel de opciones del admin general
						ventanaPrincipal.getContentPane().removeAll();
			            ventanaPrincipal.revalidate();
			            ventanaPrincipal.repaint();
			            
			            JPanel panelOpcionesLimpiador =  null;
			            panelOpcionesLimpiador = new PanelOpcionesLimpiador(empresa, usuario);
			            JPanel panelFondoGeneral = new PanelFondoGeneral(ventanaPrincipal, panelOpcionesLimpiador);
			            ventanaPrincipal.add(panelFondoGeneral, BorderLayout.CENTER);
			            ventanaPrincipal.revalidate();
			            ventanaPrincipal.repaint();
						
						
					}
				}
				
				
			}
			
			
			
			
			
		}
		
	}
}
