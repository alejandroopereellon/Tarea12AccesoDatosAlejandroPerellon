package claseAlumno;

import java.time.LocalDate;

public class Alumno {
	/**
	 * Realiza un programa Java que lea los datos de 5 alumnos y los guarde campo a
	 * campo en un fichero binario cuyo nombre (y dirección) se solicitará por
	 * teclado. Cada alumno leído deberá ser guardado antes de solicitar el
	 * siguiente. Para cada alumno se tiene la siguiente información: NIA (entero),
	 * Nombre (String), Apellidos (String), Genero (Char), Fecha de Nacimiento
	 * (Fecha), Ciclo (String), Curso (String), Grupo (String).
	 */
	private String nombre, apellidos, ciclo, curso, grupo;
	private int nia;
	private char genero;
	private LocalDate fechaNacimiento;

	public Alumno(int nia, String nombre, String apellidos, char genero, LocalDate fechaNacimiento, String ciclo,
			String curso, String grupo) {

		this.nombre = nombre;
		this.apellidos = apellidos;
		this.ciclo = ciclo;
		this.curso = curso;
		this.grupo = grupo;
		this.nia = nia;
		this.genero = genero;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public int getNia() {
		return nia;
	}

	public void setNia(int nia) {
		this.nia = nia;
	}

	public char getGenero() {
		return genero;
	}

	public void setGenero(char genero) {
		this.genero = genero;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Alumno {" + "\n  nombre='" + nombre + '\'' + ",\n  apellidos='" + apellidos + '\'' + ",\n  ciclo='"
				+ ciclo + '\'' + ",\n  curso='" + curso + '\'' + ",\n  grupo='" + grupo + '\'' + ",\n  nia=" + nia
				+ ",\n  genero='" + genero + '\'' + ",\n  fechaNacimiento=" + fechaNacimiento + "\n}";
	}

}
