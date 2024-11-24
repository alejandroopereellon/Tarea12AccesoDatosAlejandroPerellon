package claseTratamientoJSON;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import claseAlumno.Alumno;
import claseAlumno.GestionAlumnosSQL;
import claseGrupo.GestionGruposSQL;
import claseGrupo.Grupo;

public class GestionProcesarArchivoJSON {

	static final File ARCHIVO_JSON = new File("src\\claseTratamientoJSON\\consultaGruposJSON.json");

	public void recuperarObjetoGrupoDesdeJSON() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		try {
			List<Grupo> listaGrupos = mapper.readValue(ARCHIVO_JSON,
					mapper.getTypeFactory().constructCollectionLikeType(List.class, Grupo.class));
			// Anadimos los grupos a las bases de datos
			for (Grupo gru : listaGrupos) {
				// Almacenamos los grupos del json
				new GestionGruposSQL().insertarGruposBD(gru);
				// Almacenamos los alumnos en la base de datos
				for (Alumno alu : gru.getListaAlumnos()) {
					new GestionAlumnosSQL().insertarAlumno(alu);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}