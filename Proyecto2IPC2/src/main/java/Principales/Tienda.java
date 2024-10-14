package Principales;

import Anuncios.Anuncio;
import Usuarios.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class Tienda {

    private Usuario usuario;
    private Connection connection;
    protected java.sql.Date fechaTransaccion;

    public Tienda(Usuario usuario, Connection connection) {
        this.usuario = usuario;
        this.connection = connection;
    }

    public void extraerCreditos() {
        String comando = "select * FROM usuario WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\";";

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);
            if (resultSet.next()) {
                usuario.setCartera(resultSet.getBigDecimal("cartera"));

            }

        } catch (SQLException e) {
            System.out.println("Error encontrando Creditos");
            e.printStackTrace();
        }
    }

    public boolean recargar(BigDecimal creditosPorComprar) {
        String comandoCrearUsuario = "UPDATE usuario set cartera=" + (usuario.getCartera().add(creditosPorComprar)) + " WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\";";
        boolean recargaRealizada;
        // System.out.println("COmando: " + comandoCrearUsuario);
        try {
            Statement statementInsert = connection.createStatement();
            statementInsert.executeUpdate(comandoCrearUsuario);
            recargaRealizada = true;

        } catch (SQLException e) {
            System.out.println("Error recargando créditos");
            recargaRealizada = false;
            e.printStackTrace();
        }

        usuario.setCartera(usuario.getCartera().add(creditosPorComprar));
        return recargaRealizada;
    }

    public boolean comprarAnuncio(String tipoAnuncio, String vigenciaAnuncio, java.sql.Date fechaCompra, HttpServletRequest request) {
        //Nombre usuario, tipo anuncio, anuncio activado = true, fecha inicio y fecha fin
        int intVigenciaAnuncio = 0;

        switch (vigenciaAnuncio) {
            case "UNDIA":
                intVigenciaAnuncio = 1;
                break;
            case "TRESDIAS":
                intVigenciaAnuncio = 3;
                break;
            case "UNASEMANA":
                intVigenciaAnuncio = 7;
                break;
            case "DOSSEMANAS":
                intVigenciaAnuncio = 14;
                break;
            default:
                break;
        }

        Anuncio anuncio = new Anuncio(tipoAnuncio);
        anuncio.calcularFechas(vigenciaAnuncio, fechaCompra);

        String comando = "INSERT INTO anunciar (nombre_usuario, tipo_anuncio, estado_anuncio, vigencia_anuncio, fecha_creacion, fecha_fin) " + "VALUES ('"
                + usuario.getNombreUsuario() + "', '" + anuncio.getTipo() + "', '" + 0 + "', '" + intVigenciaAnuncio + "', '"
                + anuncio.getFechaInicio() + "', '" + anuncio.getFechaFin() + "');";

        System.out.println("Comando relacion comprar anuncio: " + comando);
        if (anuncioNoComprado(tipoAnuncio, intVigenciaAnuncio, request)) {
            if (creditosSuficientes(tipoAnuncio, vigenciaAnuncio, request)) {

                try {
                    Statement statementInsert = connection.createStatement();
                    statementInsert = connection.createStatement();
                    statementInsert.executeUpdate(comando);
                    request.setAttribute("mensaje", "ANUNCIO COMPRADO EXITOSAMENTE");
                    return true;
                } catch (SQLException e) {
                    System.out.println("Error creando relación comprar anuncio");
                    e.printStackTrace();
                }

            }
        } else {
            request.setAttribute("mensaje", "ANUNCIO YA COMPRADO");
        }

        return false;
    }

    private boolean anuncioNoComprado(String tipoAnuncio, int intVigenciaAnuncio, HttpServletRequest request) {
        String comando = "SELECT tipo_anuncio, caducado, vigencia_anuncio FROM anunciar WHERE (nombre_usuario=\"" + usuario.getNombreUsuario() + "\" "
                + "AND tipo_anuncio=\"" + tipoAnuncio + "\" AND caducado=0 AND vigencia_anuncio=" + intVigenciaAnuncio + ");";
        System.out.println(comando);

        try {
            Statement statementInsert = connection.createStatement();
            ResultSet resultSet = statementInsert.executeQuery(comando);
            if (!resultSet.next()) {
                System.out.println("Anuncio no comprado");
                return true;

            }

        } catch (SQLException e) {
            System.out.println("Error encontrando Creditos");
            e.printStackTrace();
        }
        System.out.println("Anuncio comprado");
        return false;
    }

    private boolean creditosSuficientes(String tipoAnuncio, String vigenciaAnuncio, HttpServletRequest request) {

        BigDecimal totalDescontar = new BigDecimal(0);

        switch (tipoAnuncio) {
            case "TEXTO":
                totalDescontar = totalDescontar.add(new BigDecimal(10));
                break;
            case "TEXTOeIMAGEN":
                totalDescontar = totalDescontar.add(new BigDecimal(20));
                break;
            case "VIDEO":
                totalDescontar = totalDescontar.add(new BigDecimal(30));
                break;
            default:
                break;
        }

        switch (vigenciaAnuncio) {
            case "UNDIA":
                totalDescontar = totalDescontar.add(new BigDecimal(10));
                break;
            case "TRESDIAS":
                totalDescontar = totalDescontar.add(new BigDecimal(25));
                break;
            case "UNASEMANA":
                totalDescontar = totalDescontar.add(new BigDecimal(50));
                break;
            case "DOSSEMANAS":
                totalDescontar = totalDescontar.add(new BigDecimal(90));
                break;
            default:
                break;
        }

        if (usuario.getCartera().compareTo(totalDescontar) >= 0) {
            usuario.setCartera(usuario.getCartera().subtract(totalDescontar));
            descontarCreditos();
            return true;
        }

        request.setAttribute("mensaje", "ANUNCIO NO COMPRADO, CREDITOS INSUFICIENTES");

        return false;

    }

    private void descontarCreditos() {
        String comandoCrearUsuario = "UPDATE usuario set cartera=" + usuario.getCartera() + " WHERE nombre_usuario LIKE \"" + usuario.getNombreUsuario() + "\";";

        try {
            Statement statementInsert = connection.createStatement();
            statementInsert.executeUpdate(comandoCrearUsuario);

        } catch (SQLException e) {
            System.out.println("Error descontando créditos");
            e.printStackTrace();
        }
    }

    public void registrarFechaTransaccion() {
        LocalDateTime now = LocalDateTime.now();
        fechaTransaccion = java.sql.Date.valueOf(now.toLocalDate());

    }
}
