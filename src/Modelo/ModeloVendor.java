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
public class ModeloVendor  extends Vendedor{

    public ModeloVendor() {
    }

    public ModeloVendor(String ContraseñaV, byte FotoV) {
        super(ContraseñaV, FotoV);
    }

    public ModeloVendor(String ContraseñaV, byte FotoV, String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
        super(ContraseñaV, FotoV, Cedula, Nombre, Telefono, Apellido, Correo);
    }
    
}
