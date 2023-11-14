package modelo;

public class ClienteEspecial{
	
	//Atributos
	
	private String nombre;
	private String cedula;
	private Usuario usuario;
	
	
	//Metodos
	
	public ClienteEspecial(String nombre, String cedula, Usuario user) {
		this.nombre = nombre;
		this.cedula = cedula;
		this.usuario = user;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCedula() {
		return cedula;
	}	
	
	public Usuario getUsuario() {
		return usuario;
	}
}
