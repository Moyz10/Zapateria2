package Persistencia;

import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Modelo.Usuario;

public class DAOLogin {
	// Este método se encarga de validar si existe un usuario especifico en al base
	// de datos.

	public boolean valida(Usuario usuario) {
		System.out.println(usuario.getUsuario() + ", " + usuario.getPass() + ", " + usuario.getTipo()
				+ ", Usuario a buscar en DAO LOGIN");
		String sql = "SELECT * FROM Loginpersonal WHERE Tipo='" + usuario.getTipo() + "'AND Usuario ='"
				+ usuario.getUsuario() + "' AND Contraseña = '" + usuario.getPass() + "'   ";
		try {
			// crea el statement
			Statement st = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			return rs.next();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Surgio un error verifique sus datos");
		}
		return false;
	}

	public Usuario buscarUsuario(String usuario, String tipo) {
		Usuario user = null;
		String sql = "SELECT * FROM Loginpersonal WHERE Tipo='" + tipo + "'AND Usuario ='" + usuario + "'   ";
		try {
			// crea el statement
			Statement st = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next())
				user = new Usuario(rs.getString("Usuario"), rs.getString("Contraseña"), rs.getString("Tipo"),
						rs.getString("Nombre"), rs.getString("Curp"), rs.getString("Correo"), rs.getString("Telefono"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Surgio un error verifique sus datos");
		}
		return user;
	}
}
