package controller;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.Cliente;
import model.Factura;
import model.Servicio;
import view.Portada;
import static com.mongodb.client.model.Filters.*;

public class Manager {
	//opción 1: MySQL
	//opción 2: Mongo
	//opción 3: Oracle
	//opción 4:Hibernate
	
	public Collection<Cliente> getClientes() throws Exception {//FALTA ORACLE
		Collection<Cliente> clientes = new ArrayList<Cliente>();
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			clientes = dbManager.getClientes();	
			break;
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			clientes=dbMngMg.getClientes();
			break;
		case 4:
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			clientes=dbMngHb.getClientes();
			break;
		case 3:
			DBManagerOracle dbMngOr=new DBManagerOracle();
			clientes=dbMngOr.getClientes();
			break;
		}
		return clientes;
	}
	public Collection<Factura> getFacturas() throws Exception {//FALTA ORACLE
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
	public Cliente getClienteById(String id) throws Exception{//FALTA ORACLE
		Cliente cliente = new Cliente();
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			cliente=dbManager.getClienteById(id);
			break;	
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			cliente=dbMngMg.getClienteById(id);
			break;
		case 4:
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			cliente=dbMngHb.getClienteById(id);
			break;
		}
		return cliente;
	}
	public Factura getFacturaById(String id) throws Exception{//FALTA ORACLE
		Factura factura = new Factura();
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			factura=dbManager.getFacturaById(id);
			break;
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			factura=dbMngMg.getFacturaById(id);
			break;
		case 4:
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			factura=dbMngHb.getFacturaById(id);
			break;
		}
		return factura;
		
	}

	public void modificarCliente(Cliente cliente, int id) throws Exception{		//FALTA ORACLE
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			dbManager.modificarCliente(cliente, id);	
			break;
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			dbMngMg.modificarCliente(cliente, id);
			break;
		case 4:
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			dbMngHb.modificarCliente(cliente, id);
			break;
		}
		
	}
	public void anyadirCliente(Cliente c) throws Exception{//FALTA ORACLE
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager= new DBManager();
			dbManager.anyadirCliente(c);
			break;
		
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			dbMngMg.anyadirCliente(c);
			break;
		case 4: 
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			dbMngHb.anyadirCliente(c);
			break;
			
	}}
	public void anyadirFactura(Factura f) throws Exception{//FALTA ORACLE, HIBERNATE
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager= new DBManager();
			dbManager.anyadirFactura(f);
			break;
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			dbMngMg.anyadirFactura(f);
			break;
		case 4:
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			dbMngHb.anyadirFactura(f);
		}
	}
	public boolean nifExiste(String nif) throws Exception{//HIBERNATE Y ORACLE (en MONGO NO FUNCIONA CORRECTAMENTE)
		Boolean existe=false;
		switch(Portada.opcion) {
		case 1:		
			DBManager dbManager = new DBManager();
			existe=dbManager.nifExiste(nif);
			break;

		}
		return existe;
		
	}
	public boolean numeroExiste(String numero) throws Exception{ //HIBERNATE Y ORACLE (en MONGO NO FUNCIONA CORRECTAMENTE)
		Boolean existe=false;
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			existe=dbManager.numeroExiste(numero);
			break;

		}
		return existe;
	}
	public int getLastId() throws Exception{ //FALTA ORACLE
		int id=0;
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager=new DBManager();
			id=dbManager.getLastId();
			break;
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			id=dbMngMg.getLastId();
			break;
		case 4:
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			id=dbMngHb.getLastId();
			break;
			
		}
		return id;
	}
	public void borrarFactura(String numero) throws Exception{ //FALTA ORACLE
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager=new DBManager();
			dbManager.borrarFactura(numero);
			break;
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			dbMngMg.borrarFactura(numero);
			break;
		case 4:
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			dbMngHb.borrarFactura(numero);
			break;
		}
		
	}
	public int getIdCliente(String numero) throws Exception{ // FALTA HIBERNATE Y ORACLE
		int num=0;
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager=new DBManager();
			num=dbManager.getIdCliente(numero);
			break;
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			num=dbMngMg.getIdCliente(numero);
			break;
		case 4:
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			num=dbMngHb.getIdCliente(numero);
			break;
		}
		return num;
		
	}
	public void modificarFactura(Factura factura, String id) throws Exception{		//FALTA HIBERNATE Y ORACLE
		switch(Portada.opcion) {
		case 1:
			DBManager dbManager = new DBManager();
			dbManager.modificarFactura(factura, id);	
			break;
		case 2:
			DBManagerMongo dbMngMg = new DBManagerMongo();
			dbMngMg.modificarFactura(factura, id);	
			break;
		case 4:
			DBManagerHibernate dbMngHb= new DBManagerHibernate();
			dbMngHb.modificarFactura(factura, id);
			break;
		}
	}
}
