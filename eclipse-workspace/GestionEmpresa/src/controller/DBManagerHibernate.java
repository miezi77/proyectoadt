package controller;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import classes.HibernateUtil;
import model.Factura;

import classes.Clientes;
import classes.Facturas;
import model.Factura;
import model.Cliente;

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
	
	//MÉTODO PARA OBTENER TODAS LAS FACTURAS
    public ArrayList<Factura> getFacturas() throws Exception{
        
        ArrayList<Factura>usu=new ArrayList<>();
         Factura aux;
         this.openConnection();
         List  <Facturas> result =  session.createQuery("FROM Facturas").list();
         Iterator<Facturas> l= result.iterator();
         
         while(l.hasNext()){
            Facturas u=l.next();
            aux=new Factura();
            aux.setNumero(u.getNumero());
            aux.setFechaEmision(u.getFecha());		
            //aux.setCliente(u.getClientes());
            aux.setConcepto(u.getConcepto());
            aux.setTotal(u.getTotal());
            aux.setEstado(u.getEstado());
            usu.add(aux);
         
         }
         
         this.closeConnection();
         return usu;
   } 
	
	
	

}
