
package umg.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/db_paqueteria?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root"; // Tu usuario de MySQL
    private static final String PASS = "admin"; // Tu contraseña de MySQL

    public static Connection abrir() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}


