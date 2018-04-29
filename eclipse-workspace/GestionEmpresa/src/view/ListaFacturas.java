package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Manager;
import model.Cliente;
import model.Factura;

public class ListaFacturas extends JDialog implements ActionListener{
	
	private final JPanel contentPanel = new JPanel();
	JMenuItem mntmNuevaFactura;
	JMenuItem mntmVerFacturas;
	JMenuItem mntmNuevoCliente;
	JMenuItem mntmVerClientes;
	private JButton btnVer;
	private JScrollPane scrollPane;
	private JTextField txtNumero;
	JList<Factura> lstFacturas;
	DefaultListModel<Factura> model;
	JButton btnBuscarFactura;
	private Manager manager = new Manager();
	JButton btnBorrar;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListaFacturas dialog = new ListaFacturas();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public ListaFacturas() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Listado de facturas");
		
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
		
		JLabel lblNumero = new JLabel("Nº: ");
		panel_1.add(lblNumero);
		
		txtNumero = new JTextField();
		txtNumero.setColumns(20);
		panel_1.add(txtNumero);
		
		btnBuscarFactura = new JButton("BUSCAR");
		panel_1.add(btnBuscarFactura);
		btnBuscarFactura.addActionListener(this);
		
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
	    lstFacturas = new JList(model);
	    JScrollPane pane = new JScrollPane(lstFacturas);
	    pane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	    pane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    pane.setPreferredSize(new Dimension(710, 340));
	    pane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		
		//lstClientes = new JList();//estas dos líneas originales
		panel_3.add(pane, BorderLayout.CENTER);// 
		//listarFacturas();
        
        JPanel panel_4 = new JPanel();
        panel_4.setBackground(SystemColor.inactiveCaptionBorder);
        panel_4.setPreferredSize(new Dimension(760, 50));
        contentPanel.add(panel_4);
        panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        
        btnVer = new JButton("MODIFICAR");
        btnVer.addActionListener(this);
        panel_4.add(btnVer);
        
        btnBorrar = new JButton("BORRAR");
        btnBorrar.addActionListener(this);
        panel_4.add(btnBorrar);
        
        txtNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        listarFacturas();
	}
	//LISTAR FACTURAS
		private void listarFacturas() {			
			  try {
				 Collection<Factura> facturas = new ArrayList<Factura>();
				 facturas = manager.getFacturas();
				for(Factura f:facturas) {
					model.addElement(f);
					f.toString();				
				}
					
			   } catch (Exception e) {
				String mensaje = "Ha ocurrido un error al cargar la lista de facturas";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
				System.out.println(e.getMessage());
				}		
			}
		//BUSCAR FACTURA POR NIF
		private void buscarFactura(String id) {
			try {
				if(txtNumero.getText().equals("")){
					String mensaje = "Por favor, introduce el número";
					JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);		
					txtNumero.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				else {
					Factura factura = new Factura();
					factura=manager.getFacturaById(id);
					model.removeAllElements();
					if(factura.getNumero().equals(null)) {
						String mensaje = "No existe esa factura";
						JOptionPane.showMessageDialog(this, mensaje, "NO EXISTE ESA FACTURA", JOptionPane.ERROR_MESSAGE);
						txtNumero.setBorder(BorderFactory.createLineBorder(Color.RED));
						
					}
					else {
					model.addElement(factura);				
					factura.toString();
					txtNumero.setText("");
					txtNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					}
				}
				
			} catch (Exception e) {
				String mensaje = "No existe esa factura";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
				System.out.println(e.getMessage());
				}		
		}
		//BORRAR FACTURA
		private void borrarFactura(String numero) {
			try {
				manager.borrarFactura(numero);
				//model.
				
			} catch (Exception e) {
				String mensaje = "Se ha producido un error al borrar la factura";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
				System.out.println(e.getMessage());
			}
			
		}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBuscarFactura)) {
			this.buscarFactura(txtNumero.getText());
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
					int index = lstFacturas.getSelectedIndex();
					System.out.println("Index Selected: " + index);
					//guardar el cliente al hacer click sobre el botón check de cara a poder modificarlo
					Factura f = (Factura) lstFacturas.getSelectedValue();
					System.out.println("Value Selected: " + f);
					FacturaDetalle nuevaFactura= new FacturaDetalle(f);
					nuevaFactura.setVisible(true);
					this.dispose();				
		}
		//Botón borrar factura
		if (e.getSource().equals(btnBorrar)) {		
			int index = lstFacturas.getSelectedIndex();
			String selected= lstFacturas.getSelectedValue().toString();
			int pos=selected.indexOf(" ");			
			String numfact=selected.substring(0,pos);
			System.out.println(numfact);
			this.borrarFactura(numfact);
			model.removeElementAt(index);
		}
		
	}

}
