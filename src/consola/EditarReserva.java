package consola;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import modelo.EmpresaVehiculos;
import modelo.Reserva;

@SuppressWarnings("serial")
public class EditarReserva extends JPanel implements ActionListener{
	
	//Atributos 
	private EmpresaVehiculos empresa;
	private Reserva reserva;
	private String cedula;
	private String idReserva;
	
	
	public final static String VALIDAR = "Validar Reserva";
	
	
	private JButton validarReservaButton;
	
	public EditarReserva(EmpresaVehiculos empresa) {
		
		this.empresa = empresa;
		this.reserva = null;
		setLayout(new BorderLayout());
		
		
		Color colorFondo = new Color(184, 185, 187);
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);

		JPanel total = new JPanel();
		total.setLayout(null);
		total.setBackground(colorFondo);
		
		
		JPanel datosBasicos = new JPanel();
		datosBasicos.setLayout(new GridBagLayout());
		datosBasicos.setOpaque(true);
		datosBasicos.setBackground(grisCasiBlanco);
		datosBasicos.setBounds(10, 10, 350, 400);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        
		JLabel cedulaClienteLabel = setLabel("<html> Info cliente <br> y reserva</html>");
		cedulaClienteLabel.setFont(fontMonserratBold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Ocupa dos columnas
        datosBasicos.add(cedulaClienteLabel, gbc);
        
        JLabel cedulaLabel = setLabel("Cedula: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        datosBasicos.add(cedulaLabel, gbc);
        JTextField cedulaTextField = new JTextField(20);
        this.cedula = cedulaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        datosBasicos.add(cedulaTextField, gbc);
        
        JLabel idReservaLabel = setLabel("Id Reserva: ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        datosBasicos.add(idReservaLabel, gbc);
        JTextField idReservaTextField = new JTextField(20);
        this.idReserva = idReservaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        datosBasicos.add(idReservaTextField, gbc);
        
        
        this.validarReservaButton = new JButton("Validar Reserva");
        validarReservaButton.addActionListener(this);
        validarReservaButton.setActionCommand(VALIDAR);
        validarReservaButton.setBackground(rojoOscuro);
        validarReservaButton.setForeground(Color.WHITE);
        validarReservaButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        datosBasicos.add(validarReservaButton, gbc);
        
        validarReservaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String cedulaIng = cedulaTextField.getText();
                String idReservaIng = idReservaTextField.getText();
                
                setCedula(cedulaIng);
                setIdReserva(idReservaIng); 
                reserva = empresa.validarReserva(cedula,idReserva);
                setReserva(reserva);
			}
        });
        
        
        
        
        JPanel centro = new JPanel();
        centro.setLayout(new BorderLayout());
        centro.setOpaque(true);
        centro.setBackground(grisCasiBlanco);
        centro.setBounds(370, 10, 580, 400);
        
        JLabel titleCentro = setLabel("Editar Reserva");
		cedulaClienteLabel.setFont(fontMonserratBold);
        centro.add(titleCentro, BorderLayout.NORTH);
        
        
        JTabbedPane cambios = new JTabbedPane();
        JPanel editarFechas = new EditarFechas(this);
        JPanel editarSedes = new EditarSedes(this);
        cambios.addTab("Fechas Inicio/Fin", editarFechas);
        cambios.addTab("Sede Inicio/Fin", editarSedes);
        
        
        centro.add(cambios, BorderLayout.CENTER);

        total.add(datosBasicos);
        total.add(centro);
        add(total, BorderLayout.CENTER);
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
	
	
	
	
	public Reserva getReserva() {
		return reserva;
	}



	public String getCedula() {
		return cedula;
	}



	public String getIdReserva() {
		return idReserva;
	}



	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}



	public void setCedula(String cedula) {
		this.cedula = cedula;
	}



	public void setIdReserva(String idReserva) {
		this.idReserva = idReserva;
	}
	
	


	public EmpresaVehiculos getEmpresa() {
		return empresa;
	}





	public void setEmpresa(EmpresaVehiculos empresa) {
		this.empresa = empresa;
	}





	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(VALIDAR)) {
			if (reserva == null) {
				JOptionPane.showMessageDialog(null, "La reserva no existe, hay datos erroneos o ya ha finalizado", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, " La informacion de la reserva es: \n " + reserva.getInformacion(), "Info", JOptionPane.INFORMATION_MESSAGE);
				setReserva(reserva);
				
			}
			
		}
		
		
	}
	

}
