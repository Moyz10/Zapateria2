package Persistencia;


import java.sql.Connection;
import java.sql.Statement;

public class CreadorBaseDeDatos {

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		try
		{
			Connection connection = ManejadorBD.dameConnection();
			Statement statement = connection.createStatement();
			
			statement.execute("CREATE TABLE Loginpersonal(Usuario varchar(40), Contraseña varchar(40), Tipo varchar(40), Nombre varchar(40), Curp varchar(40), Correo varchar(40), Telefono varchar(40))");
		    statement.execute("INSERT INTO Loginpersonal VALUES ('Edgar','Feikel10','Vendedor','Edgar Lopez Perez','ECLP983200SDDRF','edgar.lp@gmail.com','5521365412')");
			statement.execute("CREATE TABLE Producto(ID integer, modelo varchar(40), tipo varchar(40), color varchar(40), costo double, talla double)");
			statement.execute("INSERT INTO Producto VALUES(1,'Nike Airmax', 'Calzado Deportivo', 'Blanco', 989.60,27.5)");
			statement.execute("INSERT INTO Producto VALUES(2,'Adidas Future', 'Calzado Deportivo', 'Rojo', 869.60,24.5)");
			statement.execute("INSERT INTO Producto VALUES(3,'Nike Conford', 'Calzado Casual', 'Gris', 1570.40,28)");
			statement.execute("INSERT INTO Producto VALUES(4,'Pumas Limited', 'Calzado deportivo', 'Dorado', 1189.65,28.5)");
			statement.execute("CREATE TABLE Tickets(Folio integer, Fecha varchar (40), Modelo varchar (40), Color varchar(40), Talla double, Cantidad double, PrecioUnitario double, Iva double, Totalapagar double)");
			System.out.println("datos agregados");

			ManejadorBD.termina();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}




	}

}
