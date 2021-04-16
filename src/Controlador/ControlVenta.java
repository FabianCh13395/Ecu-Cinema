/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Atxy2k.CustomTextField.RestrictedTextField;
import Modelo.Cliente;
import Modelo.Funcion;
import Modelo.ModeloAsiento;
import Modelo.ModeloCliente;
import Modelo.ModeloFuncion;
import Modelo.ModeloVendedor;
import Vistas.Venta;
import Vistas.VistaAsiento;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
    private ModeloFuncion f;

    public ControlVenta(ModeloVendedor v, Venta vistacli,ModeloCliente c) {
        this.v = v;
        this.vistacli = vistacli;
        this.c=c;
        vistacli.setVisible(true);
        CargaFunciones();
        inicioControlCliente();
    }
    
    public void inicioControlCliente(){
        
         deshabilitarB(false);
        vistacli.getBtn_buscar().addActionListener(l->mostrarInfCliente());
        vistacli.getBtn_registrarCli().addActionListener(l->grabarCliente());
       vistacli.getBtn_Asiento().addActionListener(l->{
       VistaAsiento v= new VistaAsiento();
       ModeloAsiento m= new ModeloAsiento();
       ControlAsiento c= new ControlAsiento(v,m);
       
       });
    }
    private void CargaFunciones(){
        List<Funcion> lista= new ModeloFuncion().traerFuncion();
        for (Funcion funcion : lista) {
            vistacli.getjComboFuncion().addItem(funcion.toString());    
        }
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
        vistacli.getTxtcedula_cli().setText("");
        System.out.println(lista.size()+"Lista");
        System.out.println(cedulacliente+"hola");
        
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
            vistacli.getTxtcedula_cli().setText(cedulacliente);
             JOptionPane.showMessageDialog(vistacli, "No existe el cliente");
            deshabilitarB(Boolean.TRUE);
            
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
   private void validarCliente(){
       RestrictedTextField c1 =new RestrictedTextField(vistacli.getTxtcedula_cli());
        c1.setLimit(10);
        c1.setOnlyNums(true);
        RestrictedTextField c2 =new RestrictedTextField(vistacli.getTxt_buscar());
        c2.setLimit(10);
        c2.setOnlyNums(true);
         RestrictedTextField c3 =new RestrictedTextField(vistacli.getTxt_nombreCli());
        c3.setLimit(20);
        c3.setOnlyText(true);
        RestrictedTextField c4 =new RestrictedTextField(vistacli.getTxt_apeliidoCli());
        c4.setLimit(20);
        c4.setOnlyText(true);
        RestrictedTextField c5 =new RestrictedTextField(vistacli.getTxt_telefonoCli());
        c5.setLimit(10);
        c5.setOnlyNums(true);
        RestrictedTextField c6=new RestrictedTextField(vistacli.getTxt_correoCli());
        c6.setLimit(35);
        
   }
}

    
    

