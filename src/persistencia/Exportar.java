package persistencia;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import dominio.Incidencia;
import dominio.Listas;



/**
 * Exporta en formato XML los arrays introducidos en sus métodos y los almacena en distintos archivos
 * @author Joaquin Bullon Gonzalez
 * @version 1.0 13/04/2024
 */
public class Exportar {
	
	/**
	 * Convierte el array listaPendientes en un archivo xml y lo almacena
	 * @param lista [{@link List} - ArrayList a recibir
	 * @throws JAXBException - Excepción generada por la clase JAXB.
	 * @throws IOException - Excepción generada por la clase FileWriter.
	 */
	public static void exportarPendientes(List<Incidencia> lista) throws JAXBException, IOException {
			Listas tempLista = new Listas();
			tempLista.setListaPendientes(lista);
			
			JAXBContext contexto = JAXBContext.newInstance(Listas.class);
			Marshaller m = contexto.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.marshal(tempLista, new FileWriter("pendientes.xml"));
	}
	
	/**
	 * Convierte el array listaResueltos en un archivo xml y lo almacena
	 * @param lista [{@link Lista} - ArrayList a recibir
	 * @throws JAXBException - Excepción generada por la clase JAXB.
	 * @throws IOException	- Excepción generada por la clase FileWriter.
	 */
	public static void exportarResueltas(List<Incidencia> lista) throws JAXBException, IOException {
		Listas tempLista = new Listas();
		tempLista.setListaResueltos(lista);
		
		JAXBContext contexto = JAXBContext.newInstance(Listas.class);
		Marshaller m = contexto.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(tempLista, new FileWriter("resueltas.xml"));
	}
	
	/**
	 * Convierte el array listaEliminados en un archivo xml y lo almacena
	 * @param lista [{@link List} - ArrayList a recibir
	 * @throws JAXBException - Excepción generada por la clase JAXB.
	 * @throws IOException	- Excepción generada por la clase FileWriter.
	 */
	public static void exportarEliminadas(List<Incidencia> lista) throws JAXBException, IOException {
		Listas tempLista = new Listas();
		tempLista.setListaEliminados(lista);
		
		JAXBContext contexto = JAXBContext.newInstance(Listas.class);
		Marshaller m = contexto.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.marshal(tempLista, new FileWriter("eliminados.xml"));
	}
}
