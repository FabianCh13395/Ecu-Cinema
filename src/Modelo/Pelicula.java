/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Image;

/**
 *
 * @author a-adr_000
 */
public class Pelicula {
    private String IdPelicula;
    private String Descripcion;
    private String Clasificacion;
    private String Genero;
    private int Duracion;
    private String Titulo;
    private boolean Estado;
    private Image foto;

    public Pelicula() {
    }

    
     
    
    public Pelicula(String Titulo) {
        this.Titulo = Titulo;
    }

    public boolean getEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public Pelicula(String IdPelicula, String Descripcion, String Clasificacion, String Genero, int Duracion, String Titulo, boolean Estado, Image foto) {
        this.IdPelicula = IdPelicula;
        this.Descripcion = Descripcion;
        this.Clasificacion = Clasificacion;
        this.Genero = Genero;
        this.Duracion = Duracion;
        this.Titulo = Titulo;
        this.Estado = Estado;
        this.foto = foto;
    }

   

    public int getDuracion() {
        return Duracion;
    }

    public void setDuracion(int Duracion) {
        this.Duracion = Duracion;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
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

   

  
    
    
    
}
