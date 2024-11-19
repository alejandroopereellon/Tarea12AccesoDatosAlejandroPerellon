package claseGrupo;

import java.util.Scanner;

public class CrearGrupo {
	public Grupo crearGrupo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce el id de la asignatura");
		String id = sc.nextLine();
		System.out.println("Introduce el nombre");
		String nombre = sc.nextLine();
		System.out.println("Introduce la descripcion de la asignatura");
		String descripcion = sc.nextLine();
		System.out.println("Introduce el tutor");
		String tutor = sc.nextLine();
		if (sc != null) {
			sc.close();
		}

		return new Grupo(id, nombre, descripcion, tutor);

	}
}
