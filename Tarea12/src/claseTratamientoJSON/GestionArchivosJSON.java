package claseTratamientoJSON;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import claseGrupo.GestionGruposSQL;
import claseGrupo.Grupo;

public class GestionArchivosJSON extends GestionProcesarArchivoJSON {
	static final File ARCHIVO_JSON = new File("src\\claseTratamientoJSON\\consultaGruposJSON.json");

	/**
	 * Guardar todos los grupos (con toda su información como atributos) en un
	 * fichero XML o JSON. Para cada grupo se guardará también el listado de alumnos
	 * de ese grupo. Los datos del alumno serán atributos en el XML.
	 */
	public void guardarGruposJSON() {
		// Generamos una lista de grupos y dentro de los grupos anadimos el list de
		// alumnos
		List<Grupo> listaGrupos = new GestionGruposSQL().realizarConsultaListadoGrupos();
		for (Grupo grupo : listaGrupos) {
			grupo.setListaAlumnos(new GestionGruposSQL().realizarConsultaListadoAlumnosPertenecientesGrupo(grupo));
		}
		// Anadimos el arraylist de grupos al archivos json
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

			mapper.writeValue(ARCHIVO_JSON, listaGrupos);
			System.out.println("Se ha creado el listado de grupos y sus alumnos en JSON");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
