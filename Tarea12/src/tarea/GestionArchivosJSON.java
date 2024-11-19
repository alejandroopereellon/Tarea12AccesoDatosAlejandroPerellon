package tarea;

import java.util.List;
import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import claseAlumno.Alumno;
import claseAlumno.GestionAlumnosSQL;
import claseGrupo.GestionGruposSQL;
import claseGrupo.Grupo;

public class GestionArchivosJSON {
	private static final File ARCHIVO_JSON = new File("src\\tarea\\datos\\consultaGruposJSON.json");

	/**
	 * Guardar todos los grupos (con toda su información como atributos) en un
	 * fichero XML o JSON. Para cada grupo se guardará también el listado de alumnos
	 * de ese grupo. Los datos del alumno serán atributos en el XML.
	 */
	public void guardarGruposJSON() {
		List<Grupo> listaGrupos = new GestionGruposSQL().almacenarAlumnosQuePertenecenAlGrupo();

		// Anadimos el arraylist de grupos al archivos json
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.registerModule(new JavaTimeModule());
			mapper.writeValue(ARCHIVO_JSON, listaGrupos);
			System.out.println("Se ha creado el listado de grupos en JSON");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void recuperarObjetoGrupoDesdeJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			List<Grupo> listaGrupos = mapper.readValue(ARCHIVO_JSON,
					mapper.getTypeFactory().constructCollectionLikeType(List.class, Grupo.class));

			// Anadimos los grupos a las bases de datos
			for (Grupo gru : listaGrupos) {
				// Almacenamos los grupos del json
				new GestionGruposSQL().insertarGruposBD(gru);
				// Almacenamos los alumnos en la base de datos
				for (Alumno alu : gru.getListaALumnosParticipantes()) {
					new GestionAlumnosSQL().insertarAlumno(alu);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
