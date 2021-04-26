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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a-adr_000
 */
public class ModeloCompra extends Compra {

    Conexion_Ecu_Cinema con = new Conexion_Ecu_Cinema();
    Cliente c = new Cliente();

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
                if (id != null) {
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
                } else {
                    id = "c-00000";
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
        sql += "VALUES('" + GeneraridCompra() + "','" + getC().getCedula() + "','" + getFecha() + "','" + getHora() + "','" + getMetodoPago() + "','" + getCostoTotal() + "')";
        if (con.noquery(sql) == null) {
            return true;
        } else {
            return false;
        }

    }

    public List<Compra> llenarVentas() {
        try {
            String query = "select b.id_boleto,c.fecha,u.nombre,u.apellido,c2.nom_categoria,c.costo_total,u1.nombre,c.hora\n"
                    + "from compra c\n"
                    + "join boleto b on c.id_compra=b.id_compra\n"
                    + "join categoria c2 on c2.id_categoria=b.id_categoria\n"
                    + "join cliente c1 on c.id_cliente=c1.id_cliente\n"
                    + "join usuario u on u.cedula=c1.cedula\n"
                    + "join vendedor v on b.id_vendedor=v.id_vendedor\n"
                    + "join usuario u1 on v.cedula=u1.cedula";
            ResultSet rs = con.query(query);
            List<Compra> lista = new ArrayList<Compra>();
            while (rs.next()) {
                Boleto b = new Boleto();
                b.setIdBoleto(rs.getString(1));
                Compra c2 = new Compra();
                c2.setFecha(rs.getDate(2));
                c2.setCostoTotal(rs.getDouble(6));
                c2.setHora(rs.getTime(8).toLocalTime());
                Categoria cat = new Categoria();
                cat.setNombre(rs.getString(5));
                Cliente cli = new Cliente();
                cli.setNombre(rs.getString(3));
                cli.setApellido(rs.getString(4));
                Vendedor v1 = new Vendedor();
                v1.setNombre(rs.getString(7));
                c2.setB(b);
                c2.setC(cli);
                c2.setCate(cat);
                c2.setV(v1);

                lista.add(c2);
            }
            rs.close();
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCompra.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
