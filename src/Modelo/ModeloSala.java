/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a-adr_000
 */
public class ModeloSala extends Sala {

    Conexion_Ecu_Cinema con = new Conexion_Ecu_Cinema();

    public ModeloSala() {
    }

    public ModeloSala(String IdSala, int TotalAsientos, String NombreSala, boolean disponibilidad) {
        super(IdSala, TotalAsientos, NombreSala, disponibilidad);
    }

    public String Generar_idSala() {
        String id = "";
        try {
            String sql;
            sql = "Select max(id_sala)from sala";
            ResultSet dato = con.query(sql);

            if (dato.next()) {
                id = dato.getString(1);
            }
            System.out.println(id);
            int suf;
            suf = Integer.parseInt(id.split("s-")[1]);
            suf += 1;
            System.out.println(suf);
            if (suf >= 10000) {
                id = "s-" + suf;
            } else if (suf >= 1000) {
                id = "v-0" + suf;
            } else if (suf >= 100) {
                id = "s-00" + suf;
            } else if (suf >= 10) {
                id = "s-000" + suf;
            } else if (suf >= 0) {
                id = "s-0000" + suf;
            }
            System.out.println("Nuevo: " + id);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloVendedor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex);
            System.out.println(ex);
            id = "s-00000";
        }
        return id;
    }

    public boolean crearSala() {
        String sql = "";
        sql = "INSERT INTO sala (id_sala,nombre,Disponibilidad,Total_asientos)";
        sql += "VALUES('" + Generar_idSala() + "','" + getNombreSala() + "','" + getDisponibilidad() + "','" + getTotalAsientos() + "')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }
    }

    public List<Sala> NIdSala() {
        try {
            String query = "select id_sala,nombre from sala \n" +
       "where nombre <> all(\n" +
       "select s.nombre from sala s join funcion f on s.id_sala=f.id_sala \n" +
"	where f.hora_inicio='"+getF().getHoraInicio()+"') and disponibilidad='true'";
            ResultSet rs = con.query(query);
            List<Sala> lista = new ArrayList<Sala>();
            while (rs.next()) {
                Sala s = new Sala();
                s.setIdSala(rs.getString("id_sala"));
                s.setNombreSala(rs.getString("nombre"));
                lista.add(s);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloSala.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public boolean DisponibilidadSala(){
         String sql="";
         sql="Update sala set disponibilidad='false' where id_sala="+"'"+getIdSala()+"'";
         return (con.noquery(sql)!=null);
     }

    public List<Sala> ListarSalas() {
        try {
            String query = "select id_sala,nombre,total_asientos from sala";
            ResultSet rs = con.query(query);
            List<Sala> lista = new ArrayList<Sala>();
            while (rs.next()) {
                Sala s = new Sala();
                s.setIdSala(rs.getString("id_sala"));
                s.setNombreSala(rs.getString("nombre"));
                s.setTotalAsientos(rs.getInt("total_asientos"));
                lista.add(s);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloSala.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    

}
