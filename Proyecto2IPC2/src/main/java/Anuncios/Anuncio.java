package Anuncios;

import java.sql.Date;
import java.time.LocalDateTime;

public class Anuncio {
    //1 va a ser true
    private int estado;
    private String tipo;
    private String vigencia;
    private java.sql.Date fechaInicio;
    private java.sql.Date fechaFin;
    private String nombreUsuario;

    public Anuncio(String tipo) {
        this.tipo = tipo;
    }

    public Anuncio(int estado, String tipoAnuncio) {
        this.estado = estado;
        this.tipo = tipoAnuncio;
    }
    
    
    
    
    public void calcularFechas(String vigenciaAnuncio, java.sql.Date fechaCompra) {
        System.out.println("Fecha ingresada: " + fechaCompra);
        //LocalDateTime now = LocalDateTime.now();
        //this.fechaInicio = java.sql.Date.valueOf(now.toLocalDate());
        this.fechaInicio = fechaCompra;
        
        int diasPorAñadir;
        switch (vigenciaAnuncio) {
            case "UNDIA":
                diasPorAñadir = 1;
                break;
            case "TRESDIAS":
                diasPorAñadir = 3;
                break;
            case "UNASEMANA":
                diasPorAñadir = 7;
                break;
            case "DOSSEMANAS":
                diasPorAñadir = 14;
                break;    
            default:
                throw new AssertionError();
        }
        
        //fechaFin = java.sql.Date.valueOf(now.toLocalDate().plusDays(diasPorAñadir));
        fechaFin = java.sql.Date.valueOf(fechaCompra.toLocalDate().plusDays(diasPorAñadir));
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }


    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    
    
}
