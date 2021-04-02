/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.postgresql.util.Base64;

/**
 *
 * @author a-adr_000
 */
public class ModeloVendedor  extends Vendedor{
    private static Conexion_Ecu_Cinema con =new Conexion_Ecu_Cinema ();

    public ModeloVendedor() {
    }

    public ModeloVendedor(String ContraseñaV, Image FotoV) {
        super(ContraseñaV, FotoV);
    }

    public ModeloVendedor(String Cedula, String Nombre, String Telefono, String Apellido, String Correo, Date fecha_nacimiento) {
        super(Cedula, Nombre, Telefono, Apellido, Correo, fecha_nacimiento);
    }
    

    public ModeloVendedor(String ContraseñaV, Image FotoV, String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
        super(ContraseñaV, FotoV, Cedula, Nombre, Telefono, Apellido, Correo);
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
    public String GeneraridVendedor(){
        String id="";
        try{
        String sql;
        sql="Select max(id_vendedor)from vendedor";
        ResultSet dato = con.query(sql);
       
            if (dato.next()) {
                id= dato.getString(1);
            }
            System.out.println(id);
            int suf;
            suf = Integer.parseInt(id.split("v-")[1]);
            suf += 1;
            System.out.println(suf);
            if (suf >= 10000) {
                id = "v-" + suf;
            } else if(suf>=1000){
               id="v-0"+suf;
            }
            else if (suf >= 100) {
                id= "v-00" + suf;
            } else if (suf >= 10) {
                id= "v-000" + suf;
            } else if (suf >= 0) {
                id= "v-0000" + suf;
            }
            System.out.println("Nuevo: " + id);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex);
            System.out.println(ex);
            id = "v-00000";
        }
        
      
        return id;
    }
    
    public boolean grabarVendedor(){
        String foto64 = null;
        //transformar imagen a base 64 para postgres
        ByteArrayOutputStream byt = new ByteArrayOutputStream();
        try {
            BufferedImage img = imgBuffered(getFotoV());
            ImageIO.write(img, "PNG", byt);
            byte[] bgt = byt.toByteArray();
            foto64 = Base64.encodeBytes(bgt);
        } catch (IOException ex) {
            Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql;
          sql = "INSERT INTO vendedor (id_vendedor,cedula,contraseña,foto)";
        sql += "VALUES('" + GeneraridVendedor()+ "','" + getCedula() + "','" + getContraseñaV()+"','"+ foto64 + "')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }
    }
    //para transformar la imagen
    private BufferedImage imgBuffered(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage b1 = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gra = b1.createGraphics();
        gra.drawImage(img, 0, 0, null);
        gra.dispose();
        return b1;
    }
    
}
