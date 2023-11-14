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

import modelo.EmpresaVehiculos;

@SuppressWarnings("serial")
public class ActualizarEstadoVehiculo extends JPanel implements ActionListener{
	
	//Atributo
	private EmpresaVehiculos empresa;
	private String placa;
	private String login;
	
	public final static String MANDAR = "Mandar a mantenimiento";
	
	private JButton mandarButton;
	
	public ActualizarEstadoVehiculo(EmpresaVehiculos empresa,String login) {
		this.empresa = empresa;
		this.login = login;
		setLayout(new BorderLayout());
		
		Color grisCasiBlanco = new Color(220,220,220);
		Color rojoOscuro = new Color(184, 25, 25);
		Font fontBotones = new Font("Arial", Font.PLAIN, 15);
		
		JPanel mandarAMantenimiento = new JPanel();
		mandarAMantenimiento.setLayout(new GridBagLayout());
		mandarAMantenimiento.setOpaque(true);
		mandarAMantenimiento.setBackground(grisCasiBlanco);
		mandarAMantenimiento.setBounds(10, 10, 300, 300);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JLabel placaLabel = setLabel("Digite la placa del carro: ");
        gbc.gridx = 0;
        gbc.gridy = 0;
        mandarAMantenimiento.add(placaLabel, gbc);
        JTextField placaTextField = new JTextField(20);
        this.placa = placaTextField.getText();
		gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;  // Ocupa dos columnas
        mandarAMantenimiento.add(placaTextField, gbc);
        
        this.mandarButton = new JButton("Mandar a mantenimiento");
        mandarButton.addActionListener(this);
        mandarButton.setActionCommand(MANDAR);
        mandarButton.setBackground(rojoOscuro);
        mandarButton.setForeground(Color.WHITE);
        mandarButton.setFont(fontBotones);
        gbc.gridx = 0;
        gbc.gridy = 7;
        mandarAMantenimiento.add(mandarButton, gbc);
        
        mandarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                String placaIng = placaTextField.getText();
                
                setPlaca(placaIng);
                
			}
        });
        
        add(mandarAMantenimiento, BorderLayout.CENTER);
	}
		
	
	
	public String getPlaca() {
		return placa;
	}



	public void setPlaca(String placa) {
		this.placa = placa;
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
		if (comando.equals(MANDAR)) {
			if (placa.length() == 0) {
				JOptionPane.showMessageDialog(null, "Hay datos faltantes", "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				boolean encontrado = empresa.validarUbicacionVehiculo(login, placa);
				if (encontrado) {
					try {
						empresa.mandarSacarMantenimiento(placa, "mantenimiento");
						String fechaVuelta = JOptionPane.showInputDialog(null, "Digite la fecha cuando volveria el vehiculo '(formato dd/mm/yyyy)': ", "Info", JOptionPane.PLAIN_MESSAGE);
						JOptionPane.showMessageDialog(null, "El estado del vehiculo ha sido actualizado. Se mando a mantenimiento, fecha de vuelta a operaciones " + fechaVuelta, "Info", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
				else {
					System.out.print("\n El vehiculo que ha sido ingresado no pertenece a la sede donde usted trabaja o no existe en el sistema.");

			}
			}
		}
		
	}
	

}
