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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Categoria;
import modelo.EmpresaVehiculos;

@SuppressWarnings("serial")
public class ModificarPreciosCategoria extends JPanel implements ActionListener{
	
	//Atributos 
	private EmpresaVehiculos empresa;
	private String nombreCategoria;
	private Map<ArrayList<LocalDate>, Integer> mapaFechas;
	private String precioCondAux;
	private String precioEntregaSede;
	private Categoria categoriaMod;
	// Constantes asociadas a eventos
	public final static String MODIFICAR_PRECIOS = "Modificar Precios por fechas";
	public final static String GUARDAR_MODIFICACIONES = "Guardar Modificaciones";
	
	// Atributos asociados a botones de interacci�n
	private JButton modificarPrecioButton;
	private JButton guardarModificacionesButton;
	
	
	
	//Metodos
	public ModificarPreciosCategoria(EmpresaVehiculos empresa) {
		this.empresa = empresa;
		this.mapaFechas = new HashMap<>();
		
		setLayout(new BorderLayout());
		
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		Font fontMonserratButtons = new Font("Monserrat", Font.PLAIN, 15);
		
		
		JPanel total =  new JPanel();
		total.setLayout(new GridBagLayout());
		total.setBackground(grisCasiBlanco);
		total.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(1, 1, 1, 1);
        
        JLabel titleLabel = new JLabel("Modificar Precios de Categoria ");
        titleLabel.setFont(fontMonserratBold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Ocupa dos columnas
        total.add(titleLabel, gbc);
        
        JLabel infoLabel = new JLabel("<html> Solo modifique las casillas que desea cambiar, lo que deje en blanco"
        		+ "se asume que no se va a modificar <br> y se dejan los valores existentes. Tiene que seleccionar una categoria antes de todo. </html> ");
        infoLabel.setFont(fontMonserratButtons);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;  // Ocupa dos columnas
        total.add(infoLabel, gbc);
        
        JLabel categoriaLabel = setLabel("Categoria: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        total.add(categoriaLabel, gbc);
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
                setNombreCategoria(categoriaTextField.getText());
                setCategoriaMod(empresa.getCategoria(seleccion));
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  
        total.add(categoriaBox, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  
		total.add(categoriaTextField, gbc);
		
		
		
		JLabel preciosLabel = new JLabel("Precios por fechas: ");
		gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(preciosLabel, gbc);
        
        
		this.modificarPrecioButton = new JButton("Modificar precios por fecha");
		modificarPrecioButton.addActionListener(this);
		modificarPrecioButton.setActionCommand(MODIFICAR_PRECIOS);
		modificarPrecioButton.setBackground(rojoOscuro);
		modificarPrecioButton.setForeground(Color.WHITE);
		modificarPrecioButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(modificarPrecioButton, gbc);
        
        modificarPrecioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nombreCategoria == null) {
					JOptionPane.showMessageDialog(null, "No selecciono una categoria", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea cambiar todos los rangos de fechas?", "Confirmación", JOptionPane.YES_NO_OPTION);
					if (respuesta == JOptionPane.YES_OPTION) {
						

			        } else if (respuesta == JOptionPane.NO_OPTION) {
			        	mapaFechas = categoriaMod.getPrecioFechas();
			        } else {
			            JOptionPane.showMessageDialog(null, "Ha cancelado la operación.");
			        }
					
					JOptionPane.showMessageDialog(null, "A continuacion se le van a mostrar las opciones para modificar las tarifas por fechas \"\r\n"
							+ "							+ \"para la categoria deseada. \\n Simplemente ingrese sucesivamente las fechas iniciales y finales (de la forma 'dd/mm/yyyy') \"\r\n"
							+ "							+ \"y posteriormente ingrese el precio para esos dias. \\n Asegurese de que todos los dias del año cuenten con un precio.", "Info", JOptionPane.INFORMATION_MESSAGE);
					do {
			            // Pedir al usuario que ingrese datos en dos campos
			            String campo1 = JOptionPane.showInputDialog("Ingrese la fecha inicial (formato dd/mm/yyyy). Ejemplo: 01/01/2023: ");
			            String campo2 = JOptionPane.showInputDialog("Ingrese la fecha final (formato dd/mm/yyyy). Ejemplo: 31/12/2023: ");
			            String campo3 = JOptionPane.showInputDialog("Ingrese el precio para estas fechas: ");
			            
			            try {
							empresa.modificarCategoriaPreciosAnadirUno(nombreCategoria ,campo1, campo2, campo3, mapaFechas);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

			            // Preguntar al usuario si desea ingresar más datos
			            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea ingresar más rangos de fechas?", "Confirmación", JOptionPane.YES_NO_OPTION);

			            // Si la respuesta es "No", salir del bucle
			            if (opcion == JOptionPane.NO_OPTION) {
			                break;
			            }
			        } while (true); // El bucle seguirá ejecutándose hasta que el usuario seleccione "No"
				}
				
                
			}
        });
        
        
        JLabel precioCondAuxLabel = setLabel("Precio conductor extra: ");
		gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(precioCondAuxLabel, gbc);
        JTextField precioCondAuxTextField = new JTextField(20);
        this.precioCondAux = precioCondAuxTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		total.add(precioCondAuxTextField, gbc);
		
		JLabel precioEntregaSedeLabel = setLabel("Precio entrega en otra sede: ");
		gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(precioEntregaSedeLabel, gbc);
        JTextField precioEntregaSedeTextField = new JTextField(20);
        this.precioEntregaSede = precioEntregaSedeTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        
		total.add(precioEntregaSedeTextField, gbc);
		
		this.guardarModificacionesButton = new JButton("Guardar Modificaciones");
		guardarModificacionesButton.addActionListener(this);
		guardarModificacionesButton.setActionCommand(GUARDAR_MODIFICACIONES);
		guardarModificacionesButton.setBackground(rojoOscuro);
		guardarModificacionesButton.setForeground(Color.WHITE);
		guardarModificacionesButton.setFont(fontBotones);
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  
        total.add(guardarModificacionesButton, gbc);
        

        
        guardarModificacionesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (nombreCategoria == null) {
					JOptionPane.showMessageDialog(null, "No selecciono una categoria", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					String precioConduIng = precioCondAuxTextField.getText();
	                String precioSedeIng = precioEntregaSedeTextField.getText();
	             
	                setPrecioCondAux(precioConduIng);
	                setPrecioEntregaSede(precioSedeIng);
	                setCategoriaMod(empresa.getCategoria(nombreCategoria));
				}
                
                
			}
        });
        
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
	
	
	

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public Map<ArrayList<LocalDate>, Integer> getMapaFechas() {
		return mapaFechas;
	}

	public String getPrecioCondAux() {
		return precioCondAux;
	}

	public String getPrecioEntregaSede() {
		return precioEntregaSede;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public void setMapaFechas(Map<ArrayList<LocalDate>, Integer> mapaFechas) {
		this.mapaFechas = mapaFechas;
	}

	public void setPrecioCondAux(String precioCondAux) {
		this.precioCondAux = precioCondAux;
	}

	public void setPrecioEntregaSede(String precioEntregaSede) {
		this.precioEntregaSede = precioEntregaSede;
	}

	
	public Categoria getCategoriaMod() {
		return categoriaMod;
	}

	public void setCategoriaMod(Categoria categoriaMod) {
		this.categoriaMod = categoriaMod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		if (comando.equals(GUARDAR_MODIFICACIONES)) {
			if (nombreCategoria == null) {
				JOptionPane.showMessageDialog(null, "No selecciono una categoria", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				if (precioCondAux != null && !precioCondAux.equals("")) {
					try {
						empresa.modificarCategoriaConductor(categoriaMod, Integer.parseInt(precioCondAux));
						JOptionPane.showMessageDialog(null, "Se han guardado los cambios.", "Info", JOptionPane.INFORMATION_MESSAGE);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (precioEntregaSede != null && !precioEntregaSede.equals("")) {
					try {
						empresa.modificarCategoriaEntrega(categoriaMod, Integer.parseInt(precioEntregaSede));
						JOptionPane.showMessageDialog(null, "Se han guardado los cambios.", "Info", JOptionPane.INFORMATION_MESSAGE);
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			}
		}
		
	}

}
