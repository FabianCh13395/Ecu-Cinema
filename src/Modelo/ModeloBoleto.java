/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a-adr_000
 */
public class ModeloBoleto extends Boleto{
    Conexion_Ecu_Cinema con=new Conexion_Ecu_Cinema();

    public ModeloBoleto() {
    }

    public ModeloBoleto(String IdBoleto) {
        super(IdBoleto);
    }

    public ModeloBoleto(String IdBoleto, Vendedor v, Funcion f, Categoria c, Compra co) {
        super(IdBoleto, v, f, c, co);
    }
    public String GeneraridBoleto() {
        String id = "";
        try {
            String sql;
            sql = "Select max(id_boleto)from boleto";
            ResultSet dato = con.query(sql);

            if (dato.next()) {
                id = dato.getString(1);
                System.out.println(id);
                if(id!=null){
                  int suf;
            suf = Integer.parseInt(id.split("b-")[1]);
            suf += 1;
            System.out.println(suf);
            if (suf >= 10000) {
                id = "b-" + suf;
            } else if (suf >= 1000) {
                id = "b-0" + suf;
            } else if (suf >= 100) {
                id = "b-00" + suf;
            } else if (suf >= 10) {
                id = "b-000" + suf;
            } else if (suf >= 0) {
                id = "b-0000" + suf;
            }
            System.out.println("Nuevo: " + id);  
                }else{
                    id = "b-00000";
                }
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ModeloBoleto.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex);
            System.out.println(ex);
            id = "b-00000";
        }
        return id;
    }
    public boolean GuardarBoleto(){
         String sql;
          sql = "INSERT INTO boleto (id_boleto,id_vendedor,id_funcion,id_categoria,id_compra)";
        sql += "VALUES('" + GeneraridBoleto()+ "','" + getV().getId_vendedor()+ "','" + getF().getIdFuncion()+"','"+getC().getIdCategoria() +"','"+getCo().getIdCompra()+ "')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }
     }
    
}
