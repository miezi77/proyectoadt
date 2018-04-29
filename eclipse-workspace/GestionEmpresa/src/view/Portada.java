package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import org.hibernate.cfg.Configuration;

import classes.HibernateUtil;



public class Portada extends JFrame implements ActionListener{

	private JFrame frame;
	private JButton btnMySQL;
	private JButton btnOracle;
	private JButton btnMongo;
	private JButton btnHibernate;
	public static int opcion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Portada window = new Portada();
					window.frame.setVisible(true);					
					//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
					 //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Portada() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//opcion=0;
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Gestión de empresa");
		frame.setBounds(100, 100, 641, 434);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2,2));
		
		btnMySQL = new JButton("");
		btnMySQL.setBackground(SystemColor.inactiveCaptionBorder);
		frame.getContentPane().add(btnMySQL);		
		btnMySQL.setIcon(new ImageIcon(Portada.class.getResource("/view/mysql.png")));
		btnMySQL.addActionListener(this);
		
		btnOracle = new JButton("");
		btnOracle.setBackground(SystemColor.inactiveCaptionBorder);
		frame.getContentPane().add(btnOracle);
		btnOracle.setIcon(new ImageIcon(Portada.class.getResource("/view/oracle.png")));
		btnOracle.addActionListener(this);
		
		
		btnMongo = new JButton("");
		btnMongo.setBackground(SystemColor.inactiveCaptionBorder);
		frame.getContentPane().add(btnMongo);
		btnMongo.setIcon(new ImageIcon(Portada.class.getResource("/view/mongo.png")));
		btnMongo.addActionListener(this);
		
		
		btnHibernate = new JButton("");
		btnHibernate.setBackground(SystemColor.inactiveCaptionBorder);
		frame.getContentPane().add(btnHibernate);
		btnHibernate.setIcon(new ImageIcon(Portada.class.getResource("/view/hibernate.png")));
		btnHibernate.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(btnMySQL)) {
			opcion=1;
			ListaFacturas frame = new ListaFacturas();
			frame.setVisible(true);
			this.dispose();
			
		}
		else if(e.getSource().equals(btnMongo)){
			opcion=2;
			ListaFacturas frame = new ListaFacturas();
			frame.setVisible(true);
			this.dispose();
			
		}
		else if(e.getSource().equals(btnHibernate)){
			opcion=4;
			ListaFacturas frame = new ListaFacturas();
			frame.setVisible(true);
			this.dispose();
			
		}
	}

}
