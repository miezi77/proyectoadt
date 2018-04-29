package model;

public class Servicio {
	private Integer numero;
	private String concepto;
	private Float horas;
	private Float precio;
	
	
	public Servicio(Integer numero, String concepto, Float horas, Float precio) {
		super();
		this.numero = numero;
		this.concepto = concepto;
		this.horas = horas;
		this.precio = precio;
	}
	
	
	
	public Servicio() {
		super();
	}



	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setDescripcion(String concepto) {
		this.concepto = concepto;
	}
	public Float getHoras() {
		return horas;
	}
	public void setHoras(Float horas) {
		this.horas = horas;
	}
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
	}

}
