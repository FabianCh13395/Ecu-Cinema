/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Image;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author a-adr_000
 */
public class Administrador extends Usuario{
    private String Contraseña;
    private Image FotoA;
    private int edad;

    public Administrador() {
    }
    public int getEdad() {
        LocalDate fecha_inicial=getFecha_nacimiento().toLocalDate();
        this.edad=Period.between(fecha_inicial, LocalDate.now()).getYears();  
        return edad;
    }

    public Administrador(String Contraseña, Image FotoA) {
        this.Contraseña = Contraseña;
        this.FotoA = FotoA;
    }

    public Administrador(String Cedula, String Nombre, String Telefono, String Apellido, String Correo, Date fecha_nacimiento) {
        super(Cedula, Nombre, Telefono, Apellido, Correo, fecha_nacimiento);
    }

    public Administrador(String Contraseña, Image FotoA, String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
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

    public Image getFotoA() {
        return FotoA;
    }

    public void setFotoA(Image FotoA) {
        this.FotoA = FotoA;
    }
    
}
