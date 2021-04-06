/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloAdministrador;
import Vistas.Vista_Administrador;

/**
 *
 * @author FabianCh
 */
public class ControlVistaAdministrador {
    private ModeloAdministrador a;
    private Vista_Administrador v;

    public ControlVistaAdministrador(ModeloAdministrador a, Vista_Administrador v) {
        this.a = a;
        this.v = v;
        v.setVisible(true);
        
    }

    public ControlVistaAdministrador() {
    }
    
    
}
