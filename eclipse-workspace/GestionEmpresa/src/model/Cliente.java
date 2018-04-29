package model;

public class Cliente {
	private int numero;
	private String nif;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;

	
	public Cliente(int numero,String nif, String nombre, String direccion, String telefono, String email) {
		super();
		this.numero=numero;
		this.nif = nif;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;

	}
	
	
	
	
	public Cliente() {
		super();
	}


	public int getNumero() {
		return numero;
	}




	public void setNumero(int numero) {
		this.numero = numero;
	}




	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}





	@Override
	public String toString() {
		return "" + numero +     "    "+"" + nombre + "    " + nif + "    " + direccion + "    " + telefono
				+ "    " + email +  "";
	}
	

}
