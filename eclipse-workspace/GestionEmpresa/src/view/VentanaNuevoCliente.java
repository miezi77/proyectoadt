package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;

public class VentanaNuevoCliente extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtNif;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	JMenuItem mntmNuevaFactura;
	JMenuItem mntmVerFacturas;
	JMenuItem mntmNuevoCliente;
	JMenuItem mntmVerClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaNuevoCliente frame = new VentanaNuevoCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaNuevoCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		setTitle("Nuevo cliente");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JMenuBar menuBar = new JMenuBar();
		getContentPane().add(menuBar);
		menuBar.setSize(800,50);
		//Establecer los ítems de menú
		mntmNuevaFactura = new JMenuItem("NUEVA FACTURA");
		menuBar.add(mntmNuevaFactura);	
		mntmVerFacturas = new JMenuItem("LISTADO FACTURAS");
		menuBar.add(mntmVerFacturas);
		mntmNuevoCliente = new JMenuItem("NUEVO CLIENTE");
		menuBar.add(mntmNuevoCliente);
		mntmVerClientes = new JMenuItem("LISTADO CLIENTES");
		menuBar.add(mntmVerClientes);
		
		mntmNuevaFactura.addActionListener(this);
		mntmVerFacturas.addActionListener(this);
		mntmNuevoCliente.addActionListener(this);
		mntmVerClientes.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(760, 20));
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblDatosCliente = new JLabel("Datos cliente");
		panel.add(lblDatosCliente);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(760, 40));
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNif = new JLabel("NIF: ");
		panel_1.add(lblNif);
		
		txtNif = new JTextField();
		txtNif.setColumns(10);
		panel_1.add(txtNif);
		
		JLabel lblEmpresa = new JLabel("Empresa/Nombre y apellidos: ");
		panel_1.add(lblEmpresa);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(40);
		panel_1.add(txtNombre);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(760, 40));
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblDireccion = new JLabel("Dirección: ");
		panel_2.add(lblDireccion);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(67);
		panel_2.add(txtDireccion);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(760, 40));
		contentPane.add(panel_3);
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
		txtEmail.setColumns(41);
		
		JPanel panel_4 = new JPanel();
		panel_4.setPreferredSize(new Dimension(760, 40));
		contentPane.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnAadirCliente = new JButton("A\u00D1ADIR CLIENTE");
		panel_4.add(btnAadirCliente);
		
		JButton btnModificarCliente = new JButton("MODIFICAR CLIENTE");
		panel_4.add(btnModificarCliente);
		
		JButton btnBorrarCliente = new JButton("BORRAR CLIENTE");
		panel_4.add(btnBorrarCliente);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(mntmNuevaFactura)) {
			//VentanaNuevaFactura facturas = new VentanaNuevaFactura();
			//probar con la ventana de clientes
			VentanaNuevaFactura factura = new VentanaNuevaFactura();
			factura.setVisible(true);
			this.dispose();			
		}
		if(e.getSource().equals(mntmVerFacturas)) {
			VentanaListaFacturas listaFacturas= new VentanaListaFacturas();
			listaFacturas.setVisible(true);
			this.dispose();			
		}
		if(e.getSource().equals(mntmNuevoCliente)) {
			VentanaNuevoCliente nuevoCliente= new VentanaNuevoCliente();
			nuevoCliente.setVisible(true);
			this.dispose();			
		}
		if(e.getSource().equals(mntmVerClientes)) {
			VentanaListaClientes listaClientes= new VentanaListaClientes();
			listaClientes.setVisible(true);
			this.dispose();			
		}
		
	}

}
