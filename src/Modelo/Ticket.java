package Modelo;

public class Ticket {
	// declaramos atributos de ticket
	private int folio = 0;
	private String modelo, color, fecha;
	private double talla, cantidad, preciounitario, iva, total;
	
	// Constructor para asignar valores al ticket.
	public Ticket(int folio, String fecha, String modelo, String color, double talla, double cantidad,
			double preciounitario, double iva, double total) {
		this.folio = folio;
		this.fecha = fecha;
		this.modelo = modelo;
		this.color = color;
		this.talla = talla;
		this.cantidad = cantidad;
		this.preciounitario = preciounitario;
		this.iva = iva;
		this.total = total;
	}

	public int getFolio() {
		return folio;
	}

	public String getModelo() {
		return modelo;
	}

	public String getColor() {
		return color;
	}

	public double getTalla() {
		return talla;
	}

	public double getCantidad() {
		return cantidad;
	}

	public double getPreciounitario() {
		return preciounitario;
	}

	public double getIva() {
		return iva;
	}

	public double getTotal() {
		return total;
	}

	public String getFecha() {
		return fecha;
	}
}
