package zona_fit.conexion;
import java.sql.Connection;
import java.sql.DriverManager;

//retorna un objeto Connection
public class Conexion {

    public static Connection getConexion() {
        //la variable inicializa en null , si algo falla retornara null
        Connection conexion = null;

        //nombre de la base de datos , url, usuario y contraseña
        var baseDatos = "zona_fit_db";
        var url = "jdbc:mysql://localhost:3306/" + baseDatos;
        var usuario = "root";
        var password = "root";


        try {
            //Carga dinámicamente el driver de MySQL en memoria. Sin esto, Java no sabe cómo hablar con MySQL.
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Intenta abrir la conexión real con la base de datos usando la URL y credenciales.
            conexion = DriverManager.getConnection(url, usuario, password);
        } catch (Exception e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        //retorna la conexion
        return conexion;
    }

    public static void main(String[] args) {
        var conexion = Conexion.getConexion();
        if (conexion != null) {
            System.out.println("Conexión exitosa a la base de datos" + conexion);
        } else {
            System.out.println("Error al conectar a la base de datos");
        }
    }
}
