package Persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Producto;
import Modelo.Ticket;

public class DAOTicket {

	public boolean agregaTicket(Ticket ticket) {
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			// Envia instruccion SQL
			statement.execute("INSERT INTO Tickets VALUES(" + ticket.getFolio() + ",'" + ticket.getFecha() + "', '"
					+ ticket.getModelo() + "', '" + ticket.getColor() + "', " + ticket.getTalla() + ", "
					+ ticket.getCantidad() + ", " + ticket.getPreciounitario() + ", " + ticket.getIva() + ", "
					+ ticket.getTotal() + ")");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean quitaTicket(Ticket ticket) {
		int resultado = 0;
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			// Recibe los resutados
			resultado = statement.executeUpdate(
					"DELETE FROM Tickets WHERE Folio=" + ticket.getFolio() + " AND Modelo='" + ticket.getModelo()
							+ "' AND Color='" + ticket.getColor() + "' AND Talla=" + ticket.getTalla() + "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (resultado == 0)
			return false;
		else
			return true;
	}

	// Permite realizar una búsqueda de ticket especifico en la base de datos.
	public Ticket buscaTicket(int folio) {
		Ticket ticket = null;
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT * FROM Tickets WHERE Folio=" + folio + "");

			if (rs.next())
				// Crea una nueva instancia del objeto
				ticket = new Ticket(rs.getInt("Folio"), rs.getString("Fecha"), rs.getString("Modelo"),
						rs.getString("Color"), rs.getDouble("Talla"), rs.getDouble("Cantidad"),
						rs.getDouble("PrecioUnitario"), rs.getDouble("Iva"), rs.getDouble("Totalapagar"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ticket;
	}

	public Ticket[] dameTickets() {
		ArrayList<Ticket> ticketsTemp = new ArrayList<Ticket>();
		try {
			// Crea el Statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM Tickets");
			while (rs.next()) {
				// Crea una nueva instancia del objeto
				Ticket ticket = new Ticket(rs.getInt("Folio"), rs.getString("Fecha"), rs.getString("Modelo"),
						rs.getString("Color"), rs.getDouble("Talla"), rs.getDouble("Cantidad"),
						rs.getDouble("PrecioUnitario"), rs.getDouble("Iva"), rs.getDouble("Totalapagar"));
				ticketsTemp.add(ticket);
			}
			Ticket ticketsTempArreglo[] = new Ticket[ticketsTemp.size()];
			ticketsTemp.toArray(ticketsTempArreglo);
			return ticketsTempArreglo;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public int cantidadTickets() {
		try {
			// Crea el Statement
			Statement statement = ManejadorBD.dameConnection().createStatement();
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Tickets");
			if (rs.next())
				return rs.getInt(1); // Obtiene el Valor a la Columna 1
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
}
