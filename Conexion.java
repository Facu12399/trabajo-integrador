import java.util.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {
	
	public static final String controlador="com.mysql.jdbc.Driver";
	public static final String url="jdbc:mysql://localhost:3306/hospital";
	public static final String usuario="root";
	public static final String clave="Fnm20102017";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar el contolador");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null; 
		try {	
			conexion = DriverManager.getConnection(url, usuario, clave);
			System.out.println("Conexion OK");
		} catch(SQLException e) {
			System.out.println("Error al cargar el contolador");
			e.printStackTrace();
		}
		
		return conexion;
	}
}
