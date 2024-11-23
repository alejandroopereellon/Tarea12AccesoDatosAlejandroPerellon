package clasesTratamientoXML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import claseAlumno.Alumno;
import claseGrupo.GestionGruposSQL;
import claseGrupo.Grupo;

public class GestionCrearArchivosXMLGrupos {
	private static final File ARCHIVOXML = new File("src\\tarea\\datos\\xml\\DatosGruposAlumnos.xml");

	/**
	 * Guardar todos los grupos (con toda su información como atributos) en un
	 * fichero XML o JSON. Para cada grupo se guardará también el listado de alumnos
	 * de ese grupo. Los datos del alumno serán atributos en el XML.
	 */

	public void generarXMLGruposYSusAlumnos() {
		try {
			// Crear un documentBuilder y Document
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			// Crear elemento raiz
			Element root = doc.createElement("Gurpos");
			doc.appendChild(root);

			// Anadimos cada elemento grupo con sus hijos alumnos
			for (Grupo gru : new GestionGruposSQL().realizarConsultaListadoGrupos()) {
				anadirElementoGrupo(doc, gru, root);
			}

			// Guardar el archivo XML
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(doc), new StreamResult(ARCHIVOXML));
			
			System.out.println("Se han guardado los datos correctamente");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void anadirElementoGrupo(Document doc, Grupo grupo, Element root) {
		Element elementoGrupo = doc.createElement("grupo");

		// Anadimos los atributos
		elementoGrupo.setAttribute("id", grupo.getId());
		elementoGrupo.setAttribute("nombre", grupo.getNombre());
		elementoGrupo.setAttribute("descripcion", grupo.getDescripcion());
		elementoGrupo.setAttribute("tutor", grupo.getTutor());

		// Anadimos el elemento alumno del grupo
		for (Alumno alu : new GestionGruposSQL().realizarConsultaListadoAlumnosPertenecientesGrupo(grupo)) {
			anadirElementoAlumno(elementoGrupo, alu, doc);
		}

		root.appendChild(elementoGrupo);
	}

	private void anadirElementoAlumno(Element elementoGrupo, Alumno alumno, Document doc) {
		Element elementoAlumno = doc.createElement("alumno");

		// Anadimos los atributos
		elementoAlumno.setAttribute("nia", String.valueOf(alumno.getNia()));
		elementoAlumno.setAttribute("nombre", alumno.getNombre());
		elementoAlumno.setAttribute("apellidos", alumno.getApellidos());
		elementoAlumno.setAttribute("Genero", String.valueOf(alumno.getGenero()));
		elementoAlumno.setAttribute("fechaNacimiento", alumno.getFechaNacimiento().toString());
		elementoAlumno.setAttribute("ciclo", alumno.getCiclo());
		elementoAlumno.setAttribute("curso", alumno.getCurso());
		elementoAlumno.setAttribute("grupo", alumno.getGrupo());

		// Anadimos el elemento hijo al padre (Grupo)
		elementoGrupo.appendChild(elementoAlumno);
	}
}
