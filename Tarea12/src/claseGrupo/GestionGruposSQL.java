package claseGrupo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import claseAlumno.Alumno;
import tarea.GestionBaseDatos;

public class GestionGruposSQL {

	public void insertarGruposBD(Grupo grupo) {
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement ps = conexion
						.prepareStatement("INSERT INTO grupo (id, nombre, descripcion, tutor) VALUES (?, ?, ?, ?)")) {

			ps.setString(1, grupo.getId());
			ps.setString(2, grupo.getNombre());
			ps.setString(3, grupo.getDescripcion());
			ps.setString(4, grupo.getTutor());

			new GestionBaseDatos().ejecutarSentencia(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertarAlumnosBD(Alumno alumno) {
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement ps = conexion.prepareStatement(
						"INSERT INTO alumno (nia, nombre, apellidos, genero, fechaNacimiento,ciclo, curso, grupo) VALUES (?,?,?,?,?,?,?,?)")) {

			ps.setInt(1, alumno.getNia());
			ps.setString(2, alumno.getNombre());
			ps.setString(3, alumno.getNombre());
			ps.setString(4, String.valueOf(alumno.getGenero()));
			ps.setDate(5, Date.valueOf(alumno.getFechaNacimiento()));
			ps.setString(6, alumno.getCiclo());
			ps.setString(7, alumno.getCurso());
			ps.setString(8, alumno.getGrupo());

			new GestionBaseDatos().ejecutarSentencia(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostar todos los alumnos (en consola): Mostrará los datos del alumno, y del
	 * grupo al que pertenece.
	 */
	public void consultarAlumnosyGrupos() {
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement ps = conexion.prepareStatement(
						"SELECT a.nia ,a.nombre ,a.apellidos, a.genero, a.fechaNacimiento, a.ciclo, a.curso, a.grupo, g.id, g.nombre, g.descripcion, g.tutor  FROM alumno a LEFT JOIN grupo g ON a.grupo = g.id ORDER BY a.nia")) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("NIA: " + rs.getInt("a.NIA"));
				System.out.println("Nombre: " + rs.getString("a.Nombre"));
				System.out.println("Apellidos: " + rs.getString("a.Apellidos"));
				System.out.println("Género: " + rs.getString("a.Genero"));
				System.out.println("Fecha de nacimiento: " + rs.getDate("a.FechaNacimiento"));
				System.out.println("Ciclo: " + rs.getString("a.Ciclo"));
				System.out.println("Curso: " + rs.getString("a.Curso"));
				System.out.println("Grupo: " + rs.getString("a.Grupo"));
				System.out.println("Grupo ID: " + rs.getString("g.id"));
				System.out.println("Grupo Nombre: " + rs.getString("g.Nombre"));
				System.out.println("Grupo Descripción: " + rs.getString("g.Descripcion"));
				System.out.println("Tutor: " + rs.getString("g.Tutor"));
				System.out.println("----------------------------"); // Separador para cada alumno
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Eliminar los alumnos del curso indicado por el usuario (debes mostrarle
	 * previamente los cursos existentes).
	 */
	public void mostrarAlumnosyEliminarlosPorGrupo() {
		mostrarTablaGrupos();

		eliminarAlumnosPorGrupo();
	}

	private void eliminarAlumnosPorGrupo() {
		System.out.print("Selecciona el grupo para el cual se van a eliminar los alumnos: ");
		Scanner sc = new Scanner(System.in);
		String grupoBorrar = sc.nextLine();

		// Borramos los alumnos
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement ps = conexion.prepareStatement("DELETE FROM alumno WHERE grupo = ?")) {
			ps.setString(1, grupoBorrar);
			new GestionBaseDatos().ejecutarSentencia(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostramos toda la tabla de grupos
	 */
	private void mostrarTablaGrupos() {
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement ps = conexion.prepareStatement("SELECT id, Nombre, Descripcion, Tutor FROM grupo")) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("Grupo ID: " + rs.getString("id"));
				System.out.println("Grupo Nombre: " + rs.getString("Nombre"));
				System.out.println("Grupo Descripción: " + rs.getString("Descripcion"));
				System.out.println("Tutor: " + rs.getString("Tutor"));
				System.out.println("________________________________");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Grupo> realizarConsultaListadoGrupos() {
		List<Grupo> listaGrupos = new ArrayList<Grupo>();
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement ps = conexion.prepareStatement("SELECT id, Nombre, Descripcion, Tutor FROM grupo")) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				listaGrupos.add(new Grupo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaGrupos;
	}

	public List<Alumno> realizarConsultaListadoAlumnosPertenecientesGrupo(Grupo grupo) {
		List<Alumno> listaAlumnos = new ArrayList<Alumno>();

		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement ps = conexion.prepareStatement(
						"SELECT NIA, Nombre, Apellidos, Genero, FechaNacimiento, Ciclo, Curso, Grupo FROM alumno WHERE grupo = ?")) {
			ps.setString(1, grupo.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				listaAlumnos.add(new Alumno(rs.getInt("NIA"), rs.getString("Nombre"), rs.getString("Apellidos"),
						rs.getString("Genero").charAt(0), LocalDate.parse(rs.getString("FechaNacimiento")),
						rs.getString("Ciclo"), rs.getString("Curso"), rs.getString("Grupo")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaAlumnos;
	}

//	public List<Grupo> almacenarAlumnosQuePertenecenAlGrupo() {
//		List<Grupo> listaGrupos = realizarConsultaListadoGrupos();
//		for (Grupo gru : listaGrupos) {
//			gru.setListaALumnosParticipantes(realizarConsultaListadoAlumnosPertenecientesGrupo(gru));
//		}
//		return listaGrupos;
//	}

}
