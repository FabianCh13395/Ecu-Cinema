/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloVendedor;
import Vistas.Registro_Usuario;

/**
 *
 * @author FabianCh
 */
public class Control_Ventana_Principal {
  public static void main(String[] args){
      
      ModeloVendedor mvendedor= new ModeloVendedor();
      Registro_Usuario uvendedor=new Registro_Usuario();
      ControlVendedor cvendedor= new ControlVendedor (mvendedor,uvendedor);
      
  }
}
