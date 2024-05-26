package aplicacion;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.xml.bind.JAXBException;

import dominio.Incidencia;
import dominio.Listas;
import persistencia.Exportar;
import persistencia.IncidenciaDAO;
import presentacion.Menu;

/**
 * Contiene el metodo main() donde transcurre la aplicacion.
 * @author Joaquin Bullon Gonzalez
 * @version 2.0 14/04/2024
 */
public class Main {

	/**
	 * La aplicacion se ejecuta dentro de este metodo.
	 * @param args {@link String}
	 */
	public static void main(String[] args) {
		IncidenciaDAO incidenciaDAO = new IncidenciaDAO();
		incidenciaDAO.conectar();
		Listas lista = new Listas();
		Scanner teclado = new Scanner(System.in);
		int opcion = 0;
		if(incidenciaDAO.listarIncidenciasPendientes()!=null || incidenciaDAO.listarIncidenciasResueltas()!=null || incidenciaDAO.listarIncidenciasEliminadas()!=null) {
        	lista.setListaPendientes(incidenciaDAO.listarIncidenciasPendientes());
        	lista.setListaResueltos(incidenciaDAO.listarIncidenciasResueltas());
        	lista.setListaEliminados(incidenciaDAO.listarIncidenciasEliminadas());
		}
		
		do {
			Menu.mostrarMenu();
			System.out.println("\nIndica la opción que deseas elegir:");
			opcion = teclado.nextInt();
			
			switch (opcion) {
			case 1:
				int numPuesto;
				String problema;
				
				System.out.println("\nDebes indicar el número de puesto y el problema de tu incidencia a continuación");
				System.out.println("\nIndica el número de puesto:");
				try {
					numPuesto = teclado.nextInt();
					System.out.println("\nIndica el problema a reportar:");
					teclado.nextLine();
					problema = teclado.nextLine();
					incidenciaDAO.create(lista.registrarIncidencia(numPuesto, problema));
					System.out.println("\nSu incidencia ha sido registrada correctamente.");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				} catch (Exception e) {
					teclado.nextLine();
					System.out.println("\nNo se ha podido registrar su incidencia.");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				}				
				break;
			case 2:
				LocalDate fechaRegistro;
				LocalTime horaRegistro;
				int numRegistro;
				
				if(lista.getListaPendientes().isEmpty()) {
					teclado.nextLine();
					System.out.println("\n¡No puedes usar esta función con la lista de pendientes vacía!");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				}
				else {
					try {
						System.out.println("\nDebes indicar fecha, hora y número de registro a continuación:");
						System.out.println("\nIndica una fecha (el formato debe ser dia/mes/año):");
						fechaRegistro = LocalDate.parse(teclado.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
						System.out.println("\nIndica una hora (el formato debe ser horas:minutos):");
						horaRegistro = LocalTime.parse(teclado.next(), DateTimeFormatter.ofPattern("HH:mm"));
						System.out.println("\nIndica el número de registro:");
						numRegistro = teclado.nextInt();
						
						Incidencia tempIncidencia = lista.buscarIncidencia(fechaRegistro, horaRegistro, numRegistro);
						
						if(tempIncidencia != null) {
							System.out.println("\nAquí tiene los datos de su incidencia:");
							System.out.println(tempIncidencia.toString());
							teclado.nextLine();
							System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
							teclado.nextLine();	
						}
						else {
							teclado.nextLine();
							System.out.println("\nNo se ha encontrado su incidencia.");
							System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
							teclado.nextLine();
						}						
					}
					catch(Exception e) {						
						teclado.nextLine();
						System.out.println("\nNo se ha encontrado su incidencia.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}								
				}
				break;
			case 3:
				int numeroIncidencia;
				int numPuesto2;
				String problema2;
				boolean respuesta;
				
				if(lista.getListaPendientes().isEmpty()) {
					teclado.nextLine();
					System.out.println("\n¡No puedes usar esta función con la lista de pendientes vacía!");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				}
				else {
					try {
						System.out.println("\nDebes indicar el número de incidencia que quieres modificar de la lista de incidencias pendientes:");
						numeroIncidencia = teclado.nextInt();
						
						
						System.out.println("\n¿Quieres modificar el número de puesto? (true/false):");
						respuesta = teclado.nextBoolean();
						if(respuesta == true) {
							System.out.println("\nIndica el número de puesto modificado:");
							numPuesto2 = teclado.nextInt();
							incidenciaDAO.delete(lista.getListaPendientes().get(numeroIncidencia).getCodigo());
							incidenciaDAO.create(lista.modificarIncidencia(numeroIncidencia, numPuesto2));
							System.out.println("\n¡Número de puesto modificado correctamente!");
						}
						System.out.println("\n¿Quieres modificar el problema? (true/false):");
						respuesta = teclado.nextBoolean();
						if(respuesta == true) {
							System.out.println("\nIndica el problema modificado:");
							teclado.nextLine();
							problema2 = teclado.nextLine();
							incidenciaDAO.delete(lista.getListaPendientes().get(numeroIncidencia).getCodigo());
							incidenciaDAO.create(lista.modificarIncidencia(numeroIncidencia, problema2));
							System.out.println("\n¡Problema modificado correctamente!");
						}
						else {
							teclado.nextLine();
						}
									
						
						System.out.println("\nIncidencia modificada con éxito.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();	
					}
					catch(Exception e) {					
						teclado.nextLine();
						System.out.println("\nHas introducido un valor no válido.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}
								
				}
				break;
			case 4:
				int numeroIncidencia2;
				String causaEliminacion;
				
				if(lista.getListaPendientes().isEmpty()) {
					teclado.nextLine();
					System.out.println("\n¡No puedes usar esta función con la lista de pendientes vacía!");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				}
				else {					
					try {
						System.out.println("\nDebes indicar el número de incidencia que quieres eliminar de la lista de incidencias pendientes:");
						numeroIncidencia2 = teclado.nextInt();
						
						System.out.println("\nIndica la causa de eliminación:");
						teclado.nextLine();
						causaEliminacion = teclado.nextLine();
						incidenciaDAO.delete(lista.getListaPendientes().get(numeroIncidencia2).getCodigo());
						incidenciaDAO.create(lista.eliminarIncidencia(numeroIncidencia2, causaEliminacion));
						
						System.out.println("\nIncidencia eliminada con éxito.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}
					catch(ArrayIndexOutOfBoundsException e) {						
						teclado.nextLine();
						System.out.println("\nHas introducido un valor no válido.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}
					
				}
				
				break;
			case 5:
				int numeroIncidencia3;
				String resolucion;
				
				if(lista.getListaPendientes().isEmpty()) {
					teclado.nextLine();
					System.out.println("\n¡No puedes usar esta función con la lista de pendientes vacía!");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				}
				else {
					try {
						System.out.println("\nDebes indicar el número de incidencia que quieres resolver de la lista de incidencias pendientes:");
						numeroIncidencia3 = teclado.nextInt();
						
						System.out.println("\nIndica la resolución:");
						teclado.nextLine();
						resolucion = teclado.nextLine();
						incidenciaDAO.delete(lista.getListaPendientes().get(numeroIncidencia3).getCodigo());
						incidenciaDAO.create(lista.resolverIncidencia(numeroIncidencia3, resolucion));
						
						System.out.println("\nIncidencia resuelta con éxito.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}
					catch(ArrayIndexOutOfBoundsException e) {						
						teclado.nextLine();
						System.out.println("\nHas introducido un valor no válido.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}
					
				}				
				break;
			case 6:
				int numeroIncidencia4;
				LocalDate fechaResolucion;
				String resolucion2;
				boolean respuesta2;
				
				if(lista.getListaResueltos().isEmpty()) {
					teclado.nextLine();
					System.out.println("\n¡No puedes usar esta función con la lista de resueltos vacía!");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				}
				else {
					try {
						System.out.println("\nDebes indicar el número de incidencia que quieres modificar de la lista de incidencias resueltas:");
						numeroIncidencia4 = teclado.nextInt();
						
						System.out.println("\n¿Quieres modificar la fecha de resolución? (true/false):");
						respuesta2 = teclado.nextBoolean();
						if(respuesta2 == true) {
							System.out.println("\nIndica la fecha de resolución modificada (el formato debe ser dia/mes/año):");
							fechaResolucion = LocalDate.parse(teclado.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
							incidenciaDAO.delete(lista.getListaResueltos().get(numeroIncidencia4).getCodigo());
							incidenciaDAO.create(lista.modificarIncidenciaResuelta(numeroIncidencia4, fechaResolucion));
							System.out.println("\n¡Fecha de resolución modificada correctamente!");
						}
						System.out.println("\n¿Quieres modificar la resolución? (true/false):");
						respuesta2 = teclado.nextBoolean();
						if(respuesta2 == true) {
							System.out.println("\nIndica la resolución modificada:");
							teclado.nextLine();
							resolucion2 = teclado.nextLine();
							incidenciaDAO.delete(lista.getListaResueltos().get(numeroIncidencia4).getCodigo());
							incidenciaDAO.create(lista.modificarIncidenciaResuelta(numeroIncidencia4, resolucion2));
							System.out.println("\n¡Resolución modificada correctamente!");
						}
						else {
							teclado.nextLine();
						}
									
						
						System.out.println("\nIncidencia modificada con éxito.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();	
					}
					catch(Exception e) {						
						teclado.nextLine();
						System.out.println("\nHas introducido un valor no válido.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}								
				}
				
				break;
			case 7:
				int numeroIncidencia5;
				
				if(lista.getListaResueltos().isEmpty()) {
					teclado.nextLine();
					System.out.println("\n¡No puedes usar esta función con la lista de resueltos vacía!");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				}
				else {
					try {
						System.out.println("\nDebes indicar el número de incidencia que quieres devolver de la lista de resueltos:");
						numeroIncidencia5 = teclado.nextInt();
						
						incidenciaDAO.delete(lista.getListaResueltos().get(numeroIncidencia5).getCodigo());
						incidenciaDAO.create(lista.devolverIncidenciaResuelta(numeroIncidencia5));
						
						teclado.nextLine();
						System.out.println("\nIncidencia devuelta con éxito.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();	
					}
					catch(ArrayIndexOutOfBoundsException e) {						
						teclado.nextLine();
						System.out.println("\nHas introducido un valor no válido.");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}					
				}
				break;
			case 8:
				if(lista.getListaPendientes().isEmpty()) {
					teclado.nextLine();
					System.out.println("\n¡No puedes usar esta función con la lista de pendientes vacía!");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				}
				else {
					System.out.println("\nEstas son las incidencias pendientes:\n");
					lista.mostrarPendientes();
					teclado.nextLine();
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();				
				}
				break;
			case 9:
				if(lista.getListaResueltos().isEmpty()) {
					teclado.nextLine();
					System.out.println("\n¡No puedes usar esta función con la lista de resueltos vacía!");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				}
				else {
					System.out.println("\nEstas son las incidencias resueltas:\n");
					lista.mostrarResueltos();
					teclado.nextLine();
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();				
				}
				break;
			case 10:
				if(lista.getListaEliminados().isEmpty()) {
					teclado.nextLine();
					System.out.println("\n¡No puedes usar esta función con la lista de eliminados vacía!");
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();
				}
				else {
					System.out.println("\nEstas son las incidencias eliminadas:\n");
					lista.mostrarEliminados();
					teclado.nextLine();
					System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
					teclado.nextLine();				
				}
				break;
			case 11:
				System.out.println("\nIndica la opción que deseas elegir del submenú: ");
				Menu.mostrarSubMenu();
				opcion = teclado.nextInt();
				switch(opcion) {
				case 1:
					if(lista.getListaPendientes().isEmpty()) {
						teclado.nextLine();
						System.out.println("\n¡No puedes usar esta función con la lista de pendientes vacía!");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}
					else {
						try {
							Exportar.exportarPendientes(lista.getListaPendientes());
							
							teclado.nextLine();
							System.out.println("\n¡La exportación en formato xml de las listas de incidencias pendientes ha tenido éxito!");
							System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
							teclado.nextLine();
						} catch (JAXBException | IOException e) {
							e.printStackTrace();
						}
					}					
					break;
				case 2:
					if(lista.getListaResueltos().isEmpty()) {
						teclado.nextLine();
						System.out.println("\n¡No puedes usar esta función con la lista de resueltos vacía!");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}
					else {
						try {
							Exportar.exportarResueltas(lista.getListaResueltos());
							
							teclado.nextLine();
							System.out.println("\n¡La exportación en formato xml de las listas de incidencias resueltas ha tenido éxito!");
							System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
							teclado.nextLine();
						} catch (JAXBException | IOException e) {
							teclado.nextLine();
							System.out.println("\nLA EXPORTACION HA FALLADO");
							System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
							teclado.nextLine();
						}
					}					
					break;
				case 3:
					if(lista.getListaEliminados().isEmpty()) {
						teclado.nextLine();
						System.out.println("\n¡No puedes usar esta función con la lista de eliminados vacía!");
						System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
						teclado.nextLine();
					}
					else {
						try {
							Exportar.exportarEliminadas(lista.getListaEliminados());
							
							teclado.nextLine();
							System.out.println("\n¡La exportación en formato xml de las listas de incidencias eliminadas ha tenido éxito!");
							System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
							teclado.nextLine();
						} catch (JAXBException | IOException e) {
							teclado.nextLine();
							System.out.println("\nLA EXPORTACION HA FALLADO");
							System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
							teclado.nextLine();
						}
					}					
					break;
				}
				break;
			case 12:
				teclado.nextLine();
				System.out.println("\nGracias por utilizar nuestro programa.\n¡Pase un buen día!");
				System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
				teclado.nextLine();
				break;
			default:
				teclado.nextLine();
				System.out.println("\nOpción no válida");
				System.out.println("\nPRESIONE ENTER PARA CONTINUAR");
				teclado.nextLine();
				break;
			}
			
		}while(opcion != 12);
		
		incidenciaDAO.cerrarConexion();
		teclado.close();
	}
	
	

}
