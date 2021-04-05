/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Modelo.ModeloVendedor.obtenImagen;
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
public class ModeloAdministrador extends Administrador {

    private static Conexion_Ecu_Cinema con = new Conexion_Ecu_Cinema();

    public ModeloAdministrador() {
    }

    public ModeloAdministrador(String Contraseña, Image FotoA) {
        super(Contraseña, FotoA);
    }

    public ModeloAdministrador(String Cedula, String Nombre, String Telefono, String Apellido, String Correo, Date fecha_nacimiento) {
        super(Cedula, Nombre, Telefono, Apellido, Correo, fecha_nacimiento);
    }

    public ModeloAdministrador(String Contraseña, Image FotoA, String Cedula, String Nombre, String Telefono, String Apellido, String Correo) {
        super(Contraseña, FotoA, Cedula, Nombre, Telefono, Apellido, Correo);
    }

    public boolean grabarUsuario() {
        String sql;
        sql = "INSERT INTO usuario (cedula,nombre,apellido,telefono,correo,fecha_nacimiento)";
        sql += "VALUES('" + getCedula() + "','" + getNombre() + "','" + getApellido() + "','" + getTelefono() + "','" + getCorreo() + "','" + getFecha_nacimiento() + "')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }

    }

    public String GeneraridAdministrador() {
        String id = "";
        try {
            String sql;
            sql = "Select max(id_administrador)from administrador";
            ResultSet dato = con.query(sql);

            if (dato.next()) {
                id = dato.getString(1);
            }
            System.out.println(id);
            int suf;
            suf = Integer.parseInt(id.split("a-")[1]);
            suf += 1;
            System.out.println(suf);
            if (suf >= 10000) {
                id = "a-" + suf;
            } else if (suf >= 1000) {
                id = "a-0" + suf;
            } else if (suf >= 100) {
                id = "a-00" + suf;
            } else if (suf >= 10) {
                id = "a-000" + suf;
            } else if (suf >= 0) {
                id = "a-0000" + suf;
            }
            System.out.println("Nuevo: " + id);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloAdministrador.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex);
            System.out.println(ex);
            id = "a-00000";
        }
        return id;
    }

    public boolean grabarAdministtador() {
        String foto64 = null;
        //transformar imagen a base 64 para postgres
        ByteArrayOutputStream byt = new ByteArrayOutputStream();
        try {
            BufferedImage img = imgBuffered(getFotoA());
            ImageIO.write(img, "PNG", byt);
            byte[] bgt = byt.toByteArray();
            foto64 = Base64.encodeBytes(bgt);
        } catch (IOException ex) {
            Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql;
        sql = "INSERT INTO administrador (id_administrador,cedula,contraseña_admi,foto)";
        sql += "VALUES('" + GeneraridAdministrador() + "','" + getCedula() + "','" + getContraseña() + "','" + foto64 + "')";
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

    public Administrador ComprobarCedulaAdministrador() {
        try {
            String sql;
            sql = "Select cedula,contraseña_admi from administrador where cedula='" + getCedula() + "'";
            ResultSet dato = con.query(sql);
            if (dato.next()) {
                Administrador v = new Administrador();
                v.setCedula(dato.getString("cedula"));
                v.setContraseña(dato.getString("contraseña_admi"));
                return v;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloAdministrador.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public String VistaInicioAdministrador() {
        String nombre;
        String apellido;
        try {
            String sql;
            sql = "Select nombre,apellido from usuario where cedula='" + getCedula() + "'";
            ResultSet dato = con.query(sql);

            if (dato.next()) {
                nombre = dato.getString("nombre");
                apellido = dato.getString("apellido");
                return nombre + " " + apellido;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloAdministrador.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public Image traer_foto() {
        try {
            String sql;
            sql = "Select foto from administrador where cedula='" + getCedula() + "'";
            ResultSet dato = con.query(sql);
            byte[] bf;
            if (dato.next()) {

                bf = dato.getBytes("foto");
                if (bf != null) {
                    bf = Base64.decode(bf, 0, bf.length);
                    try {
                        //OBTENER IMAGEN
                        return obtenImagen(bf);
                    } catch (IOException ex) {

                        Logger.getLogger(ModeloAdministrador.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloAdministrador.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }
    
}
