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
import javax.swing.JList;
import javax.swing.JButton;

public class VentanaListaFacturas extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtNumeroFactura;
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
					VentanaListaFacturas frame = new VentanaListaFacturas();
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
	public VentanaListaFacturas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setTitle("Listado de facturas");
		contentPane = new JPanel();
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
		
		JLabel lblBuscarFactura = new JLabel("Buscar factura");
		panel.add(lblBuscarFactura);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(760, 40));
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNumero = new JLabel("N\u00FAmero: ");
		panel_1.add(lblNumero);
		
		txtNumeroFactura = new JTextField();
		txtNumeroFactura.setColumns(10);
		panel_1.add(txtNumeroFactura);
		
		JButton btnBuscar = new JButton("BUSCAR");
		panel_1.add(btnBuscar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(760, 20));
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblListado = new JLabel("Listado de facturas");
		panel_2.add(lblListado);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(760, 150));
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JList lstListaFacturas = new JList();
		panel_3.add(lstListaFacturas);
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
