package modelo;



public class TrasladoReserva {
	
	//Atributos
	
	private String placaVehiculo;
	private String sedeSalida;
	private String sedeLlegada;
	private ClienteEspecial conductor;
	
	//Metodos
	
	public TrasladoReserva(String placaVehiculo,
			 String sedeSalida, String sedeLlegada, ClienteEspecial conductor) {
		this.placaVehiculo = placaVehiculo;
		this.sedeSalida = sedeSalida;
		this.sedeLlegada = sedeLlegada;
		this.conductor = conductor;
	}

	public String getPlacaVehiculo() {
		return placaVehiculo;
	}


	public String getSedeSalida() {
		return sedeSalida;
	}

	public String getSedeLlegada() {
		return sedeLlegada;
	}
	
	public ClienteEspecial getConductor() {
		return conductor;
	}
}
