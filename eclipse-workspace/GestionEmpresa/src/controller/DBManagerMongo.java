package controller;

import java.util.List;
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

import javax.swing.JOptionPane;

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
import com.mongodb.BasicDBObject;

import org.bson.Document; // mongo-java-driver-322
import org.bson.conversions.Bson;

import model.Cliente;
import model.Factura;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Updates.*;

public class DBManagerMongo {
	private Connection con;
	private Statement stmt;
	private MongoCollection<Document> mongoCollection;
	
	
	
	
	
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
	//MÉTODO PARA OBTENER TODOS LOS CLIENTES---------------------------------------HECHO
	public Collection<Cliente>  getClientes() throws Exception {
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colClientes=db.getCollection("clientes");
		//ArrayList<Cliente> facturas = new ArrayList<Cliente>(); 
		// Visualizar los datos elemento a elemento       
        List<Cliente> clients = new ArrayList<Cliente>();        
        MongoCursor<Document> docs = colClientes.find().iterator();
        while (docs.hasNext()) {
            Document doc = docs.next();
            /*Double num=doc.getDouble("numero");
            System.out.println(num);
            int numero=num.intValue();
            System.out.println(numero);*/
            clients.add(new Cliente(doc.getInteger("numero"), doc.getString("nif"),doc.getString("nombre"), doc.getString("direccion"), doc.getString("telefono"),doc.getString("email")));
            System.out.println(clients);            
        }
        con.close();        
		return clients;
	}
	//MÉTODO PARA OBTENER UN CLIENTE POR ID----------------------HECHO
	public Cliente getClienteById(String id) throws Exception{
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colClientes=db.getCollection("clientes");		
		Document doc=colClientes.find(eq("nif",id)).first();		
		Cliente cliente = new Cliente(doc.getInteger("numero"),doc.getString("nif"),doc.getString("nombre"),doc.getString("direccion"),doc.getString("telefono"),doc.getString("email"));		
		con.close();
		return cliente;	
		
	}
	//MÉTODO PARA MODIFICAR CLIENTE---------------------------------------------HECHO
	public void modificarCliente(Cliente cliente, int id) throws Exception{
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colClientes=db.getCollection("clientes");
		//colClientes.updateOne(eq("nif",id),set("numero", cliente.getNumero()));

 
        BasicDBObject updateDocument = new BasicDBObject();
        BasicDBObject changes = new BasicDBObject();
        changes.append("nif", cliente.getNif());
        changes.append("nombre", cliente.getNombre());
        changes.append("direccion", cliente.getDireccion());
        changes.append("telefono", cliente.getTelefono());
        changes.append("email", cliente.getEmail());

        
        updateDocument.append("$set", changes);
        BasicDBObject searchQuery = new BasicDBObject().append("numero", id);
        colClientes.updateOne(searchQuery, updateDocument);
		con.close();
		
	}
	//MÉTODO PARA AÑADIR CLIENTE-------------------------------------HECHO
	public void anyadirCliente(Cliente cliente) throws Exception{
		
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colClientes=db.getCollection("clientes");

        Document doc = new Document();//numero, nif, nombre, direccion, telefono, email
        doc.put("numero",cliente.getNumero());//cast from integer to double
        doc.put("nif", cliente.getNif());
        doc.put("nombre", cliente.getNombre());
        doc.put("direccion", cliente.getDireccion());
        doc.put("telefono", cliente.getTelefono());
        doc.put("email", cliente.getEmail());      
        
       System.out.println(doc);
        colClientes.insertOne(doc);
        con.close();
	}
	//MÉTODO PARA COMPROBAR SI UN NÚMERO DE NIF YA EXISTE EN LA BBDD ------------------- NO FUNCIONA CORRECTAMENTE
	/*public boolean nifExiste(String nif) throws Exception{
		
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colClientes=db.getCollection("clientes");
		Boolean existe=false;
		Document doc=colClientes.find(eq("nif",nif)).first();
		try {
			System.out.println("Localizado: "+doc.toJson());
			existe=true;
		}
		catch(NullPointerException e) {
			//existe=false;
			System.out.println(e.getMessage()); 
		}
		con.close();
		return existe;
		
	}*/
	//MÉTODO PARA RECUPERAR EL ÚLTIMO ID-------------------------------------HECHO
	public int getLastId() throws Exception{
	
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colClientes=db.getCollection("clientes");
		
	        Integer id = 0;
	        MongoCursor<Document> docs = colClientes.find().sort(descending("numero")).iterator();
	        //docs.next().getInteger("id");
	        while (docs.hasNext()) {
	            id = docs.next().getInteger("numero");
	            break;
	        }
	        con.close();
	        return id ;//id+1 no está bien
		
	}
	//MÉTODO PARA OBTENER TODAS LAS FACTURAS------------------------------------HECHO
	public Collection<Factura>  getFacturas() throws Exception {
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colFacturas=db.getCollection("facturas");
		// Visualizar los datos elemento a elemento       
        List<Factura> facts = new ArrayList<Factura>();        
        MongoCursor<Document> docs = colFacturas.find().iterator();
        while (docs.hasNext()) {
            Document doc = docs.next();
            //Document something = doc.get("cliente", Document.class);//esto lo he pillado de stackoverflow
            //String nested = something.getString("nombre");           
            //facts.add(new Factura(doc.getString("numero"),doc.getString("fechaEmision"),nested,doc.getString("concepto"),doc.getDouble("total"),doc.getString("estado"))); 
            facts.add(new Factura(doc.getString("numero"),doc.getString("fechaEmision"),doc.getString("cliente"),doc.getString("concepto"),doc.getDouble("total"),doc.getString("estado"))); 
            
            System.out.println(doc);            
        }
        docs.close();        
		return facts;
	}
	//MÉTODO PARA OBTENER UN FACTURA POR NÚMERO---------------------------------------HECHO
	public Factura getFacturaById(String id) throws Exception{
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colFacturas=db.getCollection("facturas");
		
		Document doc=colFacturas.find(eq("numero",id)).first();
		
		Factura factura = new Factura(doc.getString("numero"),doc.getString("fechaEmision"),doc.getString("cliente"),doc.getString("concepto"),doc.getDouble("total"),doc.getString("estado"));
		con.close();
		return factura;	
		
	}
	//MÉTODO PARA AÑADIR FACTURA------------------------------------------------ HECHO
	public void anyadirFactura(Factura factura) throws Exception{
		MongoClient con = new MongoClient("localhost",27017);
		MongoDatabase db=con.getDatabase("enpresa");
		MongoCollection <Document> colFacturas=db.getCollection("facturas");

		
        Document doc = new Document();//numero, nif, nombre, direccion, telefono, email
        doc.put("numero",factura.getNumero());//cast from integer to double
        doc.put("fechaEmision", factura.getFechaEmision());
       doc.put("cliente", factura.getCliente());
        
        doc.put("concepto", factura.getConcepto());
        doc.put("total", factura.getTotal());
        doc.put("estado", factura.getEstado());
             
        
       //System.out.println(doc);
       colFacturas.insertOne(doc);
        con.close();		
	}
	//MÉTODO PARA COMPROBAR SI UN NÚMERO DE FACTURA YA EXISTE EN LA BBDD -------------------------- NO FUNCIONA CORRECTAMENTE
		/*public boolean numeroExiste(String numero) throws Exception{
			Boolean existe=false;
			MongoClient con = new MongoClient("localhost",27017);
			MongoDatabase db=con.getDatabase("enpresa");
			MongoCollection <Document> colFacturas=db.getCollection("facturas");
			Document doc=colFacturas.find(eq("numero",numero)).first();
			
			if (doc.isEmpty()) {
				existe=false;
			}
			else {existe=true;}
			/*try {
				existe=true;
			}
			catch(NullPointerException e) {
				existe=false;
				System.out.println(e.getMessage());
			}
			con.close();
			return existe;	
			
		}*/
		
		//MÉTODO PARA BORRAR FACTURA
		public void borrarFactura(String numero) throws Exception{	
			MongoClient con = new MongoClient("localhost",27017);
			MongoDatabase db=con.getDatabase("enpresa");
			MongoCollection <Document> colFacturas=db.getCollection("facturas");
			DeleteResult del=colFacturas.deleteOne(eq("numero",numero));
			con.close();
			
		}
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
		//MÉTODO PARA MODIFICAR FACTURA -------------------------------------------------- HECHO
		public void modificarFactura(Factura factura, String id) throws Exception{
			MongoClient con = new MongoClient("localhost",27017);
			MongoDatabase db=con.getDatabase("enpresa");
			MongoCollection <Document> colFacturas=db.getCollection("facturas");
			//colClientes.updateOne(eq("nif",id),set("numero", cliente.getNumero()));
			
			//String nombre;
			//Cliente cliente=getNombreClienteById(factura.getCliente());
			//nombre=cliente.getNombre();
			//int idCliente=cliente.getNumero();
			//System.out.println("nombre: "+nombre+" id: "+idCliente);
			
	        BasicDBObject updateDocument = new BasicDBObject();
	        BasicDBObject changes = new BasicDBObject();
	        changes.append("numero", factura.getNumero());
	        changes.append("fechaEmision",factura.getFechaEmision());
	        changes.append("cliente", factura.getCliente());
	        //changes.append("cliente", idCliente);
	        //changes.append("cliente", nombre);
	        changes.append("concepto", factura.getConcepto());
	        changes.append("total", factura.getTotal());
	        changes.append("estado", factura.getEstado());
	        
	        updateDocument.append("$set", changes);
	        BasicDBObject searchQuery = new BasicDBObject().append("numero", id);
	        colFacturas.updateOne(searchQuery, updateDocument);		
	        con.close();
			
		}
//GET NOMBRE CLIENTE BY ID
		public Cliente getNombreClienteById(String id) throws Exception{
			MongoClient con = new MongoClient("localhost",27017);
			MongoDatabase db=con.getDatabase("enpresa");
			MongoCollection <Document> colClientes=db.getCollection("clientes");
			String nombre;
			
			Document doc=colClientes.find(eq("numero",id)).first();			
			//Factura factura = new Factura(doc.getString("numero"),doc.getString("fechaEmision"),doc.getString("cliente"),doc.getString("concepto"),doc.getDouble("total"),doc.getString("estado"));
			Cliente cliente = new Cliente(doc.getInteger("numero"),doc.getString("nif"),doc.getString("nombre"),doc.getString("direccion"),doc.getString("telefono"),doc.getString("email"));		
			
			System.out.println(cliente);
			con.close();
			return cliente;	
			
		}
		
		//MÉTODO PARA OBTENER EL ID DEL CLIENTE
		public int getIdCliente(String numero) throws Exception{
			String num;
			MongoClient con = new MongoClient("localhost",27017);
			MongoDatabase db=con.getDatabase("enpresa");
			MongoCollection <Document> colFacturas=db.getCollection("facturas");
			
			Document doc=colFacturas.find(eq("numero",numero)).first();			
			Factura factura = new Factura(doc.getString("numero"),doc.getString("fechaEmision"),doc.getString("cliente"),doc.getString("concepto"),doc.getDouble("total"),doc.getString("estado"));
			//Cliente cliente = new Cliente(doc.getInteger("numero"),doc.getString("nif"),doc.getString("nombre"),doc.getString("direccion"),doc.getString("telefono"),doc.getString("email"));		
			num=factura.getCliente();
			
			int n = Integer.parseInt(num);
			con.close();
			return n;
			
		}
		
		
}
