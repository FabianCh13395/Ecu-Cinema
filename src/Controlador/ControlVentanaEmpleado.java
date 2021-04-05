/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloVendedor;
import Vistas.Vista_MenuEmpleado;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author FabianCh
 */
public class ControlVentanaEmpleado {
    private ModeloVendedor v;
    private Vista_MenuEmpleado m;

    public ControlVentanaEmpleado(ModeloVendedor v, Vista_MenuEmpleado m) {
        this.v = v;
        this.m = m;
        m.setLocationRelativeTo(null);
        m.setVisible(true);
        InicioControl();
        
    }

    public ControlVentanaEmpleado() {
    }
    public void InicioControl(){
        mostrarFoto();
        mostrarNF();
    }
    
      private void mostrarNF(){

        String nombre=v.VistaInicioVendedor();
        System.out.println(nombre);
        m.getLbl_nombreV().setText(nombre);
  
    }
    private void mostrarFoto(){
        Image ima=v.traer_foto();
        if (ima != null) {
                m.getLbl_foto().setIcon(new ImageIcon (ima));
            }
    }
    
}
