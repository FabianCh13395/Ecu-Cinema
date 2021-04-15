

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author a-adr_000
 */
public class Funcion {
    private String IdFuncion;
    private Date Fecha;
    private boolean disponibilidad;
    private LocalTime HoraInicio;
    private LocalTime HoraFin;
    private Sala   s;
    private Pelicula p;

    public Funcion() {
    }

    public Funcion(LocalTime HoraInicio, LocalTime HoraFin, Sala s, Pelicula p) {
        this.HoraInicio = HoraInicio;
        this.HoraFin = HoraFin;
        this.s = s;
        this.p = p;
    }

  

    public Funcion(Sala s) {
        this.s = s;
    }

    public Funcion(String IdFuncion, Date Fecha, boolean disponibilidad, LocalTime HoraInicio, LocalTime HoraFin) {
        this.IdFuncion = IdFuncion;
        this.Fecha = Fecha;
        this.disponibilidad = disponibilidad;
        this.HoraInicio = HoraInicio;
        this.HoraFin = HoraFin;
    }

    public String getIdFuncion() {
        return IdFuncion;
    }

    public void setIdFuncion(String IdFuncion) {
        this.IdFuncion = IdFuncion;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public LocalTime getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(LocalTime HoraInicio) {
        this.HoraInicio = HoraInicio;
    }

    public LocalTime getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(LocalTime HoraFin) {
        this.HoraFin = HoraFin;
    }

    @Override
    public String toString() {
        return  String.format("%15s",p.getTitulo()) +String.format("%25s",s.getNombreSala()) +String.format("%40s",HoraInicio) + String.format("%30s", HoraFin)  ;
    }

    
    
    
}
