/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author a-adr_000
 */
public class Pelicula {
    private String IdPelicula;
    private String Descripcion;
    private String Clasificacion;
    private String Genero;
    private String Duracion;
    private String Estado;

    
    public Pelicula() {
    }

    public Pelicula(String IdPelicula, String Descripcion, String Clasificacion, String Genero, String Duracion, String Estado) {
        this.IdPelicula = IdPelicula;
        this.Descripcion = Descripcion;
        this.Clasificacion = Clasificacion;
        this.Genero = Genero;
        this.Duracion = Duracion;
        this.Estado = Estado;
    }

    public String getIdPelicula() {
        return IdPelicula;
    }

    public void setIdPelicula(String IdPelicula) {
        this.IdPelicula = IdPelicula;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getClasificacion() {
        return Clasificacion;
    }

    public void setClasificacion(String Clasificacion) {
        this.Clasificacion = Clasificacion;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String Duracion) {
        this.Duracion = Duracion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
    
}
