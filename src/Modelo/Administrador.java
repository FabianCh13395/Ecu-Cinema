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
public class Administrador extends Usuario{
    private String Contraseña;
    private byte FotoA;

    public Administrador() {
    }
    

    public Administrador(String Contraseña, byte FotoA) {
        this.Contraseña = Contraseña;
        this.FotoA = FotoA;
    }

    public Administrador(String Contraseña, byte FotoA, String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
        super(Cedula, Nombre, Telefono, Apellido, Correo);
        this.Contraseña = Contraseña;
        this.FotoA = FotoA;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public byte getFotoA() {
        return FotoA;
    }

    public void setFotoA(byte FotoA) {
        this.FotoA = FotoA;
    }
    
}
