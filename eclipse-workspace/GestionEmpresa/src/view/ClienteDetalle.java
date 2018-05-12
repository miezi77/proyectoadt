package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Manager;
import model.Cliente;
import model.Factura;

import java.awt.SystemColor;

public class ClienteDetalle extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private Manager manager = new Manager();
	JMenuItem mntmNuevaFactura;
	JMenuItem mntmVerFacturas;
	JMenuItem mntmNuevoCliente;
	JMenuItem mntmVerClientes;
	private JTextField txtNif;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	JButton btnAadirCliente;	
	private JButton btnModificarCliente;	
	private int id;
	private JTextField txtNumero;
	String nifOriginal;
	int num=0;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			ClienteDetalle dialog = new ClienteDetalle(cliente);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClienteDetalle(Cliente cliente) {
		
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Detalles de cliente");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setMargin(new Insets(10, 10, 10, 10));
		menuBar.setPreferredSize(new Dimension(800, 35));
		menuBar.setAlignmentY(Component.CENTER_ALIGNMENT);
		getContentPane().add(menuBar, BorderLayout.NORTH);
		menuBar.setBackground(Color.BLACK);
		menuBar.setSize(800,50);
		//Establecer los ítems de menú

		mntmVerFacturas = new JMenuItem("LISTADO FACTURAS");
		mntmVerFacturas.setIcon(new ImageIcon(ListaClientes.class.getResource("/view/lista_facturas.png")));
		mntmVerFacturas.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmVerFacturas.setForeground(Color.WHITE);
		mntmVerFacturas.setBackground(Color.BLACK);
		mntmVerFacturas.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmVerFacturas);
		mntmNuevaFactura = new JMenuItem("NUEVA FACTURA");
		mntmNuevaFactura.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNuevaFactura.setIcon(new ImageIcon(ListaClientes.class.getResource("/view/nueva_factura.png")));
		mntmNuevaFactura.setForeground(Color.WHITE);
		mntmNuevaFactura.setBackground(Color.BLACK);
		mntmNuevaFactura.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmNuevaFactura);	
		mntmNuevoCliente = new JMenuItem("NUEVO CLIENTE");
		mntmNuevoCliente.setIcon(new ImageIcon(ListaClientes.class.getResource("/view/nuevo_cliente.png")));
		mntmNuevoCliente.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmNuevoCliente.setForeground(Color.WHITE);
		mntmNuevoCliente.setBackground(Color.BLACK);
		mntmNuevoCliente.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmNuevoCliente);
		mntmVerClientes = new JMenuItem("LISTADO CLIENTES");
		mntmVerClientes.setIcon(new ImageIcon(ListaClientes.class.getResource("/view/lista_clientes.png")));
		mntmVerClientes.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		mntmVerClientes.setForeground(Color.WHITE);
		mntmVerClientes.setBackground(Color.BLACK);
		mntmVerClientes.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(mntmVerClientes);
		
		mntmNuevaFactura.addActionListener(this);
		mntmVerFacturas.addActionListener(this);
		mntmNuevoCliente.addActionListener(this);
		mntmVerClientes.addActionListener(this);
		contentPanel.setBackground(SystemColor.inactiveCaptionBorder);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setPreferredSize(new Dimension(760, 20));
		contentPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblDatosCliente = new JLabel("Datos cliente");
		panel.add(lblDatosCliente);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setPreferredSize(new Dimension(760, 50));
		contentPanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblN = new JLabel("Nº: ");
		panel_1.add(lblN);
		
		txtNumero = new JTextField();
		panel_1.add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblNif = new JLabel("NIF: ");
		panel_1.add(lblNif);
		
		txtNif = new JTextField();
		txtNif.setColumns(10);
		panel_1.add(txtNif);
		
		
		JLabel lblEmpresa = new JLabel("Empresa: ");
		panel_1.add(lblEmpresa);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(28);
		panel_1.add(txtNombre);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setPreferredSize(new Dimension(760, 50));
		contentPanel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblDireccion = new JLabel("Dirección: ");
		panel_2.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(55);
		panel_2.add(txtDireccion);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaptionBorder);
		panel_3.setPreferredSize(new Dimension(760, 50));
		contentPanel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono: ");
		panel_3.add(lblTelefono);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(20);
		panel_3.add(txtTelefono);
		
		JLabel lblEmail = new JLabel("Email:");
		panel_3.add(lblEmail);
		
		txtEmail = new JTextField();
		panel_3.add(txtEmail);
		txtEmail.setColumns(32);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.inactiveCaptionBorder);
		panel_4.setPreferredSize(new Dimension(760, 50));
		contentPanel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnAadirCliente = new JButton("A\u00D1ADIR CLIENTE");
		panel_4.add(btnAadirCliente);		
		btnModificarCliente = new JButton("MODIFICAR CLIENTE");
		panel_4.add(btnModificarCliente);
		
		btnAadirCliente.addActionListener(this);
		btnModificarCliente.addActionListener(this);
		
		txtNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtNombre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtDireccion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtTelefono.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtNif.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		if(cliente!=null) {			
			txtNumero.setText(String.valueOf(cliente.getNumero()));
			txtNombre.setText(cliente.getNombre());
			txtDireccion.setText(cliente.getDireccion());
			txtTelefono.setText(cliente.getTelefono());
			txtEmail.setText(cliente.getEmail());
			txtNif.setText(cliente.getNif());
			id=cliente.getNumero();
			nifOriginal=cliente.getNif();
			btnAadirCliente.setEnabled(false);
			
		}
		else {
			num=getLastId();
			txtNumero.setText(String.valueOf(num+1));
			btnModificarCliente.setEnabled(false);
			//btnBorrarCliente.setEnabled(false);
		}
		txtNumero.setEditable(false);	
		
		
	}
	//MODIFICAR DATOS CLIENTE
	public void modificarCliente() {
		Boolean existe=false;		
		Cliente c= new Cliente(Integer.parseInt(txtNumero.getText()),txtNif.getText(),txtNombre.getText(),txtDireccion.getText(),txtTelefono.getText(),txtEmail.getText());
		try {			
			//Comprobar que no hay campos de texto sin informar
			if(txtNif.getText().equals("") | txtNombre.getText().equals("") | txtDireccion.getText().equals("") | txtTelefono.getText().equals("") | txtEmail.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Hay campos vacíos");
			}
			else {
				//Comprobar si se ha modificado el nif
				if(nifOriginal.equals(txtNif.getText())) {
					//si no se ha modificado, guardar 
					manager.modificarCliente(c, id);
					borrarCampos();
					resetearContornos();
					//deshabilitar los botones para modificar y borrar y habilitar el botón de añadir
					num=getLastId();
					txtNumero.setText(String.valueOf(num+1));
					btnAadirCliente.setEnabled(true);
					//btnBorrarCliente.setEnabled(false);
					btnModificarCliente.setEnabled(false);
				}
				//Comprobar que el nuevo nif introducido no existe en la bbdd
				else {
					existe=manager.nifExiste(txtNif.getText());					
					if(existe) {
						JOptionPane.showMessageDialog(this, "Ya existe un cliente con ese NIF", "ERROR", JOptionPane.ERROR_MESSAGE);
						txtNif.setText(nifOriginal);
						txtNif.setBorder(BorderFactory.createLineBorder(Color.RED));			
					}
					else {							
						manager.modificarCliente(c, id);
						borrarCampos();	
						resetearContornos();
						//deshabilitar los botones para modificar y borrar y habilitar el botón de añadir
						num=getLastId();
						txtNumero.setText(String.valueOf(num+1));
						btnAadirCliente.setEnabled(true);
						//btnBorrarCliente.setEnabled(false);
						btnModificarCliente.setEnabled(false);
					}
				}											
			}			
		} catch(Exception e) {
			 JOptionPane.showMessageDialog(this, "Ha ocurrido un error", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}	
		
	}
	//AÑADIR CLIENTE
	
	public void anyadirCliente() {
		Boolean existe=false;
		Cliente c= new Cliente(Integer.parseInt(txtNumero.getText()),txtNif.getText(),txtNombre.getText(),txtDireccion.getText(),txtTelefono.getText(),txtEmail.getText());
		try {			
			//Comprobar que no hay campos de texto sin informar
			if(txtNif.getText().equals("") | txtNombre.getText().equals("") | txtDireccion.getText().equals("") | txtTelefono.getText().equals("") | txtEmail.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Hay campos vacíos");
			}
			else {
				//Comprobar que el cliente no existe
				existe=manager.nifExiste(txtNif.getText());
				if(existe ) {
					JOptionPane.showMessageDialog(this, "Ya existe un cliente con ese NIF", "ERROR", JOptionPane.ERROR_MESSAGE);
					txtNif.setText("");
					txtNif.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				else {
					manager.anyadirCliente(c);
					borrarCampos();
					resetearContornos();
					num=getLastId();
					txtNumero.setText(String.valueOf(num+1));
					//btnBorrarCliente.setEnabled(false);
					btnModificarCliente.setEnabled(false);
					}				
			}			
		} catch(Exception e) {
			 JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Botón modificar cliente
		if(e.getSource().equals(btnModificarCliente)) {
			this.modificarCliente();
		}
		//Botón añadir cliente
		if(e.getSource().equals(btnAadirCliente)){
			this.anyadirCliente();
		}
		//Botones de menú
		if(e.getSource().equals(mntmNuevaFactura)) {
			//VentanaNuevaFactura facturas = new VentanaNuevaFactura();
			//probar con la ventana de clientes
			Factura f=null;
			FacturaDetalle factura = new FacturaDetalle(f);
			factura.setVisible(true);
			this.dispose();			
		}
		if(e.getSource().equals(mntmVerFacturas)) {
			ListaFacturas listaFacturas= new ListaFacturas();
			listaFacturas.setVisible(true);
			this.dispose();			
		}
		if(e.getSource().equals(mntmNuevoCliente)) {
			Cliente c=null;
			ClienteDetalle nuevoCliente= new ClienteDetalle(c);
			nuevoCliente.setVisible(true);
			this.dispose();			
		}
		if(e.getSource().equals(mntmVerClientes)) {
			ListaClientes listaClientes= new ListaClientes();
			listaClientes.setVisible(true);
			this.dispose();			
		}
		
	}
	//MÉTODO PARA RECUPERAR EL ÚLTIMO NÚMERO DE CLIENTE
	public int getLastId( ) {
		
		try {
			num=manager.getLastId();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	//MÉTODO PARA VACIAR CAMPOS DE TEXTO
	public void borrarCampos() {
		txtNumero.setText("");
		txtNombre.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtEmail.setText("");
		txtNif.setText("");
	}
	//MÉTODO PARA RESETEAR EL CONTORNO DE LOS CAMPOS DE TEXTO A SU COLOR ORIGINAL
	public void resetearContornos() {
		txtNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtNombre.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtDireccion.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtTelefono.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtEmail.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtNif.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}

}
