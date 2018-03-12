package Presentacion;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Presentacion.VistaAgregarProducto;
import Presentacion.VistaVendedor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VistaAlmacen extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAlmacen frame = new VistaAlmacen();
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
	public VistaAlmacen() {
		setTitle("Agregar Producto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton botonAgregar = new JButton("Agregar Producto");
		botonAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaAgregarProducto vistAgrProd = new VistaAgregarProducto();
				vistAgrProd.setVisible(true);
				dispose();
			}
		});
		botonAgregar.setBounds(162, 60, 140, 29);
		contentPane.add(botonAgregar);
		
		JButton botonConsultar = new JButton("Consultar Producto");
		botonConsultar.setBounds(158, 119, 153, 29);
		contentPane.add(botonConsultar);
		
		JButton botonEliminar = new JButton("Eliminar Producto");
		botonEliminar.setBounds(162, 180, 140, 29);
		contentPane.add(botonEliminar);
		
		JButton bottonRegresar = new JButton("");
		bottonRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VistaVendedor vistVend = new VistaVendedor();
				vistVend.setVisible(true);
				dispose();
			}
		});
		bottonRegresar.setBounds(366, 206, 50, 50);
		contentPane.add(bottonRegresar);
		ImageIcon imgIcon = new ImageIcon(VistaLogin.class.getResource("/presentacion/return.png"));
		Image user = imgIcon.getImage();
		Image userScaled = user.getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		imgIcon = new ImageIcon(userScaled);
		bottonRegresar.setIcon(imgIcon);
	}

}
