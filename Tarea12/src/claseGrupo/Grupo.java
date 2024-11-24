package claseGrupo;

import java.util.List;

import claseAlumno.Alumno;

public class Grupo {

	String id, nombre, descripcion, tutor;
	List<Alumno> listaAlumnos;

	public Grupo(String id, String nombre, String descripcion, String tutor) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tutor = tutor;
	}

	public Grupo() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public List<Alumno> getListaAlumnos() {
		return listaAlumnos;
	}

	public void setListaAlumnos(List<Alumno> listaAlumnos) {
		this.listaAlumnos = listaAlumnos;
	}

}