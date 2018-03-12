package Principal;

import Negocio.ControlLogin;
import Negocio.ControlVenta;
import Negocio.ServicioLogin;
import Negocio.ServicioTicket;
import Negocio.ServicioVenta;

import Presentacion.VistaCambioCalzado;
import Presentacion.VistaLogin;
import Presentacion.VistaTicket;
import Presentacion.VistaVendedor;
import Presentacion.VistaVentaCalzado;

public class Aplicacion {
	
	//Este metodo crea el objeto incial
	public static void main(String[] args) {
		// Crea la instancia de la aplicacion
		Aplicacion app = new Aplicacion();
		app.inicia(); // La inicia 
	}

	// Constructor de la Clase Aplicacion
	public Aplicacion() {
		// No hay nada que inicializar
	}

	// Inicia la Aplicacion 
	public void inicia() {
		// Declaramos las clases e instanciamos las clases(crear objetos de clases)
		VistaLogin login = new VistaLogin();
		VistaCambioCalzado vistacambio = new VistaCambioCalzado();
		VistaVendedor vistavendedor = new VistaVendedor();
		VistaVentaCalzado vistavendedorcalzado = new VistaVentaCalzado();
		VistaTicket vistaticket = new VistaTicket();
		ControlVenta controlventa = new ControlVenta();
		ControlLogin controllogin = new ControlLogin();
		ServicioVenta servicioventa = new ServicioVenta();
		ServicioLogin serviciologin = new ServicioLogin();
		ServicioTicket servicioticket =new ServicioTicket();

		// Establecemos las relaciones entre clases
		login.setControl(controllogin);
		vistacambio.setControl(controlventa);
		vistavendedor.setControl(controlventa);
		vistaticket.setControl(controlventa);
		vistavendedorcalzado.setControl(controlventa);
		servicioventa.setControl(controlventa);
		serviciologin.setControl(controllogin);
		servicioticket.setControl(controlventa);

		// Enviamos una instancia de cada clase al control
		controlventa.setVistaCambioCalzado(vistacambio);
		controlventa.setVistaTicket(vistaticket);
		controlventa.setVistaVendedor(vistavendedor);
		controllogin.setVistaVendedor(vistavendedor);
		controllogin.setVistaLogin(login);
		controlventa.setVistaLogin(login);
		controllogin.setServicioLogin(serviciologin);
		controlventa.setVistaVentaCalzado(vistavendedorcalzado);
		controlventa.setServicioVenta(servicioventa);
		controlventa.setServicioTicket(servicioticket);
		
		// Hacemos Visible la Clase Vistalogin 
		login.setVisible(true);
	}

}
