package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Manager;
import model.Cliente;

import javax.swing.JMenuBar;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class VentanaListaClientes extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtNif;
	JList<Cliente> lstClientes;
	DefaultListModel<Cliente> model;
	JButton btnBuscarCliente;
	private Manager manager = new Manager();
	JMenuItem mntmNuevaFactura;
	JMenuItem mntmVerFacturas;
	JMenuItem mntmNuevoCliente;
	JMenuItem mntmVerClientes;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaListaClientes frame = new VentanaListaClientes();
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
	public VentanaListaClientes() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		setTitle("Listado de clientes");
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
		
		JLabel lblBuscarCliente = new JLabel("Buscar cliente");
		panel.add(lblBuscarCliente);
		
		JPanel panel_1 = new JPanel();
		panel_1.setPreferredSize(new Dimension(760, 40));
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNif = new JLabel("NIF: ");
		panel_1.add(lblNif);
		
		txtNif = new JTextField();
		txtNif.setColumns(20);
		panel_1.add(txtNif);
		
		btnBuscarCliente = new JButton("BUSCAR");
		panel_1.add(btnBuscarCliente);
		btnBuscarCliente.addActionListener(this);
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(760, 20));
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblListadoClientes = new JLabel("Listado de clientes");
		panel_2.add(lblListadoClientes);
		
		JPanel panel_3 = new JPanel();
		panel_3.setPreferredSize(new Dimension(760, 150));
		contentPane.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		
		model = new DefaultListModel<>();
		lstClientes = new JList<>( model );
		
		//lstClientes = new JList();//estas dos líneas originales
		panel_3.add(lstClientes);// 
		listarClientes();
		
        button = new JButton("Check");
        button.addActionListener(this);
        panel_3.add(button);
		
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
			String mensaje = "Ha ocurrido un error";
			JOptionPane.showMessageDialog(this, mensaje, "Errooooor", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			}		
		}
	//BUSCAR CLIENTE POR NIF
	private void buscarCliente(String id) {
		try {
			Cliente cliente = new Cliente();
			cliente=manager.getClienteById(id);
			model.removeAllElements();
			if(cliente.getNif().equals(null)) {
				String mensaje = "No existe ese cliente";
				JOptionPane.showMessageDialog(this, mensaje, "NO EXISTE ESE CLIENTE", JOptionPane.ERROR_MESSAGE);
				
			}
			else {
			model.addElement(cliente);
			cliente.toString();
			txtNif.setText("");
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
		//Comprobar qué ítem de la lista ha sido seleccionado
		if (e.getActionCommand().equals("Check")) {
			int index = lstClientes.getSelectedIndex();
			System.out.println("Index Selected: " + index);
			//guardar el cliente al hacer click sobre el botón check de cara a poder modificarlo
			Cliente s = (Cliente) lstClientes.getSelectedValue();
			System.out.println("Value Selected: " + s);
			//al pinchar en check abrir la ventana de nuevo cliente con los datos del cliente seleccionado
			//SharedData data1 = new SharedData();
			//data1.setLoginName(Username_Login.getText().toString());

			//MainFrame frame = new MainFrame(data1);
			//VentanaNuevoCliente.main(s);
			//nuevoCliente.setVisible(true);
			//this.dispose();
			

				        }
		
	}


}
