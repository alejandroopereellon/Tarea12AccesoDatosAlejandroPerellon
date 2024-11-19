package claseGrupo;

import java.util.List;

import claseAlumno.Alumno;

public class Grupo {

	String id, nombre, descripcion, tutor;
	List<Alumno> listaALumnosParticipantes;

	public Grupo(String id, String nombre, String descripcion, String tutor) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tutor = tutor;
	}

	public Grupo() {
	}

	public List<Alumno> getListaALumnosParticipantes() {
		return listaALumnosParticipantes;
	}

	public void setListaALumnosParticipantes(List<Alumno> listaALumnosParticipantes) {
		this.listaALumnosParticipantes = listaALumnosParticipantes;
	}

	protected String getId() {
		return id;
	}

	protected void setId(String id) {
		this.id = id;
	}

	protected String getNombre() {
		return nombre;
	}

	protected void setNombre(String nombre) {
		this.nombre = nombre;
	}

	protected String getDescripcion() {
		return descripcion;
	}

	protected void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	protected String getTutor() {
		return tutor;
	}

	protected void setTutor(String tutor) {
		this.tutor = tutor;
	}

}
