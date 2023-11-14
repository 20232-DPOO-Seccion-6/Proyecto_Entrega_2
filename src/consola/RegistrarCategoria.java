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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Categoria;
import modelo.EmpresaVehiculos;

@SuppressWarnings("serial")
public class RegistrarCategoria extends JPanel implements ActionListener{

	//Atributos 
	private EmpresaVehiculos empresa;
	private String nombreCategoria;
	private Map<ArrayList<LocalDate>, Integer> mapaFechas;
	private String precioCondAux;
	private String precioEntregaSede;
	
	// Constantes asociadas a eventos
	public final static String PRECIOS = "Precios por fechas";
	public final static String GUARDAR = "Guardar Categoria";
	
		
	// Atributos asociados a botones de interacci�n
	private JButton precioButton;
	private JButton guardarButton;
	
	//Metodos
	
	public RegistrarCategoria(EmpresaVehiculos empresa) {
		this.empresa = empresa;
		this.mapaFechas = new HashMap<>();
		
		setLayout(new BorderLayout());
		
		
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		
		JPanel total =  new JPanel();
		total.setLayout(new GridBagLayout());
		total.setBackground(grisCasiBlanco);
		total.setOpaque(true);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(1, 1, 1, 1);
        
        JLabel titleLabel = new JLabel("Registrar Categoria");
        titleLabel.setFont(fontMonserratBold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Ocupa dos columnas
        total.add(titleLabel, gbc);
        
        JLabel categoriaLabel = setLabel("Nombre categoria: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(categoriaLabel, gbc);
        
        JTextField nombreCategoriaTextField = new JTextField(20);
        this.nombreCategoria = nombreCategoriaTextField.getText();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(nombreCategoriaTextField, gbc);
        
        JLabel preciosLabel = setLabel("Precios por fechas: ");
		gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(preciosLabel, gbc);
        
        
		this.precioButton = new JButton("Modificar precios por fecha");
		precioButton.addActionListener(this);
		precioButton.setActionCommand(PRECIOS);
		precioButton.setBackground(rojoOscuro);
		precioButton.setForeground(Color.WHITE);
		precioButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(precioButton, gbc);
        
        precioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "A continuacion se le van a mostrar las opciones para modificar las tarifas por fechas \n"
						+ "para la categoria deseada. \n Simplemente ingrese sucesivamente las fechas iniciales y finales (de la forma 'dd/mm/yyyy') \n"
						+ "y posteriormente ingrese el precio para esos dias. \n Asegurese de que todos los dias del año cuenten con un precio.", "Info", JOptionPane.INFORMATION_MESSAGE);
				do {
		            // Pedir al usuario que ingrese datos en dos campos
		            String campo1 = JOptionPane.showInputDialog("Ingrese la fecha inicial (formato dd/mm/yyyy). Ejemplo: 01/01/2023: ");
		            String campo2 = JOptionPane.showInputDialog("Ingrese la fecha final (formato dd/mm/yyyy). Ejemplo: 31/12/2023: ");
		            String campo3 = JOptionPane.showInputDialog("Ingrese el precio para estas fechas: ");
		            
		            mapaFechas = empresa.crearMapaFechas(campo1, campo2, campo3, mapaFechas);

		            // Preguntar al usuario si desea ingresar más datos
		            int opcion = JOptionPane.showConfirmDialog(null, "¿Desea ingresar más rangos de fechas?", "Confirmación", JOptionPane.YES_NO_OPTION);

		            // Si la respuesta es "No", salir del bucle
		            if (opcion == JOptionPane.NO_OPTION) {
		                break;
		            }
		        } while (true); // El bucle seguirá ejecutándose hasta que el usuario seleccione "No"
				}
        });
        
        JLabel precioCondAuxLabel = setLabel("Precio conductor extra: ");
		gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(precioCondAuxLabel, gbc);
        JTextField precioCondAuxTextField = new JTextField(20);
        this.precioCondAux = precioCondAuxTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 1;  // Ocupa dos columnas
		total.add(precioCondAuxTextField, gbc);
		
		JLabel precioEntregaSedeLabel = setLabel("Precio entrega en otra sede: ");
		gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(precioEntregaSedeLabel, gbc);
        JTextField precioEntregaSedeTextField = new JTextField(20);
        this.precioEntregaSede = precioEntregaSedeTextField.getText();
		gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        total.add(precioEntregaSedeTextField, gbc);
        
        
        
        this.guardarButton = new JButton("Guardar Categoria");
        guardarButton.addActionListener(this);
        guardarButton.setActionCommand(GUARDAR);
        guardarButton.setBackground(rojoOscuro);
        guardarButton.setForeground(Color.WHITE);
        guardarButton.setFont(fontBotones);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;  
        total.add(guardarButton, gbc);
        
        guardarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombreCategoriaIng = nombreCategoriaTextField.getText();
				String precioConduIng = precioCondAuxTextField.getText();
                String precioSedeIng = precioEntregaSedeTextField.getText();
             
                setNombreCategoria(nombreCategoriaIng);
                setPrecioCondAux(precioConduIng);
                setPrecioEntregaSede(precioSedeIng);

			}
        });
        
        add(total, BorderLayout.CENTER);
        
        
	}
	
	
	private void setNombreCategoria(String text) {
		this.nombreCategoria = text;
		
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
	
	
	
	public Map<ArrayList<LocalDate>, Integer> getMapaFechas() {
		return mapaFechas;
	}


	public String getPrecioCondAux() {
		return precioCondAux;
	}


	public String getPrecioEntregaSede() {
		return precioEntregaSede;
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


	@Override
	public void actionPerformed(ActionEvent e) {
		String comando = e.getActionCommand();
		
		if (comando.equals(GUARDAR)) {
			if (precioCondAux != null && !precioCondAux.equals("") && precioEntregaSede != null && !precioEntregaSede.equals("")
					&& !mapaFechas.isEmpty() && nombreCategoria != null && !nombreCategoria.equals("")) {
				String texto = "";
				ArrayList<Categoria> catSort = new ArrayList<>();
				int n = empresa.getCategorias().keySet().size();
		        ArrayList<Integer> arrayList = new ArrayList<>();
		        for (int i = 1; i <= n; i++) {
		            arrayList.add(i);
		        }
				Map<String, Categoria> categorias = empresa.getCategorias();
				for (Integer index: arrayList) {
					for (String cat: categorias.keySet()) {
						if (categorias.get(cat).getNivel() == index) {
							catSort.add(categorias.get(cat));
						}
						
					}
				}
				
				for (int i1=0; i1< catSort.size(); i1++) {
					texto += "\n " + (i1 + 1) + " " + catSort.get(i1).getIdCategoria();
				}
				
				JOptionPane.showMessageDialog(null, "\n A continuacion se muestra el orden de categorias existente. Por favor ingrese el \n"
						+ "indice que corresponderia a la nueva categoria. Por ejemplo: si pequeño es 4 y sedan es 3,\n su nueva categoria"
						+ " es mejor que pequeño pero peor que sedan, usted debe ingresar el numero 4.\n Si es de tipo especial y no desea asignarle"
						+ " una jerarquia, ingrese 0: " + texto, "Info", JOptionPane.INFORMATION_MESSAGE);
				String nivel = JOptionPane.showInputDialog("Ingrese el numero que corresponderia a la jerarquia de la categoria:");
				Categoria catNueva = new Categoria(nombreCategoria,Integer.parseInt(precioEntregaSede), Integer.parseInt(precioCondAux), mapaFechas,
						Integer.parseInt(nivel));
				
				if (nivel.equals("0")) {
					try {
						empresa.setCategoria(catNueva);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						empresa.setNewNivelCategorias(catSort, Integer.parseInt(nivel), catNueva);
					} catch (NumberFormatException | IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				JOptionPane.showMessageDialog(null, "Se han guardado la categoria", "Info", JOptionPane.INFORMATION_MESSAGE);
				
			}
			else {
				JOptionPane.showMessageDialog(null, "No se ha podido guardar, faltan datos", "Info", JOptionPane.INFORMATION_MESSAGE);
			}

		}
	}
		
	
	

}
