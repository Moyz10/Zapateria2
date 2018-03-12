package Negocio;

import Modelo.Producto;
import Persistencia.DAOProducto;

public class ServicioVenta {
	// Declaramos atributos de nuestro servicio.

	@SuppressWarnings("unused")
	private ControlVenta control;
	private DAOProducto daoproducto = new DAOProducto();
	
	// Instanciamos el control de nuestro servicio.
	public void setControl(ControlVenta control) {
		this.control = control;
	}
	
	// Este método consulta al DAOProducto si existe alguno con ese id. y lo
	// retorna.
	public Producto buscaProducto(int id) {
		Producto producto = daoproducto.buscaProducto(id);
		return producto;
	}

}
