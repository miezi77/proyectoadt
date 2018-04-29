package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.util.JDatePickerUtil;
import java.awt.GridLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTextArea;

public class VentanaNuevaFactura extends JFrame implements ActionListener{
	private JTextField txtNumero;
	private JTextField txtNif;
	private JTextField txtFecha;
	private JTextField txtHoras;
	private JTextField porcentajeIva;
	private JTextField textField;
	private JTextField textField_1;
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
					VentanaNuevaFactura frame = new VentanaNuevaFactura();
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
	public VentanaNuevaFactura() {
		setBounds(100, 100, 800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Nueva factura");
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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
		getContentPane().add(panel);
		panel.setPreferredSize(new Dimension(760, 40));
		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNmero = new JLabel("N\u00FAmero: ");
		panel.add(lblNmero);
		
		txtNumero = new JTextField();
		panel.add(txtNumero);
		txtNumero.setColumns(10);
		
		JLabel lblNifCliente = new JLabel("Nif cliente: ");
		panel.add(lblNifCliente);
		
		txtNif = new JTextField();
		panel.add(txtNif);
		txtNif.setColumns(12);
		
		JLabel lblFechaEmisinddmmyyyy = new JLabel("Fecha (dd-MM-yyyy): ");
		panel.add(lblFechaEmisinddmmyyyy);
		
		txtFecha = new JTextField();
		panel.add(txtFecha);
		txtFecha.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Pagada");
		panel.add(chckbxNewCheckBox);
		
		JPanel panel_5 = new JPanel();
		panel_5.setPreferredSize(new Dimension(760, 100));
		getContentPane().add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblConcepto = new JLabel("Concepto: ");
		panel_5.add(lblConcepto);
		
		JTextArea txtConcepto = new JTextArea();
		txtConcepto.setRows(4);
		txtConcepto.setColumns(70);
		panel_5.add(txtConcepto);
		
		JPanel panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(760, 40));
		getContentPane().add(panel_8);
		panel_8.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblBaseImponible = new JLabel("Base imponible: ");
		panel_8.add(lblBaseImponible);
		
		txtHoras = new JTextField();
		txtHoras.setColumns(10);
		panel_8.add(txtHoras);
		
		JLabel lblPorcentajeIva = new JLabel("% IVA: ");
		panel_8.add(lblPorcentajeIva);
		
		porcentajeIva = new JTextField();
		porcentajeIva.setColumns(2);
		panel_8.add(porcentajeIva);
		
		JLabel lblIva = new JLabel("IVA: ");
		panel_8.add(lblIva);
		
		textField = new JTextField();
		panel_8.add(textField);
		textField.setColumns(10);
		
		JLabel lblTotal = new JLabel("TOTAL: ");
		panel_8.add(lblTotal);
		
		textField_1 = new JTextField();
		panel_8.add(textField_1);
		textField_1.setColumns(10);
		
		JPanel panel_7 = new JPanel();
		panel_7.setPreferredSize(new Dimension(760, 20));
		getContentPane().add(panel_7);
		panel_7.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1);
		panel_1.setPreferredSize(new Dimension(760, 40));
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		JButton btnAnyadirFactura = new JButton("A\u00D1ADIR FACTURA");
		panel_1.add(btnAnyadirFactura);
		
		JButton btnModificarFactura = new JButton("MODIFICAR FACTURA");
		btnModificarFactura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_1.add(btnModificarFactura);
		
		JButton btnBorrarFactura = new JButton("BORRAR FACTURA");
		panel_1.add(btnBorrarFactura);
		

		
		

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
