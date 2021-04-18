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
public class ModeloPelicula extends Pelicula{
    Conexion_Ecu_Cinema con= new Conexion_Ecu_Cinema();

    public ModeloPelicula() {
    }

    public ModeloPelicula(String IdPelicula, String Descripcion, String Clasificacion, String Genero, int Duracion, String Titulo, boolean Estado, Image foto) {
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
    
    
   public boolean GrabarPelicula(){
        String foto64 = null;
        //transformar imagen a base 64 para postgres
        ByteArrayOutputStream byt = new ByteArrayOutputStream();
        try {
            BufferedImage img = imgBuffered(getFoto());
            ImageIO.write(img, "PNG", byt);
            byte[] bgt = byt.toByteArray();
            foto64 = Base64.encodeBytes(bgt);
        } catch (IOException ex) {
            Logger.getLogger(ModeloPelicula.class.getName()).log(Level.SEVERE, null, ex);
        }
       String sql;
        sql = "INSERT INTO pelicula (id_pelicula,disponibilidad,genero,clasificacion,titulo,duracion,foto,descripcion)";
        sql += "VALUES('" + GeneraridPelicula()+ "','" + getEstado()+ "','" + getGenero()+ "','" + getClasificacion()+ "','" + getTitulo()+ "','" + getDuracion()+"','"+ foto64+"','"+getDescripcion()+"')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }
   }
   public List<Pelicula> listarPeliculaFuncion(){
       try {
            String query = "select id_pelicula,titulo,duracion,genero,clasificacion from pelicula where disponibilidad='true'";
            ResultSet rs = con.query(query);
            List<Pelicula> lista = new ArrayList<Pelicula>();
            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setIdPelicula(rs.getString("id_pelicula"));
                p.setTitulo(rs.getString("titulo"));
                p.setGenero(rs.getString("genero"));
                p.setClasificacion(rs.getString("clasificacion"));
                p.setDuracion(rs.getInt("duracion"));
               
               lista.add(p);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPelicula.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   }
   public  List<Pelicula> listarPeliculas() {
        try {
            String query = "select titulo,genero,disponibilidad,clasificacion,duracion,foto from pelicula";
            ResultSet rs = con.query(query);
            List<Pelicula> lista = new ArrayList<Pelicula>();
            byte[] bf;
            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setTitulo(rs.getString("titulo"));
                p.setGenero(rs.getString("genero"));
                p.setClasificacion(rs.getString("clasificacion"));
                p.setDuracion(rs.getInt("duracion"));
                p.setEstado(rs.getBoolean("disponibilidad"));
                bf = rs.getBytes("foto");

                if (bf != null) {
                    bf = Base64.decode(bf, 0, bf.length);
                    try {
                        //OBTENER IMAGEN
                        p.setFoto(obtenImagen(bf));
                    } catch (IOException ex) {
                        p.setFoto(null);
                        Logger.getLogger(ModeloPelicula.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    p.setFoto(null);
                }

                lista.add(p);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloPelicula.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
