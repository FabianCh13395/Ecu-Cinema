/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a-adr_000
 */
public class ModeloCompra extends Compra{
    Conexion_Ecu_Cinema con = new Conexion_Ecu_Cinema();
    Cliente c= new Cliente ();

    public ModeloCompra() {
    }

    public ModeloCompra(String IdCompra, Date Fecha, LocalTime Hora, String MetodoPago, double CostoTotal) {
        super(IdCompra, Fecha, Hora, MetodoPago, CostoTotal);
    }
    public String GeneraridCompra() {
        String id = "";
        try {
            String sql;
            sql = "Select max(id_compra)from compra";
            ResultSet dato = con.query(sql);

            if (dato.next()) {
                id = dato.getString(1);
                System.out.println(id);
                if(id!=null){
                   int suf;
            suf = Integer.parseInt(id.split("c-")[1]);
            suf += 1;
            System.out.println(suf);
            if (suf >= 10000) {
                id = "c-" + suf;
            } else if (suf >= 1000) {
                id = "c-0" + suf;
            } else if (suf >= 100) {
                id = "c-00" + suf;
            } else if (suf >= 10) {
                id = "c-000" + suf;
            } else if (suf >= 0) {
                id = "c-0000" + suf;
            }
            System.out.println("Nuevo: " + id);  
                }else{
                    id="c-00000";
                }
           
            }
             
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCompra.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR: " + ex);
            System.out.println(ex);
            id = "c-00000";
        }
        setIdCompra(id);
        return id;
    }
    public boolean grabarCompra() {
        String sql;
        sql = "INSERT INTO compra (id_compra,id_cliente,fecha,hora,metodo_pago,costo_total)";
        sql += "VALUES('" + GeneraridCompra() + "','" + getC().getCedula() + "','" + getFecha()+ "','" + getHora()+ "','" + getMetodoPago()+ "','" + getCostoTotal()+ "')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }

    }
    
}
