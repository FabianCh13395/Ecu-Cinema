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
public class Usuario {
    private String Cedula;
    private String Nombre;
    private String Telefono;
    private String Apellido;
    private String Correo;
    private Date fecha_nacimiento;

    public Usuario() {
    }

    public Usuario(String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Telefono = Telefono;
        this.Apellido = Apellido;
        this.Correo = Correo;
    }

    public Usuario(String Cedula, String Nombre, String Telefono, String Apellido, String Correo, Date fecha_nacimiento) {
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Telefono = Telefono;
        this.Apellido = Apellido;
        this.Correo = Correo;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    

    public String getCedula() {
        return Cedula;
    }

    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
    
    
}
