package Presentacion;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Negocio.ControlVenta;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class VistaVentaCalzado extends JFrame {

	private JTextField producto;
	private JLabel total, iva, montounitario;
	private JButton btnBuscar, btnContinuar, btnCancelar, btnCalcularTotal;
	private ControlVenta control;
	private JTable table;
	private DefaultTableModel modelo = new DefaultTableModel(new Object[][] {},
			new String[] { "Modelo", "Tipo", "Color", "Talla", "Costo", "Disponibles", "Pares a vender" }) {
		@SuppressWarnings("rawtypes")
		Class[] columnTypes = new Class[] { String.class, String.class, String.class, Double.class, Double.class,
				Integer.class, Object.class };

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public Class getColumnClass(int columnIndex) {
			return columnTypes[columnIndex];
		}

		boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, true };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaVentaCalzado frame = new VistaVentaCalzado();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Crea Ventana Venta de Calzado
	public VistaVentaCalzado() {
		setTitle("Venta de calzado");
		setBounds(100, 100, 680, 360);
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
					control.limpiarDatos("Venta");
					control.muestraVistaVendedor();
					dispose();
				}
			}
		});
	}

	private void iniciarComponentes() {
		JPanel contentPane = new JPanel();
		JLabel lblId = new JLabel("ID Producto");
		JLabel lblmonto = new JLabel("Monto por unidad  $");
		JLabel lblIva = new JLabel("IVA (16%)   $");
		JLabel lblTotal = new JLabel("Total  $");
		producto = new JTextField();
		montounitario = new JLabel("       -");
		total = new JLabel("       -");
		iva = new JLabel("       -");
		btnBuscar = new JButton("Buscar");
		btnContinuar = new JButton("Continuar");
		btnCalcularTotal = new JButton("Calcular Total");
		btnCancelar = new JButton("Cancelar");
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();

		// Propiedades del Panel y se Agrega a la Ventana
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// Propiedades de la Etiqueta "Id" y se Agrega al Panel
		lblId.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblId.setBounds(40, 25, 100, 26);
		contentPane.add(lblId);

		// Propiedades de la Etiqueta "Monto" y se Agrega al Panel
		lblmonto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblmonto.setBounds(320, 170, 160, 26);
		contentPane.add(lblmonto);

		// Propiedades de la Etiqueta "Iva" y se Agrega al Panel
		lblIva.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIva.setBounds(380, 200, 100, 26);
		contentPane.add(lblIva);

		// Propiedades de la Etiqueta "Total" y se Agrega al Panel
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTotal.setBounds(420, 250, 60, 26);
		contentPane.add(lblTotal);

		// Propiedades del textfield "Id Producto" y se Agrega al Panel
		producto.setFont(new Font("Tahoma", Font.BOLD, 14));
		producto.setBounds(140, 25, 90, 24);
		producto.setColumns(10);
		contentPane.add(producto);

		// Propiedades del textfield "Monto Unitario" y se Agrega al Panel
		montounitario.setFont(new Font("Tahoma", Font.BOLD, 14));
		montounitario.setBounds(480, 170, 90, 24);
		contentPane.add(montounitario);

		// Propiedades del textfield "Iva" y se Agrega al Panel
		iva.setFont(new Font("Tahoma", Font.BOLD, 14));
		iva.setBounds(480, 200, 90, 24);
		contentPane.add(iva);

		// Propiedades del textfield "Total" y se Agrega al Panel
		total.setFont(new Font("Tahoma", Font.BOLD, 14));
		total.setBounds(480, 250, 90, 24);
		contentPane.add(total);

		// Propiedades del Boton Buscar y se Agrega al Panel
		btnBuscar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBuscar.setBounds(250, 20, 90, 26);
		contentPane.add(btnBuscar);

		// Propiedades del Boton Continuar y se Agrega al Panel
		btnContinuar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnContinuar.setBounds(460, 290, 120, 26);
		contentPane.add(btnContinuar);

		// Propiedades del Boton CalcularTotal y se Agrega al Panel
		btnCalcularTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCalcularTotal.setBounds(180, 200, 160, 26);
		contentPane.add(btnCalcularTotal);

		// Propiedades del Boton Cancelar y se Agrega al Panel
		btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCancelar.setBounds(280, 290, 120, 26);
		contentPane.add(btnCancelar);

		// Propiedades de la table y se Agrega el modelo
		table.setFocusable(false);
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);

		// Propiedades del ScrollPane Agregando la Tabla al Scrollpane y se Agrega al
		// Panel
		scrollPane.setBounds(40, 80, 600, 74);
		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);

		// Acciones de los Componentes
		accionesComponentes();
	}

	private void accionesComponentes() {
		// Validamos si los datos son correctos, y ejecutamos la búsqueda en este
		// método.
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String prod=producto.getText();
				if (control.esNumero(prod)) {
					if (modelo.getRowCount() != 0)
						control.limpiarDatos("Venta");
					control.buscaProducto(Integer.valueOf(prod));
					producto.setText(prod);
				} else 
					JOptionPane.showMessageDialog(null, "Ingrese id de producto a buscar");
			}
		});

		// Validamos si los datos son correctos, y ejecutamos la impresión del ticket.
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (montounitario.getText().equals("       -") || iva.getText().equals("       -")
						|| total.getText().equals("       -")) {
					JOptionPane.showMessageDialog(null, "Imposible pasar a imprimir ticket");
				} else {
					control.creaTicket();
					dispose();
				}
			}
		});

		// Validamos si ya hay producto seleccionado, para calcular su costo y total
		btnCalcularTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (control.esNumero(producto.getText())) 
					control.calculaTotal();
				else
				 JOptionPane.showMessageDialog(null, "No es posible calcular el total");
			}
		});

		// Cerramos la ventana.
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				control.muestraVistaVendedor();
				control.limpiarDatos("Venta");
				dispose();
			}
		});
	}

	// Instanciamos control a nuestra vista.

	public void setControl(ControlVenta control) {
		this.control = control;

	}
	// Estos métodos nos sirven para agregar datos a los textfields que despliegan
	// los costos.

	public void setIdProducto(String id) {
		this.producto.setText(id);
	}

	public void setMontototal(String total) {
		this.total.setText(total);
	}

	public void setIva(String iva) {
		this.iva.setText(iva);
	}

	public void setMontounitario(String montounitario) {
		this.montounitario.setText(montounitario);
	}

	// Al ya estar calculados, solo los retornamos, para la impresión del ticket.

	public String getIva() {
		return iva.getText();
	}

	public String getTotal() {
		return total.getText();
	}

	public DefaultTableModel getTablaModelo() {
		return modelo;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.GRAY);
		g.drawLine(470, 265, 580, 265);
	}
}
