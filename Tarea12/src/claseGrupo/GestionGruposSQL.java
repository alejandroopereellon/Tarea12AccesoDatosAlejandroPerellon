package claseGrupo;

import java.sql.Connection;
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
						.prepareStatement("INSERT id, nombre, descripcion, tutor INTO GRUPO VALUES ?,?,?,?")) {

			ps.setString(1, grupo.getId());
			ps.setString(2, grupo.getNombre());
			ps.setString(3, grupo.getDescripcion());
			ps.setString(4, grupo.getTutor());

			new GestionBaseDatos().ejecutarSentencia(ps);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mostar todos los alumnos (en consola): Mostrará los datos del alumno, y del
	 * grupo al que pertenece.
	 */
	public void mostrarAlumnosyGrupos() {
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement ps = conexion.prepareStatement(
						"SELECT a.nia ,a.nombre ,a.apellidos, a.genero, a.fechaNacimiento, a.ciclo, a.curso, a.grupo, g.id, g.nombre, g.descripcion, g.tutor  FROM alumno a LEFT JOIN grupo g ON a.grupo = g.id ORDER BY a.id")) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("NIA: " + rs.getInt("NIA"));
				System.out.println("Nombre: " + rs.getString("Nombre"));
				System.out.println("Apellidos: " + rs.getString("Apellidos"));
				System.out.println("Género: " + rs.getString("Genero"));
				System.out.println("Fecha de nacimiento: " + rs.getDate("FechaNacimiento"));
				System.out.println("Ciclo: " + rs.getString("Ciclo"));
				System.out.println("Curso: " + rs.getString("Curso"));
				System.out.println("Grupo: " + rs.getString("Grupo"));
				System.out.println("Grupo ID: " + rs.getInt("id"));
				System.out.println("Grupo Nombre: " + rs.getString("Nombre"));
				System.out.println("Grupo Descripción: " + rs.getString("Descripcion"));
				System.out.println("Tutor: " + rs.getString("Tutor"));
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
	public void eliminarAlumnosPorCurso() {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		sc.close();
	}

	/**
	 * Mostramos toda la tabla de grupos
	 */
	private void mostrarTablaGrupos() {
		try (Connection conexion = new GestionBaseDatos().CrearConexion();
				PreparedStatement ps = conexion.prepareStatement("SELECT id, Nombre, Grupo, Tutor FROM grupo")) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("Grupo ID: " + rs.getInt("id"));
				System.out.println("Grupo Nombre: " + rs.getString("Nombre"));
				System.out.println("Grupo Descripción: " + rs.getString("Descripcion"));
				System.out.println("Tutor: " + rs.getString("Tutor"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<Grupo> realizarConsultaListadoGrupos() {
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

	private List<Alumno> realizarConsultaListadoAlumnosPertenecientesGrupo(Grupo grupo) {
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

	public List<Grupo> almacenarAlumnosQuePertenecenAlGrupo() {
		List<Grupo> listaGrupos = realizarConsultaListadoGrupos();
		for (Grupo gru : listaGrupos) {
			gru.setListaALumnosParticipantes(realizarConsultaListadoAlumnosPertenecientesGrupo(gru));
		}
		return listaGrupos;
	}
}
