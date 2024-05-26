package dominio;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;



/**
 * Clase que maneja los arraylists de objetos de tipo incidencia.
 * @author Joaquin Bullon Gonzalez
 * @version 2.0 14/04/2024
 */
@XmlRootElement (name="controlIncidencias")
@XmlType (propOrder = {"listaPendientes", "listaEliminados", "listaResueltos"})
@XmlAccessorType (XmlAccessType.FIELD)
public class Listas implements Serializable{
	
	@XmlElement(name = "incidencia" , required = false)
	private List<Incidencia> listaPendientes = new LinkedList<>();
	@XmlElement(name = "incidencia" , required = false)
	private List<Incidencia> listaEliminados = new LinkedList<>();
	@XmlElement(name = "incidencia" , required = false)
	private List<Incidencia> listaResueltos = new LinkedList<>();
	
	
	/**
	 * Devuelve el estado actual de la listaPendientes.
	 * @return {@link List} - listaPendientes actual
	 */	
	public List<Incidencia> getListaPendientes() {
		return listaPendientes;
	}	

	/**
	 * Modifica el valor de listaPendientes por el introducido en el metodo.
	 * @param listaPendientes {@link List} - listaPendientes nueva
	 */
	public void setListaPendientes(List<Incidencia> listaPendientes) {
		this.listaPendientes = listaPendientes;
	}

	
	/**
	 * Devuelve el estado actual de la listaEliminados.
	 * @return {@link List} - listaEliminados actual
	 */
	public List<Incidencia> getListaEliminados() {
		return listaEliminados;
	}	

	/**
	 * Modifica el valor de listaEliminados por el introducido en el metodo.
	 * @param listaPendientes {@link List} - listaEliminados nueva
	 */
	public void setListaEliminados(List<Incidencia> listaEliminados) {
		this.listaEliminados = listaEliminados;
	}

	/**
	 * Devuelve el estado actual de la listaResueltos.
	 * @return {@link List} - listaResueltos actual
	 */
	public List<Incidencia> getListaResueltos() {
		return listaResueltos;
	}	

	/**
	 * Modifica el valor de listaResueltos por el introducido en el metodo.
	 * @param listaPendientes {@link List} - listaResueltos nueva
	 */
	public void setListaResueltos(List<Incidencia> listaResueltos) {
		this.listaResueltos = listaResueltos;
	}

	/**
	 * Muestra la informacion de cada incidencia dentro de la lista de incidencias pendientes.
	 */
	public void mostrarPendientes() {
		for (Incidencia incidencia : listaPendientes) {
			System.out.println(incidencia.toString());
			System.out.println();
		}
	}

	/**
	 * Muestra la informacion de cada incidencia dentro de la lista de incidencias eliminadas.
	 */
	public void mostrarEliminados() {
		for (Incidencia incidencia : listaEliminados) {
			System.out.println(incidencia.toString());
			System.out.println();
		}
	}
	
	/**
	 * Muestra la informacion de cada incidencia dentro de la lista de incidencias resueltas.
	 */
	public void mostrarResueltos() {
		for (Incidencia incidencia : listaResueltos) {
			System.out.println(incidencia.toString());
			System.out.println();
		}
	}
	
	/**
	 * Crea una incidencia nueva con los parametros pasados por el metodo y la incluye en la lista de incidencias pendientes.
	 * @param numPuesto {@link Integer} - numero de puesto a introducir en la incidencia.
	 * @param problema {@link String} - problema a introducir en la incidencia.
	 */
	public Incidencia registrarIncidencia(int numPuesto, String problema) {
		 Incidencia nuevaIncidencia = new Incidencia(numPuesto, problema);
		 if(!getListaPendientes().isEmpty()){
			 if(getListaPendientes().get(getListaPendientes().size()-1).getNumRegistro() >= Incidencia.getContadorNumRegistro()) {
				 Incidencia.setContadorNumRegistro(getListaPendientes().get(getListaPendientes().size()-1).getNumRegistro()+1);
				 nuevaIncidencia.setNumRegistro(getListaPendientes().get(getListaPendientes().size()-1).getNumRegistro()+1);
			 }
		 }
		 
		 if(!getListaResueltos().isEmpty()){
			 if(getListaResueltos().get(getListaResueltos().size()-1).getNumRegistro() >= Incidencia.getContadorNumRegistro()) {
				 Incidencia.setContadorNumRegistro(getListaResueltos().get(getListaResueltos().size()-1).getNumRegistro()+1);
				 nuevaIncidencia.setNumRegistro(getListaResueltos().get(getListaResueltos().size()-1).getNumRegistro()+1);
			 }
		 }
		 
		 if(!getListaEliminados().isEmpty()){
			 if(getListaEliminados().get(getListaEliminados().size()-1).getNumRegistro() >= Incidencia.getContadorNumRegistro()) {
				 Incidencia.setContadorNumRegistro(getListaEliminados().get(getListaEliminados().size()-1).getNumRegistro()+1);
				 nuevaIncidencia.setNumRegistro(getListaEliminados().get(getListaEliminados().size()-1).getNumRegistro()+1);
			 }
		 }
		 
		 getListaPendientes().add(nuevaIncidencia);
		 
		 setListaPendientes(ordenarLista(getListaPendientes()));
		 
		 return nuevaIncidencia;
		 
	}

	/**
	 * Busca una incidencia recorriendo cada lista mediante los parametros introducidos en el metodo.
	 * @param fechaRegistro {@link LocalDate} - fecha de registro a buscar.
	 * @param horaRegistro {@link LocalTime} - hora de registro a buscar.
	 * @param numRegistro {@link Integer} - numero de registro a buscar.
	 * @return {@link Incidencia} - Puede devolver tanto una incidencia como el valor null (en el caso de no encontrar incidencia).
	 */
	public Incidencia buscarIncidencia(LocalDate fechaRegistro,LocalTime horaRegistro, int numRegistro) {
		Incidencia incidenciaBuscada = new Incidencia(fechaRegistro, horaRegistro, numRegistro);
		
		for (Incidencia incidencia : listaPendientes) {
			if(incidencia.equals(incidenciaBuscada))
				return incidencia;
		}
		
		for (Incidencia incidencia : listaEliminados) {
			if(incidencia.equals(incidenciaBuscada))
				return incidencia;
		}
		
		for (Incidencia incidencia : listaResueltos) {
			if(incidencia.equals(incidenciaBuscada))
				return incidencia;
		}
		
		return null;
	}

	/**
	 * Modifica el valor del numero de puesto de la incidencia indicada mediante el numero de incidencia.
	 * @param numeroIncidencia {@link Integer} - numero de incidencia de la lista de pendientes.
	 * @param numPuesto {@link Integer} - numero de puesto nuevo.
	 */
	public Incidencia modificarIncidencia(int numeroIncidencia, int numPuesto) {
		listaPendientes.get(numeroIncidencia).setNumPuesto(numPuesto);
		return listaPendientes.get(numeroIncidencia);
	}
	
	/**
	 * Modifica el valor del problema de la incidencia indicada mediante el numero de incidencia.
	 * @param numeroIncidencia {@link Integer} - numero de incidencia de la lista de pendientes.
	 * @param problema {@link String} - problema nuevo.
	 */
	public Incidencia modificarIncidencia(int numeroIncidencia, String problema) {
		listaPendientes.get(numeroIncidencia).setProblema(problema);
		return listaPendientes.get(numeroIncidencia);
	}
	
	/**
	 * Elimina una incidencia de la lista de pendientes indicando su causa de eliminacion.
	 * @param numeroIncidencia {@link Integer} - numero de incidencia de la lista de pendientes.
	 * @param causaEliminacion {@link String} - causa de eliminacion de la incidencia.
	 */
	public Incidencia eliminarIncidencia(int numeroIncidencia, String causaEliminacion) {
		listaPendientes.get(numeroIncidencia).setEstado(Estado.ELIMINADA);
		listaPendientes.get(numeroIncidencia).setFechaEliminacion(LocalDate.now());
		listaPendientes.get(numeroIncidencia).setFechaEliminacionTexto(LocalDate.now().toString());
		listaPendientes.get(numeroIncidencia).setCausaEliminacion(causaEliminacion);
		
		Incidencia incidenciaAEliminar = listaPendientes.get(numeroIncidencia);
		
		getListaEliminados().add(listaPendientes.get(numeroIncidencia));
		setListaEliminados(ordenarLista(getListaEliminados()));
		getListaPendientes().remove(numeroIncidencia);
		setListaPendientes(ordenarLista(getListaPendientes()));
		
		return incidenciaAEliminar;
		
	}

	/**
	 * Resuelve una incidencia de la lista de pendientes indicando su resolucion.
	 * @param numeroIncidencia {@link Integer} - numero de incidencia de la lista de pendientes.
	 * @param resolucion {@link String} - resolucion de la incidencia.
	 */
	public Incidencia resolverIncidencia(int numeroIncidencia, String resolucion) {
		listaPendientes.get(numeroIncidencia).setEstado(Estado.RESUELTA);
		listaPendientes.get(numeroIncidencia).setFechaResolucion(LocalDate.now());
		listaPendientes.get(numeroIncidencia).setFechaResolucionTexto(LocalDate.now().toString());
		listaPendientes.get(numeroIncidencia).setResolucion(resolucion);
		
		Incidencia incidenciaAResolver = listaPendientes.get(numeroIncidencia);
		
		getListaResueltos().add(listaPendientes.get(numeroIncidencia));
		setListaEliminados(ordenarLista(getListaEliminados()));
		getListaPendientes().remove(numeroIncidencia);
		setListaPendientes(ordenarLista(getListaPendientes()));
		
		return incidenciaAResolver;
	}
	
	/**
	 * Modifica el valor de la fecha de resolucion de la incidencia resuelta indicada mediante el numero de incidencia.
	 * @param numeroIncidencia {@link Integer} - numero de incidencia de la lista de resueltos.
	 * @param nuevaFecha {@link LocalDate} - fecha de resolucion nueva.
	 */
	public Incidencia modificarIncidenciaResuelta(int numeroIncidencia, LocalDate nuevaFecha) {
		listaResueltos.get(numeroIncidencia).setFechaResolucion(nuevaFecha);
		listaResueltos.get(numeroIncidencia).setFechaResolucionTexto(nuevaFecha.toString());
		return listaResueltos.get(numeroIncidencia);
	}
	
	/**
	 * Modifica el valor de la resolucion de la incidencia resuelta indicada mediante el numero de incidencia.
	 * @param numeroIncidencia {@link Integer} - numero de incidencia de la lista de resueltos.
	 * @param resolucion {@link String} - resolucion nueva.
	 */
	public Incidencia modificarIncidenciaResuelta(int numeroIncidencia, String resolucion) {
		listaResueltos.get(numeroIncidencia).setResolucion(resolucion);		
		return listaResueltos.get(numeroIncidencia);
	}
	
	/**
	 * Devuelve una incidencia resuelta indicada mediante el numero de incidencia a la lista de incidencias pendientes.
	 * @param numeroIncidencia {@link Integer} - numero de incidencia de la lista de resueltos.
	 */
	public Incidencia devolverIncidenciaResuelta(int numeroIncidencia) {
		listaResueltos.get(numeroIncidencia).setEstado(Estado.PENDIENTE);
		listaResueltos.get(numeroIncidencia).setFechaResolucion(null);
		listaResueltos.get(numeroIncidencia).setFechaResolucionTexto(null);
		listaResueltos.get(numeroIncidencia).setResolucion(null);
		
		Incidencia incidenciaADevolver = listaResueltos.get(numeroIncidencia);
		
		getListaPendientes().add(getListaResueltos().get(numeroIncidencia));
		setListaPendientes(ordenarLista(getListaPendientes()));
		getListaResueltos().remove(numeroIncidencia);
		setListaResueltos(ordenarLista(getListaResueltos()));
		
		return incidenciaADevolver;
	}
	
	/**
	 * Ordena la lista pasada por parametro 
	 * @param listaParaOrdenar
	 * @return
	 */
	private List<Incidencia> ordenarLista(List<Incidencia> listaParaOrdenar){
		TreeSet<Incidencia> listaOrdenada = new TreeSet<>();
		listaOrdenada.addAll(listaParaOrdenar);		
		return new LinkedList<Incidencia>(listaOrdenada);
	}
}
