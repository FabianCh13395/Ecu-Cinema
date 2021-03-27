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
public class ModeloAdministrador extends Administrador{

    public ModeloAdministrador() {
    }

    public ModeloAdministrador(String Contraseña, byte FotoA) {
        super(Contraseña, FotoA);
    }

    public ModeloAdministrador(String Contraseña, byte FotoA, String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
        super(Contraseña, FotoA, Cedula, Nombre, Telefono, Apellido, Correo);
    }
    
    
}
