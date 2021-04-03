/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.ModeloVendedor;
import Modelo.Vendedor;
import Vistas.Vista_MenuEmpleado;
import Vistas.vista_loguin;
import javax.swing.JOptionPane;

/**
 *
 * @author FabianCh
 */
public class Control_Iniciar_Sesion {
    private ModeloVendedor v;
    private vista_loguin login;
    private Vendedor vendedor;

    public Control_Iniciar_Sesion(ModeloVendedor v, vista_loguin login, Vendedor vendedor) {
        this.v = v;
        this.login = login;
        this.vendedor = vendedor;
    }

    public Control_Iniciar_Sesion(ModeloVendedor v, vista_loguin login) {
        this.v = v;
        this.login = login;
        InicioControlVentana();
        login.setVisible(true);
    }
    

    

    public void InicioControlVentana() {
        login.getBtn_inicio().addActionListener(l->comprobacionCedulaVendedor());
    }
     public void comprobacionCedulaVendedor(){
         String cedula=login.getTxt_inicioCedula().getText();
         String contraseña=login.getTxt_paswInicio().getText();
         ModeloVendedor ve=new ModeloVendedor();
         ve.setCedula(cedula);
         ve.setContraseñaV(contraseña);
         Vendedor v1=ve.ComprobarCedulaVendedor();
         if(v1==null){
             JOptionPane.showMessageDialog(login, "No existe el Usuario");
             
         }else{
             if(ve.getCedula().equals(v1.getCedula())&& ve.getContraseñaV().equals(v1.getContraseñaV())){
                 Vista_MenuEmpleado menuE=new Vista_MenuEmpleado();
                 menuE.setVisible(true);
             }else{
                  JOptionPane.showMessageDialog(login, "Contraseña Incorrecta");
             }
         }
         
     }

    
}
