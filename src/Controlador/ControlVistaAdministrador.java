/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModeloAdministrador;
import Modelo.ModeloCliente;
import Modelo.ModeloCompra;
import Modelo.ModeloFuncion;
import Modelo.ModeloPelicula;
import Modelo.ModeloSala;
import Modelo.ModeloVendedor;
import Vistas.Vista_Administrador;
import Vistas.Vista_GestionFunciones;
import Vistas.Vista_MenuGeneral;
import Vistas.Vista_ReporteAdministradores;
import Vistas.Vista_ReporteVendedores;
import Vistas.Vista_ReportesClientes;
import Vistas.Vista_ReportesVentas;
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
        v.getBtnNuevasFunciones().addActionListener(l->{
        ModeloFuncion f= new ModeloFuncion();
        Vista_GestionFunciones v1=new Vista_GestionFunciones();
        ControlGestionFunciones r1=new ControlGestionFunciones(f,v1);
        v.getMenuAdmin().add(v1);
        });
        v.getBtnSalir().addActionListener(l->{
        Vista_MenuGeneral vista= new Vista_MenuGeneral();
        vista.dispose();
        });
        v.getBtnReportClientes().addActionListener(l->{
        ModeloCliente c=new ModeloCliente();
        Vista_ReportesClientes rv=new Vista_ReportesClientes();
        ControlReporteClientes c1=new ControlReporteClientes(c,rv);
        v.getMenuAdmin().add(rv);
        });
        v.getBtnreportEmpleados().addActionListener(l->{
        ModeloVendedor vendedor= new ModeloVendedor();
        Vista_ReporteVendedores v2=new Vista_ReporteVendedores();
        ControlReporteVendedores cv=new ControlReporteVendedores(vendedor,v2);
        v.getMenuAdmin().add(v2);
        });
        v.getMenuReporteAdministrador().addActionListener(l->{
        ModeloAdministrador admin=new ModeloAdministrador();
        Vista_ReporteAdministradores ad1=new Vista_ReporteAdministradores();
        ControlReporteAdministrador ad2=new ControlReporteAdministrador(admin,ad1);
        v.getMenuAdmin().add(ad1);
        });
        v.getBtnreportVentas().addActionListener(l->{
        ModeloCompra compra=new ModeloCompra();
        Vista_ReportesVentas vent=new Vista_ReportesVentas();
        ControlReporteVentas ventas1 =new ControlReporteVentas(compra,vent);
        v.getMenuAdmin().add(vent);
        });
    }
    
}
