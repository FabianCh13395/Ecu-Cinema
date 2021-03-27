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
public class ModeloSala extends Sala{

    public ModeloSala() {
    }

    public ModeloSala(String IdSala, int TotalAsientos, String NombreSala, int vacante) {
        super(IdSala, TotalAsientos, NombreSala, vacante);
    }
    
}
