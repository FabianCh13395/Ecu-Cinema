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
    private String MetodoPago;
    private double CostoTotal;
    private Cliente c;
    private Boleto b;
    private Vendedor v;
    private Categoria cate;

    public Boleto getB() {
        return b;
    }

    public void setB(Boleto b) {
        this.b = b;
    }

    public Vendedor getV() {
        return v;
    }

    public void setV(Vendedor v) {
        this.v = v;
    }

    public Categoria getCate() {
        return cate;
    }

    public void setCate(Categoria cate) {
        this.cate = cate;
    }

    public Compra() {
    }

    public Compra(String IdCompra, Date Fecha, LocalTime Hora, String MetodoPago, double CostoTotal) {
        this.IdCompra = IdCompra;
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.MetodoPago = MetodoPago;
        this.CostoTotal = CostoTotal;
    }

    public Compra(String IdCompra, LocalTime Hora, String MetodoPago, double CostoTotal, Cliente c) {
        this.IdCompra = IdCompra;
        this.Hora = Hora;
        this.MetodoPago = MetodoPago;
        this.CostoTotal = CostoTotal;
        this.c = c;
    }

    public Cliente getC() {
        return c;
    }

    public void setC(Cliente c) {
        this.c = c;
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

    public String getMetodoPago() {
        return MetodoPago;
    }

    public void setMetodoPago(String MetodoPago) {
        this.MetodoPago = MetodoPago;
    }

   

    public double getCostoTotal() {
        return CostoTotal;
    }

    public void setCostoTotal(double CostoTotal) {
        this.CostoTotal = CostoTotal;
    }
    
}
