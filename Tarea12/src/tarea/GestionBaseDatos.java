package tarea;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

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
			new GestionBaseDatos().truncarTabla();
			// Volvemos a ejecutar la sentencia
			ejecutarSentencia(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void truncarTabla() {
		System.out.println("Se va a truncar la tabla");
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement stmt = conexion.prepareStatement("TRUNCATE TABLE ALUMNO")) {
			ejecutarSentencia(stmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
