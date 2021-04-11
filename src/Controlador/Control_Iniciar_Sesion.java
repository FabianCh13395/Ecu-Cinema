/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Atxy2k.CustomTextField.RestrictedTextField;
import Modelo.Administrador;
import Modelo.ModeloAdministrador;
import Modelo.ModeloVendedor;
import Modelo.Vendedor;
import Vistas.Vista_MenuEmpleado;
import Vistas.Vista_MenuGeneral;
import Vistas.Vista_ventanaCarga;
import Vistas.vista_loguin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author FabianCh
 */
public class Control_Iniciar_Sesion {

    private ModeloVendedor v;
    private vista_loguin login;
    private Vendedor vendedor;
    private Vista_ventanaCarga l;
    private Timer t;
    private ModeloAdministrador a;
    private int x = 0;
    private ActionListener ac;
    private Administrador admi;

    public Control_Iniciar_Sesion(ModeloVendedor v, vista_loguin login, Vendedor vendedor) {
        this.v = v;
        this.login = login;
        this.vendedor = vendedor;
    }

    public Control_Iniciar_Sesion(ModeloVendedor v, vista_loguin login,Vista_ventanaCarga l,ModeloAdministrador a) {
        this.v = v;
        this.login = login;
        this.l=l;
        InicioControlVentana();
        VentanaInicial();
        l.setLocationRelativeTo(null);
        l.setVisible(true);
    }

    public void InicioControlVentana() {
        login.getBtn_inicio().addActionListener(l -> comprobacionCedulaVendedor());
        validarInicio();
    }

    public void comprobacionCedulaVendedor() {
        String cedula = login.getTxt_inicioCedula().getText();
        String contraseña = String.valueOf(login.getTxt_paswInicio().getPassword());
        ModeloVendedor ve = new ModeloVendedor();
        ve.setCedula(cedula);
        ve.setContraseñaV(contraseña);
        Vendedor v1 = ve.ComprobarCedulaVendedor();
        if (v1 == null) {
            comprobacionCedulaAdministrador();
            login.getTxt_inicioCedula().setText("");
            login.getTxt_paswInicio().setText("");

        } else if (ve.getCedula().equals(v1.getCedula()) && ve.getContraseñaV().equals(v1.getContraseñaV())) {
            JOptionPane.showMessageDialog(login, "Usted a iniciado sesión como vendedor");
           
           Vista_MenuEmpleado menuE=new  Vista_MenuEmpleado();
           ControlVentanaEmpleado e=new ControlVentanaEmpleado(ve,menuE);
            login.getTxt_inicioCedula().setText("");
            login.getTxt_paswInicio().setText("");
           
        } else {
            JOptionPane.showMessageDialog(login, "Contraseña Incorrecta");
            login.getTxt_paswInicio().setText("");
        }

    }
    public void comprobacionCedulaAdministrador(){
        String cedula = login.getTxt_inicioCedula().getText();
        String contraseña = String.valueOf(login.getTxt_paswInicio().getPassword());
        ModeloAdministrador va=new ModeloAdministrador();
        va.setCedula(cedula);
        va.setContraseña(contraseña);
        Administrador vr=va.ComprobarCedulaAdministrador();
                if (vr == null) {
                   
            JOptionPane.showMessageDialog(login, "No existe el Usuario");
            login.getTxt_inicioCedula().setText("");
            login.getTxt_paswInicio().setText("");

        } else if (va.getCedula().equals(vr.getCedula()) && va.getContraseña().equals(vr.getContraseña())) {
            JOptionPane.showMessageDialog(login, "Usted a iniciado sesión como Administrador");
           
           Vista_MenuGeneral menuE=new  Vista_MenuGeneral();
           ControladorVistaGeneral e=new ControladorVistaGeneral(menuE,va);
           
        } else {
            JOptionPane.showMessageDialog(login, "Contraseña Incorrecta");
            login.getTxt_paswInicio().setText("");
        }
    }
    

    public void VentanaInicial() {
          ac=new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                 x = x + 1;
        l.getBarra_progreso().setValue(x);
        if (l.getBarra_progreso().getValue() == 100) {
            login.setLocationRelativeTo(null);
            login.setVisible(true);
            l.dispose();
            t.stop();
        }
              }
          };
        t = new Timer(35, ac);
        t.start();
    }
   private void validarInicio(){
       RestrictedTextField t =new RestrictedTextField(login.getTxt_inicioCedula());
        t.setLimit(10);
        t.setOnlyNums(true);
        RestrictedTextField g=new RestrictedTextField(login.getTxt_paswInicio());
        g.setLimit(15);
   }
}
