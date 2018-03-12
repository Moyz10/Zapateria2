
package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Producto;

/**
 * Esta clase representa una zapateria
 *
 * @author humberto
 *
 */
public class DAOProducto {

	// Constructor de la clase
	public DAOProducto() {
	}

	public boolean agregaAutor(Producto producto) {
		int llave;
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			// statement.execute("insert into values
			// (DEFAULT,'"+autor.dameNombre()+"','"+autor.dameApellido()+"',"+autor.dameAnioNacimiento()+")",Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave
			if (rs != null && rs.next()) {
				llave = rs.getInt(1);
				System.out.println(llave);
				// producto.cambiaId(llave); // Asigna la llave al autor
			}
			return true;
		} catch (SQLException e) {
			// Cacha excepcion
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permite quitar un material a la librería
	 * 
	 * @return true si el material se quito exitosamente, false sino
	 */
	public boolean quitaProducto(Producto producto) {

		int resultado = 0;

		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			resultado = statement.executeUpdate("DELETE FROM Producto WHERE nombre='" + producto.damemodelo()
					+ "' AND tipo='" + producto.dametipo() + "'");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (resultado == 0) {
			return false;
		} else {
			return true;
		}

	}

	public Producto buscaProducto(String modelo, String tipo) {
		Producto producto = null;
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			ResultSet rs = statement
					.executeQuery("SELECT * FROM Producto WHERE modelo='" + modelo + "' AND tipo='" + tipo + "'");

			if (rs.next()) {
				// Crea una nueva instancia del objeto
				producto = new Producto(rs.getInt("id"), rs.getString("modelo"), rs.getString("tipo"),
						rs.getString("color"), rs.getDouble("costo"), rs.getDouble("talla"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;

	}

	public Producto buscaProducto(int id) {

		Producto producto = null;
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT * FROM Producto WHERE Id=" + id);

			if (rs.next()) {
				// Crea una nueva instancia del objeto
				producto = new Producto(rs.getInt("id"), rs.getString("modelo"), rs.getString("tipo"),
						rs.getString("color"), rs.getDouble("costo"), rs.getDouble("talla"));
				producto.setCantidadproductos(producto.cantidadproductos + 1);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;

	}

	/**
	 * Regresa los materiales de la libreria como un arreglo de materiales
	 * 
	 * @return el arreglo de material
	 */
	public Producto[] dameProductos() {

		ArrayList<Producto> productosTemp = new ArrayList<Producto>();

		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT * FROM Producto");

			while (rs.next()) {
				// Crea una nueva instancia del objeto
				Producto producto = new Producto(rs.getInt("id"), rs.getString("modelo"), rs.getString("tipo"),
						rs.getString("color"), rs.getDouble("costo"), rs.getDouble("talla"));
				productosTemp.add(producto);
			}

			Producto productosTempArreglo[] = new Producto[productosTemp.size()];
			productosTemp.toArray(productosTempArreglo);
			return productosTempArreglo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Regresa numero de materiales en la libreria
	 * 
	 * @return un entero con el numero de materiales
	 */
	public int cuantosProductos() {
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Producto");
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
}
