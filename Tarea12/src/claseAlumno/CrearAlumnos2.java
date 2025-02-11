package claseAlumno;

import java.time.LocalDate;
import java.time.Year;
import java.util.Scanner;

public class CrearAlumnos2 {

	public Alumno crearAlumno() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce el nia del alumno: ");
		// Solicitamos los datos del NIA
		int nia = 0;
		do {
			try {
				nia = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.print("Introduce un numero: ");
			}
		} while (nia == 0);

		// Solicitamos los datos escritos
		String nombre, apellidos, ciclo, curso, grupo;
		System.out.print("Introduce el nombre: ");
		nombre = sc.nextLine().toUpperCase();
		System.out.print("Introduce apellidos: ");
		apellidos = sc.nextLine().toUpperCase();
		System.out.print("Introduce el ciclo: ");
		ciclo = sc.nextLine().toUpperCase();
		System.out.print("Introduce el curso: ");
		curso = sc.nextLine().toUpperCase();
		System.out.print("Introduce el grupo: ");
		grupo = sc.nextLine().toUpperCase();

		// Solicitamos el genero de alumno
		System.out.print("Introduce el genero del alumno\tH/M: ");
		char genero;
		do {
			genero = sc.nextLine().toUpperCase().charAt(0);
			if (genero != 72 && genero != 77) {
				System.out.print("Introduce H o M: ");
			}
		} while (genero != 72 && genero != 77);

		// Solicitamos la fecha de nacimiento
		int dia = 0, mes = 0, ano = 0;
		System.out.print("Introduce el dia: ");
		do {
			try {
				dia = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.print("Introduce un numero: ");
			}
		} while (dia == 0);
		System.out.print("Introduce el mes: ");
		do {
			try {
				mes = Integer.parseInt(sc.nextLine());
			} catch (Exception e) {
				System.out.print("Introduce un numero: ");
			}
		} while (mes == 0);
		System.out.print("Introduce el año: ");
		do {
			try {
				ano = Integer.parseInt(sc.nextLine());
				if (ano > Year.now().getValue() || ano < 1900) {
					System.out.print("Introduce un año real: ");
					ano = 0;
				}
			} catch (Exception e) {
				System.out.print("Introduce un numero: ");
			}
		} while (ano == 0);
		// Creamos la fecha de nacimiento
		LocalDate fechaNacimiento = null;
		do {
			try {
				fechaNacimiento = LocalDate.of(ano, mes, dia);
			} catch (Exception e) {
				System.out.println("Ha ocurrido un error en la fecha");
			}
		} while (fechaNacimiento == null);

		System.out.println("________________________________");
		// Generamos un nuevo alumno
		return new Alumno(nia, nombre, apellidos, genero, fechaNacimiento, ciclo, curso, grupo);
	}

	public CrearAlumnos2() {
		super();
	}

}