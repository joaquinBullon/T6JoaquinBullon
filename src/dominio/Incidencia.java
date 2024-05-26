package dominio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.xml.bind.annotation.*;
import java.io.Serializable;



/**
 * Clase que gestiona los objetos Incidencia.
 * @author Joaquin Bullon Gonzalez
 * @version 2.0 14/04/2024
 */
@XmlRootElement (name="incidencia")
@XmlType (propOrder = {"estado", "fechaRegistroTexto", "horaRegistroTexto", "numRegistro", "numPuesto", "problema", "fechaEliminacionTexto", "causaEliminacion", "fechaResolucionTexto", "resolucion"})
@XmlAccessorType (XmlAccessType.FIELD) 
public class Incidencia implements Comparable<Incidencia>, Serializable{
	
	@XmlElement (required=true)
	private Estado estado;
	@XmlTransient
	private LocalDate fechaRegistro;
	@XmlElement (name="fechaRegistro", required=true)
	private String fechaRegistroTexto;
	@XmlTransient
	private LocalTime horaRegistro;
	@XmlElement (name="horaRegistro", required=true)
	private String horaRegistroTexto;
	private int numRegistro;
	private int numPuesto;
	private String problema;
	
	
	@XmlTransient
	private LocalDate fechaEliminacion;
	@XmlElement (name="fechaEliminacion", required=true)
	private String fechaEliminacionTexto;
	private String causaEliminacion;
	
	@XmlTransient
	private LocalDate fechaResolucion;
	@XmlElement (name="fechaResolucion", required=true)
	private String fechaResolucionTexto;
	private String resolucion;
	
	@XmlTransient
	private static int contadorNumRegistro = 0;

	
	public Incidencia() { //Metodo constructor sin parámetros para evitar fallos de exportacion
	}
	
	/**
	 * Metodo constructor de Incidencia para realizar busquedas.
	 * @param fRegistro {@link LocalDate} - Valor que se almacena en la variable fechaRegistro.
	 * @param hRegistro {@link LocalTime} - Valor que se almacena en la variable horaRegistro.
	 * @param nRegistro {@link Integer} - Valor que se almacena en la variable numRegistro
	 */
	public Incidencia(LocalDate fRegistro, LocalTime hRegistro, int nRegistro) {
		final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
		
		String tempFecha = formatoFecha.format(fRegistro);
		String tempHora = formatoHora.format(hRegistro);
		
		setFechaRegistro(LocalDate.parse(tempFecha, formatoFecha));
		setHoraRegistro(LocalTime.parse(tempHora, formatoHora));
		setNumRegistro(nRegistro);
	}

	/**
	 * Metodo constructor de Incidencia.
	 * @param numPuesto {@link Integer} - Valor que se almacena en la variable numPuesto.
	 * @param problema {@link String} - Valor que se almacena en la variable problema.
	 */
	public Incidencia(int numPuesto, String problema) {
		final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
		
		String tempFecha = formatoFecha.format(LocalDate.now());
		String tempHora = formatoHora.format(LocalTime.now());
		
		setEstado(Estado.PENDIENTE);
		setFechaRegistro(LocalDate.parse(tempFecha, formatoFecha));
		setFechaRegistroTexto(tempFecha);
		setHoraRegistro(LocalTime.parse(tempHora, formatoHora));
		setHoraRegistroTexto(tempHora);
		contadorNumRegistro++;
		setNumRegistro(contadorNumRegistro);
		setNumPuesto(numPuesto);
		setProblema(problema);
	}

	
	/**
	 * Devuelve el estado actual de la incidencia.
	 * @return {@link Estado} estado actual.
	 */
	public Estado getEstado() {
		return estado;
	}

	/**
	 * Modifica el valor de estado de la incidencia por el introducido en el metodo.
	 * @param estado {@link Estado} - estado nuevo.
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	
	/**
	 * Devuelve la fecha de registro de la incidencia.
	 * @return {@link LocalDate} - fecha de registro actual.
	 */
	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Modifica el valor de fecha de registro por el introducido en el metodo.
	 * @param fechaRegistro {@link LocalDate} - fecha de registro nueva.
	 */
	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	/**
	 * Modifica el valor de fecha de registro texto por el introducido en el metodo.
	 * @param fechaRegistro {@link String} - fecha de registro nueva.
	 */
	public void setFechaRegistroTexto(String fechaRegistro) {
		this.fechaRegistroTexto = fechaRegistro;
	}

	
	/**
	 * Devuelve la hora de registro de la incidencia.
	 * @return {@link LocalTime} - hora de registro actual.
	 */
	public LocalTime getHoraRegistro() {
		return horaRegistro;
	}

	/**
	 * Modifica el valor de la hora de registro por el introducido en el metodo.
	 * @param horaRegistro {@link LocalTime} - hora de registro nueva.
	 */
	public void setHoraRegistro(LocalTime horaRegistro) {
		this.horaRegistro = horaRegistro;
	}
	
	/**
	 * Modifica el valor de la hora de registro texto por el introducido en el metodo.
	 * @param horaRegistro {@link String} - hora de registro nueva.
	 */
	public void setHoraRegistroTexto(String horaRegistro) {
		this.horaRegistroTexto = horaRegistro;
	}

	
	/**
	 * Devuelve el numero de registro de la incidencia.
	 * @return {@link Integer} - numero de registro actual.
	 */
	public int getNumRegistro() {
		return numRegistro;
	}

	/**
	 * Modifica el valor del numero de registro por el introducido en el metodo.
	 * @param numRegistro {@link Integer} - numero de registro nuevo. 
	 */
	public void setNumRegistro(int numRegistro) {
		this.numRegistro = numRegistro;
	}
	
	
	/**
	 * Devuelve el numero de puesto de la incidencia.
	 * @return {@link Integer} - numero de puesto actual.
	 */
	public int getNumPuesto() {
		return numPuesto;
	}

	/**
	 * Modifica el valor de numero de puesto por el introducido en el metodo. 
	 * @param numPuesto {@link Integer} - numero de puesto nuevo.
	 */
	public void setNumPuesto(int numPuesto) {
		this.numPuesto = numPuesto;
	}

	
	/**
	 * Devuelve el problema de la incidencia.
	 * @return {@link String} - problema actual.
	 */
	public String getProblema() {
		return problema;
	}

	/**
	 * Modifica el valor del problema por el introducido en el metodo.
	 * @param problema {@link String} - problema nuevo.
	 */
	public void setProblema(String problema) {
		this.problema = problema;
	}

	/**
	 * Devuelve la fecha de eliminacion de la incidencia.
	 * @return {@link LocalDate} - fecha de eliminacion actual.
	 */
	public LocalDate getFechaEliminacion() {
		return fechaEliminacion;
	}

	/**
	 * Modifica el valor de la fecha de eliminacion por el introducido en el metodo.
	 * @param fechaEliminacion {@link LocalDate} - fecha de eliminacion nueva.
	 */
	public void setFechaEliminacion(LocalDate fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}
	
	/**
	 * Modifica el valor de la fecha de eliminacion texto por el introducido en el metodo.
	 * @param fechaEliminacion {@link String} - fecha de eliminacion nueva.
	 */
	public void setFechaEliminacionTexto(String fechaEliminacion) {
		this.fechaEliminacionTexto = fechaEliminacion;
	}

	/**
	 * Devuelve la causa de eliminacion de la incidencia.
	 * @return {@link String} - causa de eliminacion actual.
	 */
	public String getCausaEliminacion() {
		return causaEliminacion;
	}

	/**
	 * Modifica el valor de la causa de eliminacion por el introducido en el metodo.
	 * @param causaEliminacion {@link String} - causa de eliminacion nueva.
	 */
	public void setCausaEliminacion(String causaEliminacion) {
		this.causaEliminacion = causaEliminacion;
	}

	/**
	 * Devuelve la fecha de resolucion de la incidencia.
	 * @return {@link LocalDate} - fecha de resolucion actual.
	 */
	public LocalDate getFechaResolucion() {
		return fechaResolucion;
	}

	/**
	 * Modifica el valor de la fecha de resolucion de la incidencia.
	 * @param fechaResolucion {@link LocalDate} - fecha de resolucion nueva.
	 */
	public void setFechaResolucion(LocalDate fechaResolucion) {
		this.fechaResolucion = fechaResolucion;
	}
	
	/**
	 * Modifica el valor de la fecha de resolucion texto de la incidencia.
	 * @param fechaResolucion {@link String} - fecha de resolucion nueva.
	 */
	public void setFechaResolucionTexto(String fechaResolucion) {
		this.fechaResolucionTexto = fechaResolucion;
	}

	public String getFechaResolucionTexto() {
		return fechaResolucionTexto;
	}

	/**
	 * Devuelve la resolucion de la incidencia.
	 * @return {@link String} - resolucion actual.
	 */
	public String getResolucion() {
		return resolucion;
	}

	/**
	 * Modifica el valor de la resolucion por el introducido en el metodo.
	 * @param resolucion {@link String} - resolucion nueva.
	 */
	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	/**
	 * Devuelve el valor del contador del numero de registro de incidencias
	 * @return {@link Integer} - contador actual
	 */
	public static int getContadorNumRegistro() {
		return contadorNumRegistro;
	}

	/**
	 * Modifica el valor del contador del numero de registro por el introducido en el metodo.
	 * @param contadorNumRegistro {@link Integer} - contador del numero de registro nuevo.
	 */
	public static void setContadorNumRegistro(int contadorNumRegistro) {
		Incidencia.contadorNumRegistro = contadorNumRegistro;
	}
	
	public String getCodigo() {
		final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
		
		String tempFechaMostrada = formatoFecha.format(getFechaRegistro());
		String tempHoraMostrada = formatoHora.format(getHoraRegistro());
		
		return tempFechaMostrada+"-"+tempHoraMostrada+"-"+getNumRegistro();
	}
	
	public void setCodigo(String codigo) {
		final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		final DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
		
		setFechaRegistro(LocalDate.parse(codigo.subSequence(0, 10), formatoFecha));
		setFechaRegistroTexto(codigo.substring(0,10));
		setHoraRegistro(LocalTime.parse(codigo.subSequence(11, 16), formatoHora));
		setHoraRegistroTexto(codigo.substring(11, 16));
		setNumRegistro((Integer.valueOf(codigo.substring(17, codigo.length()))));
	}
	
	/**
	 * Muestra toda la informacion disponible de la incidencia, dependiendo del estado en el que se encuentre
	 * mostrara solo la informacion de la incidencia, la fecha y causa de eliminacion si su estado es ELIMINADA,
	 * fecha de resolucion y resolucion si su estado es RESUELTA.
	 * @return 
	 */
	@Override
	public String toString() {
		final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String tempFechaMostrada = formatoFecha.format(getFechaRegistro());
		String datosIncidencia = "\nCódigo: "+tempFechaMostrada+"-"+getHoraRegistro()+"-"+getNumRegistro()+
				"\nEstado: "+getEstado() + "\nPuesto: "+getNumPuesto() + "\nProblema: "+getProblema() + "\n";
		
		if(getEstado() == Estado.ELIMINADA) {
			String tempFechaEliminada = formatoFecha.format(getFechaEliminacion());			
			datosIncidencia += "Fecha de eliminación: "+tempFechaEliminada + "\nCausa de eliminación: "+getCausaEliminacion() + "\n";
		}
		else if(getEstado() == Estado.RESUELTA) {
			String tempFechaResolucion = formatoFecha.format(getFechaResolucion());			
			datosIncidencia += "Fecha resolución: "+tempFechaResolucion + "\nResolución: "+getResolucion() + "\n";
		}
		return datosIncidencia;
	}
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(fechaRegistro, horaRegistro, numRegistro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incidencia other = (Incidencia) obj;
		return Objects.equals(fechaRegistro, other.fechaRegistro) && Objects.equals(horaRegistro, other.horaRegistro)
				&& numRegistro == other.numRegistro;
	}

	/**
	 * @param incidenciaComparada {@link Incidencia} - Incidencia con la que se compara dentro del metodo.
	 */
	@Override
	public int compareTo(Incidencia incidenciaComparada) {
		if(getFechaRegistro().isBefore(incidenciaComparada.getFechaRegistro())) {
			return -1;
		}
		else if(getFechaRegistro().isAfter(incidenciaComparada.getFechaRegistro())) {
			return 1;
		}
		else {
			if(getHoraRegistro().isBefore(incidenciaComparada.getHoraRegistro())) {
				return -1;
			}
			else if(getHoraRegistro().isAfter(incidenciaComparada.getHoraRegistro())) {
				return 1;
			}
			else {
				if(getNumRegistro() < incidenciaComparada.getNumRegistro()) {
					return -1;
				}
				else if(getNumRegistro() > incidenciaComparada.getNumRegistro()) {
					return 1;
				}
				else {
					return 0;
				}
			}
		}
	}
}
