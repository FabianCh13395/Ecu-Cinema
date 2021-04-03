/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloCliente;
import Vistas.Registro_Usuario;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import javax.swing.JOptionPane;

/**
 *
 * @author FabianCh
 */
public class ControlCliente {
    private ModeloCliente cliente;
    private Registro_Usuario vistacli;

    public ControlCliente() {
    }
    
    

    public ControlCliente(ModeloCliente cliente, Registro_Usuario vistacli) {
        this.cliente = cliente;
        this.vistacli = vistacli;
    }
    
    public void inicioControlCliente(){
        
    }
    public void grabarCliente(){
        String cedula = vistacli.getT_txt_cedulaV().getText();
        String nombre = vistacli.getTxt_nombreV().getText();
        String apellido = vistacli.getTxt_nombreV().getText();
        String telefono = vistacli.getTxt_tlfV().getText();
        String correo= vistacli.getTxt_CorreoV().getText();
        Instant instant = vistacli.getFechaNaciV().getDate().toInstant();
        ZoneId zid = ZoneId.of("America/Guayaquil");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zid);
        Date fecha_nacimiento = Date.valueOf(zdt.toLocalDate());
        ModeloCliente usuario=new ModeloCliente(cedula, nombre, telefono, apellido, correo, fecha_nacimiento);
        if (usuario.grabarUsuario()== true) {
            
            System.out.println("Usuario registrado");
        } else {
            System.out.println("ERROR");

        }
        ModeloCliente cliente= new ModeloCliente();
        cliente.setCedula(cedula);
        cliente.setCedula(cedula);
         if (cliente.grabarCliente()== true) {
            JOptionPane.showMessageDialog(vistacli, "Cliente Guardado exitosamente");
        } else {
            JOptionPane.showMessageDialog(vistacli, "ERROR ");
        }
    }
}
