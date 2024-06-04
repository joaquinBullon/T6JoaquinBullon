package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dominio.Estado;
import dominio.Incidencia;


/**
 * Clase que gestiona los objetos IncidenciaDAO.
 * @author Joaquin Bullon Gonzalez
 * @version 1.0 26/06/2024
 */
public class IncidenciaDAO {
		private Connection conexion;

	/**
	 * Contructor que lo que hace es conectar con la base de datos
	 */
		public IncidenciaDAO(){
			conexion = conectar();
		}
		/**
		 * Metodo para conectar con la base de datos
		 * @return Connection
		 */
		public Connection conectar() {
			Connection con = null;
			String url ="jdbc:mysql://localhost/incidencias";
			try {
				con =  DriverManager.getConnection(url,"joaquin","12345");
				
			} catch (SQLException e) {
				System.out.println("Error al conectar con la BD");
			}
			return con;
			
		}
		
		/**
		 * Metodo para cerrar la conexión la base de datos
		 */
		public void cerrarConexion(){
			try {
				conectar().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Metodo que busca una incidencia pendiente
		 * @param codigoBuscar
		 * @return incidencia
		 */
		public Incidencia buscaIncidenciaPendiente(String codigoBuscar) {
			Connection con = conectar();
			Incidencia encontrada = new Incidencia();
			
			String sql = "SELECT * FROM pendientes WHERE Codigo = ?";
			
		try(PreparedStatement sentencia = con.prepareStatement(sql)) {
			
			sentencia.setString(1, codigoBuscar);
			
			
				ResultSet rs = sentencia.executeQuery();
				if (rs.next()) {
					
					String codigo = rs.getString("Codigo");
					encontrada.setCodigo(codigo);
					encontrada.setEstado(Estado.PENDIENTE);
					int puesto = rs.getInt("Puesto");
					encontrada.setNumPuesto(puesto);
					String problema = rs.getString("Problema");
					encontrada.setProblema(problema);
					
					return encontrada;
				}

			
		} catch (SQLException e) {
			e.getMessage();
			e.getSQLState();
			e.getErrorCode();
		}
			return null;
		}
		/**
		 * Metodo que busca una incidencia eliminada
		 * @param codigoBuscar
		 * @return incidencia
		 */
		public Incidencia buscaIncidenciaEliminada(String codigoBuscar) {
			Connection con = conectar();
			Incidencia encontrada = new Incidencia();
			int numero=0;
			String sql = "SELECT * FROM eliminadas WHERE Codigo = ?";
			
		try(PreparedStatement sentencia = con.prepareStatement(sql)) {
			
			sentencia.setString(1, codigoBuscar);
			
			
				ResultSet rs = sentencia.executeQuery();
				if (rs.next()) {
					
					String codigo = rs.getString("Codigo");
					encontrada.setCodigo(codigo);
					encontrada.setEstado(Estado.ELIMINADA);
					int puesto = rs.getInt("Puesto");
					encontrada.setNumPuesto(puesto);
					String problema = rs.getString("Problema");
					encontrada.setProblema(problema);
					String fecha = rs.getString("Fechadeeliminacion");
					String causa = rs.getString("Causadeeliminacion");
					encontrada.setCausaEliminacion(causa);
					
					return encontrada;
				}

			
		} catch (SQLException e) {
			e.getMessage();
			e.getSQLState();
			e.getErrorCode();
		}
			return null;
		}
		/**
		 * Metodo que busca una incidencia resuelta
		 * @param codigoBuscar
		 * @return incidencia
		 */
		public Incidencia buscaIncidenciaResuelta(String codigoBuscar) {
			Connection con = conectar();
			Incidencia encontrada = new Incidencia();
			int numero=0;
			String sql = "SELECT * FROM resueltas WHERE Codigo = ?";
			
		try(PreparedStatement sentencia = con.prepareStatement(sql)) {
			
			sentencia.setString(1, codigoBuscar);
			
			
				ResultSet rs = sentencia.executeQuery();
				if (rs.next()) {
					
					String codigo = rs.getString("Codigo");
					encontrada.setCodigo(codigo);
					encontrada.setEstado(Estado.RESUELTA);
					int puesto = rs.getInt("Puesto");
					encontrada.setNumPuesto(puesto);
					String problema = rs.getString("Problema");
					encontrada.setProblema(problema);
					String fecha = rs.getString("Fecharesolucion");
					String resolucion = rs.getString("Resolucion");
					encontrada.setResolucion(resolucion);
					
					return encontrada;
				}

			
		} catch (SQLException e) {
			e.getMessage();
			e.getSQLState();
			e.getErrorCode();
		}
			return null;
		}
		/**
		 * Metodo que crea una incidencia en la base de datos
		 * @param incidencia
		 */
		public void create(Incidencia incidencia) {
			Connection con = conectar();
			if(incidencia.getEstado().toString().equalsIgnoreCase("PENDIENTE")) {
				String sql = "INSERT INTO pendientes (Codigo,Estado,Puesto,Problema) VALUES(?,?,?,?) ";
				try (PreparedStatement sentencia = con.prepareStatement(sql)){
					
					sentencia.setString(1, incidencia.getCodigo());
					sentencia.setString(2, incidencia.getEstado().toString());
					sentencia.setInt(3, incidencia.getNumPuesto());
					sentencia.setString(4, incidencia.getProblema());
					sentencia.executeUpdate();
				} catch (SQLException e) {
			        System.out.println("Mensaje de error: " + e.getMessage());
			        System.out.println("Estado SQL: " + e.getSQLState());
			        System.out.println("Codigo de error: " + e.getErrorCode());
				}
			}else if(incidencia.getEstado().toString().equalsIgnoreCase("RESUELTA")){
				String sql = "INSERT INTO resueltas (Codigo,Estado,Puesto,Problema,Fecharesolucion,Resolucion) VALUES(?,?,?,?,?,?) ";
				try (PreparedStatement sentencia = con.prepareStatement(sql)){
					sentencia.setString(1, incidencia.getCodigo());
					sentencia.setString(2, incidencia.getEstado().toString());
					sentencia.setInt(3, incidencia.getNumPuesto());
					sentencia.setString(4, incidencia.getProblema());
					sentencia.setString(5,incidencia.getFechaResolucionTexto());
					sentencia.setString(6, incidencia.getResolucion());
					sentencia.executeUpdate();
				} catch (SQLException e) {
			        System.out.println("Mensaje de error: " + e.getMessage());
			        System.out.println("Estado SQL: " + e.getSQLState());
			        System.out.println("Codigo de error: " + e.getErrorCode());
				}
			}else if(incidencia.getEstado().toString().toString().equalsIgnoreCase("ELIMINADA")){
				String sql = "INSERT INTO eliminadas (Codigo,Estado,Puesto,Problema,Fechadeeliminacion,Causadeeliminacion) VALUES(?,?,?,?,?,?) ";
				try (PreparedStatement sentencia = con.prepareStatement(sql)){
					sentencia.setString(1, incidencia.getCodigo());
					sentencia.setString(2, incidencia.getEstado().toString());
					sentencia.setInt(3, incidencia.getNumPuesto());
					sentencia.setString(4, incidencia.getProblema());
					sentencia.setString(5, incidencia.getFechaEliminacionTexto());
					sentencia.setString(6, incidencia.getCausaEliminacion());
					sentencia.executeUpdate();
				} catch (SQLException e) {
			        System.out.println("Mensaje de error: " + e.getMessage());
			        System.out.println("Estado SQL: " + e.getSQLState());
			        System.out.println("Codigo de error: " + e.getErrorCode());
				}
			}
		}
		
		/**
		 * Metodo qeu elimina una incidecnia en la base de datos
		 * @param codigoBuscar
		 */
		public void delete(String codigoBuscar) {
			Connection con = conectar();
			String sql = "";
			if(buscaIncidenciaPendiente(codigoBuscar)!=null) {
				sql = "DELETE FROM pendientes where Codigo =?";
				try(PreparedStatement sentencia = con.prepareStatement(sql)) {
					sentencia.setString(1, codigoBuscar);	
					sentencia.executeUpdate();					
				} catch (SQLException e) {
					e.getMessage();
					e.getSQLState();
					e.getErrorCode();
				}
				
			}else if(buscaIncidenciaResuelta(codigoBuscar)!=null) {
				sql = "DELETE FROM resueltas where Codigo =?";
				try(PreparedStatement sentencia = con.prepareStatement(sql)) {
					sentencia.setString(1, codigoBuscar);	
					sentencia.executeUpdate();					
				} catch (SQLException e) {
					e.getMessage();
					e.getSQLState();
					e.getErrorCode();
				}
			}else if(buscaIncidenciaEliminada(codigoBuscar)!=null) {
				sql = "DELETE FROM eliminadas where Codigo =?";
				try(PreparedStatement sentencia = con.prepareStatement(sql)) {
					sentencia.setString(1, codigoBuscar);	
					sentencia.executeUpdate();					
				} catch (SQLException e) {
					e.getMessage();
					e.getSQLState();
					e.getErrorCode();
				}
			}
			
		}
		
		/**
		 * Metodo que actualiza la base de datos
		 * @param incidencia
		 */
		public void update(Incidencia incidencia) {
			Connection con = conectar();
			if(incidencia.getEstado().equals(Estado.PENDIENTE)) {
				String sql = "UPDATE pendientes SET Puesto=?,Problema=? WHERE Codigo = ?";
				try(PreparedStatement sentencia = con.prepareStatement(sql)) {
					
					sentencia.setInt(1, incidencia.getNumPuesto());
					sentencia.setString(2, incidencia.getProblema());
					sentencia.setString(3, incidencia.getCodigo());
					sentencia.executeUpdate();			
				} catch (SQLException e) {
					e.getMessage();
					e.getSQLState();
					e.getErrorCode();
				}
			}else if(incidencia.getEstado().equals(Estado.RESUELTA)) {
				String sql = "UPDATE resueltas SET Fecharesolucion=?,Resolucion=? WHERE Codigo = ?";
				try(PreparedStatement sentencia = con.prepareStatement(sql)) {
					sentencia.setString(1, incidencia.getFechaResolucionTexto());
					sentencia.setString(2, incidencia.getResolucion());
					sentencia.setString(3, incidencia.getCodigo());
					sentencia.executeUpdate();			
				} catch (SQLException e) {
					e.getMessage();
					e.getSQLState();
					e.getErrorCode();
				}
			}else if(incidencia.getEstado().equals(Estado.ELIMINADA)) {
				String sql = "UPDATE eliminadas SET Fechadeeliminacion=?,Causadeeliminacion=? WHERE Codigo = ?";
				try(PreparedStatement sentencia = con.prepareStatement(sql)) {
					sentencia.setString(1, incidencia.getFechaResolucionTexto());
					sentencia.setString(2, incidencia.getCausaEliminacion());
					sentencia.setString(3, incidencia.getCodigo());
					sentencia.executeUpdate();			
				} catch (SQLException e) {
					e.getMessage();
					e.getSQLState();
					e.getErrorCode();
				}
			}
		}
		
		
		/**
		 * Metodo que convierte todos los registros de la tabla pendientes en un listado
		 * @return Lista pendientes
		 */
		public List<Incidencia> listarIncidenciasPendientes(){
			Connection con = conectar();
			String sql = "SELECT * FROM pendientes";
			Incidencia nueva = new Incidencia();
			List<Incidencia> lista = new ArrayList<>();
			try(PreparedStatement sentencia = con.prepareStatement(sql);
					ResultSet rs = sentencia.executeQuery(sql)) {
				while (rs.next()) {
					String codigo = rs.getString("Codigo");
					nueva.setCodigo(codigo);
					nueva.setEstado(Estado.PENDIENTE);
					int puesto = rs.getInt("Puesto");
					nueva.setNumPuesto(puesto);
					String problema = rs.getString("Problema");
					nueva.setProblema(problema);
					
					lista.add(nueva);
				}
				
			} catch (SQLException e) {
				e.getMessage();
				e.getSQLState();
				e.getErrorCode();
			}
			
			return lista;
		}
		/**
		 * Metodo que convierte todos los registros de la tabla resueltas en un listado
		 * @return Lista resueltas
		 */
		public List<Incidencia> listarIncidenciasResueltas(){
			final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			Connection con = conectar();
			String sql = "SELECT * FROM resueltas";
			Incidencia nueva = new Incidencia();
			List<Incidencia> lista = new ArrayList<>();
			try(PreparedStatement sentencia = con.prepareStatement(sql);
					ResultSet rs = sentencia.executeQuery(sql)) {
				while (rs.next()) {
					String codigo = rs.getString("Codigo");
					nueva.setCodigo(codigo);
					nueva.setEstado(Estado.RESUELTA);
					int puesto = rs.getInt("Puesto");
					nueva.setNumPuesto(puesto);
					String problema = rs.getString("Problema");
					nueva.setProblema(problema);
					String fecha = rs.getString("Fecharesolucion");
					nueva.setFechaResolucion(LocalDate.parse(fecha, formatoFecha));
					nueva.setFechaResolucionTexto(fecha);
					String resolucion = rs.getString("Resolucion");
					nueva.setResolucion(resolucion);
					
					lista.add(nueva);
				}
				
			} catch (SQLException e) {
				e.getMessage();
				e.getSQLState();
				e.getErrorCode();
			}
			
			return lista;
		}
		/**
		 * Metodo que convierte todos los registros de la tabla eliminadas en un listado
		 * @return Lista eliminadas
		 */
		public List<Incidencia> listarIncidenciasEliminadas(){
			final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			Connection con = conectar();
			String sql = "SELECT * FROM eliminadas";
			Incidencia nueva = new Incidencia();
			List<Incidencia> lista = new ArrayList<>();
			try(PreparedStatement sentencia = con.prepareStatement(sql);
					ResultSet rs = sentencia.executeQuery(sql)) {
				while (rs.next()) {
					String codigo = rs.getString("Codigo");
					nueva.setCodigo(codigo);
					nueva.setEstado(Estado.ELIMINADA);
					int puesto = rs.getInt("Puesto");
					nueva.setNumPuesto(puesto);
					String problema = rs.getString("Problema");
					nueva.setProblema(problema);
					String fecha = rs.getString("Fechadeeliminacion");
					nueva.setFechaEliminacion(LocalDate.parse(fecha, formatoFecha));
					nueva.setFechaEliminacionTexto(fecha);
					String causa = rs.getString("Causadeeliminacion");
					nueva.setCausaEliminacion(causa);
					
					lista.add(nueva);
				}
				
			} catch (SQLException e) {
				e.getMessage();
				e.getSQLState();
				e.getErrorCode();
			}
			
			return lista;
		}
}

