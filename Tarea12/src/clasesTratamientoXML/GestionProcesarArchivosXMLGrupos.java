package clasesTratamientoXML;

import java.io.File;
import java.time.LocalDate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import claseAlumno.Alumno;
import claseGrupo.GestionGruposSQL;
import claseGrupo.Grupo;

public class GestionProcesarArchivosXMLGrupos {
	private static final File ARCHIVOXML = new File("src\\tarea\\datos\\xml\\DatosGruposAlumnos.xml");

	public void convertirXMLGrupoAlumnosIntroducirlosBD() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(ARCHIVOXML);

			// Obtenemos los elementos "Grupo"
			NodeList listaNodos = doc.getElementsByTagName("grupo");

			// Recorremos todos los elementos grupo
			for (int i = 0; i < listaNodos.getLength(); i++) {
				Node nodo = listaNodos.item(i);

				// Procesar cada grupo y alumno
				Element elemento = (Element) nodo;
				// Anadimos los grupos
				recuperarGruposyAlumnos(elemento);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void recuperarGruposyAlumnos(Element elementoGrupo) {
		// Recuperamos los datos de grupo
		String id, nombre, descripcion, tutor;
		id = elementoGrupo.getAttribute("id");
		nombre = elementoGrupo.getAttribute("nombre");
		descripcion = elementoGrupo.getAttribute("descripcion");
		tutor = elementoGrupo.getAttribute("tutor");

		// Creamos el objeto grupo y lo anadimos a la base de datos
		new GestionGruposSQL().insertarGruposBD(new Grupo(id, nombre, descripcion, tutor));
		NodeList listaAlumnos = elementoGrupo.getElementsByTagName("alumno");
		
		for (int i = 0; i < listaAlumnos.getLength(); i++) {
			
			Node nodo = listaAlumnos.item(i);
			Element elementoAlumno = (Element) nodo;
			recuperarAlumnos(elementoAlumno);
		}

	}

	private void recuperarAlumnos(Element elementoAlumno) {
		new GestionGruposSQL().insertarAlumnosBD(new Alumno(Integer.parseInt(elementoAlumno.getAttribute("nia")),
				elementoAlumno.getAttribute("nombre"), elementoAlumno.getAttribute("apellidos"),
				elementoAlumno.getAttribute("nombre").charAt(0),
				LocalDate.parse(elementoAlumno.getAttribute("fechaNacimiento")), elementoAlumno.getAttribute("ciclo"),
				elementoAlumno.getAttribute("curso"), elementoAlumno.getAttribute("grupo")));

	}
}
