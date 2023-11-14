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

import modelo.Conductor;
import modelo.EmpresaVehiculos;
import modelo.Reserva;

@SuppressWarnings("serial")
public class ConductorExtra extends JPanel implements ActionListener{
	
	//Atributo 
	private RegistrarEntregaVehiculo panel;
	private EmpresaVehiculos empresa;
	private Reserva reserva;
	private String nombre;
	private String cedula;
	private String numeroLicencia;
	private String paisLicencia;
	private String fechaVencimientoLicencia;
	private String foto;
	private String celular;
	private String correo;
	
	public final static String REGISTRAR = "Registrar Conductor";
	
	
	private JButton registrarConductorButton;
	
	
	//Metodos
	public ConductorExtra(RegistrarEntregaVehiculo panel) {
		this.panel = panel;
		this.reserva = panel.getReserva();
		this.empresa = panel.getEmpresa();
		setLayout(new BorderLayout());
		
	
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		
		JPanel registro = new JPanel();
		registro.setLayout(new GridBagLayout());
		registro.setOpaque(true);
		registro.setBackground(grisCasiBlanco);
		registro.setBounds(10, 10, 300, 300);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        
        JLabel nombreLabel = setLabel("Nombre: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        registro.add(nombreLabel, gbc);
        JTextField nombreTextField = new JTextField(20);
        this.nombre = nombreTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 1;
		registro.add(nombreTextField, gbc);
		
		JLabel cedulaLabel = setLabel("Cedula: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        registro.add(cedulaLabel, gbc);
        JTextField cedulaTextField = new JTextField(20);
        this.cedula = cedulaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 3;
		registro.add(cedulaTextField, gbc);
		
		JLabel numeroLicenciaLabel = setLabel("Numero Licencia: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        registro.add(numeroLicenciaLabel, gbc);
        JTextField numeroLicenciaTextField = new JTextField(20);
        this.numeroLicencia = numeroLicenciaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 5;
		registro.add(numeroLicenciaTextField, gbc);
		
		JLabel paisLicenciaLabel = setLabel("Pais expedicion licencia: ");
        gbc.gridx = 0;
        gbc.gridy = 6;
        registro.add(paisLicenciaLabel, gbc);
        JTextField paisLicenciaTextField = new JTextField(20);
        this.paisLicencia = paisLicenciaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 7;
		registro.add(paisLicenciaTextField, gbc);
		
		JLabel fechaVencimientoLicenciaLabel = setLabel("Vencimiento licencia (dd/mm/yyyy): ");
        gbc.gridx = 1;
        gbc.gridy = 0;
        registro.add(fechaVencimientoLicenciaLabel, gbc);
        JTextField fechaVencimientoLicenciaTextField = new JTextField(20);
        this.fechaVencimientoLicencia = fechaVencimientoLicenciaTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 1;
		registro.add(fechaVencimientoLicenciaTextField, gbc);
		
		JLabel fotoLabel = setLabel("Foto (path al archivo): ");
        gbc.gridx = 1;
        gbc.gridy = 2;
        registro.add(fotoLabel, gbc);
        JTextField fotoTextField = new JTextField(20);
        this.foto = fotoTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 3;
		registro.add(fotoTextField, gbc);
		
		JLabel celularLabel = setLabel("Celular: ");
        gbc.gridx = 1;
        gbc.gridy = 4;
        registro.add(celularLabel, gbc);
        JTextField celularTextField = new JTextField(20);
        this.celular = celularTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 5;
		registro.add(celularTextField, gbc);
		
		JLabel correoLabel = setLabel("Correo: ");
        gbc.gridx = 1;
        gbc.gridy = 6;
        registro.add(correoLabel, gbc);
        JTextField correoTextField = new JTextField(20);
        this.correo = correoTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 7;
		registro.add(correoTextField, gbc);
		
		
		this.registrarConductorButton = new JButton("Registrar Conductor");
		registrarConductorButton.addActionListener(this);
		registrarConductorButton.setActionCommand(REGISTRAR);
		registrarConductorButton.setBackground(rojoOscuro);
		registrarConductorButton.setForeground(Color.WHITE);
		registrarConductorButton.setFont(fontBotones);
        gbc.gridx = 1;
        gbc.gridy = 8;
        registro.add(registrarConductorButton, gbc);
        
        
        
        registrarConductorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reserva reserva = panel.getReserva();
                String nombreIng = nombreTextField.getText();
                String numeroLicenciaIng = numeroLicenciaTextField.getText();
                String paisLicenciaIng = paisLicenciaTextField.getText();
                String fechaVencimientoLicenciaIng = fechaVencimientoLicenciaTextField.getText();
                String fotoIng = fotoTextField.getText();
                String celularIng = celularTextField.getText();
                String correoIng = correoTextField.getText();
                String cedulaIng = cedulaTextField.getText();
                
                setReserva(reserva);
                setNombre(nombreIng);
                setNumeroLicencia(numeroLicenciaIng);
                setPaisLicencia(paisLicenciaIng);
                setFechaVencimientoLicencia(fechaVencimientoLicenciaIng);
                setFoto(fotoIng);
                setCelular(celularIng);
                setCorreo(correoIng);
                setCedula(cedulaIng);
                
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



	public String getNumeroLicencia() {
		return numeroLicencia;
	}



	public String getPaisLicencia() {
		return paisLicencia;
	}



	public String getFechaVencimientoLicencia() {
		return fechaVencimientoLicencia;
	}



	public String getFoto() {
		return foto;
	}



	public String getCelular() {
		return celular;
	}



	public String getCorreo() {
		return correo;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setCedula(String cedula) {
		this.cedula = cedula;
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



	public void setFoto(String foto) {
		this.foto = foto;
	}



	public void setCelular(String celular) {
		this.celular = celular;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
	


	public Reserva getReserva() {
		return reserva;
	}



	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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
		if (comando.equals(REGISTRAR)) {
			if (nombre.length()==0 || cedula.length()==0 || numeroLicencia.length()==0 || 
				paisLicencia.length()==0 || fechaVencimientoLicencia.length()==0 || 
				foto.length()==0 || celular.length()==0 || correo.length()==0 || reserva == null) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar. Asegurese de validar reserva", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Conductor conduAdicional = new Conductor(nombre,cedula,numeroLicencia,paisLicencia,fechaVencimientoLicencia,
						foto,celular,correo);
				
				try {
					reserva.setConductorAdicional(conduAdicional, true);
					empresa.guardarReserva(reserva);
					setReserva(reserva);
					JOptionPane.showMessageDialog(null, "Fue registrado con exito", "Info", JOptionPane.INFORMATION_MESSAGE);
					panel.setReserva(reserva);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		}
		
	}
	
	

}
