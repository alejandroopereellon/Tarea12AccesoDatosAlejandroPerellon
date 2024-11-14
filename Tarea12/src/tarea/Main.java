package tarea;

import java.io.File;
import java.util.Scanner;

import alumno.Alumno;
import alumno.CrearAlumnos;

public class Main {
	File fichero = new File("src\\tarea11\\BasesDatos\\ConsultaAlumnos.txt");
	Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		while (true) {
			new Main().interfazGrafica();
		}

	}

	private void interfazGrafica() {
		System.out.print("Bienvendo al menu de operaciones en bases de datos, selecciona una opcion:"
				+ System.lineSeparator() + "1. Insertar un nuevo alumno" + System.lineSeparator()
				+ "2. Mostrar todos los alumnos" + System.lineSeparator() + "3. Guardar los alumnos en un fichero"
				+ System.lineSeparator() + "4. Leer alumnos de un fichero y guardarlo en la base de datos"
				+ System.lineSeparator() + "5. Modificar el nombre de un alumno" + System.lineSeparator()
				+ "6. Eliminar un alumno de la base de datos por su clave primaria" + System.lineSeparator()
				+ "7. Eliminar un alumnno de la base de datos por su apellido" + System.lineSeparator()
				+ "8. Guardar todos los alumnos de la base de datos en un fichero XML" + System.lineSeparator()
				+ "9. Leer un fichero XML y guardarlo en la base de datos" + System.lineSeparator()
				+ "10. Vaciar la base de datos" + System.lineSeparator() + "11. Salir" + System.lineSeparator()
				+ "Respuesta: ");
		swtichRealizarAccion(solicitarNumero());
		opcionesResumidas();
	}

	private void opcionesResumidas() {
		System.out.print("________________________________" + System.lineSeparator() + "0. Mostrar menu"
				+ System.lineSeparator() + "Respuesta: ");
		int numero = solicitarNumero();
		if (numero == 0) {
			System.out.println("________________________________");
			interfazGrafica();
		} else {
			swtichRealizarAccion(numero);
		}
	}

	private void swtichRealizarAccion(int numero) {
		switch (numero) {
		case 1:
			/**
			 * Insertar un nuevo alumno.
			 */
			new GestionAlumnosSQL().insertarAlumno(new CrearAlumnos().crearAlumno());
			break;
		case 2:
			/**
			 * Mostar todos los alumnos (en consola).
			 */
			System.out.println(new GestionAlumnosSQL().mostarAlumnosFormatoString());
			break;
		case 3:
			/**
			 * Guardar todos los alumnos en un fichero (tú eliges el formato del fichero,
			 * pero no puede ser XML ni JSON).
			 */
			new GestionAlumnosSQL().almacenarTextoBaseDatos(fichero);
			break;
		case 4:
			/**
			 * Leer alumnos de un fichero (con el formato anterior), y guardarlo en una BD.
			 */
			for (Alumno alum : new GestionAlumnosSQL().leerAlumnosDesdeFicheroTexto(fichero)) {
				new GestionAlumnosSQL().insertarAlumno(alum);
			}
			break;
		case 5:
			/**
			 * Modificar el nombre de un alumno guardado en la base de datos a partir de su
			 * Primary Key (PK).
			 */

			System.out.println("Introduce el NIA del alumno");
			int numeroNia = solicitarNumero();
			sc.nextLine();
			System.out.println("Introduce el nuevo nombre del alumno");
			new GestionAlumnosSQL().modificarNombreAlumnoPK(numeroNia, sc.nextLine());
			break;
		case 6:
			/**
			 * Eliminar un alumno a partir de su (PK).
			 */
			System.out.println("Introduce el NIA del alumno a eliminar");
			new GestionAlumnosSQL().eliminarAlumnoPK(solicitarNumero());
			break;
		case 7:
			/**
			 * Eliminar los alumnos que su apellido contengan la palabra dada por el
			 * usuario.
			 */
			sc.nextLine();
			System.out.println("Introduce el apellido del los alumnos a eliminar");
			new GestionAlumnosSQL().eliminarAlumnosPorApellidos(sc.nextLine());

			break;
		case 8:
			/*
			 * Guardar todos los alumnos en un fichero XML o JSON. //
			 */
			new GestionArchivosXML().almacenarBaseDatosEnXML();
			break;
		case 9:
			/**
			 * Leer un fichero XML o JSON de alumnos (con en formato anterior) y guardarlos
			 * en la BD.
			 */
			new GestionArchivosXML().recuperarObjetoDesdeXMLAnadirBD();
			break;
		case 10:
			/**
			 * Vaciamos la tabla
			 */
			new GestionBaseDatos().truncarTabla();
			System.out.println("Se ha truncado la tabla");
			break;
		case 11:
			/**
			 * Salimos del programa
			 */
			System.out.println("Se va a finalizar el programa");
			System.exit(0);
			break;
		default:
			interfazGrafica();
			break;
		}
	}

	private int solicitarNumero() {
		try {
			return sc.nextInt();
		} catch (Exception e) {
			System.out.println("Ha ocurrido un error al procesar el texto");
			solicitarNumero();
		}
		return 0;
	}
}
