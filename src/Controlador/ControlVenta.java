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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        validarCliente();
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
            vistacli.getTxt_buscar().setText("");
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
          vistacli.getTxt_buscar().addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {
               if (vistacli.getTxt_buscar().getText().length() >= 10) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();
                }
           }

           @Override
           public void keyPressed(KeyEvent e) {
               
           }
           @Override
           public void keyReleased(KeyEvent e) {
               
           }
       });
       vistacli.getTxtcedula_cli().addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {
               if (vistacli.getTxtcedula_cli().getText().length() >= 10) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();
                }
           }

           @Override
           public void keyPressed(KeyEvent e) {
               
           }
           @Override
           public void keyReleased(KeyEvent e) {
               
           }
       });
       vistacli.getTxt_nombreCli().addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {
               if(vistacli.getTxt_nombreCli().getText().length()>=15){
                   char c = e.getKeyChar();
                   e.consume();
                if (Character.isDigit(c)) {
                    e.consume();

                }
               }
                 
           }
           @Override
           public void keyPressed(KeyEvent e) {
              
           }
           @Override
           public void keyReleased(KeyEvent e) {
               
           }
       });
       vistacli.getTxt_apeliidoCli().addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {
                 if(vistacli.getTxt_apeliidoCli().getText().length()>=15){
                   char c = e.getKeyChar();
                   e.consume();
                if (Character.isDigit(c)) {
                    e.consume();

                }
               }
           }
           @Override
           public void keyPressed(KeyEvent e) {
              
           }
           @Override
           public void keyReleased(KeyEvent e) {
               
           }
       });
       vistacli.getTxt_telefonoCli().addKeyListener(new KeyListener() {
           @Override
           public void keyTyped(KeyEvent e) {
               if (vistacli.getTxt_telefonoCli().getText().length() >= 12) {
                    e.consume();
                }
                char c = e.getKeyChar();
                if (c < '0' || c > '9') {
                    e.consume();
                }
           }

           @Override
           public void keyPressed(KeyEvent e) {
               
           }
           @Override
           public void keyReleased(KeyEvent e) {
               
           }
       });
        vistacli.getTxt_correoCli().addFocusListener(new FocusListener() {
           @Override
           public void focusGained(FocusEvent e) {
              
           }

           @Override
           public void focusLost(FocusEvent e) {
               if (paraEmail(vistacli.getTxt_correoCli().getText())) {

                } else {
                    JOptionPane.showMessageDialog(null, "Correo Incorrecto", "Validar correo", JOptionPane.INFORMATION_MESSAGE);
                    vistacli.getTxtcedula_cli().requestFocus();
                }
           }
       });

       
        
   }
     public boolean paraEmail(String correo2) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^[\\w\\-\\_\\+]+(\\.[\\w\\-\\_]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        mat = pat.matcher(correo2);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }
}

    
    

