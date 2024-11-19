package tarea;

import java.io.File;
import java.time.LocalDate;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import claseAlumno.Alumno;
import claseAlumno.GestionAlumnosSQL;

public class GestionArchivosXML {

	private void crearNuevoElementoHijo(Document doc, Element alumno, String nombreElemento, String datosElemento) {
		Element elemento = doc.createElement(nombreElemento);
		elemento.setTextContent(datosElemento);
		alumno.appendChild(elemento);
	}

	public void almacenarAlumnosBaseDatosEnXML() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.newDocument();

			// Creamos el elemento raiz
			Element root = doc.createElement("Alumnos");
			doc.appendChild(root);

			// Anadir nuevo elemento hijo por cada alumno
			for (Alumno alum : new GestionAlumnosSQL().mostarAlumnosConsultaSelect()) {
				Element alumno = doc.createElement("Alumno");

				// Anadimos los elementos hijos
				crearNuevoElementoHijo(doc, alumno, "NIA", String.valueOf(alum.getNia()));
				crearNuevoElementoHijo(doc, alumno, "Nombre", alum.getNombre());
				crearNuevoElementoHijo(doc, alumno, "Apellidos", alum.getApellidos());
				crearNuevoElementoHijo(doc, alumno, "Genero", String.valueOf(alum.getGenero()));
				crearNuevoElementoHijo(doc, alumno, "FechaNacimiento", alum.getFechaNacimiento().toString());
				crearNuevoElementoHijo(doc, alumno, "Ciclo", alum.getCiclo());
				crearNuevoElementoHijo(doc, alumno, "Curso", alum.getCurso());
				crearNuevoElementoHijo(doc, alumno, "Grupo", alum.getGrupo());

				root.appendChild(alumno);
			}

			// Guardamos los elementos en un fichero XML
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(doc),
					new StreamResult(new File("src\\tarea11\\BasesDatos\\ConsultaAlumnosXML.xml")));
			System.out.println("Se han almacenado los datos en formato XML");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void recuperarObjetoAlumnoDesdeXMLAnadirBD() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new File("src\\tarea11\\BasesDatos\\ConsultaAlumnosXML.xml"));

			// Obtenemos los elementos alumno
			NodeList listaNodos = doc.getElementsByTagName("Alumno");

			// Recorremos todos los elementos alumno
			for (int i = 0; i < listaNodos.getLength(); i++) {
				Node nodo = listaNodos.item(i);

				// Extraemos el nodo correspondiente, creamos el objeto Alumno anadimos a la BD
				Element elemento = (Element) nodo;

				new GestionAlumnosSQL().insertarAlumno(
						new Alumno(Integer.parseInt(elemento.getElementsByTagName("NIA").item(0).getTextContent()),
								elemento.getElementsByTagName("Nombre").item(0).getTextContent(),
								elemento.getElementsByTagName("Apellidos").item(0).getTextContent(),
								elemento.getElementsByTagName("Genero").item(0).getTextContent().charAt(0),
								LocalDate.parse(
										elemento.getElementsByTagName("FechaNacimiento").item(0).getTextContent()),
								elemento.getElementsByTagName("Ciclo").item(0).getTextContent(),
								elemento.getElementsByTagName("Curso").item(0).getTextContent(),
								elemento.getElementsByTagName("Grupo").item(0).getTextContent()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
