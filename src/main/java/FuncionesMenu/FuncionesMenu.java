package FuncionesMenu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FuncionesMenu {
	public static void anadirClientes() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Nombre del cliente: ");
		String nombreCliente = sc.nextLine();

		System.out.println("Nombre de Contacto: ");
		String nombreContacto = sc.nextLine();

		System.out.println("Apellido de Contacto: ");
		String apellidoContacto = sc.nextLine();

		System.out.println("Telefono: ");
		String telefono = sc.nextLine();

		System.out.println("Fax: ");
		String fax = sc.nextLine();

		System.out.println("Linea de direccion: ");
		String lineaDireccion = sc.nextLine();

		System.out.println("Ciudad: ");
		String ciudad = sc.nextLine();

		try {

			Connection connection = null;

			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"admin", "4DM1n4DM1n");
			int codigoCliente = 0;
			Statement stat = connection.createStatement();
			ResultSet resSet = stat.executeQuery("select codigo_cliente from cliente");

			int checkSQLNoExists = 0;
			while (resSet.next()) {
				codigoCliente++;
				if (resSet.getInt("codigo_cliente") >= codigoCliente) {
					checkSQLNoExists++;
				}
			}
			
			codigoCliente += checkSQLNoExists;
			PreparedStatement preparedstat = connection.prepareStatement("insert into cliente values (?,?,?,?,?,?,?)");

			
			preparedstat.setInt(1, codigoCliente);
			preparedstat.setString(2, nombreCliente);
			preparedstat.setString(3, nombreContacto);
			preparedstat.setString(4, apellidoContacto);
			preparedstat.setString(5, telefono);
			preparedstat.setString(6, fax);
			preparedstat.setString(7, lineaDireccion);
			preparedstat.setString(9, ciudad);

			int retorno = preparedstat.executeUpdate();
			if (retorno > 0) {
				System.out.println("Ha sido insertado");
			}

			connection.close();
		} catch (SQLException sqle) {
			System.out.println("SQLState: " + sqle.getSQLState() + " SQLErrorCode: " + sqle.getErrorCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void mostrarCliente() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escriba el cliente a mostrar en formato numerico ");
		int codigoCliente = 0;
		try {
			codigoCliente = sc.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Debe introducir un numero");
			mostrarCliente();
		}

		try {

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"admin", "4DM1n4DM1n");
			Statement stat = con.createStatement();
			ResultSet resSet = stat.executeQuery("select * from cliente where codigo_cliente = " + codigoCliente);

			while (resSet.next()) {
				System.out.print(resSet.getInt("codigo_cliente") + "\t");
				System.out.print(resSet.getString("nombre_cliente") + "\t");
				System.out.print(resSet.getString("nombre_contacto") + "\t");
				System.out.print(resSet.getString("apellido_contacto") + "\t");
				System.out.print(resSet.getString("telefono") + "\t");
				System.out.print(resSet.getString("fax") + "\t");
				System.out.print(resSet.getString("linea_direccion1") + "\t");
				System.out.print(resSet.getString("ciudad") + "\t");
				System.out.println();
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		System.out.println("...........................................................................");
	}

	public static void mostrarTodosLosClientes() {

		try {
			Connection conexion = null;
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"admin", "4DM1n4DM1n");

			Statement stat = conexion.createStatement();
			ResultSet resSet = stat.executeQuery("select * from cliente");
			while (resSet.next()) {
				System.out.print(resSet.getInt("codigo_cliente") + "||");
				System.out.print(resSet.getString("nombre_cliente") + "||");
				System.out.print(resSet.getString("nombre_contacto") + "||");
				System.out.print(resSet.getString("apellido_contacto") + "||");
				System.out.print(resSet.getString("telefono") + "||");
				System.out.print(resSet.getString("fax") + "||");
				System.out.print(resSet.getString("linea_direccion1") + "||");
				System.out.print(resSet.getString("ciudad") + "||");
				System.out.println();

			}
			conexion.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

	}

	public static void buscarClientes() {
		System.out.println("Inserte el nombre del cliente que usted desea visualizar");
		Scanner sc = new Scanner(System.in);
		String cadenaDeTexto = sc.nextLine();
		try {
			Connection conexion = null;
			conexion = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/jardineria?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"admin", "4DM1n4DM1n");
			Statement stat = conexion.createStatement();
			ResultSet resSet = stat.executeQuery("select * from cliente where nombre_cliente = '" + cadenaDeTexto
					+ "' or nombre_contacto = '" + cadenaDeTexto + "' or apellido_contacto = '" + cadenaDeTexto + "'");
			while (resSet.next()) {
				System.out.print(resSet.getInt("codigo_cliente") + "||");
				System.out.print(resSet.getString("nombre_cliente") + "||");
				System.out.print(resSet.getString("nombre_contacto") + "||");
				System.out.print(resSet.getString("apellido_contacto") + "||");
				System.out.print(resSet.getString("telefono") + "||");
				System.out.print(resSet.getString("fax") + "||");
				System.out.print(resSet.getString("linea_direccion1") + "||");
				System.out.print(resSet.getString("ciudad") + "||");
				System.out.println();
			}
			conexion.close();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
