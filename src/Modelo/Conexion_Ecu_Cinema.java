/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FabianCh
 */
public class Conexion_Ecu_Cinema {
    private String cadenaConexion="jdbc:postgresql://localhost:5432/EcuCinema";
    private String usuarioPos="postgres";
    //private String pasw="15xyz1820111";
    private String pasw="1234";
    
    private Connection con;

    public Conexion_Ecu_Cinema() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion_Ecu_Cinema.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            con = DriverManager.getConnection(cadenaConexion, usuarioPos ,pasw);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_Ecu_Cinema.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error en la conexión");
        }
        
    }
     public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
   public ResultSet query(String sql){
       
        try {
            Statement st;
             st=con.createStatement();
           ResultSet rs=st.executeQuery(sql);
               return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_Ecu_Cinema.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
       
   
   }
   public SQLException  noquery(String nsql){
        try {
            Statement st;
            st=con.createStatement();
            st.execute(nsql);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_Ecu_Cinema.class.getName()).log(Level.SEVERE, null, ex);
            return ex;
        }
   }
    
    public SQLException  noqueryActualizar(String nsql){
        try {
            Statement st;
            st=con.createStatement();
            int i=st.executeUpdate(nsql);
            System.out.println(i);
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion_Ecu_Cinema.class.getName()).log(Level.SEVERE, null, ex);
            return ex;
        }
   }
}
