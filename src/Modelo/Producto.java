package Modelo;

public class Producto {
	// Declaramos atributos de nuestro producto.
	public String modelo, tipo, color;
	double costo, talla;
	public int cantidadproductos = 0;
	public int id;

	// constructor que nos permite asignar valores a los atributos.
	public Producto(int id, String modelo, String tipo, String color, double costo, double talla) {
		this.modelo = modelo;
		this.tipo = tipo;
		this.color = color;
		this.costo = costo;
		this.talla = talla;
		this.id = id;
	}

	// Declaramos estos métodos, para que en la consulta en el DAO podamos crear el
	// objeto sacándolo del resultset

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public void setTalla(double talla) {
		this.talla = talla;
	}

	public void setCantidadproductos(int string) {
		this.cantidadproductos = string;
	}

	public void setId(int id) {
		this.id = id;
	}

	// Estos otros metodos nos serviran para mostrar los atributos en el Jtable
	public String damemodelo() {
		return modelo;
	}

	public String dametipo() {
		return tipo;
	}

	public String damecolor() {
		return color;
	}

	public double damecosto() {
		return costo;
	}

	public double dametalla() {
		return talla;
	}

	public int dameid() {
		return id;
	}

	@Override
	public String toString() {
		return "Producto [modelo=" + modelo + ", tipo=" + tipo + ", color=" + color + ", costo=" + costo + ", talla="
				+ talla + ", cantidadproductos=" + cantidadproductos + ", id=" + id + "]";
	}

}
