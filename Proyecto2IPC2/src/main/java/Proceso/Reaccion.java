package Proceso;

import Principales.Revista;
import Usuarios.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reaccion extends Proceso{

    public Reaccion(Usuario usuario, Connection connection) {
        super(usuario, connection);
    }

    
    
    
    public void comentar(Revista revista, String comentario, HttpServletRequest request) {
        this.registrarFechaRealizacion();
        String comando = "INSERT INTO comentar (nombre_usuario, numero_revista, fecha_creacion, comentario) "
                + "VALUES ('" + usuario.getNombreUsuario() + "', '" + revista.getNumeroRevista() + "', '" + fechaProceso + "', '" + comentario + "');";
        
        try {
            Statement statementInsert = connection.createStatement();
            statementInsert = connection.createStatement();
            statementInsert.executeUpdate(comando);
            request.setAttribute("mensajeComentario", "Comentario hecho SATISFACTORIAMENTE");
        } catch (SQLException e) {
            System.out.println("Error relación comentar");
            request.setAttribute("mensajeComentario", "Comentario hecho INSATISFACTORIAMENTE");
            e.printStackTrace();
        }
        
        
    }
    
    public void darMeGusta(Revista revista) {
        
        
        //Acá comprobar que el usuario no le haya dado me gusta antes, si ya le dió no tendría que darjar mostrar el botón de megusta
        String comandoSuscribir = "UPDATE revista set me_gustas=" + (obtenerTotalMeGustas(revista) + 1) + " "
                + "WHERE numero_revista LIKE " + revista.getNumeroRevista() + ";";
        
        try {
            Statement statementInsert = connection.createStatement();
            statementInsert = connection.createStatement();
            statementInsert.executeUpdate(comandoSuscribir);
            this.registrarFechaRealizacion();
            crearRelacionDarMeGusta(revista);
            //request.setAttribute("mensajeSuscripcion", "SUSCRIPCION CORRECTA");
            
            
        } catch (SQLException e) {
            //request.setAttribute("mensajeSuscripcion", "ERROR AL SUSCRIBIR");
            System.out.println("Error dando Me gusta");
            e.printStackTrace();
        }
    }
    
    private void crearRelacionDarMeGusta(Revista revista) {
        
        String comandoPublicar = "INSERT INTO megusta (nombre_usuario, numero_revista, fecha_creacion) "
                + "VALUES ('" + usuario.getNombreUsuario() + "', '" + revista.getNumeroRevista() + "', '" + fechaProceso + "');";
        
        try {
            Statement statementInsert = connection.createStatement();
            statementInsert = connection.createStatement();
            statementInsert.executeUpdate(comandoPublicar);
        } catch (SQLException e) {
            System.out.println("Error relación dar me gusta");
            e.printStackTrace();
        }
    }
    
    public int obtenerTotalMeGustas (Revista revista) {
        int totalMeGustas = 0;
        
        String comandoNombre = "select * from revista WHERE numero_revista LIKE " + revista.getNumeroRevista() + ";";
        //System.out.println("COmando: " + comandoNombre);
        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comandoNombre);

            if (resultSet.next()) {
                totalMeGustas = resultSet.getInt("me_gustas");
                //System.out.println("Número de me gustas: " + totalMeGustas);
            }

        } catch (SQLException e) {
            System.out.println("Error al extraer me gustas");
            e.printStackTrace();
        }
        
        
        
        return totalMeGustas;
    }

}
