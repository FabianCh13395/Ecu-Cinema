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
public class ModeloCompra extends Compra{

    public ModeloCompra() {
    }

    public ModeloCompra(String IdCompra, Date Fecha, LocalTime Hora, int MetodoPago, double CostoTotal) {
        super(IdCompra, Fecha, Hora, MetodoPago, CostoTotal);
    }
    
}
