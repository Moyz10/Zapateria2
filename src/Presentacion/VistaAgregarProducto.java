package Presentacion;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Presentacion.VistaMensaje;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class VistaAgregarProducto extends JFrame {
	private JPanel contentPane;
	private JTextField txtModelo;
	private JTextField textTalla;
	private JTextField textColor;
	private JTextField textCantidad;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAgregarProducto frame = new VistaAgregarProducto();
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
	public VistaAgregarProducto() {
		setTitle("Almacen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblAgregarProducto = new JLabel("Agregar Producto");
		lblAgregarProducto.setBounds(21, 24, 116, 16);
		contentPane.add(lblAgregarProducto);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(112, 64, 130, 26);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);
		
		textTalla = new JTextField();
		textTalla.setBounds(112, 110, 130, 26);
		contentPane.add(textTalla);
		textTalla.setColumns(10);
		
		textColor = new JTextField();
		textColor.setBounds(112, 158, 130, 26);
		contentPane.add(textColor);
		textColor.setColumns(10);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(112, 206, 130, 26);
		contentPane.add(textCantidad);
		textCantidad.setColumns(10);
		
		JButton botonAgregar = new JButton("Agregar");
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaMensaje vistMsj = new VistaMensaje();
				vistMsj.setVisible(true);
				
			}
		});
		botonAgregar.setBounds(254, 206, 117, 29);
		contentPane.add(botonAgregar);
		
		JButton bottonRegresar = new JButton("");
		bottonRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaAlmacen vistAl = new VistaAlmacen();
				vistAl.setVisible(true);
				dispose();
			}
		});
		bottonRegresar.setBounds(383, 206, 50, 50);
		contentPane.add(bottonRegresar);
		ImageIcon imgIcon = new ImageIcon(VistaLogin.class.getResource("/presentacion/return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);
		bottonRegresar.setIcon(imgIcon);
	}
}
