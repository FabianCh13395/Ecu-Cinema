package Modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a-adr_000
 */
public class Vendedor extends Usuario{
    private String ContraseñaV;
    private byte FotoV; 

    public Vendedor() {
    }

    
    public Vendedor(String ContraseñaV, byte FotoV) {
        this.ContraseñaV = ContraseñaV;
        this.FotoV = FotoV;
    }

    public Vendedor(String ContraseñaV, byte FotoV, String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
        super(Cedula, Nombre, Telefono, Apellido, Correo);
        this.ContraseñaV = ContraseñaV;
        this.FotoV = FotoV;
    }

    public String getContraseñaV() {
        return ContraseñaV;
    }

    public void setContraseñaV(String ContraseñaV) {
        this.ContraseñaV = ContraseñaV;
    }

    public byte getFotoV() {
        return FotoV;
    }

    public void setFotoV(byte FotoV) {
        this.FotoV = FotoV;
    }
    
}

