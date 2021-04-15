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
public class ModeloPelicula extends Pelicula{
    Conexion_Ecu_Cinema con= new Conexion_Ecu_Cinema();

    public ModeloPelicula() {
    }

    public ModeloPelicula(String IdPelicula, String Descripcion, String Clasificacion, String Genero, int Duracion, String Titulo, String Estado, Image foto) {
        super(IdPelicula, Descripcion, Clasificacion, Genero, Duracion, Titulo, Estado, foto);
    }
    
    public String GeneraridPelicula() {
        String id = "";
        try {
            String sql;
            sql = "Select max(id_pelicula)from pelicula";
            ResultSet dato = con.query(sql);

            if (dato.next()) {
                id = dato.getString(1);
            }
            System.out.println(id);
            int suf;
            suf = Integer.parseInt(id.split("p-")[1]);
            suf += 1;
            System.out.println(suf);
            if (suf >= 10000) {
                id = "p-" + suf;
            } else if (suf >= 1000) {
                id = "p-0" + suf;
            } else if (suf >= 100) {
                id = "p-00" + suf;
            } else if (suf >= 10) {
                id = "p-000" + suf;
            } else if (suf >= 0) {
                id = "p-0000" + suf;
            }
            System.out.println("Nuevo: " + id);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPelicula.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex);
            System.out.println(ex);
            id = "p-00000";
        }
        return id;
    }
    
   private boolean GrabarPelicula(){
        String foto64 = null;
        //transformar imagen a base 64 para postgres
        ByteArrayOutputStream byt = new ByteArrayOutputStream();
        try {
            BufferedImage img = imgBuffered(getFoto());
            ImageIO.write(img, "PNG", byt);
            byte[] bgt = byt.toByteArray();
            foto64 = Base64.encodeBytes(bgt);
        } catch (IOException ex) {
            Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
       String sql;
        sql = "INSERT INTO pelicula (id_pelicula,disponibilidad,genero,clasificacion,titulo,duracion,foto)";
        sql += "VALUES('" + GeneraridPelicula()+ "','" + getEstado() + "','" + getGenero()+ "','" + getClasificacion()+ "','" + getTitulo()+ "','" + getDuracion()+"','"+ foto64+"')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }
   }
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
