package tarea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class GestionBaseDatos {
	public Connection CrearConexion() {
		String url = "jdbc:mysql://localhost:3306/alumnos02";
		String usuario = "root";
		String contrasena = "puesto02";

		try {
			Connection conexion = DriverManager.getConnection(url, usuario, contrasena);
			return conexion;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void ejecutarSentencia(PreparedStatement stmt) {
		try {
			// Ejecutar la consulta y obtener resultados
			int filasAfectadas = stmt.executeUpdate();
			System.out.println("Actualizacion completa " + filasAfectadas + " filas afectadas");
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("La tabla ya contiene los valores");
//			new GestionBaseDatos().truncarTabla("alumno");
//			new GestionBaseDatos().truncarTabla("grupo");
			// Volvemos a ejecutar la sentencia
			ejecutarSentencia(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void truncarTabla(String tabla) {
		System.out.println("Se va a truncar la tabla");
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement stmt = conexion.prepareStatement("TRUNCATE TABLE ?")) {
			stmt.setString(1, tabla);
			ejecutarSentencia(stmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void seleccionaTablaParaTruncar() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el nombre de la tabla");
		truncarTabla(sc.nextLine());
		sc.close();
	}

}
