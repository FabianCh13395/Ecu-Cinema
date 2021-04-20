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
public class ModeloCategoria extends Categoria{
    Conexion_Ecu_Cinema con=new Conexion_Ecu_Cinema();

    public ModeloCategoria() {
    }

    public ModeloCategoria(String IdCategoria, String Nombre, Double Precio) {
        super(IdCategoria, Nombre, Precio);
    }
    public String GeneraridCategoria() {
        String id = "";
        try {
            String sql;
            sql = "Select max(id_categoria)from categoria";
            ResultSet dato = con.query(sql);

            if (dato.next()) {
                id = dato.getString(1);
            }
            System.out.println(id);
            int suf;
            suf = Integer.parseInt(id.split("ct-")[1]);
            suf += 1;
            System.out.println(suf);
            if (suf >= 10000) {
                id = "ct-" + suf;
            } else if (suf >= 1000) {
                id = "ct-0" + suf;
            } else if (suf >= 100) {
                id = "ct-00" + suf;
            } else if (suf >= 10) {
                id = "ct-000" + suf;
            } else if (suf >= 0) {
                id = "ct-0000" + suf;
            }
            System.out.println("Nuevo: " + id);
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategoria.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex);
            System.out.println(ex);
            id = "ct-00000";
        }
        return id;
    }
    public List<Categoria> traerCategoria(){
        try {
            String query = "select* from categoria";
            ResultSet rs = con.query(query);
            List<Categoria> lista = new ArrayList<Categoria>();
            while (rs.next()) {
                Categoria s = new Categoria();
                s.setIdCategoria(rs.getString("id_categoria"));
                s.setNombre(rs.getString("nom_categoria"));
                s.setPrecio(rs.getDouble("precio"));
                lista.add(s);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCategoria.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
