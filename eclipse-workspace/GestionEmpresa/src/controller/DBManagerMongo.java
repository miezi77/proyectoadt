package controller;

import java.awt.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.function.Consumer;

import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import static com.mongodb.client.model.Sorts.*;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Aggregates.*;
import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Updates.*;


import org.bson.Document; // mongo-java-driver-322
import org.bson.conversions.Bson;

import model.Cliente;
import model.Factura;

public class DBManagerMongo {
	private Connection con;
	private Statement stmt;

	
	
	
	
	
	private void openConnection() throws Exception {
		/*Class.forName("com.mysql.jdbc.Driver");
		 String url="jdbc:mysql://localhost/empresa2";
		con=DriverManager.getConnection(url,"root","root");  //(url, “nombreBD”, “contraseña”)
		stmt=con.createStatement();*/
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colFacturas=db.getCollection("facturas");
		MongoCollection <Document> colClientes=db.getCollection("clientes");
		
		
		// Nos conectamos a la BD
				// MongoClient cliente = new MongoClient("localhost",27017);
				//MongoClient cliente = new MongoClient();
				//MongoDatabase db = cliente.getDatabase("mibasedatos");
				//MongoCollection<Document> coleccion = db.getCollection("amigos");
		
	}

	private void closeConnection() throws SQLException {
		stmt.close();
		con.close();
	}
	//MÉTODO PARA OBTENER TODOS LOS CLIENTES
	/*public Collection<Cliente>  getClientes() throws Exception {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		this.openConnection();
		//String select = "select * from clientes where id = '" + id + "'";
		String select="select * from clientes";
		ResultSet rs = stmt.executeQuery(select);
		while (rs.next()) {
			clientes.add(new Cliente(rs.getInt("numero"),rs.getString("nif"), rs.getString("nombre"), rs.getString("direccion"), rs.getString("telefono"), rs.getString("email")));

		}	
		rs.close();
		this.closeConnection();
		return clientes;

	}*/
	//MÉTODO PARA OBTENER UN CLIENTE POR ID
	/*public Cliente getClienteById(String id) throws Exception{
		Cliente cliente = new Cliente();
		this.openConnection();
		String select = "select * from clientes where nif = '" + id + "'";
		ResultSet rs = stmt.executeQuery(select);
		while (rs.next()) {
			cliente.setNumero(rs.getInt("numero"));
			cliente.setNif(id);
			cliente.setNombre(rs.getString("nombre"));
			cliente.setDireccion(rs.getString("direccion"));
			cliente.setTelefono(rs.getString("telefono"));
			cliente.setEmail(rs.getString("email"));			
		}	
		rs.close();
		this.closeConnection();		
		return cliente;	
		
	}*/
	//MÉTODO PARA MODIFICAR CLIENTE
	/*public void modificarCliente(Cliente cliente, int id) throws Exception{
		this.openConnection();
		String update = "update clientes set nif = '" + cliente.getNif().toUpperCase() + "', nombre ='" + cliente.getNombre().toUpperCase() + "', direccion = '" + cliente.getDireccion() + "', telefono = '" + cliente.getTelefono() + "', email = '" + cliente.getEmail() + "' where numero = '" + id + "'";		
		stmt.executeUpdate(update);
		this.closeConnection();			
		
	}*/
	//MÉTODO PARA AÑADIR CLIENTE
	/*public void anyadirCliente(Cliente cliente) throws Exception{
		this.openConnection();
		String insert="insert into clientes values ('" + cliente.getNumero() + "','" + cliente.getNif().toUpperCase() + "','" + cliente.getNombre().toUpperCase() + "','" + cliente.getDireccion() + "','" + cliente.getTelefono() + "','" + cliente.getEmail() + "')";
		stmt.executeUpdate(insert);
		this.closeConnection();	
	}*/
	//MÉTODO PARA COMPROBAR SI UN NÚMERO DE NIF YA EXISTE EN LA BBDD
	/*public boolean nifExiste(String nif) throws Exception{
		Boolean existe=false;
		Cliente cliente = new Cliente();
		this.openConnection();
		String query = "SELECT COUNT(*) FROM clientes where nif = '" + nif + "'";
		ResultSet rs = stmt.executeQuery(query);                  
		rs.next();
		existe= rs.getInt("COUNT(*)") > 0;
		// Close connection, statement, and result set.		   
		this.closeConnection();		
		return existe;	
		
	}*/
	//MÉTODO PARA RECUPERAR EL ÚLTIMO ID
	/*public int getLastId() throws Exception{
		int id=0;
		this.openConnection();
		String query="SELECT MAX(numero) FROM clientes";
		ResultSet rs=stmt.executeQuery(query);
		//rs.next();
		if (rs.next()) {
			  id = rs.getInt(1);
			}
		return id;	
	}*/
	//MÉTODO PARA OBTENER TODAS LAS FACTURAS
	public Collection<Factura>  getFacturas() throws Exception {
		
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colFacturas=db.getCollection("facturas");
		MongoCollection <Document> colClientes=db.getCollection("clientes");
 
		// Visualizar los datos elemento a elemento
		ArrayList<Document> consulta = colFacturas.find().into(new ArrayList<Document>());
		System.out.println(" - ----------------------------------------");
		for (int i = 0; i < consulta.size(); i++) {
			Document fact = consulta.get(i);
			//System.out.println(" - " + fact.getString("numero") + " - " + fact.getString("fecha") + " - "	+ fact.getString("nombre") + " - " + fact.getString("concepto")+((ResultSet) fact).getFloat("total")+fact.getString("estado"));
			//System.out.println(fact.getString("numero").toString());
			//facturas.add(new Factura(fact.getString("numero"),fact.getString("fecha"),fact.getString("nombre"),fact.getString("concepto"), i, null));
			
		//db.listCollections().forEach((Consumer<Document>) (Document d) -> { System.out.println(d.toJson()); });//lista las colecciones
		}
		System.out.println(" - ----------------------------------------");
		return facturas;
	}
	//MÉTODO PARA OBTENER UN FACTURA POR NÚMERO
	/*public Factura getFacturaById(String id) throws Exception{
		Factura factura = new Factura();
		this.openConnection();
		String select = "select f.numero, f.fecha, c.nombre, f.concepto, f.total, f.estado from facturas f, clientes c where f.numero = '" + id + "' and f.cliente=c.numero";
		ResultSet rs = stmt.executeQuery(select);
		while (rs.next()) {
			factura.setNumero(id);
			factura.setFechaEmision(rs.getString("fecha"));
			factura.setCliente(rs.getString("nombre"));
			factura.setConcepto(rs.getString("concepto"));
			factura.setEstado(rs.getString("estado"));	
			factura.setTotal(rs.getFloat("total"));
					
		}	
		rs.close();
		this.closeConnection();		
		return factura;	
		
	}*/
	//MÉTODO PARA AÑADIR FACTURA
	/*public void anyadirFactura(Factura factura) throws Exception{
		this.openConnection();
		String fecString = factura.getFechaEmision();
	        		java.sql.Date fecFormatoDate = null;
	        		try {
	        		      SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd", new Locale("es", "ES"));
	        		      fecFormatoDate = new java.sql.Date(sdf.parse(fecString).getTime());
	        		      System.out.println("Fecha con el formato java.sql.Date: " + fecFormatoDate);
	        		} catch (Exception ex) {
	        		      System.out.println("Error al obtener el formato de la fecha/hora: " + ex.getMessage());
	        		}
	        		int numcliente=Integer.parseInt(factura.getCliente());
	    
		String insert="insert into facturas values ('" + factura.getNumero() + "','" + fecFormatoDate + "','" + factura.getEstado()+ "','" + factura.getCliente() + "','" + factura.getConcepto() + "','" + factura.getTotal() + "')";
		stmt.executeUpdate(insert);
		this.closeConnection();	
	}*/
	//MÉTODO PARA COMPROBAR SI UN NÚMERO DE FACTURA YA EXISTE EN LA BBDD
		/*public boolean numeroExiste(String numero) throws Exception{
			Boolean existe=false;
			Factura factura = new Factura();
			this.openConnection();
			String query = "SELECT COUNT(*) FROM facturas where numero = '" + numero + "'";
			ResultSet rs = stmt.executeQuery(query);                  
			rs.next();
			existe= rs.getInt("COUNT(*)") > 0;
			// Close connection, statement, and result set.		   
			this.closeConnection();		
			return existe;	
			
		}*/
		
		//MÉTODO PARA BORRAR FACTURA
		/*public void borrarFactura(String numero) throws Exception{	
			this.openConnection();
			String update = "DELETE FROM facturas where numero = '" + numero + "'";
			stmt.executeUpdate(update);
			this.closeConnection();		
			
		}*/
		//MÉTODO PARA OBTENER EL ID DEL CLIENTE
		/*public int getIdCliente(String numero) throws Exception{
			int num=0;
			this.openConnection();
			String query="SELECT cliente FROM facturas where numero = '" + numero + "'";
			//stmt.executeUpdate(query);
			
			ResultSet rs = stmt.executeQuery(query); 
			while(rs.next()) {
				num=rs.getInt(1);
			}			
			return num;
			
		}*/
		//MÉTODO PARA MODIFICAR CLIENTE
		/*public void modificarFactura(Factura factura, String id) throws Exception{
			this.openConnection();
			String update = "update facturas set numero = '" + factura.getNumero() + "', fecha ='" + factura.getFechaEmision() + "', estado = '" + factura.getEstado() + "', cliente = '" + factura.getCliente() + "', concepto = '" + factura.getConcepto() + "' , total = '" + factura.getTotal() +"' where numero = '" + id + "'";		
			stmt.executeUpdate(update);
			this.closeConnection();			
			
		}*/

}
