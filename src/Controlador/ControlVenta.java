/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ModeloCliente;
import Modelo.ModeloVendedor;
import Vistas.Venta;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author FabianCh
 */
public class ControlVenta {
    private ModeloVendedor v;
    private ModeloCliente c;
    private Venta vistacli;

    public ControlVenta(ModeloVendedor v, Venta vistacli,ModeloCliente c) {
        this.v = v;
        this.vistacli = vistacli;
        this.c=c;
        vistacli.setVisible(true);
        inicioControlCliente();
    }
    
    public void inicioControlCliente(){
         deshabilitarB(false);
        vistacli.getBtn_buscar().addActionListener(l->mostrarInfCliente());
        vistacli.getBtn_registrarCli().addActionListener(l->grabarCliente());
       
    }
    
    public void grabarCliente(){
        String cedula = vistacli.getTxtcedula_cli().getText();
        String nombre = vistacli.getTxt_nombreCli().getText();
        String apellido = vistacli.getTxt_apeliidoCli().getText();
        String telefono = vistacli.getTxt_telefonoCli().getText();
        String correo= vistacli.getTxt_correoCli().getText();
        Instant instant = vistacli.getFecha_cliente().getDate().toInstant();
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
         if (cliente.grabarCliente()== true) {
            JOptionPane.showMessageDialog(vistacli, "Cliente Guardado exitosamente");
        } else {
            JOptionPane.showMessageDialog(vistacli, "ERROR ");
        }
    }
    private void mostrarInfCliente(){
        
        String cedulacliente=vistacli.getTxt_buscar().getText();
        ModeloCliente cli=new ModeloCliente();
        cli.setCedula(cedulacliente);
        List<Cliente> lista=cli.buscarCedula();
        if(lista.size()>0){
            vistacli.getTxt_buscar().setText("");
            deshabilitarB(false);
           lista.forEach(c1->{
        vistacli.getTxtcedula_cli().setText(c1.getCedula());
        vistacli.getTxt_nombreCli().setText(c1.getNombre());
        vistacli.getTxt_apeliidoCli().setText(c1.getApellido());
        vistacli.getTxt_telefonoCli().setText(c1.getTelefono());
        vistacli.getTxt_correoCli().setText(c1.getCorreo());
        vistacli.getFecha_cliente().setDate(new java.util.Date(c1.getFecha_nacimiento().getTime()));
        }); 
        }else{
             JOptionPane.showMessageDialog(vistacli, "No existe el cliente");
             
            deshabilitarB(Boolean.TRUE);
            vistacli.getTxtcedula_cli().setText(cedulacliente);
            vistacli.getTxt_buscar().setText("");
            
        }
        
        
    }
    private void deshabilitarB(Boolean opcion){
        
        vistacli.getTxtcedula_cli().setEditable(opcion);
        vistacli.getTxt_nombreCli().setEditable(opcion);
        vistacli.getTxt_apeliidoCli().setEditable(opcion);
        vistacli.getTxt_telefonoCli().setEditable(opcion);
        vistacli.getTxt_correoCli().setEditable(opcion);
        vistacli.getFecha_cliente().setEnabled(opcion);
        vistacli.getBtn_registrarCli().setEnabled(opcion);
    }
   
}

    
    

