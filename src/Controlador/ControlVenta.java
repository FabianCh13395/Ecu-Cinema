/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloVendedor;
import Vistas.Venta;

/**
 *
 * @author FabianCh
 */
public class ControlVenta {
    private ModeloVendedor v;
    private Venta v1;

    public ControlVenta(ModeloVendedor v, Venta v1) {
        this.v = v;
        this.v1 = v1;
        v1.setVisible(true);
    }
    
    
    
}
