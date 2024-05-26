package presentacion;

/**
 * Contiene el metodo encargado de mostrar el menu de la aplicacion.
 * @author Joaquin Bullon Gonzalez
 * @version 2.0 14/04/2024
 */
public class Menu {

	/**
	 * Muestra por consola el menu de la aplicacion.
	 */
	public static void mostrarMenu() {
		System.out.println("1. Registrar incidencia");
		System.out.println("2. Buscar incidencia");
		System.out.println("3. Modificar incidencia");
		System.out.println("4. Eliminar incidencia");
		System.out.println("5. Resolver incidencia");
		System.out.println("6. Modificar incidencia resuelta");
		System.out.println("7. Devolver incidencia resuelta");
		System.out.println("8. Mostrar incidencias pendientes");
		System.out.println("9. Mostrar incidencias resueltas");
		System.out.println("10. Mostrar incidencias eliminadas");
		System.out.println("11. Exportar XML");
		System.out.println("\t1. Exportar pendientes");
		System.out.println("\t2. Exportar resueltas");
		System.out.println("\t3. Exportar eliminadas");
		System.out.println("12. Salir");
	}
	
	/**
	 * Muestra por consola el submenu para exportar a xml
	 */
	public static void mostrarSubMenu() {
		System.out.println("Exportar XML");
		System.out.println("\t1. Exportar pendientes");
		System.out.println("\t2. Exportar resueltas");
		System.out.println("\t3. Exportar eliminadas");
	}
}
