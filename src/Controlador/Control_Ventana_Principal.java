/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloAdministrador;
import Modelo.ModeloVendedor;
import Vistas.Registro_Usuario;
import Vistas.Vista_ventanaCarga;
import Vistas.vista_loguin;

/**
 *
 * @author FabianCh
 */
public class Control_Ventana_Principal {
  public static void main(String[] args){
      
      ModeloVendedor mvendedor= new ModeloVendedor();
      ModeloAdministrador a=new ModeloAdministrador();
//      Registro_Usuario uvendedor=new Registro_Usuario();
//      ControlVendedor cvendedor= new ControlVendedor (mvendedor,uvendedor);
//      
      vista_loguin l=new vista_loguin();
      Vista_ventanaCarga l1=new Vista_ventanaCarga();
     Control_Iniciar_Sesion i=new Control_Iniciar_Sesion(mvendedor,l,l1,a); 
    
  }
}
