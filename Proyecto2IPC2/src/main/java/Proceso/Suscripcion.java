package Proceso;

import Usuarios.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Suscripcion extends Proceso{
    
    public Suscripcion(Usuario usuario, Connection connection) {
        super(usuario, connection);
    }
    
    public void suscribir(java.sql.Date fechaSuscripcion, HttpServletRequest request) {
        
        String comandoSuscribir = "INSERT INTO suscribir (nombre_usuario, numero_revista, fecha_creacion) "
                + "VALUES ('" + usuario.getNombreUsuario() + "', '" + revista.getNumeroRevista() + "', '" + fechaSuscripcion + "');";
        
        try {
            Statement statementInsert = connection.createStatement();
            statementInsert = connection.createStatement();
            statementInsert.executeUpdate(comandoSuscribir);
            request.setAttribute("mensajeSuscripcion", "SUSCRIPCION CORRECTA");
            
            
        } catch (SQLException e) {
            request.setAttribute("mensajeSuscripcion", "ERROR AL SUSCRIBIR");
            System.out.println("Error suscribiendo");
            e.printStackTrace();
        }
    }
}
