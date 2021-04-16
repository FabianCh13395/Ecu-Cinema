/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloAdministrador;
import Modelo.ModeloPelicula;
import Modelo.ModeloSala;
import Vistas.Vista_Administrador;
import Vistas.Vista_Salas;
import Vistas.Vista_gestionPeliculas;

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
        InicioControl();
    }

    public ControlVistaAdministrador() {
    }
    
    private void  InicioControl(){
        v.getBtnNuevasPelis().addActionListener(l->{
        ModeloPelicula m= new ModeloPelicula();
        Vista_gestionPeliculas a=new Vista_gestionPeliculas();
        ControlVistaPelicula m1=new ControlVistaPelicula(m,a);
        v.getMenuAdmin().add(a);
        });
        v.getBtnNuevasSalas().addActionListener(l->{
        ModeloSala s= new ModeloSala();
        Vista_Salas k=new Vista_Salas();
        ControlVistaSala s1=new ControlVistaSala(s,k);
        v.getMenuAdmin().add(k);
        });
    }
    
}
