package controller;
import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import model.Factura;
import model.Servicio;
import model.Cliente;

public class DBManager {
	private Connection con;
	private Statement stmt;
	
	private void openConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		 String url="jdbc:mysql://localhost/empresa2";
		con=DriverManager.getConnection(url,"root","root");  //(url, �nombreBD�, �contrase�a�)
		stmt=con.createStatement();
	}

	private void closeConnection() throws SQLException {
		stmt.close();
		con.close();
	}
	//M�TODO PARA OBTENER TODOS LOS CLIENTES
	public Collection<Cliente>  getClientes() throws Exception {
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

	}
	//M�TODO PARA OBTENER UN CLIENTE POR ID
	public Cliente getClienteById(String id) throws Exception{
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
		
	}
	//M�TODO PARA MODIFICAR CLIENTE
	public void modificarCliente(Cliente cliente, int id) throws Exception{
		this.openConnection();
		String update = "update clientes set nif = '" + cliente.getNif().toUpperCase() + "', nombre ='" + cliente.getNombre().toUpperCase() + "', direccion = '" + cliente.getDireccion() + "', telefono = '" + cliente.getTelefono() + "', email = '" + cliente.getEmail() + "' where numero = '" + id + "'";		
		stmt.executeUpdate(update);
		this.closeConnection();			
		
	}
	//M�TODO PARA A�ADIR CLIENTE
	public void anyadirCliente(Cliente cliente) throws Exception{
		this.openConnection();
		String insert="insert into clientes values ('" + cliente.getNumero() + "','" + cliente.getNif().toUpperCase() + "','" + cliente.getNombre().toUpperCase() + "','" + cliente.getDireccion() + "','" + cliente.getTelefono() + "','" + cliente.getEmail() + "')";
		stmt.executeUpdate(insert);
		this.closeConnection();	
	}
	//M�TODO PARA COMPROBAR SI UN N�MERO DE NIF YA EXISTE EN LA BBDD
	public boolean nifExiste(String nif) throws Exception{
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
		
	}
	//M�TODO PARA RECUPERAR EL �LTIMO ID
	public int getLastId() throws Exception{
		int id=0;
		this.openConnection();
		String query="SELECT MAX(numero) FROM clientes";
		ResultSet rs=stmt.executeQuery(query);
		//rs.next();
		if (rs.next()) {
			  id = rs.getInt(1);
			}
		return id;	
	}
	//M�TODO PARA OBTENER TODAS LAS FACTURAS
	public Collection<Factura>  getFacturas() throws Exception {
		ArrayList<Factura> facturas = new ArrayList<Factura>();
		this.openConnection();
		//String select = "select * from clientes where id = '" + id + "'";
		String select="select f.numero, f.fecha, c.nombre, f.concepto, f.total, f.estado from facturas f, clientes c where f.cliente=c.numero";
		ResultSet rs = stmt.executeQuery(select);
		while (rs.next()) {
			facturas.add(new Factura(rs.getString("numero"),rs.getString("fecha"),rs.getString("nombre"),rs.getString("concepto"),rs.getFloat("total"),rs.getString("estado")));

		}	
		rs.close();
		this.closeConnection();
		return facturas;
	}
	//M�TODO PARA OBTENER UN FACTURA POR N�MERO
	public Factura getFacturaById(String id) throws Exception{
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
		
	}
	//M�TODO PARA A�ADIR FACTURA
	public void anyadirFactura(Factura factura) throws Exception{
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
	}
	//M�TODO PARA COMPROBAR SI UN N�MERO DE FACTURA YA EXISTE EN LA BBDD
		public boolean numeroExiste(String numero) throws Exception{
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
			
		}
		
		//M�TODO PARA BORRAR FACTURA
		public void borrarFactura(String numero) throws Exception{	
			this.openConnection();
			String update = "DELETE FROM facturas where numero = '" + numero + "'";
			stmt.executeUpdate(update);
			this.closeConnection();		
			
		}
		//M�TODO PARA OBTENER EL ID DEL CLIENTE
		public int getIdCliente(String numero) throws Exception{
			int num=0;
			this.openConnection();
			String query="SELECT cliente FROM facturas where numero = '" + numero + "'";
			//stmt.executeUpdate(query);
			
			ResultSet rs = stmt.executeQuery(query); 
			while(rs.next()) {
				num=rs.getInt(1);
			}			
			return num;
			
		}
		//M�TODO PARA MODIFICAR CLIENTE
		public void modificarFactura(Factura factura, String id) throws Exception{
			this.openConnection();
			String update = "update facturas set numero = '" + factura.getNumero() + "', fecha ='" + factura.getFechaEmision() + "', estado = '" + factura.getEstado() + "', cliente = '" + factura.getCliente() + "', concepto = '" + factura.getConcepto() + "' , total = '" + factura.getTotal() +"' where numero = '" + id + "'";		
			stmt.executeUpdate(update);
			this.closeConnection();			
			
		}
}
