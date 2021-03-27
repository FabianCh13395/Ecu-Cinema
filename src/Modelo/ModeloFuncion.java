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
public class ModeloFuncion extends Funcion{

    public ModeloFuncion() {
    }

    public ModeloFuncion(String IdFuncion, Date Fecha, boolean disponibilidad, LocalTime HoraInicio, LocalTime HoraFin) {
        super(IdFuncion, Fecha, disponibilidad, HoraInicio, HoraFin);
    }
    
}
