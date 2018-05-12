package controller;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.type.IntegerType;

import classes.HibernateUtil;
import model.Factura;

import model.Factura;
import model.Cliente;
import classes.Clientes;
import classes.Facturas;


public class DBManagerHibernate {
	
    private Session session;
    //private static final Logger LOGGER=Logger.getLogger("GestorHorarios");

	private void openConnection() throws Exception {
		SessionFactory sesion=HibernateUtil.getSessionFactory();
		session=sesion.openSession();
	}
	private void closeConnection() throws Exception {
		session.close();
	}
	
	//MÉTODO PARA OBTENER TODAS LAS FACTURAS ---------------------------------FUNCIONA
    public ArrayList<Factura> getFacturas() throws Exception{
        
        ArrayList<Factura>usu=new ArrayList<>();
         Factura aux;
         this.openConnection();
         List  <Facturas> result =  session.createQuery("FROM Facturas").list();//esto funciona pero el problema es que no me muestra el nombre del cliente
         //List  <Facturas> result =  session.createQuery("select f.numero, f.fecha, c.nombre, f.concepto, f.total, f.estado  from Facturas f, Clientes c  where f.clientes = c.numero").list();
         //en mysql: select f.numero, f.fecha, c.nombre, f.concepto, f.total, f.estado from facturas f, clientes c where f.cliente=c.numero"
         Iterator<Facturas> l= result.iterator();
         
         while(l.hasNext()){
            Facturas u=l.next();
            aux=new Factura();
            aux.setNumero(u.getNumero());
            aux.setFechaEmision(u.getFecha());		
            aux.setCliente(u.getClientes().getNombre());
            aux.setConcepto(u.getConcepto());
            aux.setTotal(u.getTotal());
            aux.setEstado(u.getEstado());
            usu.add(aux);
         
         }
         
         this.closeConnection();
         return usu;
   } 
    
    //MÉTODO PARA OBTENER TODOS LOS CLIENTES --------------------------------FUNCIONA
    public ArrayList<Cliente> getClientes() throws Exception{
    ArrayList<Cliente>usu=new ArrayList<>();
    Cliente aux;
    this.openConnection();
    List  <Clientes> result =  session.createQuery("FROM Clientes").list();//esto funciona pero el problema es que no me muestra el nombre del cliente
    //List  <Facturas> result =  session.createQuery("select f.numero, f.fecha, c.nombre, f.concepto, f.total, f.estado  from Facturas f, Clientes c  where f.clientes = c.numero").list();
    //en mysql: select f.numero, f.fecha, c.nombre, f.concepto, f.total, f.estado from facturas f, clientes c where f.cliente=c.numero"
    Iterator<Clientes> l= result.iterator();
    
    while(l.hasNext()){
       Clientes u=l.next();
       aux=new Cliente();
       aux.setNumero(u.getNumero());
       aux.setNif(u.getNif());		
       aux.setNombre(u.getNombre());
       aux.setDireccion(u.getDireccion());
       aux.setTelefono(u.getTelefono());
       aux.setEmail(u.getEmail());
       usu.add(aux);
    
    }
    
    this.closeConnection();
    return usu;
    }
    //MÉTODO PARA CREAR NUEVO CLIENTE
    public void anyadirCliente(Cliente cliente) throws Exception{
       
        this.openConnection();        
        Transaction tx=session.beginTransaction();
        Clientes c=new Clientes();
         c.setNumero(cliente.getNumero());
         c.setNif(cliente.getNif());
         c.setNombre(cliente.getNombre());
         c.setDireccion(cliente.getDireccion());
         c.setTelefono(cliente.getTelefono());
         c.setEmail(cliente.getEmail());      
        session.save(c);
        tx.commit();         
        this.closeConnection();
    }
    //MÉTODO PARA OBTENER EL ÚLTIMO ID DEL CLIENTE
	public int getLastId() throws Exception{
		int id=0;
		this.openConnection();
		 Integer count = (Integer) session.createSQLQuery("Select max(numero) as num_results FROM Clientes").addScalar("num_results", new IntegerType()).uniqueResult();
		this.closeConnection();
		return count;	
	}
    //MÉTODO PARA INSERTAR NUEVA FACTURA ---------------------------------------------------- SOLO ME FALTA AÑADIR
	public void anyadirFactura(Factura factura) throws Exception{
		
	      this.openConnection();        
	        Transaction tx=session.beginTransaction();
	      	  
	        Cliente cli=new Cliente();
	     			int num=Integer.parseInt(factura.getCliente());
	     			System.out.println("el número del cliente es "+num);
	     	        //this.openConnection();        
	     	         List  <Clientes> result =  session.createQuery("FROM Clientes where numero='"+num+"'").list();
	     	         Iterator<Clientes> l= result.iterator();        
	     	         Clientes c=l.next();         
	     	         cli.setNumero(c.getNumero());
	     	         cli.setEmail(c.getEmail());
	     	         cli.setNif(c.getNif());
	     	         cli.setNombre(c.getNombre());
	     	         cli.setDireccion(c.getDireccion());
	     	         cli.setTelefono(c.getTelefono());
	     	         cli.setEmail(c.getEmail());
	     	         
	     	    	Clientes cl=new Clientes();
	     			cl.setNumero(cli.getNumero());
	     			cl.setNif(cli.getNif());
	     			cl.setNombre(cli.getNombre());
	     			cl.setDireccion(cli.getDireccion());
	     			cl.setTelefono(cli.getTelefono());
	     			cl.setEmail(cli.getEmail());
	     			System.out.println("el cliente es "+cl.getNombre());
	        
	        Facturas f=new Facturas();
	         f.setNumero(factura.getNumero());
	         f.setClientes(cl);
	         f.setFecha(factura.getFechaEmision());	         
	         f.setEstado(factura.getEstado());
	         f.setConcepto(factura.getConcepto());
	         f.setTotal((float) factura.getTotal());
	        session.save(f);
	        tx.commit();         
	        this.closeConnection();
		
		/* this.openConnection();        
	        //obtener el cliente entero  
	         Cliente cli=new Cliente();
			int num=Integer.parseInt(factura.getCliente());
			System.out.println("el número del cliente es "+num);
	        //this.openConnection();        
	         List  <Clientes> result =  session.createQuery("FROM Clientes where numero='"+num+"'").list();
	         Iterator<Clientes> l= result.iterator();        
	         Clientes c=l.next();         
	         cli.setNumero(c.getNumero());
	         cli.setEmail(c.getEmail());
	         cli.setNif(c.getNif());
	         cli.setNombre(c.getNombre());
	         cli.setDireccion(c.getDireccion());
	         cli.setTelefono(c.getTelefono());
	         cli.setEmail(c.getEmail());            
	         //this.closeConnection();     
	         System.out.println(cli);       
        
        Transaction tx=session.beginTransaction();
        
        Facturas f=new Facturas();
         f.setNumero(factura.getNumero());
         f.setFecha(factura.getFechaEmision());
         f.setClientes(c);
         f.setEstado(factura.getEstado());
         f.setConcepto(factura.getConcepto());
         f.setTotal((float) factura.getTotal());            
        
          session.save(f);
          tx.commit();         
        this.closeConnection();	*/	
	
	}
    
	//MÉTODO PARA OBTENER UN CLIENTE POR NIF para buscar un cliente por nif en el campo de búsqueda
	public Cliente getClienteById(String id) throws Exception{
		Cliente cli=new Cliente();
        this.openConnection();        
         List  <Clientes> result =  session.createQuery("FROM Clientes where nif='"+id+"'").list();
         Iterator<Clientes> l= result.iterator();        
         Clientes c=l.next();         
         cli.setNumero(c.getNumero());
         cli.setEmail(c.getEmail());
         cli.setNif(c.getNif());
         cli.setNombre(c.getNombre());
         cli.setDireccion(c.getDireccion());
         cli.setTelefono(c.getTelefono());
         cli.setEmail(c.getEmail());            
         this.closeConnection();     
         System.out.println(cli);
       return cli;		
	}
	//MÉTODO PARA OBTENER UN CLIENTE POR ID para obtener todo el cliente por su número y poder modificar y añadir la factura
	public Cliente getClienteById2(String id) throws Exception{
		Cliente cli=new Cliente();
		int num=Integer.parseInt(id);
        this.openConnection();        
         List  <Clientes> result =  session.createQuery("FROM Clientes where numero='"+num+"'").list();
         Iterator<Clientes> l= result.iterator();        
         Clientes c=l.next();         
         cli.setNumero(c.getNumero());
         cli.setEmail(c.getEmail());
         cli.setNif(c.getNif());
         cli.setNombre(c.getNombre());
         cli.setDireccion(c.getDireccion());
         cli.setTelefono(c.getTelefono());
         cli.setEmail(c.getEmail());            
         this.closeConnection();     
         System.out.println(cli);
       return cli;		
	}
	//obtener un cliente a partir de la factura ----------------------------------- NO UTILIZADO
	public Cliente getClienteByName(Factura factura) throws Exception{
		Cliente cli=new Cliente();
        this.openConnection();
        String id=factura.getCliente();//por el nombre del cliente de la factura, obtenemos todo el cliente  
        this.openConnection();
        List  <Clientes> result =  session.createQuery("FROM Clientes where id='"+id+"'").list();
         Iterator<Clientes> l= result.iterator();        
         Clientes c=l.next();         
         cli.setNumero(c.getNumero());
         cli.setEmail(c.getEmail());
         cli.setNif(c.getNif());
         cli.setNombre(c.getNombre());
         cli.setDireccion(c.getDireccion());
         cli.setTelefono(c.getTelefono());
         cli.setEmail(c.getEmail());         
         System.out.println(cli);        
         this.closeConnection();         
       return cli;
		
	}
	//MÉTODO PARA MODIFICAR UN CLIENTE ------------------------------------------ HECHO
	public void modificarCliente(Cliente cliente,int id) throws Exception{        
        this.openConnection();        
          Transaction tx=session.beginTransaction();
          List  <Clientes> result =  session.createQuery("FROM Clientes where numero='"+id+"'").list();
          Iterator<Clientes> l= result.iterator();         
          Clientes c=l.next();          
          c.setEmail(cliente.getEmail());
          c.setNombre(cliente.getNombre());
          c.setNif(cliente.getNif());
          c.setDireccion(cliente.getDireccion());
          c.setTelefono(cliente.getTelefono());
          c.setEmail(cliente.getEmail());
            session.update(c);
            tx.commit();                 
        this.closeConnection();
        
    }
	//MÉTODO PARA BUSCAR FACTURA POR Nº
	public Factura getFacturaById(String id) throws Exception{
		Factura fact=new Factura();
        this.openConnection();        
         List  <Facturas> result =  session.createQuery("FROM Facturas where numero='"+id+"'").list();
         Iterator<Facturas> l= result.iterator();        
         Facturas c=l.next();         
         fact.setNumero(c.getNumero());
         fact.setCliente(c.getClientes().getNombre());
         fact.setConcepto(c.getConcepto());
         fact.setEstado(c.getEstado());
         fact.setFechaEmision(c.getFecha());
         fact.setTotal(c.getTotal());            
         this.closeConnection();         
       return fact;  
				
	}
	//MÉTODO PARA BORRAR FACTURA
	public void borrarFactura(String numero) throws Exception{	
		this.openConnection();
	    Transaction tx=session.beginTransaction();
	    Facturas f = (Facturas) session.get(Facturas.class, numero);
	    session.delete(f);    
	    tx.commit();   
	    this.closeConnection();	
	}
	//MÉTODO PARA OBTENER EL ID DEL CLIENTE
	public int getIdCliente(String numero) throws Exception{		
		this.openConnection();
		Integer num = (Integer) session.createSQLQuery("Select cliente as num_results FROM Facturas WHERE numero = '" + numero + "'").addScalar("num_results", new IntegerType()).uniqueResult();
		this.closeConnection();
		return num;
		
	}
	//MÉTODO PARA MODIFICAR UN UNA FACTURA -------------------------------------------------- HECHO
	public void modificarFactura(Factura factura,String id) throws Exception{ 
		
        this.openConnection();        
        //obtener el cliente entero      
        
        Cliente cli=new Cliente();
		int num=Integer.parseInt(factura.getCliente());
		System.out.println("el número del cliente es "+num);
        //this.openConnection();        
         List  <Clientes> result =  session.createQuery("FROM Clientes where numero='"+num+"'").list();
         Iterator<Clientes> l= result.iterator();        
         Clientes c=l.next();         
         cli.setNumero(c.getNumero());
         cli.setEmail(c.getEmail());
         cli.setNif(c.getNif());
         cli.setNombre(c.getNombre());
         cli.setDireccion(c.getDireccion());
         cli.setTelefono(c.getTelefono());
         cli.setEmail(c.getEmail());            
         //this.closeConnection();     
         System.out.println(cli);       
        
                
		Clientes cl=new Clientes();
		cl.setNumero(cli.getNumero());
		cl.setNif(cli.getNif());
		cl.setNombre(cli.getNombre());
		cl.setDireccion(cli.getDireccion());
		cl.setTelefono(cli.getTelefono());
		cl.setEmail(cli.getEmail());
		System.out.println("el cliente es "+cl.getNombre());
		
          Transaction tx=session.beginTransaction();
          List  <Facturas> result2 =  session.createQuery("FROM Facturas where numero='"+id+"'").list();
          Iterator<Facturas> l2= result2.iterator();         
          Facturas c2=l2.next();          
          c2.setConcepto(factura.getConcepto());
          c2.setClientes(cl);
          c2.setEstado(factura.getEstado());
          c2.setFecha(factura.getFechaEmision());
          c2.setNumero(factura.getNumero());
          c2.setTotal((float) factura.getTotal());
            session.update(c);
            tx.commit();                 
        this.closeConnection();
        
    }

}
