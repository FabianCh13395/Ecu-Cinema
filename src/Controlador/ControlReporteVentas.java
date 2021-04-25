/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.Compra;
import Modelo.ModeloCliente;
import Modelo.ModeloCompra;
import Vistas.Vista_ReportesVentas;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.Holder;

/**
 *
 * @author FabianCh
 */
public class ControlReporteVentas {

    private ModeloCompra c1;
    private Vista_ReportesVentas v1;

    public ControlReporteVentas(ModeloCompra c1, Vista_ReportesVentas v1) {
        this.c1 = c1;
        this.v1 = v1;
        v1.setVisible(true);
        InicioControl();
    }

    private void InicioControl() {
llenarTabla();
v1.getBtn_salir().addActionListener(l->v1.dispose());
    }

    private void llenarTabla() {
        DefaultTableModel tblModel;
        tblModel = (DefaultTableModel) v1.getTabla_venta().getModel();
        tblModel.setNumRows(0);
        int ncol = tblModel.getColumnCount();
        Holder<Integer> i = new Holder<>(0);

        List<Compra> lista = new ModeloCompra().llenarVentas();
        lista.stream().forEach(p1 -> {
            //   String[] persona={p1.getIdPersona(),p1.getNombres(),p1.getApellidos(),String.valueOf(p1.getEdad())};

            tblModel.addRow(new Object[ncol]);
            v1.getTabla_venta().setValueAt(p1.getB().getIdBoleto(), i.value, 0);
            v1.getTabla_venta().setValueAt(p1.getFecha(), i.value, 1);
            v1.getTabla_venta().setValueAt(p1.getC().getNombre() + " " + p1.getC().getApellido(), i.value, 2);
            v1.getTabla_venta().setValueAt(p1.getCostoTotal(), i.value, 3);
            v1.getTabla_venta().setValueAt(p1.getV().getNombre(), i.value, 4);
            v1.getTabla_venta().setValueAt(p1.getHora(), i.value, 5);
            v1.getTabla_venta().setValueAt(p1.getCate().getNombre(), i.value, 6);
            i.value++;

        });
    }
}
