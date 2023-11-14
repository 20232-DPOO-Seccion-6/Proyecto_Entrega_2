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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Categoria;
import modelo.ClienteNormal;
import modelo.Conductor;
import modelo.EmpresaVehiculos;
import modelo.Reserva;
import modelo.Sede;
import modelo.Vehiculo;

@SuppressWarnings("serial")
public class CrearReserva extends JPanel implements ActionListener{
	
	//Atributos 
	private EmpresaVehiculos empresa;
	private String fechaInicial;
	private String fechaFinal;
	private String categoria;
	private String sedeInicial;
	private String sedeFinal;
	private String login;
	
	public final static String CREAR_RESERVA = "Crear Reserva";
	
	private JButton crearReservaButton;
	
	
	
	//Metodos
	public CrearReserva(EmpresaVehiculos empresa, String login) {
		
		this.empresa = empresa;
		this.login = login;
		setLayout(new BorderLayout());
		
		Color colorFondo = new Color(184, 185, 187);
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		
		
		JPanel total =  new JPanel();
		total.setLayout(null);
		total.setBackground(colorFondo);
		
		JPanel info = new JPanel();
		info.setLayout(new GridBagLayout());
		info.setOpaque(true);
		info.setBackground(grisCasiBlanco);
		info.setBounds(10, 10, 800, 400);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        
        JLabel titleLabel = new JLabel("Crear Reserva");
        titleLabel.setFont(fontMonserratBold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Ocupa dos columnas
        info.add(titleLabel, gbc);
        
        
        JLabel fechaInicialLabel = setLabel("Fecha inicio (dd/mm/yyyy): ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        info.add(fechaInicialLabel, gbc);
        JTextField fechaInicialTextField = new JTextField(20);
        this.fechaInicial = fechaInicialTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        info.add(fechaInicialTextField, gbc);
        
        
        JLabel fechaFinalLabel = setLabel("Fecha final (dd/mm/yyyy): ");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        info.add(fechaFinalLabel, gbc);
        JTextField fechaFinalTextField = new JTextField(20);
        this.fechaFinal = fechaFinalTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        info.add(fechaFinalTextField, gbc);
		
        
        JLabel categoriaLabel = new JLabel("Categoria: ");
		gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        info.add(categoriaLabel, gbc);
        
        Map<String, Categoria> posiblesCategorias = empresa.getCategorias();
		String[] arrayCategorias = posiblesCategorias.keySet().toArray(new String[0]);
		
		JComboBox<String> categoriaBox = new JComboBox<>(arrayCategorias);
        JTextField categoriaTextField = new JTextField(20);
        categoriaTextField.setEditable(false); 
        categoriaBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = categoriaBox.getSelectedItem().toString();
                categoriaTextField.setText(seleccion);
                setCategoria(seleccion);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  
        info.add(categoriaBox, gbc);
        
        this.categoria = categoriaTextField.getText();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;  
        info.add(categoriaTextField, gbc);
        
        
        ArrayList<Sede> posiblesSedes = empresa.getSedes();
		String[] sedes = new String[posiblesSedes.size()];
		for (int i = 0; i<posiblesSedes.size(); i++) {
			sedes[i] = posiblesSedes.get(i).getNombre();	
		}
		
		JLabel sedeLabel = new JLabel("Sede Inicial: ");
		gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        info.add(sedeLabel, gbc);
		
        JComboBox<String> sedesBox = new JComboBox<>(sedes);
        JTextField sedeTextField = new JTextField(20);
        sedeTextField.setEditable(false); 
        sedesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = sedesBox.getSelectedItem().toString();
                sedeTextField.setText(seleccion);
                setSedeInicial(seleccion);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  
        info.add(sedesBox, gbc);
        
        this.sedeInicial = sedeTextField.getText();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  
        info.add(sedeTextField, gbc);
        
        JLabel sedeLabel1 = new JLabel("Sede Final: ");
		gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        info.add(sedeLabel1, gbc);
		
        JComboBox<String> sedesBox1 = new JComboBox<>(sedes);
        JTextField sedeTextField1 = new JTextField(20);
        sedeTextField1.setEditable(false); 
        sedesBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = sedesBox1.getSelectedItem().toString();
                sedeTextField1.setText(seleccion);
                setSedeFinal(seleccion);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  
        info.add(sedesBox1, gbc);
        
        this.sedeFinal = sedeTextField1.getText();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  
        info.add(sedeTextField1, gbc);
        
        
        this.crearReservaButton = new JButton("Crear Reserva");
        crearReservaButton.addActionListener(this);
        crearReservaButton.setActionCommand(CREAR_RESERVA);
        crearReservaButton.setBackground(rojoOscuro);
        crearReservaButton.setForeground(Color.WHITE);
        crearReservaButton.setFont(fontBotones);
        gbc.gridx = 1;
        gbc.gridy = 7;
        info.add(crearReservaButton, gbc);
        
        crearReservaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String fechaInicialIng = fechaInicialTextField.getText();
                String fechaFinalIng = fechaFinalTextField.getText();
                String categoriaIng = getCategoria();
                String sedeInicialIng = getSedeInicial();
                String sedeFinalIng = getSedeFinal();
                
                setFechaInicial(fechaInicialIng);
                setFechaFinal(fechaFinalIng);
                setCategoria(categoriaIng);
                setSedeInicial(sedeInicialIng);
                setSedeFinal(sedeFinalIng);

			}
        });
        
        total.add(info);
        add(total, BorderLayout.CENTER);
        
        
		
	}
	
	
	
	
	public String getFechaInicial() {
		return fechaInicial;
	}




	public String getFechaFinal() {
		return fechaFinal;
	}




	public String getCategoria() {
		return categoria;
	}




	public String getSedeInicial() {
		return sedeInicial;
	}




	public String getSedeFinal() {
		return sedeFinal;
	}




	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}




	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}




	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}




	public void setSedeInicial(String sedeInicial) {
		this.sedeInicial = sedeInicial;
	}




	public void setSedeFinal(String sedeFinal) {
		this.sedeFinal = sedeFinal;
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
		
		if (comando.equals(CREAR_RESERVA)) {
			if (fechaFinal.length()==0|| categoria.length()==0|| sedeInicial.length()==0||
				sedeFinal.length()==0|| fechaInicial.length()==0) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				
				Sede sedInit = null;
				Sede sedFinal = null;
				ArrayList<Sede> sedes2 = empresa.getSedes();
				for (int i1=0; i1<sedes2.size(); i1++) {
					if (sedes2.get(i1).getNombre().equals(sedeInicial)) {
						sedInit = sedes2.get(i1);
					}
					if (sedes2.get(i1).getNombre().equals(sedeFinal)) {
						sedFinal = sedes2.get(i1);
					}
				}
				
				Categoria cat = empresa.getCategorias().get(categoria);
				String placaVehiculoValido = empresa.revisarFechasReserva(sedInit, sedFinal, cat, fechaInicial, fechaFinal);
				
				if (placaVehiculoValido.equals("")) {
					JOptionPane.showMessageDialog(null, "No hay vehiculos disponibles para esta fecha. Seleccione otra sede o categoria", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else if(placaVehiculoValido.equals("-")) {
					JOptionPane.showMessageDialog(null, "No hay vehiculos disponibles para esta fecha. Las reservas previas del vehiculo "
							+ "\n impiden que su reserva pueda ser iniciada. Por favor intente cambiar la sede de entrega.", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					int respuesta = JOptionPane.showConfirmDialog(null, "El cliente que hace la reserva va a ser el conductor principal?", "Confirmación", JOptionPane.YES_NO_OPTION);
					Conductor conductor = null;
					ClienteNormal cliente = empresa.encontrarCliente(login);
					if (respuesta == JOptionPane.YES_OPTION) {
						DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
						String fechaVencimientoLicencia = cliente.getFechaVencimientoLicencia().format(formato);
						
						
						conductor = new Conductor(cliente.getNombre(),cliente.getCedula(), 
								cliente.getNumeroLicencia(), cliente.getPaisExpedicionLicencia(), fechaVencimientoLicencia,
								"fotico.png", cliente.getNumeroCelular(), cliente.getCorreo());
					}
					else {
						
						String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del conductor:", "Entrada de Texto", JOptionPane.PLAIN_MESSAGE);
						String cedula = JOptionPane.showInputDialog(null, "Ingrese la cedula del conductor:", "Entrada de Texto", JOptionPane.PLAIN_MESSAGE);
						String numeroLicencia = JOptionPane.showInputDialog(null, "Ingrese el numero de licencia del condcutor:", "Entrada de Texto", JOptionPane.PLAIN_MESSAGE);
						String paisExpedicionLicencia = JOptionPane.showInputDialog(null, "Ingrese el pais de expedicion de la licencia:", "Entrada de Texto", JOptionPane.PLAIN_MESSAGE);
						String fechaVencimientoLicencia = JOptionPane.showInputDialog(null, "Ingrese la fecha de vencimiento de la licencia (formato dd/mm/yyyy): ", "Entrada de Texto", JOptionPane.PLAIN_MESSAGE);
						String fotoLicencia = JOptionPane.showInputDialog(null, "Ingrese el path de la foto de la licencia:", "Entrada de Texto", JOptionPane.PLAIN_MESSAGE);
						String numeroContacto = JOptionPane.showInputDialog(null, "Ingrese el numero de contacto del conductor: ", "Entrada de Texto", JOptionPane.PLAIN_MESSAGE);
						String correo = JOptionPane.showInputDialog(null, "Ingrese el correo del conductor: ", "Entrada de Texto", JOptionPane.PLAIN_MESSAGE);

						conductor = new Conductor(nombre,cedula,numeroLicencia,paisExpedicionLicencia,fechaVencimientoLicencia,
								fotoLicencia,numeroContacto,correo);
						empresa.agregarConductor(conductor);
						
						
					}
					
					Vehiculo vehiculoArrendado = empresa.encontrarVehiculo(placaVehiculoValido);
					boolean cobroExtraEntrega = false;
					if (!sedeInicial.equals(sedeFinal)) {
						cobroExtraEntrega = true;
					}
					
					String horas = JOptionPane.showInputDialog(null, "Ingrese en que horas devolveria el vehiculo (formato 24h. Ejemplo: 9-11. Recuerde "
							+ "\n ingresar el intervalo, no horas sueltas y separado por '-'): ", "Entrada de Texto", JOptionPane.PLAIN_MESSAGE);
					
					String[] partes = fechaFinal.split("/");
					LocalDate fechaRecogida =  LocalDate.of(Integer.parseInt(partes[2]), Integer.parseInt(partes[1]), Integer.parseInt(partes[0]));
					boolean valHoras = empresa.validarHorasDevuelta(sedFinal, horas, fechaRecogida );
					
					if (valHoras == true) {
						Reserva reserva = new Reserva(null,fechaInicial,fechaFinal, conductor,vehiculoArrendado,cliente,
								cobroExtraEntrega, sedeInicial, sedeFinal, "activa");
						
						double precio30 = reserva.getPrecioTotal() * 0.3;
						
						JOptionPane.showMessageDialog(null,  "Debe cancelar el 30% del valor total de su reserva, lo cual es: " + precio30,"Info", JOptionPane.YES_NO_OPTION);
						try {
							empresa.guardarReserva(reserva);
							JOptionPane.showMessageDialog(null,  "Reserva guardada.","Info", JOptionPane.YES_NO_OPTION);
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null,  "Las horas seleccionadas para la devolucion no son validas.","Info", JOptionPane.YES_NO_OPTION);
						
					}
					
				}
				
			}
			
			
		}
		
	}
	

}
