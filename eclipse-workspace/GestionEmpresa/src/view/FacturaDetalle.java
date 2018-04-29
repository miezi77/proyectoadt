package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Manager;
import model.Cliente;
import model.Factura;
import javax.swing.JComboBox;

public class FacturaDetalle extends JDialog implements ActionListener{

	private final JPanel contentPanel = new JPanel();
	private Manager manager = new Manager();
	JMenuItem mntmNuevaFactura;
	JMenuItem mntmVerFacturas;
	JMenuItem mntmNuevoCliente;
	JMenuItem mntmVerClientes;
	private JTextField txtNumero;
	private JTextField txtNif;
	private JTextField txtFecha;
	private JTextField txtHoras;
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtConcepto;
	JButton btnAadirFactura;	
	private JButton btnModificarFactura;	
	private int id;
	JTextField txtDia;
	JTextField txtMes;
	JTextField txtAnyo;
	
	String nifOriginal;
	int num=0;
	private JTextField txtTotal;
	private JTextField txtEstado;
	JComboBox cbCliente;
	private JTextField textField_2;
	private JTextField textField_3;
	String numFactura;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			FacturaDetalle dialog = new FacturaDetalle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public FacturaDetalle(Factura factura) {
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Detalles de factura");
		
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
		
		JLabel lblDatosCliente = new JLabel("Datos factura");
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
		
		JLabel lblFecha = new JLabel("Fecha (dd-MM-aaaa): ");
		panel_1.add(lblFecha);
		
		txtDia = new JTextField();
		txtDia.setColumns(2);
		panel_1.add(txtDia);			
		
		JLabel label = new JLabel("-");
		panel_1.add(label);
		
		txtMes = new JTextField();
		panel_1.add(txtMes);
		txtMes.setColumns(2);
		
		JLabel label_1 = new JLabel("-");
		panel_1.add(label_1);
		
		txtAnyo = new JTextField();
		panel_1.add(txtAnyo);
		txtAnyo.setColumns(4);
		
		
		JLabel lblCliente = new JLabel("Cliente: ");
		panel_1.add(lblCliente);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaptionBorder);
		panel_2.setPreferredSize(new Dimension(760, 50));
		contentPanel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblConcepto = new JLabel("Concepto: ");
		panel_2.add(lblConcepto);
		
		txtConcepto = new JTextField();
		txtConcepto.setColumns(55);
		panel_2.add(txtConcepto);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(SystemColor.inactiveCaptionBorder);
		panel_5.setPreferredSize(new Dimension(760, 40));
		contentPanel.add(panel_5);
		panel_5.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel label_3 = new JLabel("TOTAL: ");
		panel_5.add(label_3);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		panel_5.add(txtTotal);
		
		JLabel lblEstado = new JLabel("Estado de la factura: ");
		panel_5.add(lblEstado);
		
		txtEstado = new JTextField();
		panel_5.add(txtEstado);
		txtEstado.setColumns(25);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(SystemColor.inactiveCaptionBorder);
		panel_4.setPreferredSize(new Dimension(760, 50));
		contentPanel.add(panel_4);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
		
		btnAadirFactura = new JButton("A\u00D1ADIR FACTURA");
		panel_4.add(btnAadirFactura);		
		btnModificarFactura = new JButton("MODIFICAR FACTURA");
		panel_4.add(btnModificarFactura);
		
		btnAadirFactura.addActionListener(this);
		btnModificarFactura.addActionListener(this);
		
		txtNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtDia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtMes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtAnyo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtConcepto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtTotal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtEstado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		cbCliente = new JComboBox();
		cbCliente.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		listarClientes();
		
		if(factura!=null) {	
			int index=0;
			txtNumero.setText(factura.getNumero());
			numFactura=factura.getNumero();
			//extraer el día, el mes y el año
			String fecha=factura.getFechaEmision();
			int pos1=fecha.indexOf("-");
			int pos2=fecha.lastIndexOf("-");
			String anyo=fecha.substring(0,pos1);
			String mes=fecha.substring(pos1+1,pos2);
			String dia=fecha.substring(pos2+1);			
			txtDia.setText(dia);
			txtMes.setText(mes);
			txtAnyo.setText(anyo);
			//extraer el id del cliente
			try {
				index=manager.getIdCliente(factura.getNumero());
				System.out.println(index);
			} catch (Exception e) {
				String mensaje = "Ha ocurrido un error al cargar el cliente";
				JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
				System.out.println(e.getMessage());
			}	
			cbCliente.setSelectedIndex(index-1);			
			txtConcepto.setText(factura.getConcepto());
			txtTotal.setText(String.valueOf(factura.getTotal()));
			txtEstado.setText(factura.getEstado());		
			//Deshabilitar el botón de añadir factura
			btnAadirFactura.setEnabled(false);
			
		}
		else {
			//Deshabilitar el botón de modificar factura
			btnModificarFactura.setEnabled(false);
			
		}

		cbCliente.setMaximumRowCount(50);
		panel_1.add(cbCliente);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Botón añadir factura
				if(e.getSource().equals(btnAadirFactura)){
					this.anyadirFactura();
				}
		//Botón modificar factura
				if(e.getSource().equals(btnModificarFactura)) {
					this.modificarFactura();
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
	//CARGAR TODOS LOS CLIENTES EN EL COMBOBOX
	private void listarClientes() {
		
		  try {
			 Collection<Cliente> clientes = new ArrayList<Cliente>();
			clientes = manager.getClientes();
			for(Cliente c:clientes) {
				cbCliente.addItem(c.getNumero()+"- "+c.getNombre());
				c.toString();				
			}
				
		   } catch (Exception e) {
			String mensaje = "Ha ocurrido un error al cargar la lista de clientes";
			JOptionPane.showMessageDialog(this, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
			}		
		}
	//AÑADIR FACTURA
	public void anyadirFactura() {
		String fecha=txtAnyo.getText()+"-"+txtMes.getText()+"-"+txtDia.getText();
		Boolean existe=false; 		
		try {		
			Factura f = new Factura(txtNumero.getText(),fecha,String.valueOf(cbCliente.getSelectedIndex()+1),txtConcepto.getText(),Float.parseFloat(txtTotal.getText()),txtEstado.getText());
			System.out.println(cbCliente.getSelectedIndex()+1);			
			//Comprobar que no hay campos de texto sin informar
			if(txtNumero.getText().equals("") | txtDia.getText().equals("") | txtMes.getText().equals("")|  txtAnyo.getText().equals("") | txtConcepto.getText().equals("") | txtTotal.getText().equals("") | txtEstado.getText().equals("") | cbCliente.getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(this, "Hay campos vacíos");
			}
			else {
				//Comprobar que se han ingresado números en los campos de fecha y que son correctos (días>31,30, mes>12, año>2018)
				int dia=Integer.parseInt(txtDia.getText());
				int mes=Integer.parseInt(txtMes.getText());
				int anyo=Integer.parseInt(txtAnyo.getText());
				int year = Year.now().getValue();
				if(anyo>year) {
					JOptionPane.showMessageDialog(this, "El año no puede ser superior al actual", "ERROR", JOptionPane.ERROR_MESSAGE);
					txtAnyo.setText("");					
					txtAnyo.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				else if(mes>12) {
					JOptionPane.showMessageDialog(this, "El número de mes no puede ser superior a 12", "ERROR", JOptionPane.ERROR_MESSAGE);
					txtMes.setText("");					
					txtMes.setBorder(BorderFactory.createLineBorder(Color.RED));
				} //ARREGLAR ESTO
				else if(mes==2 | mes==4 | mes==6 | mes==9 | mes ==11 ) {
					//comprobar que el mes de febrero no tiene más días que 28 y si el año es bisiesto más que 29. COMPLETAR
					if(mes==2 & dia>29 & anyo%400==0) {
						JOptionPane.showMessageDialog(this, "Febrero no puede tener más de 29 días", "ERROR", JOptionPane.ERROR_MESSAGE);
						txtDia.setText("");	
						txtDia.setBorder(BorderFactory.createLineBorder(Color.RED));
						
					}else { 
						if(mes==2 & dia>28) {
							JOptionPane.showMessageDialog(this, "Febrero no puede tener más de 28 días", "ERROR", JOptionPane.ERROR_MESSAGE);
							txtDia.setText("");	
							txtDia.setBorder(BorderFactory.createLineBorder(Color.RED));
							}
						else if(dia>30){
							JOptionPane.showMessageDialog(this, "El mes no puede tener más de 30 días", "ERROR", JOptionPane.ERROR_MESSAGE);
							txtDia.setText("");								
							txtDia.setBorder(BorderFactory.createLineBorder(Color.RED));
						}
					}
				}
				else if (dia>31){
					JOptionPane.showMessageDialog(this, "El mes no tiene más de 31 días", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
				
				//Comprobar que la factura no existe
				existe=manager.numeroExiste(txtNumero.getText());				
				if(existe ) {
					JOptionPane.showMessageDialog(this, "Ya existe una factura con ese número", "ERROR", JOptionPane.ERROR_MESSAGE);
					txtNumero.setText("");
					txtNumero.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				else {
					manager.anyadirFactura(f);
					borrarCampos();		
					resetearContornos();
					btnModificarFactura.setEnabled(false);
					}				
			}			
			//al añadir factura nueva se muestra un mensaje de error, a pesar de que inserta los valores en la bbdd, REVISARLO
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "No has introducido números", "ERROR", JOptionPane.ERROR_MESSAGE);			
			System.out.println(e.getMessage());
			
		}catch(Exception e) {
			 JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}
		
	}
	//MODIFICAR DATOS FACTURA
	public void modificarFactura() {
		Boolean existe=false;
		String fecha=txtAnyo.getText()+"-"+txtMes.getText()+"-"+txtDia.getText();
		try {	
			Factura f = new Factura(txtNumero.getText(),fecha,String.valueOf(cbCliente.getSelectedIndex()+1),txtConcepto.getText(),Float.parseFloat(txtTotal.getText()),txtEstado.getText());		
			
			//Comprobar que no hay campos de texto sin informar
			if(txtNumero.getText().equals("") | txtDia.getText().equals("") | txtMes.getText().equals("")|  txtAnyo.getText().equals("") | txtConcepto.getText().equals("") | txtTotal.getText().equals("") | txtEstado.getText().equals("") | cbCliente.getSelectedIndex()==-1) {
				JOptionPane.showMessageDialog(this, "Hay campos vacíos");
			}
			else {
				//Comprobar que se han ingresado números en los campos de fecha y que son correctos (días>31,30, mes>12, año>2018)
				int dia=Integer.parseInt(txtDia.getText());
				int mes=Integer.parseInt(txtMes.getText());
				int anyo=Integer.parseInt(txtAnyo.getText());
				int year = Year.now().getValue();
				if(anyo>year) {
					JOptionPane.showMessageDialog(this, "El año no puede ser superior al actual", "ERROR", JOptionPane.ERROR_MESSAGE);
					txtAnyo.setText("");					
					txtAnyo.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				else if(mes>12) {
					JOptionPane.showMessageDialog(this, "El número de mes no puede ser superior a 12", "ERROR", JOptionPane.ERROR_MESSAGE);
					txtMes.setText("");					
					txtMes.setBorder(BorderFactory.createLineBorder(Color.RED));
				} //ARREGLAR ESTO
				else if(mes==2 | mes==4 | mes==6 | mes==9 | mes ==11 ) {
					//comprobar que el mes de febrero no tiene más días que 28 y si el año es bisiesto más que 29. COMPLETAR
					if(mes==2 & dia>29 & anyo%400==0) {
						JOptionPane.showMessageDialog(this, "Febrero no puede tener más de 29 días", "ERROR", JOptionPane.ERROR_MESSAGE);
						txtDia.setText("");			
						txtDia.setBorder(BorderFactory.createLineBorder(Color.RED));
						
					}else { 
						if(mes==2 & dia>28) {
							JOptionPane.showMessageDialog(this, "Febrero no puede tener más de 28 días", "ERROR", JOptionPane.ERROR_MESSAGE);
							txtDia.setText("");								
							txtDia.setBorder(BorderFactory.createLineBorder(Color.RED));
							}
						else if(dia>30){
							JOptionPane.showMessageDialog(this, "El mes no puede tener más de 30 días", "ERROR", JOptionPane.ERROR_MESSAGE);
							txtDia.setText("");								
							txtDia.setBorder(BorderFactory.createLineBorder(Color.RED));
						}
					}
				}
				else if (dia>31){
					JOptionPane.showMessageDialog(this, "El mes no tiene más de 31 días", "ERROR", JOptionPane.ERROR_MESSAGE);
				}else {
				//Comprobar si se ha modificado el número
				if(numFactura.equals(txtNumero.getText())) {
					//si no se ha modificado, guardar 
					manager.modificarFactura(f, numFactura);
					borrarCampos();
					resetearContornos();
				}
				//Comprobar que el nuevo número introducido no existe en la bbdd
				else {
					existe=manager.numeroExiste(txtNumero.getText());					
					if(existe) {						
						JOptionPane.showMessageDialog(this, "Ya existe una factura con ese número", "ERROR", JOptionPane.ERROR_MESSAGE);
						txtNumero.setText(numFactura);							
						txtNumero.setBorder(BorderFactory.createLineBorder(Color.RED));
						
					}
					else {							
						manager.modificarFactura(f, numFactura);
						borrarCampos();	
						resetearContornos();
						//deshabilitar los botones para modificar y borrar y habilitar el botón de añadir
						//num=getLastId();
						//txtNumero.setText(String.valueOf(num+1));
						btnAadirFactura.setEnabled(true);						
						btnModificarFactura.setEnabled(false);
					}
				}											
			}	}		
		} catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "No has introducido números", "ERROR", JOptionPane.ERROR_MESSAGE);			
			System.out.println(e.getMessage());
			
		} catch(Exception e) {
			 JOptionPane.showMessageDialog(this, "Ha ocurrido un error", "ERROR", JOptionPane.ERROR_MESSAGE);
			System.out.println(e.getMessage());
		}	
		
	}
	//MÉTODO PARA VACIAR CAMPOS DE TEXTO
	public void borrarCampos() {
		txtNumero.setText("");		
		txtDia.setText("");
		txtMes.setText("");
		txtAnyo.setText("");
		cbCliente.setSelectedIndex(-1);
		txtConcepto.setText("");
		txtTotal.setText("");
		txtEstado.setText("");

	}
	//MÉTODO PARA RESETEAR EL CONTORNO DE LOS CAMPOS DE TEXTO A SU COLOR ORIGINAL
	public void resetearContornos() {
		txtNumero.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtDia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtMes.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtAnyo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtConcepto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtTotal.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		txtEstado.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}

}
