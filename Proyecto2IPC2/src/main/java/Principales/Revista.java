package Principales;

import Usuarios.Editor;
import Usuarios.Usuario;
import java.sql.Date;
import java.util.ArrayList;

public class Revista {
    private int numeroRevista;
    
    private String descripcion;
    private String archivoRevista;
    private String nombreAutor;
    private String Categoria;
    private String tagsString;
    private String[] tags;
    private int costoSuscripcion;
    private int meGustas;
    private String comentario;
    private String usuarioQueComento;
    private String usuarioQueSeSuscribio;
    private java.sql.Date fechaProceso;
    private boolean usuarioSuscrito;
    private boolean usuarioYaMeGusta;
    private ArrayList<String> comentarios;
    private int occurrences;

    public Revista(String nombreAutor, String archivoRevista, String descripcion, String Categoria, String tagsString) {
        this.archivoRevista = archivoRevista;
        this.descripcion = descripcion;
        this.nombreAutor = nombreAutor;
        this.Categoria = Categoria;
        this.tagsString = tagsString;
        this.comentarios = new ArrayList<String>();
    }

    public Revista(int numeroRevista, String descripcion, String nombreAutor, String Categoria, String tagsString, int meGustas) {
        this.numeroRevista = numeroRevista;
        this.descripcion = descripcion;
        this.nombreAutor = nombreAutor;
        this.Categoria = Categoria;
        this.tagsString = tagsString;
        this.meGustas = meGustas;
    }

    public Revista(int numeroRevista) {
        this.numeroRevista = numeroRevista;
    }
    
    @Override
    public String toString() {
        return "Revista{" + "descripcion=" + descripcion + ", archivoRevista=" + archivoRevista + ", nombreAutor=" + nombreAutor + ", Categoria=" + Categoria + ", tagsString=" + tagsString + '}';
    }

    public int getNumeroRevista() {
        return numeroRevista;
    }

    public void setNumeroRevista(int numeroRevista) {
        this.numeroRevista = numeroRevista;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getArchivoRevista() {
        return archivoRevista;
    }

    public void setArchivoRevista(String archivoRevista) {
        this.archivoRevista = archivoRevista;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String Categoria) {
        this.Categoria = Categoria;
    }

    public String getTagsString() {
        return tagsString;
    }

    public void setTagsString(String tagsString) {
        this.tagsString = tagsString;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getCostoSuscripcion() {
        return costoSuscripcion;
    }

    public void setCostoSuscripcion(int costoSuscripcion) {
        this.costoSuscripcion = costoSuscripcion;
    }

    public int getMeGustas() {
        return meGustas;
    }

    public void setMeGustas(int meGustas) {
        this.meGustas = meGustas;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isUsuarioSuscrito() {
        return usuarioSuscrito;
    }

    public void setUsuarioSuscrito(boolean usuarioSuscrito) {
        this.usuarioSuscrito = usuarioSuscrito;
    }

    public boolean isUsuarioYaMeGusta() {
        return usuarioYaMeGusta;
    }

    public void setUsuarioYaMeGusta(boolean usuarioYaMeGusta) {
        this.usuarioYaMeGusta = usuarioYaMeGusta;
    }

    public ArrayList<String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<String> comentarios) {
        this.comentarios = comentarios;
    }

    public String getUsuarioQueComento() {
        return usuarioQueComento;
    }

    public void setUsuarioQueComento(String usuarioQueComento) {
        this.usuarioQueComento = usuarioQueComento;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    public String getUsuarioQueSeSuscribio() {
        return usuarioQueSeSuscribio;
    }

    public void setUsuarioQueSeSuscribio(String usuarioQueSeSuscribio) {
        this.usuarioQueSeSuscribio = usuarioQueSeSuscribio;
    }

    public Date getFechaProceso() {
        return fechaProceso;
    }

    public void setFechaProceso(Date fechaProceso) {
        this.fechaProceso = fechaProceso;
    }
    
    
    
}
