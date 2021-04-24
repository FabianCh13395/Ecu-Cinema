package Modelo;

import java.awt.Image;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

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
    private String id_vendedor;
    private int edad;

    public Vendedor() {
    }
    public int getEdad() {
        LocalDate fecha_inicial=getFecha_nacimiento().toLocalDate();
        this.edad=Period.between(fecha_inicial, LocalDate.now()).getYears();  
        return edad;
    }
    public String getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(String id_vendedor) {
        this.id_vendedor = id_vendedor;
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

