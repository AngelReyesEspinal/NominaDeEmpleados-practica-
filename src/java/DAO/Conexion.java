
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
    public  Connection conectar() throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/Empleados", "root", "");
        } catch (ClassNotFoundException ex) {
            return null;
        } finally {
        
        }
    }
}
