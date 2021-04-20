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
public class ModeloAsiento extends Asiento{
    Conexion_Ecu_Cinema con=new Conexion_Ecu_Cinema();

    public ModeloAsiento(String IdAsiento, String NombreAsiento, boolean Disponibilidad) {
        super(IdAsiento, NombreAsiento, Disponibilidad);
    }

    public ModeloAsiento() {
    }
    public List<Asiento> listarAsiento(String id_sala){
        try {
            String query = "select* from asiento where id_sala='"+id_sala+"' order by nombre";
            ResultSet rs = con.query(query);
            List<Asiento> lista = new ArrayList<Asiento>();
            while (rs.next()) {
                Asiento s = new Asiento();
                s.setIdAsiento(rs.getString("id_asiento"));
                s.setNombreAsiento(rs.getString("nombre"));
                s.setDisponibilidad(rs.getBoolean("disponibilidad"));
                lista.add(s);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloAsiento.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    public boolean CambiarDisponibilidad(){
        String query="Update asiento set disponibilidad='"+isDisponibilidad()+"' where nombre='"+getNombreAsiento()+"' and id_sala='"+getS().getIdSala()+"'";
        System.out.println(query);
         if (con.noquery(query) == null) {
            return true;
        } else {
            return false;
        }
    }
    
}
