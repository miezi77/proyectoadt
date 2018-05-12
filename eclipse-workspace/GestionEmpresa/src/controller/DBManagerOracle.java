package controller;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleStatement;
import com.mysql.jdbc.Statement;

import model.Cliente;


public class DBManagerOracle {
	
    private static DBManagerOracle instance = null;
    private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:XE";
    private static final String DBUSER = "SYSTEM";
    private static final String DBPASS = "root";
    private Connection dbconn;
    //private Statement stmt;
    private OracleStatement stmt;
    
	private void openConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		 String url="jdbc:oracle:thin:@localhost:1521:XE";
		 dbconn = DriverManager.getConnection(url, "SYSTEM", "root");  //(url, “nombreBD”, “contraseña”)
		 stmt=(OracleStatement) dbconn.createStatement();
		 
	}
	private void closeConnection() throws SQLException {
		stmt.close();
		dbconn.close();
	}
	
	
	
    
    
	//MÉTODO PARA OBTENER TODOS LOS CLIENTES
	/*public Collection<Cliente>  getClientes() throws Exception {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		this.openConnection();
		//String select = "select * from clientes where id = '" + id + "'";
		String select="SELECT * FROM CLIENTES";
		Statement stm = (Statement) dbconn.createStatement();
        ResultSet res = stm.executeQuery(select);

		while (res.next()) {
			clientes.add(new Cliente(res.getInt("numero"),res.getString("nif"), res.getString("nombre"), res.getString("direccion"), res.getString("telefono"), res.getString("email")));

		}	
		res.close();

		return clientes;

	}*/
	public Collection<Cliente> getClientes() throws Exception{
        int count = 0;
        this.openConnection();
        String query="SELECT * FROM CLIENTES";
        ArrayList<Cliente> clientes = new ArrayList<>();
        try {
        	
           
            ResultSet res = stmt.executeQuery(query);
            //ResultSet res = cs.executeQuery(query);
            
            while(res.next()){
                
            	clientes.add(new Cliente(res.getInt("numero"),res.getString("nif"), res.getString("nombre"), res.getString("direccion"), res.getString("telefono"), res.getString("email")));

            }
            res.close();
            this.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return clientes;
    }
    

}
