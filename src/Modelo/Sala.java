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
public class Sala {
    private String IdSala;
    private int TotalAsientos;
    private String NombreSala;
    private boolean disponibilidad;

    public Sala() {
    }

    public Sala(String IdSala, int TotalAsientos, String NombreSala, boolean disponibilidad) {
        this.IdSala = IdSala;
        this.TotalAsientos = TotalAsientos;
        this.NombreSala = NombreSala;
        this.disponibilidad = disponibilidad;
    }

    public String getIdSala() {
        return IdSala;
    }

    public void setIdSala(String IdSala) {
        this.IdSala = IdSala;
    }

    public int getTotalAsientos() {
        return TotalAsientos;
    }

    public void setTotalAsientos(int TotalAsientos) {
        this.TotalAsientos = TotalAsientos;
    }

    public String getNombreSala() {
        return NombreSala;
    }

    public void setNombreSala(String NombreSala) {
        this.NombreSala = NombreSala;
    }

    public  boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
}
