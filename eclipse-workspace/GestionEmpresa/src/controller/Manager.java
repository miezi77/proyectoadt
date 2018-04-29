package controller;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.Cliente;
import model.Factura;
import model.Servicio;
import view.Portada;

public class Manager {
	//opción 1: MySQL
	//opción 2: Mongo
	//opción 3:
	//opción 4:
	
	public Collection<Cliente> getClientes() throws Exception {
		Collection<Cliente> clientes = new ArrayList<Cliente>();
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			clientes = dbManager.getClientes();	
			break;
		}
		return clientes;
	}
	public Collection<Factura> getFacturas() throws Exception {
		Collection<Factura> facturas = new ArrayList<Factura>();
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			facturas = dbManager.getFacturas();
			break;
		
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			facturas=dbMngMg.getFacturas();
			break;
		case 4:
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			facturas=dbMngHb.getFacturas();
			break;
		 
	}
		return facturas;
	}
	public Cliente getClienteById(String id) throws Exception{
		Cliente cliente = new Cliente();
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			cliente=dbManager.getClienteById(id);
			break;			
		}
		return cliente;
	}
	public Factura getFacturaById(String id) throws Exception{
		Factura factura = new Factura();
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			factura=dbManager.getFacturaById(id);
			break;
		}
		return factura;
		
	}

	public void modificarCliente(Cliente cliente, int id) throws Exception{		
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			dbManager.modificarCliente(cliente, id);	
			break;
		}
	}
	public void anyadirCliente(Cliente c) throws Exception{
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager= new DBManager();
			dbManager.anyadirCliente(c);
			break;
		}
	}
	public void anyadirFactura(Factura f) throws Exception{
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager= new DBManager();
			dbManager.anyadirFactura(f);
			break;
		}
	}
	public boolean nifExiste(String nif) throws Exception{
		Boolean existe=false;
		switch(Portada.opcion) {
		case 1:		
			DBManager dbManager = new DBManager();
			existe=dbManager.nifExiste(nif);
			break;
		}
		return existe;
		
	}
	public boolean numeroExiste(String numero) throws Exception{
		Boolean existe=false;
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			existe=dbManager.numeroExiste(numero);
			break;
		}
		return existe;
	}
	public int getLastId() throws Exception{
		int id=0;
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager=new DBManager();
			id=dbManager.getLastId();
			break;
		}
		return id;
	}
	public void borrarFactura(String numero) throws Exception{
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager=new DBManager();
			dbManager.borrarFactura(numero);
			break;
		}
		
	}
	public int getIdCliente(String numero) throws Exception{
		int num=0;
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager=new DBManager();
			num=dbManager.getIdCliente(numero);
			break;
		}
		return num;
		
	}
	public void modificarFactura(Factura factura, String id) throws Exception{		
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			dbManager.modificarFactura(factura, id);	
			break;
		}
	}
}
