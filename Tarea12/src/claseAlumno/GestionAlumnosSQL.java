package claseAlumno;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import basesDatos.GestionBaseDatos;

public class GestionAlumnosSQL {
	public void insertarAlumno(Alumno alumno) {
		String sentencia = "INSERT INTO alumno VALUES(?,?,?,?,?,?,?,?)";
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement stmt = conexion.prepareStatement(sentencia);) {

			stmt.setInt(1, alumno.getNia());
			stmt.setString(2, alumno.getNombre());
			stmt.setString(3, alumno.getApellidos());

			// Realizamos la conversion de char a String de sql
			stmt.setString(4, String.valueOf(alumno.getGenero()));

			// Realizamos la conversion de localDate a date de sql
			// Primero lo convertimos a date
			// Date fecha = Date.valueOf(alumno.getFechaNacimiento());
			// A continuacion lo insertamos al stmt
			// stmt.setDate(5, fecha);
			stmt.setDate(5, Date.valueOf(alumno.getFechaNacimiento()));

			stmt.setString(6, alumno.getCiclo());
			stmt.setString(7, alumno.getCurso());
			stmt.setString(8, alumno.getGrupo());

			new GestionBaseDatos().ejecutarSentencia(stmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este metod retorna la base de datos de los alumnos entera en un formato de
	 * texto
	 * 
	 * @return String con toda la base de datos escrita
	 */
	public void almacenarAlumnosFormatoTexto() {
		File ficheroAlumnosTexto = new File("src\\datos\\archivosTexto\\ConsultaAlumnosTexto.txt");

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroAlumnosTexto))) {
			for (Alumno alumno : mostarAlumnosConsultaSelect()) {
				bw.write(alumno.getNia() + " | " + alumno.getNombre() + " | " + alumno.getApellidos() + " | "
						+ alumno.getGenero() + " | " + alumno.getFechaNacimiento() + " | " + alumno.getCiclo() + " | "
						+ alumno.getCurso() + " | " + alumno.getGrupo() + System.lineSeparator());
			}
			System.out.println("Se han almacenado los datos correctamente");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Este metodo recopila las lineas de texto y las convierte en un objeto alumno,
	 * recopila todos los alumnos y devuelve un listado de los alumnos
	 * 
	 * @return listado de alumnos
	 */
	public ArrayList<Alumno> leerAlumnosDesdeFicheroTexto() {
		File ficheroAlumnosTexto = new File("src\\datos\\archivosTexto\\ConsultaAlumnosTexto.txt");
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		try (BufferedReader br = new BufferedReader(new FileReader(ficheroAlumnosTexto))) {
			String linea = "";
			String[] datos = null;

			// Almacenamos cada linea en un objeto de alumno
			while ((linea = br.readLine()) != null) {
				datos = linea.split(" \\| ");
				listaAlumnos.add(new Alumno(Integer.valueOf(datos[0]), datos[1], datos[2], datos[3].charAt(0),
						LocalDate.parse(datos[4]), datos[5], datos[6], datos[7]));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}

	public void mostarAlumnosFormatoString() {
		for (Alumno alumno : mostarAlumnosConsultaSelect()) {
			System.out.println(alumno);
		}
	}

	/**
	 * Este metodo retorna un listado de alumnos de la base de datos, realiza una
	 * consulta y de la lectura de la consulta crea un objeto alumno
	 * 
	 * @return listado de alumnos obtenida de la base de datos
	 */
	public ArrayList<Alumno> mostarAlumnosConsultaSelect() {
		ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>();
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement stmt = conexion.prepareStatement(
						"Select nia,nombre,apellidos,genero,fechanacimiento,ciclo,curso,grupo from alumno")) {
			// Ejecutar la consulta y obtener resultados
			ResultSet rs = stmt.executeQuery();

			// Procesar el resultado
			while (rs.next()) {
				int nia = rs.getInt("nia");
				String nombre = rs.getString("Nombre");
				String apellidos = rs.getString("Apellidos");
				String genero = rs.getString("Genero");
				LocalDate fechaNacimiento = rs.getDate("FechaNacimiento").toLocalDate();
				String ciclo = rs.getString("Ciclo");
				String curso = rs.getString("Curso");
				String grupo = rs.getString("Grupo");

				listaAlumnos.add(
						new Alumno(nia, nombre, apellidos, genero.charAt(0), fechaNacimiento, ciclo, curso, grupo));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}

	public void modificarNombreAlumnoPK(int primaryKey, String nombreNuevo) {
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement stmt = conexion.prepareStatement("UPDATE alumno SET nombre = ? WHERE nia = ?")) {
			stmt.setString(1, nombreNuevo);
			stmt.setInt(2, primaryKey);
			new GestionBaseDatos().ejecutarSentencia(stmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void eliminarAlumnoPK(int primaryKey) {
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement stmt = conexion.prepareStatement("DELETE FROM alumno WHERE nia = ?")) {
			stmt.setInt(1, primaryKey);
			new GestionBaseDatos().ejecutarSentencia(stmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Este metodo solicita el apellido a eliminar, creamos la sentencia delete y
	 * ponemos de sentencia prepara el %apellido% para saber si lo tiene de primer o
	 * segundo apellido
	 * 
	 * @param apellido es el apellido de los alumnos que se van a borrar
	 */
	public void eliminarAlumnosPorApellidos(String apellido) {
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement stmt = conexion.prepareStatement("DELETE FROM alumno WHERE apellidos LIKE ?")) {
			stmt.setString(1, "%" + apellido + "%");
			new GestionBaseDatos().ejecutarSentencia(stmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
