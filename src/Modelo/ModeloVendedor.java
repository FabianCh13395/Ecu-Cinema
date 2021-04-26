/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.postgresql.util.Base64;

/**
 *
 * @author a-adr_000
 */
public class ModeloVendedor extends Vendedor {

    private static Conexion_Ecu_Cinema con = new Conexion_Ecu_Cinema();

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

    public String GeneraridVendedor() {
        String id = "";
        try {
            String sql;
            sql = "Select max(id_vendedor)from vendedor";
            ResultSet dato = con.query(sql);

            if (dato.next()) {
                id = dato.getString(1);
            }
            System.out.println(id);
            int suf;
            suf = Integer.parseInt(id.split("v-")[1]);
            suf += 1;
            System.out.println(suf);
            if (suf >= 10000) {
                id = "v-" + suf;
            } else if (suf >= 1000) {
                id = "v-0" + suf;
            } else if (suf >= 100) {
                id = "v-00" + suf;
            } else if (suf >= 10) {
                id = "v-000" + suf;
            } else if (suf >= 0) {
                id = "v-0000" + suf;
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

    public boolean grabarVendedor() {
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
        sql += "VALUES('" + GeneraridVendedor() + "','" + getCedula() + "','" + getContraseñaV() + "','" + foto64 + "')";
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

    public Vendedor ComprobarCedulaVendedor() {
        try {
            String sql;
            sql = "Select cedula,contraseña from vendedor where cedula='" + getCedula() + "'";
            ResultSet dato = con.query(sql);
            if (dato.next()) {
                Vendedor v = new Vendedor();
                v.setCedula(dato.getString("cedula"));
                v.setContraseñaV(dato.getString("contraseña"));
                return v;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public String VistaInicioVendedor() {
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
            Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public Image traer_foto() {
        try {
            String sql;
            sql = "Select foto from vendedor where cedula='" + getCedula() + "'";
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

                        Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);

        }
        return null;
    }

    public static Image obtenImagen(byte[] bytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        Iterator it = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = (ImageReader) it.next();
        Object source = bis;
        ImageInputStream iis = ImageIO.createImageInputStream(source);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        param.setSourceSubsampling(1, 1, 0, 0);
        return reader.read(0, param);
    }

    public List<Vendedor> ListarVendedores(String aguja) {
        try {
            String query = "select u.cedula,u.nombre,u.apellido,u.telefono,u.correo,u.fecha_nacimiento,v.contraseña,v.foto\n"
                    + "from usuario u\n"
                    + "join vendedor v on u.cedula=v.cedula"
                    + "WHERE UPPER(u.nombre) LIKE UPPER('%" + aguja + "%') OR "
                    + "UPPER(u.apellido) LIKE UPPER('%" + aguja + "%') OR "
                    + "UPPER(v.cedula) LIKE UPPER('%" + aguja + "%')";
            ResultSet rs = con.query(query);
            List<Vendedor> lista = new ArrayList<Vendedor>();
            byte[] bf;
            while (rs.next()) {
                Vendedor p = new Vendedor();
                p.setCedula(rs.getString("cedula"));
                p.setNombre(rs.getString("nombre"));
                p.setApellido(rs.getString("apellido"));
                p.setTelefono(rs.getString("telefono"));
                p.setCorreo(rs.getString("correo"));
                p.setContraseñaV(rs.getString("contraseña"));
                // PARA FECHA
                p.setFecha_nacimiento(rs.getDate("fecha_nacimiento"));
                //
                bf = rs.getBytes("foto");

                if (bf != null) {
                    bf = Base64.decode(bf, 0, bf.length);
                    try {
                        //OBTENER IMAGEN
                        p.setFotoV(obtenImagen(bf));
                    } catch (IOException ex) {
                        p.setFotoV(null);
                        Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    p.setFotoV(null);
                }

                lista.add(p);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Vendedor getVendedor() {
        Vendedor v = null;
        try {

            String sql = "select id_vendedor from vendedor where cedula='" + getCedula() + "'";
            ResultSet rs = con.query(sql);
            if (rs.next()) {
                v = new Vendedor();
                v.setId_vendedor(rs.getString("id_vendedor"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public boolean EditarVendedor() {

        String foto64 = null;
        //Transformar imgagen a base64 para postgresql

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            BufferedImage img = imgBuffered(getFotoV());
            ImageIO.write(img, "PNG", bos);
            byte[] imgb = bos.toByteArray();
            foto64 = Base64.encodeBytes(imgb);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String sql;

        sql = "UPDATE vendedor set contraseña='" + getContraseñaV() + "',foto='" + foto64 + "'";
        sql += " WHERE cedula='" + getCedula() + "'";

        System.out.println(sql);
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }

    }

    public boolean editarUsuario() {
        String sql;
        sql = "UPDATE usuario set nombre='" + getNombre() + "',apellido='" + getApellido() + "',fecha_nacimiento='" + getFecha_nacimiento() + "',telefono='" + getTelefono() + "'";
        sql += " WHERE cedula='" + getCedula() + "'";
          System.out.println(sql);
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }

    }

}
