/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author a-adr_000
 */
public class Cliente extends Usuario{

    public Cliente() {
    }

    public Cliente(String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
        super(Cedula, Nombre, Telefono, Apellido, Correo);
    }

    public Cliente(String Cedula, String Nombre, String Telefono, String Apellido, String Correo, Date fecha_nacimiento) {
        super(Cedula, Nombre, Telefono, Apellido, Correo, fecha_nacimiento);
    }
    
    
}
