/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Image;

/**
 *
 * @author a-adr_000
 */
public class ModeloAdministrador extends Administrador{
     private static Conexion_Ecu_Cinema con =new Conexion_Ecu_Cinema ();

    public ModeloAdministrador() {
    }

    public ModeloAdministrador(String Contraseña, Image FotoA) {
        super(Contraseña, FotoA);
    }

    public ModeloAdministrador(String Contraseña, Image FotoA, String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
        super(Contraseña, FotoA, Cedula, Nombre, Telefono, Apellido, Correo);
    }
    
    
}
