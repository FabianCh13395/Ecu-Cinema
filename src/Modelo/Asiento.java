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
public class Asiento {
    private String IdAsiento;
    private String NombreAsiento;
    private boolean Disponibilidad;

    public Asiento(String IdAsiento, String NombreAsiento, boolean Disponibilidad) {
        this.IdAsiento = IdAsiento;
        this.NombreAsiento = NombreAsiento;
        this.Disponibilidad = Disponibilidad;
    }

    public Asiento() {
    }

    public String getIdAsiento() {
        return IdAsiento;
    }

    public void setIdAsiento(String IdAsiento) {
        this.IdAsiento = IdAsiento;
    }

    public String getNombreAsiento() {
        return NombreAsiento;
    }

    public void setNombreAsiento(String NombreAsiento) {
        this.NombreAsiento = NombreAsiento;
    }

    public boolean isDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(boolean Disponibilidad) {
        this.Disponibilidad = Disponibilidad;
    }
    
    
}
