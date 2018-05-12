package model;

import java.sql.Date;
import java.util.Collection;

public class Factura {
	private String numero;
	private String fechaEmision;	
	private String estado;
	private String cliente;
	private String concepto;
	private double total;//antes float
	
	
		
	public Factura() {
		super();
	}
	
	public Factura(String numero, String fechaEmision, String cliente, String concepto, double total, String estado) {
		super();
		this.numero = numero;
		this.fechaEmision = fechaEmision;		
		this.cliente=cliente;
		this.concepto=concepto;
		this.total=total;
		this.estado = estado;
		
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}


	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "" + numero + "    " + fechaEmision + "    " + cliente + "    "
				+ concepto + "    " + total + "€    " + estado + "";
	}

}
