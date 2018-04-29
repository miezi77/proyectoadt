package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.Manager;
import model.Cliente;
import model.Factura;

import javax.swing.ListModel;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ListaClientes extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNif;
	JList<Cliente> lstClientes;
	DefaultListModel<Cliente> model;
	JButton btnBuscarCliente;
	private Manager manager = new Manager();
	JMenuItem mntmNuevaFactura;
	JMenuItem mntmVerFacturas;
	JMenuItem mntmNuevoCliente;
	JMenuItem mntmVerClientes;
	private JButton btnVer;
	private JScrollPane scrollPane;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListaClientes dialog = new ListaClientes();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListaClientes( ) {
		setBackground(SystemColor.inactiveCaptionBorder);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Listado de clientes");
		
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.inactiveCaptionBorder);
		panel_1.setPreferredSize(new Dimension(760, 40));
		contentPanel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNif = new JLabel("NIF: ");
		panel_1.add(lblNif);
		
		txtNif = new JTextField();
		txtNif.setColumns(20);
		panel_1.add(txtNif);
		
		btnBuscarCliente = new JButton("BUSCAR");
		panel_1.add(btnBuscarCliente);
		btnBuscarCliente.addActionListener(this);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.inactiveCaptionBorder);
		panel_3.setPreferredSize(new Dimension(720, 380));
		contentPanel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		/*model = new DefaultListModel<>();		
		scrollPane = new JScrollPane();
		panel_3.add(scrollPane);		
		lstClientes = new JList<>( model );*/
		
	    model = new DefaultListModel();
	    lstClientes = new JList(model);
	    JScrollPane pane = new JScrollPane(lstClientes);
	    pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    pane.setPreferredSize(new Dimension(710, 340));
	    pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		//lstClientes = new JList();//estas dos líneas originales
		panel_3.add(pane, BorderLayout.CENTER);// 
		listarClientes();
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(SystemColor.inactiveCaptionBorder);
        panel_4.setPreferredSize(new Dimension(760, 50));
        contentPanel.add(panel_4);
        panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        
        btnVer = new JButton("VER CLIENTE");
        btnVer.addActionListener(this);
        panel_4.add(btnVer);
        txtNif.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	//LISTAR CLIENTES
	private void listarClientes() {
		
		  try {
			 Collection<Cliente> clientes = new ArrayList<Cliente>();
			clientes = manager.getClientes();
			for(Cliente c:clientes) {
				model.addElement(c);
				c.toString();				
			}
				
		   } catch (Exception e) {
			String mensaje = "Ha ocurrido un error al cargar la lista de clientes";
			JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			}		
		}
	//BUSCAR CLIENTE POR NIF
	private void buscarCliente(String id) {
		try {
			if(txtNif.getText().equals("")){
				String mensaje = "Por favor, introduce el nif";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);		
				txtNif.setBorder(BorderFactory.createLineBorder(Color.RED));
			}
			else {
				Cliente cliente = new Cliente();
				cliente=manager.getClienteById(id);
				model.removeAllElements();
				if(cliente.getNif().equals(null)) {
					String mensaje = "No existe ese cliente";
					JOptionPane.showMessageDialog(this, mensaje, "NO EXISTE ESE CLIENTE", JOptionPane.ERROR_MESSAGE);
					txtNif.setBorder(BorderFactory.createLineBorder(Color.RED));
					
				}
				else {
				model.addElement(cliente);				
				cliente.toString();
				txtNif.setText("");
				txtNif.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				}
			}
			
		} catch (Exception e) {
			String mensaje = "No existe ese cliente";
			JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			}		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBuscarCliente)) {
			this.buscarCliente(txtNif.getText());
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
		//Comprobar qué ítem de la lista ha sido seleccionado
		if (e.getSource().equals(btnVer)) {
			int index = lstClientes.getSelectedIndex();
			System.out.println("Index Selected: " + index);
			//guardar el cliente al hacer click sobre el botón check de cara a poder modificarlo
			Cliente c = (Cliente) lstClientes.getSelectedValue();
			System.out.println("Value Selected: " + c);
			ClienteDetalle nuevoCliente= new ClienteDetalle(c);
			nuevoCliente.setVisible(true);
			this.dispose();				
			}


		
	}

}
