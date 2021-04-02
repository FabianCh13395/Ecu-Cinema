package Modelo;

import java.awt.Image;
import java.sql.Date;

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
    private Image FotoV; 

    public Vendedor() {
    }

    public Vendedor(String Cedula, String Nombre, String Telefono, String Apellido, String Correo, Date fecha_nacimiento) {
        super(Cedula, Nombre, Telefono, Apellido, Correo, fecha_nacimiento);
    }

    
    public Vendedor(String ContraseñaV, Image FotoV) {
        this.ContraseñaV = ContraseñaV;
        this.FotoV = FotoV;
    }

    public Vendedor(String ContraseñaV, Image FotoV, String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
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

    public Image getFotoV() {
        return FotoV;
    }

    public void setFotoV(Image FotoV) {
        this.FotoV = FotoV;
    }
    
}

