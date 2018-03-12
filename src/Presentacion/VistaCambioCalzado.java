package Presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import Negocio.ControlVenta;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VistaCambioCalzado extends JFrame {

	private JTextField tffolioventa;
	private DefaultTableModel modeloventa, modelocambio;
	private JButton btnbuscar, btncancelar, btncambio;
	private JTable tablacambio;
	private ControlVenta control;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaCambioCalzado frame = new VistaCambioCalzado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea Ventana Venta de Cambio de Calzado
	public VistaCambioCalzado() {
		setTitle("Cambio de calzado");
		setBounds(100, 100, 660, 440);
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
				if (JOptionPane.showConfirmDialog(rootPane, "¿Realmente Desea Cancelar la Venta?", "¿Cancelar?",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					control.limpiarDatos("Cambio");
					// control.muestraVistaVendedor();
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		JPanel contentPane = new JPanel();
		JLabel lblFolioDeVenta = new JLabel("Folio de Venta");
		JLabel lblcambios = new JLabel("Opciones de Cambio");
		tffolioventa = new JTextField();
		btnbuscar = new JButton("Buscar");
		btncambio = new JButton("Cambio");
		btncancelar = new JButton("Cancelar");
		String[] columnaventa = { "Modelo", "Color", "Talla", "Total" };
		String[] columnacambio = { "Modelo", "Color", "Talla", "Total", "Disponibles", "Selección" };
		Object[][] datosventa = {};
		Object[][] datoscambio = {};
		modeloventa = new DefaultTableModel(datosventa, columnaventa);
		modelocambio = new DefaultTableModel(datoscambio, columnacambio) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class, Double.class, Double.class, Integer.class,
					Boolean.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		JTable tablaventa = new JTable(modeloventa);
		tablacambio = new JTable(modelocambio);
		JScrollPane scrollventa = new JScrollPane();
		JScrollPane scrollcambio = new JScrollPane();

		// Propiedades del Panel y se Agrega a la Ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Propiedades de la Etiqueta "Folio de Venta" y se Agrega al Panel
		lblFolioDeVenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblFolioDeVenta.setBounds(20, 20, 140, 30);
		contentPane.add(lblFolioDeVenta);

		// Propiedades de la Etiqueta "Opciones de Cambio" y se Agrega al Panel
		lblcambios.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblcambios.setBounds(20, 110, 160, 30);
		contentPane.add(lblcambios);

		// Propiedades del textfield "Folio Venta" y se Agrega al Panel
		tffolioventa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tffolioventa.setBounds(140, 20, 90, 24);
		contentPane.add(tffolioventa);
		tffolioventa.setColumns(10);

		// Propiedades del Boton Buscar y se Agrega al Panel
		btnbuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnbuscar.setBounds(260, 20, 100, 26);
		contentPane.add(btnbuscar);

		// Propiedades del Boton Continuar y se Agrega al Panel
		btncambio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncambio.setBounds(440, 360, 100, 26);
		contentPane.add(btncambio);

		// Propiedades del Boton Cancelar y se Agrega al Panel
		btncancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncancelar.setBounds(120, 360, 100, 26);
		contentPane.add(btncancelar);

		// Propiedades del scrollventa, se Agrega al Panel y se Agrega la Tabla al
		// scrollventa
		scrollventa.setBounds(20, 60, 620, 40);
		contentPane.add(scrollventa);
		scrollventa.setViewportView(tablaventa);

		// Propiedades del scrollcambio, se Agrega al Panel y se Agrega la Tabla al
		// scrollcambio
		scrollcambio.setBounds(20, 140, 620, 200);
		contentPane.add(scrollcambio);
		scrollcambio.setViewportView(tablacambio);

		// Acciones de los Componentes
		accionesComponentes();
	}

	private void accionesComponentes() {
		tablacambio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = tablacambio.getSelectedRow();
				for (int j = 0; j < modelocambio.getRowCount(); j++)
					if (j != i)
						modelocambio.setValueAt(false, j, 5);
			}
		});

		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String folio = tffolioventa.getText();
				if (control.esNumero(folio)) {
					if (modeloventa.getRowCount() != 0)
						control.limpiarDatos("Cambio");
					control.buscaTicket(Integer.valueOf(folio));
					tffolioventa.setText(folio);
				} else
					JOptionPane.showMessageDialog(null, "Ingrese folio de venta");
			}
		});

		// Agregamos un listener al botón, en este caso continua a la impresión de
		// ticket.
		btncambio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.cambioProducto();
			}
		});

		// Agregamos un listener al botón, en este caso solo se cierra la ventana.
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.limpiarDatos("Cambio");
				control.muestraVistaVendedor();
				dispose();
			}
		});

	}

	public DefaultTableModel getTablaModeloVenta() {
		return this.modeloventa;
	}

	public DefaultTableModel getTablaModeloCambio() {
		return this.modelocambio;
	}

	public void setFolioventa(String folioventa) {
		this.tffolioventa.setText(folioventa);
	}

	// Asignamos control a nuestra vista.
	public void setControl(ControlVenta control) {
		this.control = control;
	}
}
