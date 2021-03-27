/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.time.LocalTime;

/**
 *
 * @author a-adr_000
 */
public class Compra {
    private String IdCompra;
    private Date Fecha;
    private LocalTime Hora;
    private int MetodoPago;
    private double CostoTotal;

    public Compra() {
    }

    public Compra(String IdCompra, Date Fecha, LocalTime Hora, int MetodoPago, double CostoTotal) {
        this.IdCompra = IdCompra;
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.MetodoPago = MetodoPago;
        this.CostoTotal = CostoTotal;
    }

    public String getIdCompra() {
        return IdCompra;
    }

    public void setIdCompra(String IdCompra) {
        this.IdCompra = IdCompra;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public LocalTime getHora() {
        return Hora;
    }

    public void setHora(LocalTime Hora) {
        this.Hora = Hora;
    }

    public int getMetodoPago() {
        return MetodoPago;
    }

    public void setMetodoPago(int MetodoPago) {
        this.MetodoPago = MetodoPago;
    }

    public double getCostoTotal() {
        return CostoTotal;
    }

    public void setCostoTotal(double CostoTotal) {
        this.CostoTotal = CostoTotal;
    }
    
}
