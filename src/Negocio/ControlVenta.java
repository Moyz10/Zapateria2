package Negocio;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Modelo.Producto;
import Modelo.Ticket;
import Presentacion.VistaCambioCalzado;
import Presentacion.VistaLogin;
import Presentacion.VistaTicket;
import Presentacion.VistaVendedor;
import Presentacion.VistaVentaCalzado;

public class ControlVenta implements Printable {
	// Instanciamos nuestros atributos
	private VistaCambioCalzado vistacambio;
	private VistaVentaCalzado vistaventacalzado;
	private VistaTicket vistaticket;
	private VistaLogin vistalogin;
	private VistaVendedor vistavendedor;
	private ServicioVenta servicioventa;
	private ServicioTicket servicioticket;

	// Agregamos la Vista Cambio de Calzado
	public void setVistaCambioCalzado(VistaCambioCalzado vistacambio) {
		this.vistacambio = vistacambio;
	}

	// Agregamos la Vista Venta de Calzado
	public void setVistaVentaCalzado(VistaVentaCalzado vistaventacalzado) {
		this.vistaventacalzado = vistaventacalzado;
	}

	// Agregamos el Servicio de Venta
	public void setServicioVenta(ServicioVenta servicioventa) {
		this.servicioventa = servicioventa;
	}

	//
	public void setServicioTicket(ServicioTicket servicioticket) {
		this.servicioticket = servicioticket;
	}

	//
	public void setVistaLogin(VistaLogin vistalogin) {
		this.vistalogin = vistalogin;
	}

	//
	public void setVistaVendedor(VistaVendedor vistavendedor) {
		this.vistavendedor = vistavendedor;
	}

	// Mostramos Ventana de Venta de Calzado
	public void muestraVentadeCalzado() {
		vistaventacalzado.setVisible(true);
	}

	// Mostramos Ventana de Cambio de Calzado
	public void muestraCambioCalzado() {
		vistacambio.setVisible(true);
	}

	//
	public void muestraVistaLogin() {
		vistalogin.setVisible(true);
	}

	//
	public void muestraVistaVendedor() {
		vistavendedor.setVisible(true);
	}

	// Agregamos Vista ticket
	public void setVistaTicket(VistaTicket vistaticket) {
		this.vistaticket = vistaticket;
	}

	// Este método nos permite agregar los datos que nos regresa el DAO a la tabla
	// de consulta.
	public void buscaProducto(int id) {
		Object[] fila = new Object[vistaventacalzado.getTablaModelo().getColumnCount()];
		Producto producto = servicioventa.buscaProducto(id);
		if (producto != null) {
			fila[0] = producto.damemodelo();
			fila[1] = producto.dametipo();
			fila[2] = producto.damecolor();
			fila[3] = producto.dametalla();
			fila[4] = producto.damecosto();
			fila[5] = producto.cantidadproductos;
			vistaventacalzado.getTablaModelo().addRow(fila);
		} else
			JOptionPane.showMessageDialog(null, "No se encontraron productos");
	}

	// Este método nos permite agregar los datos que nos regresa el DAO a la tabla
	// de consulta.
	public void buscaTicket(int folio) {
		Object[] filaventa = new Object[vistacambio.getTablaModeloVenta().getColumnCount()];
		Object[] filacambio = new Object[vistacambio.getTablaModeloCambio().getColumnCount()];
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		Ticket ticket = servicioticket.dameTicket(folio);
		if (ticket != null) {
			tickets.add(ticket);
			filaventa[0] = tickets.get(0).getModelo();
			filaventa[1] = tickets.get(0).getColor();
			filaventa[2] = tickets.get(0).getTalla();
			filaventa[3] = tickets.get(0).getTotal();
			vistacambio.getTablaModeloVenta().addRow(filaventa);
			for (Ticket t : servicioticket.obtenTodosTickets()) {
				if (t.getTotal() >= (double) filaventa[3]) {
					filacambio[0] = t.getModelo();
					filacambio[1] = t.getColor();
					filacambio[2] = t.getTalla();
					filacambio[3] = t.getTotal();
					filacambio[4] = 1;
					filacambio[5] = false;
					vistacambio.getTablaModeloCambio().addRow(filacambio);
				}
			}
		} else
			JOptionPane.showMessageDialog(null, "No se Encontraron los Datos del Folio");
	}

	// Este método calcula el total sobre las unidades que se van a vender y su
	// precio unitario.
	public void calculaTotal() {
		if (vistaventacalzado.getTablaModelo().getValueAt(0, 6) == null)
			JOptionPane.showMessageDialog(null, "Agregue pares a vender y presione enter");
		else {
			double costo = (double) vistaventacalzado.getTablaModelo().getValueAt(0, 4);
			int cantidadproductos = (int) (vistaventacalzado.getTablaModelo().getValueAt(0, 5));
			int cantidadventa = Integer.parseInt((String) vistaventacalzado.getTablaModelo().getValueAt(0, 6));
			if (cantidadventa > cantidadproductos)
				JOptionPane.showMessageDialog(null, "Cantidad insuficiente de productos en almacén");
			else {
				double iva = (costo * 0.16);
				double montototal = (costo * cantidadventa) + iva;
				vistaventacalzado.setMontounitario(String.valueOf(costo));
				vistaventacalzado.setIva(String.format("%.2f", iva));
				vistaventacalzado.setMontototal(String.format("%.2f", montototal));
			}
		}
	}

	public void limpiarDatos(String tipo) {
		if (vistaventacalzado.getTablaModelo().getRowCount() != 0 && tipo.equals("Venta")) {
			vistaventacalzado.setIdProducto("");
			vistaventacalzado.setMontounitario("       -");
			vistaventacalzado.setIva("       -");
			vistaventacalzado.setMontototal("       -");
			vistaventacalzado.getTablaModelo().removeRow(0);
		} else if (vistacambio.getTablaModeloVenta().getRowCount() != 0
				|| vistacambio.getTablaModeloCambio().getRowCount() != 0 && tipo.equals("Cambio")) {
			vistacambio.getTablaModeloVenta().removeRow(0);
			vistacambio.setFolioventa("");
			for (int i = vistacambio.getTablaModeloCambio().getRowCount() - 1; i >= 0; i--)
				vistacambio.getTablaModeloCambio().removeRow(i);
		}
	}

	// Creamos el ticket de venta
	public void creaTicket() {
		vistaticket.setFolio(String.valueOf(servicioticket.dameFolio()));
		vistaticket.setFecha(servicioticket.getFechaActual());
		vistaticket.setModelo((String) vistaventacalzado.getTablaModelo().getValueAt(0, 1));
		vistaticket.setColor((String) vistaventacalzado.getTablaModelo().getValueAt(0, 2));
		vistaticket.setTalla(String.valueOf(vistaventacalzado.getTablaModelo().getValueAt(0, 3)));
		vistaticket.setCantidad((String) vistaventacalzado.getTablaModelo().getValueAt(0, 6));
		vistaticket.setIva(vistaventacalzado.getIva());
		vistaticket.setPrecioUnitario(String.valueOf(vistaventacalzado.getTablaModelo().getValueAt(0, 4)));
		vistaticket.setTotal(vistaventacalzado.getTotal());
		vistaticket.setVisible(true);
	}

	public void cambioProducto() {
		int fila = 0;
		String modelo;
		boolean seleccion;
		for (int i = 0; i < vistacambio.getTablaModeloCambio().getRowCount(); i++) {
		System.out.println("filas: "+vistacambio.getTablaModeloCambio().getRowCount());
			System.out.println("i: "+i);
			seleccion = (boolean) vistacambio.getTablaModeloCambio().getValueAt(i, 5);
			if (seleccion) {
				fila = i;
				System.out.println("Entro: "+i);
			}
		}
		if (fila != 0) {
			modelo = (String) vistacambio.getTablaModeloCambio().getValueAt(fila, 0);
			System.out.println("Modelo: " + modelo);
		} else
			JOptionPane.showMessageDialog(null, "Seleccione un Producto para Realizar Cambio");
	}

	public void almacenarDatosTicket(String[] datosTicket) {
		String fecha, modelo, color;
		double talla, cantidad, preciounitario, iva, total;
		int folio = Integer.valueOf(datosTicket[0]);
		fecha = datosTicket[1];
		modelo = datosTicket[2];
		color = datosTicket[3];
		talla = Double.valueOf(datosTicket[4]);
		cantidad = Double.valueOf(datosTicket[5]);
		preciounitario = Double.valueOf(datosTicket[6]);
		iva = Double.valueOf(datosTicket[7]);
		total = Double.valueOf(datosTicket[8]);
		Ticket ticket = new Ticket(folio, fecha, modelo, color, talla, cantidad, preciounitario, iva, total);
		servicioticket.agregaTicket(ticket);
	}

	// Este método imprime el ticket.
	public void imprimeTicket() {
		try {
			PrinterJob gap = PrinterJob.getPrinterJob();
			gap.setPrintable(this);
			boolean top = gap.printDialog();
			if (top)
				gap.print();
		} catch (PrinterException e) {
			JOptionPane.showMessageDialog(null, "Error al imprimir");
		}
	}

	public boolean esNumero(String num) {
		if (num.isEmpty())
			return false;
		for (Character c : num.toCharArray())
			if (!Character.isDigit(c))
				return false;
		return true;
	}

	// Valida y le da formato de impresión al ticket.
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if (pageIndex > 0)
			return NO_SUCH_PAGE;
		Graphics2D hub = (Graphics2D) graphics;
		hub.translate(pageFormat.getImageableX() + 20, pageFormat.getImageableY() + 20);
		hub.scale(1, 1);
		vistaticket.getTicket().printAll(graphics);
		return PAGE_EXISTS;
	}

}
