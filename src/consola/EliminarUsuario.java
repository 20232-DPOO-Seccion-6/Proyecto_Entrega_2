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
public class EliminarUsuario extends JPanel implements ActionListener{
	
	//Atributos 
	private EmpresaVehiculos empresa;
	private String loginAdmin;
	private String sede;
	private String login;
	
	
	public final static String ELIMINAR_USUARIO = "Eliminar usuario";
	private JButton eliminarUsuarioButton;
	
	
	
	//Metodos
	public EliminarUsuario(EmpresaVehiculos empresa, String login) {
		
		this.empresa = empresa;
		this.loginAdmin = login;
		
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		Font fontMonserratBold = new Font("Monserrat", Font.BOLD, 20);
		
		JPanel eliminar = new JPanel();
		eliminar.setLayout(new GridBagLayout());
		eliminar.setOpaque(true);
		eliminar.setBackground(grisCasiBlanco);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JLabel titleLabel = new JLabel("Eliminar Usuario");
        titleLabel.setFont(fontMonserratBold);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;  // Ocupa dos columnas
        eliminar.add(titleLabel, gbc);
        
		JLabel loginLabel = setLabel(" Seleccione el login del usuario a eliminar: ");
        gbc.gridx = 0;
        gbc.gridy = 4;
        eliminar.add(loginLabel, gbc);
		
		Usuario user = empresa.getUserLogin(loginAdmin);
		ArrayList<Usuario> posiblesUsuarios = empresa.getUsuariosEmpleados(user);
		String[] usuarios = new String[posiblesUsuarios.size()];
		for (int i = 0; i<posiblesUsuarios.size(); i++) {
			usuarios[i] = posiblesUsuarios.get(i).getLogin();	
		}
		
		JComboBox<String> usuarioBox = new JComboBox<>(usuarios);
        JTextField usuarioTextField = new JTextField(20);
        usuarioTextField.setEditable(false); 
        usuarioBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String seleccion = usuarioBox.getSelectedItem().toString();
                usuarioTextField.setText(seleccion);
                setLogin(seleccion);
                
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        eliminar.add(usuarioBox, gbc);

		gbc.gridx = 0;
        gbc.gridy = 6;
		eliminar.add(usuarioTextField, gbc);
		
		
		
		
		
		this.eliminarUsuarioButton = new JButton("Eliminar Usuario");
		eliminarUsuarioButton.addActionListener(this);
		eliminarUsuarioButton.setActionCommand(ELIMINAR_USUARIO);
		eliminarUsuarioButton.setBackground(rojoOscuro);
		eliminarUsuarioButton.setForeground(Color.WHITE);
		eliminarUsuarioButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 7;
        eliminar.add(eliminarUsuarioButton, gbc);
        
        eliminarUsuarioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String sedeIng = getSede();
                String loginIng = getLogin();
                
                setSede(sedeIng);
                setLogin(loginIng);
			}
        });
        
        add(eliminar, BorderLayout.CENTER);
		
		
		
	}
	
	
	
	public String getSede() {
		return sede;
	}



	public String getLogin() {
		return login;
	}



	public void setSede(String sede) {
		this.sede = sede;
	}



	public void setLogin(String login) {
		this.login = login;
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
		if (comando.equals(ELIMINAR_USUARIO)) {
			if (login.length() == 0) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes, no se pudo registrar.", "Info", JOptionPane.INFORMATION_MESSAGE);
				
			}
			else {
				Usuario user = empresa.getUserLogin(loginAdmin);
				Sede sede = empresa.getSedeAdmin(user);

				ArrayList<Usuario> posiblesUsuarios = empresa.getUsuariosEmpleados(user);
				Usuario userEliminar = null;
				for (int i=0; i<posiblesUsuarios.size(); i++) {
					if (posiblesUsuarios.get(i).getLogin().equals(login)) {
						userEliminar = posiblesUsuarios.get(i);
					}
				}
				try {
					empresa.eliminarUsuario(userEliminar, sede.getNombre());
					JOptionPane.showMessageDialog(null, "El usuario fue eliminado", "Info", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
		
	}
	

}
