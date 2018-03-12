package Negocio;

import javax.swing.JOptionPane;

import Modelo.Usuario;
import Presentacion.VistaLogin;
import Presentacion.VistaVendedor;

public class ControlLogin {

	// declaramos los atributos de nuestro control.
	private VistaVendedor vistavendedor;
	private ServicioLogin serviciologin;
	private VistaLogin login;
	private Usuario usuario = new Usuario();
	
	// Agregamos una instancia de las vistas a nuesto control
	public void setVistaVendedor(VistaVendedor vistavendedor) {
		this.vistavendedor = vistavendedor;
	}

	// Hacemos visible la ventana "VistaVendedor"
	public void muestraVistaVendedor(Usuario user) {
		vistavendedor.actualizarDatos(user);
		vistavendedor.setVisible(true);
	}

	// Instanciamos la vista login
	public void setVistaLogin(VistaLogin login) {
		this.login = login;
	}

	// Este metodo nos permite saber si el usuario ingresado existe en nuestra base
	// de datos, pasandolo al servicio Login.
	public boolean validaIngreso() {
		String nl = System.getProperty("line.separator");
		if (serviciologin.validaingreso(usuario)) {
			Usuario user = serviciologin.dameUsuario(usuario.getUsuario(), usuario.getTipo());
			JOptionPane.showMessageDialog(null,
					"Ingresaste como: Vendedor" + nl + " Bienvenido " + user.getNombre());
			muestraVistaVendedor(user);
			login.dispose();
			return true;
		} else 
			JOptionPane.showMessageDialog(null,
					"Usuario y/o contraseña incorrecta" + nl + "¿Seleccionó correctamente el campo cargo?");
		return false;
	}

	// Agregamos instancia de servicio login
	public void setServicioLogin(ServicioLogin serviciologin) {
		this.serviciologin = serviciologin;
	}

	// Validamos que hay algo escrito en el textfield de usuario para consultarlo al
	// DAO
	public void recibeUsuario(String user) {
		if (user.length() > 1) 
			usuario.setUsuario(user);
	}

	// Validamos que tipo de usuario trata de ingresar al sistema
	public void recibeTipo(String tipo) {
		usuario.setTipo(tipo);
	}

	// Validamos que hay algo escrito en el textfield de contraseña para la
	// consulta.
	public void recibeContraseña(String pass) {
		if (pass.length() > 1) 
			usuario.setPass(pass);
	}
}
