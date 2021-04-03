/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.postgresql.util.Base64;

/**
 *
 * @author a-adr_000
 */
public class ModeloCliente extends Cliente{
    Conexion_Ecu_Cinema con= new Conexion_Ecu_Cinema();

    public ModeloCliente() {
    }

    public ModeloCliente(String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
        super(Cedula, Nombre, Telefono, Apellido, Correo);
    }

    public ModeloCliente(String Cedula, String Nombre, String Telefono, String Apellido, String Correo, Date fecha_nacimiento) {
        super(Cedula, Nombre, Telefono, Apellido, Correo, fecha_nacimiento);
    }
    
      public boolean grabarUsuario(){
        String sql;
        sql = "INSERT INTO usuario (cedula,nombre,apellido,telefono,correo,fecha_nacimiento)";
        sql += "VALUES('" + getCedula()+ "','" + getNombre() + "','" + getApellido() + "','" + getTelefono()+ "','" + getCorreo()+ "','" + getFecha_nacimiento() + "')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }
    }
      public boolean grabarCliente(){
    
        String sql;
          sql = "INSERT INTO cliente (id_cliente,cedula)";
        sql += "VALUES('" + getCedula()+ "','" + getCedula()+ "')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }
    }
      public boolean actualizarUsuario(){
          String sql;
          sql = "UPDATE usuario set nombre='" + getNombre() + "',apellido='" + getApellido() + "',telefono='" + getTelefono() + "',correo='" + getCorreo() + "',fecha_nacimiento='" + getFecha_nacimiento()+"'";
        sql += " WHERE cedula='" + getCedula() + "'";

        if (con.noqueryActualizar(sql) == null) {
            return true;
        } else {
            return false;
        }
      }
      public boolean eliminarCliente(){
          String sql;
        sql = "DELETE FROM cliente where id_cliente='" + getCedula() + "'";
        System.out.println(sql);
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }
      }
      public boolean  eliminarUsuario(){
          String sql;
        sql = "DELETE FROM usuario where id_cliente='" + getCedula() + "'";
        System.out.println(sql);
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }
      }
      
}
