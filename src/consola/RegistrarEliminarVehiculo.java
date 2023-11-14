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
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Categoria;
import modelo.EmpresaVehiculos;
import modelo.Sede;
import modelo.Vehiculo;

@SuppressWarnings("serial")
public class RegistrarEliminarVehiculo extends JPanel implements ActionListener{
	
	//Atributos
	private EmpresaVehiculos empresa;
	private String placa;
	private String marca;
	private String modelo;
	private String color;
	private String transmicion;
	private String sede;
	private String categoria;
	private String placaEliminar;
	
	
	
	// Constantes asociadas a eventos
	public final static String REGISTRAR_VEHICULO = "Registrar vehiculo";
	public final static String ELIMINAR_VEHICULO = "Eliminar vehiculo";
	
	
	// Atributos asociados a botones de interacci�n
	private JButton registrarVehiculoButton;
	private JButton eliminarVehiculoButton;
	
	
	//Metodos
	
	public RegistrarEliminarVehiculo(EmpresaVehiculos empresa){
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
        
        
        JLabel titleLabel = new JLabel("Registrar Vehiculo ");
        titleLabel.setFont(fontMonserratBold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Ocupa dos columnas
        registro.add(titleLabel, gbc);
        
        
        JLabel placaLabel = setLabel("Placa: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(placaLabel, gbc);
        JTextField placaTextField = new JTextField(20);
        this.placa = placaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(placaTextField, gbc);
        
		
		JLabel marcaLabel = setLabel("Marca: ");
		gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(marcaLabel, gbc);
        JTextField marcaTextField = new JTextField(20);
        this.marca = marcaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(marcaTextField, gbc);
        
		
		JLabel modeloLabel = setLabel("Modelo: ");
		gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(modeloLabel, gbc);
        JTextField modeloTextField = new JTextField(20);
        this.modelo = modeloTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(modeloTextField, gbc);
		
		
		JLabel colorLabel = setLabel("Color: ");
		gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(colorLabel, gbc);
        JTextField colorTextField = new JTextField(20);
        this.color = colorTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(colorTextField, gbc);
		
		JLabel transmicionLabel = setLabel("Transmicion: ");
		gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(transmicionLabel, gbc);
        JTextField transmicionTextField = new JTextField(20);
        this.transmicion = transmicionTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		registro.add(transmicionTextField, gbc);
		
		ArrayList<Sede> posiblesSedes = empresa.getSedes();
		String[] sedes = new String[posiblesSedes.size()];
		for (int i = 0; i<posiblesSedes.size(); i++) {
			sedes[i] = posiblesSedes.get(i).getNombre();	
		}
		
		JLabel sedeLabel = new JLabel("Sede: ");
		gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(sedeLabel, gbc);
		
        JComboBox<String> sedesBox = new JComboBox<>(sedes);
        JTextField sedeTextField = new JTextField(20);
        sedeTextField.setEditable(false); 
        sedesBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = sedesBox.getSelectedItem().toString();
                sedeTextField.setText(seleccion);
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  
        registro.add(sedesBox, gbc);
        
        this.sede = sedeTextField.getText();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  
		registro.add(sedeTextField, gbc);
		
		
		JLabel categoriaLabel = new JLabel("Categoria: ");
		gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(categoriaLabel, gbc);
        
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
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1;  
        registro.add(categoriaBox, gbc);
        
        this.categoria = categoriaTextField.getText();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 1;  
		registro.add(categoriaTextField, gbc);
        
		
		this.registrarVehiculoButton = new JButton("Registrar Vehiculo ");
		registrarVehiculoButton.addActionListener(this);
		registrarVehiculoButton.setActionCommand(REGISTRAR_VEHICULO);
		registrarVehiculoButton.setBackground(rojoOscuro);
		registrarVehiculoButton.setForeground(Color.WHITE);
		registrarVehiculoButton.setFont(fontBotones);
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        registro.add(registrarVehiculoButton, gbc);
        
        registrarVehiculoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String placaIng = placaTextField.getText();
                String marcaIng = marcaTextField.getText();
                String modeloIng = modeloTextField.getText();
                String colorIng = colorTextField.getText();
                String transmicionIng = transmicionTextField.getText();
                String categoriaIng = categoriaTextField.getText();
                String sedeIng = sedeTextField.getText();
                
                setPlaca(placaIng);
                setMarca(marcaIng);
                setModelo(modeloIng);
                setColor(colorIng);
                setTransmicion(transmicionIng);
                setCategoria(categoriaIng);
                setSede(sedeIng);
                
			}
        });
		total.add(registro);
		
		
		JPanel eliminar = new JPanel();
		eliminar.setLayout(new GridBagLayout());
		eliminar.setOpaque(true);
		eliminar.setBackground(grisCasiBlanco);
		eliminar.setBounds(610, 10, 300, 300);
		GridBagConstraints gbc1 = new GridBagConstraints();
		gbc1.fill = GridBagConstraints.HORIZONTAL;
        gbc1.insets = new Insets(5, 5, 5, 5);
        
        JLabel eliminarLabel = setLabel("<html> Seleccione la placa  <br> del vehiculo a eliminar</html>");
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        eliminar.add(eliminarLabel, gbc1);
        ArrayList<String> posiblesVehiculos = empresa.listadoPlacasVehiculos();
        String[] vehiculos = posiblesVehiculos.toArray(new String[0]);
        
        JComboBox<String> vehiculoBox = new JComboBox<>(vehiculos);
        JTextField eliminarTextField = new JTextField(20);
        eliminarTextField.setEditable(false); 
        vehiculoBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = vehiculoBox.getSelectedItem().toString();
                eliminarTextField.setText(seleccion);
                setPlacaEliminar(seleccion);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        eliminar.add(vehiculoBox, gbc);
        
        
		gbc1.gridx = 0;
        gbc1.gridy = 2;
		eliminar.add(eliminarTextField, gbc1);
		
		this.eliminarVehiculoButton = new JButton("Eliminar Vehiculo ");
		eliminarVehiculoButton.addActionListener(this);
		eliminarVehiculoButton.setActionCommand(ELIMINAR_VEHICULO);
		eliminarVehiculoButton.setBackground(rojoOscuro);
		eliminarVehiculoButton.setForeground(Color.WHITE);
		eliminarVehiculoButton.setFont(fontBotones);
        gbc1.gridx = 0;
        gbc1.gridy = 3;
        eliminar.add(eliminarVehiculoButton, gbc1);
        
        eliminarVehiculoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String placaEliminarIng = getPlacaEliminar();
                setPlacaEliminar(placaEliminarIng);
			}
        });
		
		
		total.add(eliminar);
		
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
	
	
	
	
	public EmpresaVehiculos getEmpresa() {
		return empresa;
	}



	public String getPlaca() {
		return placa;
	}



	public String getMarca() {
		return marca;
	}



	public String getModelo() {
		return modelo;
	}



	public String getColor() {
		return color;
	}



	public String getTransmicion() {
		return transmicion;
	}



	public String getSede() {
		return sede;
	}



	public String getCategoria() {
		return categoria;
	}



	public void setEmpresa(EmpresaVehiculos empresa) {
		this.empresa = empresa;
	}



	public void setPlaca(String placa) {
		this.placa = placa;
	}



	public void setMarca(String marca) {
		this.marca = marca;
	}



	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public void setColor(String color) {
		this.color = color;
	}



	public void setTransmicion(String transmicion) {
		this.transmicion = transmicion;
	}



	public void setSede(String sede) {
		this.sede = sede;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	



	public String getPlacaEliminar() {
		return placaEliminar;
	}



	public void setPlacaEliminar(String placaEliminar) {
		this.placaEliminar = placaEliminar;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(REGISTRAR_VEHICULO)) {
			if (placa.length() == 0 || marca.length() == 0 || modelo.length() == 0 ||
				color.length() == 0 || transmicion.length() == 0 || categoria.length() == 0 ||
				sede.length() == 0) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				Categoria catNuevo = empresa.consultarCategoria(categoria);
		        Vehiculo carro = new Vehiculo(placa, marca, modelo, color, transmicion, catNuevo, sede, "-" ,"activo");
				try {
					empresa.anadirVehiculo(sede,carro);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "El vehiculo fue registrado", "Info", JOptionPane.INFORMATION_MESSAGE);
				
			}
			
			
		}
		else if (comando.equals(ELIMINAR_VEHICULO)) {
			if (placaEliminar.length() == 0) {
				JOptionPane.showMessageDialog(null, "No ha puesto la placa que se debe eliminar.", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				try {
					empresa.eliminarVehiculo(placaEliminar);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "El vehiculo fue eliminado", "Info", JOptionPane.INFORMATION_MESSAGE);
				
			}
		}
	}

}
