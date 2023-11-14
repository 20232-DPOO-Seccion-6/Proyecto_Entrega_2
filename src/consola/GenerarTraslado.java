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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.ClienteEspecial;
import modelo.Empleado;
import modelo.EmpresaVehiculos;
import modelo.Sede;

@SuppressWarnings("serial")
public class GenerarTraslado extends JPanel implements ActionListener{
	
	//Atributos 
	private EmpresaVehiculos empresa;
	private String placaTraslado;
	private String empleado;
	private String fecha;
	private String sedeDestino;
	
	
	// Constantes asociadas a eventos
	public final static String GENERAR_TRASLADO = "Generar Traslado";
	
	
	// Atributos asociados a botones de interacci�n
	private JButton generarTrasladoButton;
	
	//Metodos
	public GenerarTraslado(EmpresaVehiculos empresa) {
		this.empresa = empresa;
		setLayout(new BorderLayout());
		
		Color colorFondo = new Color(184, 185, 187);
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		
		JPanel total = new JPanel();
		total.setLayout(null);
		total.setBackground(colorFondo);
		
		JPanel infoSedes = new JPanel();
		infoSedes.setLayout(new BorderLayout());
		infoSedes.setOpaque(true);
		infoSedes.setBackground(grisCasiBlanco);
		infoSedes.setBounds(10, 10, 450, 400);
		
		JLabel titleLabel = new JLabel("<html> El inventario en cada sede es el siguiente </html>");
        titleLabel.setFont(fontMonserratBold);
        infoSedes.add(titleLabel, BorderLayout.NORTH);
        
		
		String infoTexto = empresa.contadorVehiculosSedes();
		JTextArea textArea = new JTextArea();
		textArea.setText(infoTexto);
        JScrollPane scrollPane = new JScrollPane(textArea);
        infoSedes.add(scrollPane, BorderLayout.CENTER);
        total.add(infoSedes);
        
        
        JPanel gestionTraslado = new JPanel();
        gestionTraslado.setLayout(new GridBagLayout());
        gestionTraslado.setOpaque(true);
        gestionTraslado.setBackground(grisCasiBlanco);
        gestionTraslado.setBounds(450, 10, 500, 400); 
        GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        
        JLabel titleLabel2 = new JLabel("Info traslado");
        titleLabel2.setFont(fontMonserratBold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gestionTraslado.add(titleLabel2, gbc);
        
        JLabel placaLabel = setLabel("Placa del carro a trasladar: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gestionTraslado.add(placaLabel, gbc);
        ArrayList<String> posiblesVehiculos = empresa.listadoPlacasVehiculos();
        String[] vehiculos = posiblesVehiculos.toArray(new String[0]);
        
        JComboBox<String> vehiculoBox = new JComboBox<>(vehiculos);
        JTextField placaTrasladoTextField = new JTextField(20);
        placaTrasladoTextField.setEditable(false); 
        vehiculoBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = vehiculoBox.getSelectedItem().toString();
                placaTrasladoTextField.setText(seleccion);
                setPlacaTraslado(seleccion);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gestionTraslado.add(vehiculoBox, gbc);
        
        
		gbc.gridx = 0;
        gbc.gridy = 3;
        gestionTraslado.add(placaTrasladoTextField, gbc);
        
        
        JLabel sedeDestinoLabel = setLabel("Sede destino: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gestionTraslado.add(sedeDestinoLabel, gbc);
        ArrayList<Sede> posiblesSedes = empresa.getSedes();
		String[] sedes = new String[posiblesSedes.size()];
		for (int i = 0; i<posiblesSedes.size(); i++) {
			sedes[i] = posiblesSedes.get(i).getNombre();	
		}
        JComboBox<String> sedesBox = new JComboBox<>(sedes);
        JTextField sedeDestinoTextField = new JTextField(20);
        sedeDestinoTextField.setEditable(false); 
        sedesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = sedesBox.getSelectedItem().toString();
                sedeDestinoTextField.setText(seleccion);
                setSedeDestino(seleccion);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gestionTraslado.add(sedesBox, gbc);

		gbc.gridx = 0;
        gbc.gridy = 6;
        gestionTraslado.add(sedeDestinoTextField, gbc);
        
        
        JLabel fechaLabel = setLabel("Ingrese la fecha 'formato (dd/mm/yyyy)' : ");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gestionTraslado.add(fechaLabel, gbc);
        JTextField fechaTextField = new JTextField(20);
        this.fecha = fechaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 8;
        gestionTraslado.add(fechaTextField, gbc);
        
        
        this.generarTrasladoButton = new JButton("Generar Traslado");
        generarTrasladoButton.addActionListener(this);
        generarTrasladoButton.setActionCommand(GENERAR_TRASLADO);
        generarTrasladoButton.setBackground(rojoOscuro);
        generarTrasladoButton.setForeground(Color.WHITE);
        generarTrasladoButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 9;
        gestionTraslado.add(generarTrasladoButton, gbc);
        
        generarTrasladoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String placaTrasladoIng = getPlacaTraslado();
                String fechaIng = fechaTextField.getText();
                String sedeDestinoIng = getSedeDestino();
                
                
                setPlacaTraslado(placaTrasladoIng);
                setFecha(fechaIng);
                setSedeDestino(sedeDestinoIng);
			}
        });
        
        total.add(gestionTraslado);
        
        add(total, BorderLayout.CENTER);
		
		
		
	}
	
	
	
	public String getPlacaTraslado() {
		return placaTraslado;
	}



	public String getEmpleado() {
		return empleado;
	}



	public String getFecha() {
		return fecha;
	}



	public String getSedeDestino() {
		return sedeDestino;
	}



	public void setPlacaTraslado(String placaTraslado) {
		this.placaTraslado = placaTraslado;
	}



	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	public void setSedeDestino(String sedeDestino) {
		this.sedeDestino = sedeDestino;
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
		if (comando.equals(GENERAR_TRASLADO)) {
			System.out.print(placaTraslado);
			System.out.print(fecha);
			System.out.print(sedeDestino);
			
			if (placaTraslado.length() == 0 || fecha.length() == 0 || sedeDestino.length() == 0 ) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			
			else {
				
				boolean valido = empresa.validarTraslado(placaTraslado);
				if (valido == false) {
					JOptionPane.showMessageDialog(null, " El vehiculo ingresado cuenta con reservas activas en la sede de inicio,\n"
							+ "por lo cual no se autoriza su traslado.", "Info", JOptionPane.INFORMATION_MESSAGE);
				
				}
				else {
					ArrayList<Empleado> empleadosValidos = empresa.getEmpleadosValidos(placaTraslado);
		            String[] empleados = new String[empleadosValidos.size()];
		    		for (int i = 0; i<empleadosValidos.size(); i++) {
		    			empleados[i] = empleadosValidos.get(i).getNombre();	
		    		}
		    		
			        int seleccion = JOptionPane.showOptionDialog(
			            null,
			            "Selecciona una opción:",
			            "Título del cuadro de diálogo",
			            JOptionPane.DEFAULT_OPTION,
			            JOptionPane.QUESTION_MESSAGE,
			            null,
			            empleados,
			            empleados[0]
			        );
			        
			        // Verificar la opción seleccionada por el usuario
			        if (seleccion != JOptionPane.CLOSED_OPTION) {
			           
			             String nombreEmpleado = empleados[seleccion];
			             
			             Empleado emple = null;
			             for (Empleado empleado: empleadosValidos) {
			            	 if (empleado.getNombre().equals(nombreEmpleado)){
			            		 emple = empleado;
			            	 }
			             }
			             ClienteEspecial empleadoTraslado = new ClienteEspecial(emple.getNombre(), emple.getCedula(), emple.getUsuarioEmpleado());
			             Sede sedeInit = empresa.getPlacaSede(placaTraslado);;
			             Sede sedeFin = null;
			             
			             for (Sede sede: empresa.getSedes()) {
			            	 if (sede.getNombre().equals(sedeDestino)) {
			            		 sedeFin = sede; 
			            	 }
			             }
			             
			             
			             try {
							empresa.generarTraslado(placaTraslado, sedeInit , sedeFin, empleadoTraslado, fecha);
							JOptionPane.showMessageDialog(null, "Se realizo el traslado con exito.", "Info", JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			        } else {
			            System.out.println("El usuario cerró el cuadro de diálogo sin seleccionar ninguna opción.");
			        }
				}
				
			}
		}
		
	}
	

}
