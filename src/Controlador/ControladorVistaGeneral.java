/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloAdministrador;
import Modelo.ModeloVendedor;
import Vistas.Registro_Usuario;
import Vistas.Vista_Administrador;
import Vistas.Vista_MenuGeneral;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author FabianCh
 */
public class ControladorVistaGeneral {
    private Vista_MenuGeneral g;
    private ModeloAdministrador a;
    

    public ControladorVistaGeneral(Vista_MenuGeneral g, ModeloAdministrador a) {
        this.g = g;
        this.a = a;
        g.setVisible(true);
        g.setLocationRelativeTo(null);
        InicioControl();
        abrirDestokpainAdministrador();
    }

    public ControladorVistaGeneral() {
    }
    private void abrirDestokpainAdministrador(){
        
        ModeloAdministrador admi=new ModeloAdministrador();
        Vista_Administrador v=new Vista_Administrador();
        ControlVistaAdministrador m= new ControlVistaAdministrador(admi,v);
        g.getMenu_general().add(v);
    }
    
    public void InicioControl(){
       g.getBtn_registroA().addActionListener(l->RegistroAdministrador());
       g.getBtn_registroV().addActionListener(l->RegistroVendedor());
       g.getBtn_Salir().addActionListener(l->g.dispose());
        mostrarNF();
        mostrarFoto();
    }
    private void mostrarNF(){

        String nombre=a.VistaInicioAdministrador();
        System.out.println(nombre);
        g.getLbl_nombreA().setText(nombre);
    }
    private void mostrarFoto(){
        Image ima=a.traer_foto();
        if (ima != null) {
                g.getLbl_fotoA().setIcon(new ImageIcon (ima));
            }
    }
    private void RegistroVendedor(){
        Registro_Usuario u=new Registro_Usuario();
        ModeloVendedor m= new ModeloVendedor();
        ControlVendedor c=new ControlVendedor(m,u);
    }
    private void RegistroAdministrador(){
        Registro_Usuario u=new Registro_Usuario();
        ModeloAdministrador m= new ModeloAdministrador();
        ControlAdministradror c=new ControlAdministradror(m,u);
    }
    
}
