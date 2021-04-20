/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a-adr_000
 */
public class ModeloFuncion extends Funcion{
    Conexion_Ecu_Cinema con = new Conexion_Ecu_Cinema();

    public ModeloFuncion() {
    }

    public ModeloFuncion(String IdFuncion, Date Fecha, boolean disponibilidad, LocalTime HoraInicio, LocalTime HoraFin) {
        super(IdFuncion, Fecha, disponibilidad, HoraInicio, HoraFin);
    }
     public String GeneraridFuncion() {
        String id = "";
        try {
            String sql;
            sql = "Select max(id_funcion)from funcion";
            ResultSet dato = con.query(sql);

            if (dato.next()) {
                id = dato.getString(1);
            }
            System.out.println(id);
            int suf;
            suf = Integer.parseInt(id.split("f-")[1]);
            suf += 1;
            System.out.println(suf);
            if (suf >= 10000) {
                id = "f-" + suf;
            } else if (suf >= 1000) {
                id = "f-0" + suf;
            } else if (suf >= 100) {
                id = "f-00" + suf;
            } else if (suf >= 10) {
                id = "f-000" + suf;
            } else if (suf >= 0) {
                id = "f-0000" + suf;
            }
            System.out.println("Nuevo: " + id);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCompra.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex);
            System.out.println(ex);
            id = "f-00000";
        }
        return id;
    }
     public boolean GuardarFuncion(){
         String sql;
          sql = "INSERT INTO funcion (id_funcion,hora_inicio,hora_final,id_sala,id_pelicula,fecha,disponibilidad)";
        sql += "VALUES('" + GeneraridFuncion()+ "','" + getHoraInicio()+ "','" + getHoraFin()+"','"+getS().getIdSala() +"','"+getP().getIdPelicula()+"','"+getFecha() +"','"+getDisponibilidad() + "')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }
     }
    
     
     public List<Funcion> traerFuncion(){
         String query="select p.titulo,s.nombre,s.id_sala,f.hora_inicio,f.hora_final "+
          "from funcion f "+
          "join pelicula p on f.id_pelicula=p.id_pelicula "+
          "join sala s on f.id_sala=s.id_sala "+
          "where f.disponibilidad='true' and f.hora_inicio>=(select current_time)";
         ResultSet rs =con.query(query);
         List<Funcion> lista=new ArrayList<Funcion>();
        try {
            while(rs.next()){
                Sala s1=new Sala();
                s1.setIdSala(rs.getString(3));
                s1.setNombreSala(rs.getString(2));
                lista.add(new Funcion(
                        rs.getTime(4).toLocalTime(),
                        rs.getTime(5).toLocalTime(),
                       s1,
                        new Pelicula(
                        rs.getString(1))                       
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModeloFuncion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
     }
     public List<Funcion> ListarFunciones() {
        try {
            String query = "select* from funcion";
            ResultSet rs = con.query(query);
            List<Funcion> lista = new ArrayList<Funcion>();
            while (rs.next()) {
                  Pelicula p=new Pelicula();
                  Sala s=new Sala();
                  p.setIdPelicula(rs.getString("id_pelicula"));
                  s.setIdSala(rs.getString("id_sala"));
                  lista.add(new Funcion(
                        rs.getString("id_funcion"),
                        rs.getDate("fecha"),
                          rs.getBoolean("disponibilidad"),
                        rs.getTime("hora_inicio").toLocalTime(),
                        rs.getTime("hora_final").toLocalTime(),
                         s,
                          p
                ));
              
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloFuncion.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     public boolean DisponibilidadFuncion(){
         String sql="";
         sql="Update funcion set disponibilidad='false' where hora_inicio >(Select current_time) and fecha<(select current_date)";
         return (con.noquery(sql)!=null);
     }
}
