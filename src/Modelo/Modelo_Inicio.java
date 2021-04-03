/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.Base64;

/**
 *
 * @author FabianCh
 */
public class Modelo_Inicio {
    public static Conexion_Ecu_Cinema con=new Conexion_Ecu_Cinema();
    
    public List<Vendedor> comprobarCedulaVendedor(){
           try {
            String query;
            query = "SELECT cedula FROM vendedor ";

            ResultSet rs = con.query(query);
            byte[] bf;
            List<Vendedor> lista = new ArrayList<Vendedor>();
            while (rs.next()) {
                Vendedor p = new Vendedor();
                p.setCedula(rs.getString("cedula"));
                lista.add(p);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_Inicio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     public List<Vendedor> comprobarContraseñaVendedor(){
           try {
            String query;
            query = "SELECT contraseña FROM vendedor ";

            ResultSet rs = con.query(query);
            List<Vendedor> lista = new ArrayList<Vendedor>();
            while (rs.next()) {
                Vendedor p = new Vendedor();
                p.setContraseñaV(rs.getString("contraseña"));
                lista.add(p);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(Modelo_Inicio.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
