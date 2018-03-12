package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Usuario;
import Negocio.ControlVenta;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VistaVendedor extends JFrame {

	private JButton bntVenta, bntCambio, btnCancelar;
	private JLabel nombre, correo, telefono;
	private ControlVenta control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaVendedor frame = new VistaVendedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea la Vista para el Vendedor
	public VistaVendedor() {
		// Propiedades de la Ventana
		setTitle("Vendedor");
		setBounds(100, 100, 450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		confirmarSalida();
		iniciarComponentes();
	}

	// Si Da Click en Cerrar (X) Pregunta si Desea Cerrar la Sesion de la Aplicacion
	private void confirmarSalida() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent evt) {
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Cerrar Sesión?", "Cerrar Sesión",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaLogin();
					dispose();
				}
			}
		});
	}

	// Creamos y Agregamos los Componetes de la Ventana
	private void iniciarComponentes() {
		// Creamos e Instanciamos los Componentes de la Ventana
		JPanel contentPane = new JPanel();
		JLabel titulo = new JLabel("Ventas y Cambios");
		JLabel subtitulo = new JLabel("Vendedor");
		nombre = new JLabel("Nombre");
		correo = new JLabel("Correo");
		telefono = new JLabel("Telefono");
		bntVenta = new JButton("Venta de Calzado");
		bntCambio = new JButton("Cambio de Calzado");
		btnCancelar = new JButton("Cerrar Sesión");

		// Propiedades del Panel y se Agrega a la Ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Propiedades de la Etiqueta "Relizar Venta" y se Agrega al Panel
		titulo.setFont(new Font("Lucida Grande", Font.BOLD, 26));
		titulo.setBounds(120, 20, 280, 25);
		contentPane.add(titulo);

		//
		subtitulo.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		subtitulo.setBounds(60, 60, 150, 25);
		contentPane.add(subtitulo);

		//
		nombre.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		nombre.setBounds(20, 100, 280, 25);
		contentPane.add(nombre);

		//
		correo.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		correo.setBounds(20, 140, 180, 25);
		contentPane.add(correo);

		//
		telefono.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		telefono.setBounds(20, 180, 180, 25);
		contentPane.add(telefono);

		// Propiedades del Boton "Venta de Calzado" y se Agrega al Panel
		bntVenta.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bntVenta.setBounds(250, 100, 180, 30);
		contentPane.add(bntVenta);

		// Propiedades del Boton "Cambio de Calzado" y se Agrega al Panel
		bntCambio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		bntCambio.setBounds(250, 160, 180, 30);
		contentPane.add(bntCambio);

		// Propiedades del Boton "Cerrar Sesion" y se Agrega al Panel
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(40, 220, 160, 30);
		contentPane.add(btnCancelar);

		// Acciones de los Componentes
		accionesComponentes();
	}

	// Realiza las Acciones de los Componentes
	private void accionesComponentes() {
		// Mostramos la ventana de Venta de Calzado
		bntVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.muestraVentadeCalzado();
				dispose();
			}
		});

		// Mostramos la ventana de Cambio de Calzado
		bntCambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.muestraCambioCalzado();
				dispose();
			}
		});

		// Cerramos la Ventana Actual y Regresa a la Ventana VistaLogin
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (JOptionPane.showConfirmDialog(rootPane, "¿Desea Realmente Cerrar Sesión?", "Cerrar Sesión",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.muestraVistaLogin();
					dispose();
				}
			}
		});
	}

	public void actualizarDatos(Usuario user) {
		this.nombre.setText(user.getNombre());
		this.correo.setText(user.getCorreo());
		this.telefono.setText(user.getTelefono());
	}

	// Instanciamos control a nuestra vista.
	public void setControl(ControlVenta controlvent) {
		this.control = controlvent;
	}
}
